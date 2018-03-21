<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.IPDetector"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="com.liferay.portal.service.persistence.PortletUtil"%>
<%@page import="com.liferay.portlet.PortletURLFactoryUtil"%>
<%@page import="com.liferay.portlet.PortletURLFactory"%>
<%@page import="com.liferay.portlet.PortletURLUtil"%>
<%@page import="javax.portlet.WindowState"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.DateUtil"%>
<%@page import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil"%>
<%@page import="com.liferay.portlet.asset.model.AssetEntry"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@page import="it.smartcommunitylab.platform.idea.portlet.Constants"%>
<%@page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>
<%@page import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>
<%@ page import="it.smartcommunitylab.platform.idea.permission.CallPermission"%>
<%@ page import="it.smartcommunitylab.platform.idea.permission.CallModelPermission"%>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil" %>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.SubscriptionLocalServiceUtil" %>
<%@ page import="com.liferay.portal.model.Subscription" %>

<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>


<%@  include file="/html/common-init.jsp"%>

<%
	Call call = (Call) request.getAttribute("call");
	long callId = ParamUtil.getLong(renderRequest, "callId");
	if (call == null) {
	    call = CallLocalServiceUtil.getCall(callId);    
	}
	call = call.toEscapedModel();
%>

<%
	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			Call.class.getName(), call.getCallId());

  List<AssetCategory> categories = Utils.getOrderedCategories(call.getCategoryIds(), assetEntry);
  java.util.Map<String,String> CC = IdeaLocalServiceUtil.getCategoryColors(scopeGroupId);

  List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(Call.class.getName(), call.getCallId());

  String currentURL = PortalUtil.getCurrentURL(request);

  java.util.Set<String> tagSet = new java.util.HashSet<String>();
  for (AssetTag tag : assetTags) {
    tagSet.add(tag.getName());
  }

  // followers
  List<Subscription> subs = SubscriptionLocalServiceUtil.getSubscriptions(themeDisplay.getCompanyId(), Call.class.getName(), call.getCallId());
  boolean subscribed = SubscriptionLocalServiceUtil.isSubscribed(themeDisplay.getCompanyId(), user.getUserId(), Call.class.getName(), call.getCallId());

  java.util.Date today = DateUtil.newDate();
  int distance = Integer.MAX_VALUE;
  if (call.getDeadline() != null) distance = 
		    DateUtil.getDaysBetween(today,call.getDeadline())
		  * (DateUtil.compareTo(today, call.getDeadline()) <=0 ? 1: -1);
  
%>

<div class="row-fluid">
  <div class="span8 call-title">
    <div class="call-maintitle">
    <%=call.getTitle() %>
    <c:if test='<%= Utils.callEditEnabled(call, renderRequest) %>'>
      <portlet:renderURL var="editCall" windowState="maximized">
        <portlet:param name="mvcPath" value="/html/callmanagement/edit_call.jsp" />
        <portlet:param name="callId" value="<%=String.valueOf(call.getCallId()) %>" />
      </portlet:renderURL>
      <a href="<%=editCall.toString()%>"><i class="icon-pencil"></i></a>
    </c:if>
    </div>
    <% for (AssetCategory ac : categories) {
      //String localRedirectCategoryUrl = redirectCategoryUrl + ac.getCategoryId();
      String categoryColor = CC.get(""+ac.getCategoryId());
    %>
    <portlet:renderURL var="localRedirectCategoryUrl">
      <portlet:param name="resetCur" value="true" />
      <portlet:param name="categoryId" value="<%=String.valueOf(ac.getCategoryId()) %>" />
    </portlet:renderURL>
    <div class="call-cattitle" style="background-color: <%=categoryColor %>;">
      <a href="<%= localRedirectCategoryUrl %>"><%= ac.getTitle(locale) %></a>
      <!-- <%=ac.getTitle(locale) %> -->  
    </div>
    <% } %>
  </div>
  <div class="span4 call-deadlines">
    <div class="call-deadline-remains">
    <% if (distance == Integer.MAX_VALUE) { %>
    <% } else if (distance > 0) {%>
    <liferay-ui:message key="call_deadline_remains_many" arguments="<%=distance %>"/>
    <% } else if (distance ==0) { %>
    <liferay-ui:message key="call_deadline_remains_one"/>
    <% } else { %>
    <liferay-ui:message key="call_deadline_remains_expired"/>
    <% } %>  
    </div>
    <div class="call-deadline-date">
    <c:if test='<%= call.getDeadline() != null %>'>
    <liferay-ui:message key="call_deadline_expireson" arguments="<%=dateFormatter.format(call.getDeadline()) %>"/>
    </c:if>
    </div>
  </div>
</div>

<div class="call-description">
<%=HtmlUtil.unescape(call.getDescription())%>
</div>
<div class="call-tags">
    <c:if test="<%=tagSet.size() > 0 %>">
    <div class="row-fluid info-meta">
      <i class="ftn-user_tag"></i>
      <span class="info-meta-label"><liferay-ui:message key="lbl_tags"/></span>
        <% for (String tag: tagSet) {%>
        <span class="badge"><%=tag %></span>
        <%} %>
    </div>
    </c:if>
</div>
<div class="call-attachments">
    <liferay-ui:asset-links
      assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
      className="<%=Call.class.getName()%>" classPK="<%=call.getCallId()%>" />
</div>
 <%
 boolean discussionEnabled = Utils.discussionEnabled(call, renderRequest);
%>

  <c:if test="<%=!themeDisplay.isSignedIn() && Utils.discussionOpen()%>">
      <div class="row-fluid">
        <span class="span12 idea-creator idea-creator-warning text-right">
            <a class="use-dialog" href="<%= themeDisplay.getURLSignIn() %>"><liferay-ui:message key="lbl_access_call"/></a>  
        </span>
      </div>
  </c:if>

<%-- <div class="idea-social">
    <c:if test="<%=!themeDisplay.isSignedIn()%>">
      <div class="row-fluid">
        <span class="span12 idea-creator idea-creator-warning text-right">
            <a class="use-dialog" href="<%= themeDisplay.getURLSignIn() %>"><liferay-ui:message key="lbl_access_call"/></a>  
        </span>
      </div>
      <div class="row-fluid">
        <div class="span6 text-center idea-discussion-rating"> 
          <div>
            <liferay-ui:ratings className="<%= Call.class.getName() %>" classPK="<%= call.getCallId() %>"/> 
          </div>
        </div>
        <div class="span6 text-center">
          <div>
            <liferay-ui:message key="lbl_followercount"/>
          </div> 
          <div class="participation-details">
            <span><%=subs.size() %></span>
            <i class="ftn-sum_users"></i>
          </div>
        </div>
      </div>
    </c:if>
    <c:if test="<%=themeDisplay.isSignedIn()%>">
      <div class="row-fluid text-center">
        <div class="span6"> <liferay-ui:message key="lbl_rate"/> </div>
        <div class="span6"> <liferay-ui:message key="lbl_participate"/> </div>
      </div>
      <div class="row-fluid">
        <div class="span6 text-center idea-discussion-rating">
          <liferay-ui:ratings className="<%= Call.class.getName() %>" classPK="<%= call.getCallId() %>" />
        </div>
        <div class="span6 text-center">
          <portlet:actionURL var="followIdea" name="followIdea">
            <portlet:param name="mvcPath" value="/html/idea/asset/full_content.jsp" />
            <portlet:param name="callId" value="<%=String.valueOf(call.getCallId()) %>" />
            <portlet:param name="subscribed" value="<%=String.valueOf(subscribed) %>" />
          </portlet:actionURL>
          <div><a title="<liferay-ui:message key="lbl_tooltip_follow"/>" class='idea-button idea-button-tooltip-avail idea-button-follow-<%= subscribed ? "disabled" : "enabled" %>' href="<%=followIdea.toString() %>"></a></div>
          <div><span><liferay-ui:message key="lbl_following"/></span></div>
          <div class="participation-details">
            <span><%=subs.size() %></span>
            <i class="ftn-sum_users"></i>
          </div>
        </div>
      </div>
    </c:if>
</div>
 --%>
 <liferay-ui:panel-container accordion="true" extended="true">
  <liferay-ui:panel state="open" collapsible="true" id="discussion" title='<%= LanguageUtil.get(locale, "lbl_discussion") %>'>

 <div class='row-fluid discussion-container'>
   <portlet:actionURL name="addComment" var="discussionURL">
     <!-- workaround to invoke liferary class that manage comment/discussion -->
     <portlet:param name="struts_action"
       value="/asset_publisher/edit_entry_discussion" />
   </portlet:actionURL>
 <liferay-ui:discussion hideControls="<%=!discussionEnabled %>" className="<%=Call.class.getName()%>" 
   classPK="<%=call.getCallId()%>" formAction="<%=discussionURL%>"
   formName="discussionForm" ratingsEnabled="<%=true %>" redirect="<%=currentURL%>"
   subject="<%=call.getTitle()%>" userId="<%=call.getUserId()%>" />
 </div>    
 
   </liferay-ui:panel>


</liferay-ui:panel-container>
 
