<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@page import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>
<%@page import="com.liferay.util.portlet.PortletProps"%>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@ page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>

<%@ page import="com.liferay.portal.kernel.util.DateFormatFactoryUtil" %>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.SubscriptionLocalServiceUtil" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.model.Subscription" %>
<%@ page import="com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.ratings.model.RatingsStats" %>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="java.util.Map" %>

<%@ include file="/html/common-init.jsp" %>

<%
	Idea idea = (Idea) request.getAttribute("idea");
  long ideaId = ParamUtil.getLong(renderRequest, "ideaId");
	if (idea == null) {
		  idea = IdeaLocalServiceUtil.getIdea(ideaId);		
	}
	idea = idea.toEscapedModel();
	
	long callId = idea.getCallId();
	String redirectUrl = null;
	String callTitle = null;
	if(callId > 0) {
		Call call = CallLocalServiceUtil.getCall(callId);
		if(call != null) {
			callTitle = call.getTitle().toUpperCase();
		}
		String portalUrl = themeDisplay.getPortalURL();
		String siteUrl = layout.getGroup().getFriendlyURL();
		String redirectPage = PortletProps.get("call.page");
		redirectUrl = portalUrl + "/web" + siteUrl + "/" + redirectPage + "/-/call/-/" 
				+ callId + "/view";
	}
	
	User owner = UserLocalServiceUtil.getUser(idea.getUserId());
	
	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			Idea.class.getName(), idea.getIdeaId());

	String currentURL = PortalUtil.getCurrentURL(request);

	PortalUtil.addPortletBreadcrumbEntry(request, idea.getTitle(),
			currentURL);

	PortalUtil.setPageSubtitle(idea.getTitle(), request);
	PortalUtil.setPageDescription(idea.getTitle(), request);

  List<AssetCategory> categories = Utils.getOrderedCategories(idea.getCategoryIds(), assetEntry);
  java.util.Map<String,String> CC = IdeaLocalServiceUtil.getCategoryColors(scopeGroupId);
  String redirectCategoryUrl = null;
  if(categories.size() > 0) {
		String portalUrl = themeDisplay.getPortalURL();
		String siteUrl = layout.getGroup().getFriendlyURL();
		String redirectPage = PortletProps.get("category.page");
		redirectCategoryUrl = portalUrl + "/web" + siteUrl + "/" + redirectPage + "?resetCur=true&categoryId=";
  }

	List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(
			Idea.class.getName(), idea.getIdeaId());
	
	List<AssetTag> categoryTags = IdeaLocalServiceUtil.getCategoryTags(assetEntry.getCategoryIds(), assetEntry.getGroupId());  

	java.util.Set<String> categoryTagsSet = new java.util.HashSet<String>();
	java.util.Set<String> parentTagSet = new java.util.HashSet<String>();
	java.util.Set<String> ownTagSet = new java.util.HashSet<String>();
	for (AssetTag tag : categoryTags) {
		categoryTagsSet.add(tag.getName());
	}
  for (AssetTag tag : assetTags) {
    if (categoryTagsSet.contains(tag.getName())) parentTagSet.add(tag.getName());
    else ownTagSet.add(tag.getName());
  }
	// participants
  List<User> users = UserLocalServiceUtil.getGroupUsers(idea.getUserGroupId());
  boolean isOwner = owner.getUserId() == user.getUserId();
  boolean participates = false;
  for (User u : users) {
	  if (u.getUserId() == user.getUserId()) participates = true;
  }
  // followers
  List<Subscription> subs = SubscriptionLocalServiceUtil.getSubscriptions(themeDisplay.getCompanyId(), Idea.class.getName(), idea.getIdeaId());
  boolean subscribed = SubscriptionLocalServiceUtil.isSubscribed(themeDisplay.getCompanyId(), user.getUserId(), Idea.class.getName(), idea.getIdeaId());
  
  
	PortalUtil.setPageKeywords(ListUtil.toString(assetTags, "name"),
			request);
	
	String canonicalURL = PortalUtil.getCanonicalURL(currentURL.toString(), themeDisplay, layout);
	String htmlTitle = HtmlUtil.unescape(idea.getTitle());
	
  RatingsStats stat = RatingsStatsLocalServiceUtil.getStats(Idea.class.getName(),idea.getIdeaId());

  String state = idea.realState();
  if (state == null || state.equals("")) state = Constants.IDEA_STATE_PROPOSED;

%>
<div class="row-fluid">
  <span class="span8 idea-view-title">
  <%=HtmlUtil.unescape(idea.getTitle())%>
      <c:if test="<%= Utils.ideaEditEnabled(idea, renderRequest) %>">
      <portlet:renderURL var="editIdea" windowState="maximized">
        <portlet:param name="mvcPath" value="/html/idea/edit_idea.jsp" />
        <portlet:param name="ideaId" value="<%=String.valueOf(idea.getIdeaId()) %>" />
      </portlet:renderURL>
      <a href="<%=editIdea.toString()%>"><i class="icon-pencil"></i></a>
    </c:if>
  </span>
  <span class="span4 idea-creator text-right">
    <liferay-ui:message key="lbl_createdWhenWho" arguments="<%=new String[]{DateFormatFactoryUtil.getDate(locale).format(idea.getCreateDate()),owner.getScreenName()}%>" />  
  </span>
</div>

<%-- <div class="row-fluid">
  <div class="span12 pull-right">
    <liferay-ui:social-bookmarks displayStyle="vertical" target="_blank" title='<%= LanguageUtil.get(locale, "lbl_share") %>' url="<%= PortalUtil.getCanonicalURL(currentURL.toString(), themeDisplay, layout) %>"></liferay-ui:social-bookmarks>
  </div>  
</div>
 --%>
<div class="row-fluid idea-view-data">
  <div class="span6">
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
  <div class="span6 text-right">
      <span class="share-on-label"><liferay-ui:message key="lbl_share"/></span>
      <a class="share-on-link share-on-twitter" target="_blank" href="https://twitter.com/intent/tweet?text=<%=htmlTitle %>&url=<%=canonicalURL%>&hashtags=FuturaTrento"></a>
      <a class="share-on-link share-on-facebook" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=<%=canonicalURL%>"></a>
      <a class="share-on-link share-on-googleplus" target="_blank" href="https://plus.google.com/share?url=<%=canonicalURL%>"></a>
  </div>  
</div>

<div class="idea-info">
		<c:if test="<%= callId > 0 %>">
			<div class="idea-view-callref">
				<liferay-ui:message key="lbl_idea_callref"/> <a href="<%= redirectUrl %>"><%= callTitle %></a>
			</div>
		</c:if>
	  <div><%=HtmlUtil.unescape(idea.getLongDesc())%></div>
    <c:if test="<%=parentTagSet.size() > 0 %>">
    <div class="row-fluid info-meta">
      <i class="ftn-tag"></i>
      <span class="info-meta-label"><liferay-ui:message key="lbl_tags"/></span>
        <% for (String tag: parentTagSet) {%>
        <span class="badge"><%=tag %></span>
        <%} %>
    </div>
    </c:if>
    <c:if test="<%=ownTagSet.size() > 0 %>">
    <div class="row-fluid info-meta">
      <i class="ftn-user_tag"></i>
      <span class="info-meta-label"><liferay-ui:message key="lbl_usertags"/></span>
        <% for (String tag: ownTagSet) {%>
        <span class="badge"><%=tag %></span>
        <%} %>
    </div>
    </c:if>
		<liferay-ui:asset-links
		  assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
		  className="<%=Idea.class.getName()%>" classPK="<%=idea.getIdeaId()%>" />
</div>
<div class="idea-social">
    <c:if test="<%=!themeDisplay.isSignedIn()%>">
      <div class="row-fluid">
        <span class="span12 idea-creator idea-creator-warning text-right">
            <a class="use-dialog" href="<%= themeDisplay.getURLSignIn() %>"><liferay-ui:message key="lbl_access"/></a>  
        </span>
      </div>
      <div class="row-fluid">
        <div class="span6 text-center idea-discussion-rating"> 
          <div>
          	<liferay-ui:ratings className="<%= Idea.class.getName() %>" classPK="<%= idea.getIdeaId() %>"/> 
          </div>
        </div>
        <div class="span3 text-center">
        	<div>
        		<liferay-ui:message key="lbl_followercount"/>
        	</div> 
          <div class="participation-details">
            <span><%=subs.size() %></span>
            <i class="ftn-sum_users"></i>
          </div>
        </div>
        <div class="span3 text-center">
        	<div>
        		<liferay-ui:message key="lbl_participationcount"/>
        	</div> 
          <div class="participation-details">
            <span><%=users.size() %></span>
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
				  <liferay-ui:ratings className="<%= Idea.class.getName() %>" classPK="<%= idea.getIdeaId() %>" />
        </div>
	      <div class="span3 text-center">
		      <portlet:actionURL var="followIdea" name="followIdea">
            <portlet:param name="mvcPath" value="/html/idea/asset/full_content.jsp" />
		        <portlet:param name="ideaId" value="<%=String.valueOf(idea.getIdeaId()) %>" />
            <portlet:param name="subscribed" value="<%=String.valueOf(subscribed) %>" />
		      </portlet:actionURL>
  	      <div><a title="<liferay-ui:message key="lbl_tooltip_follow"/>" class='idea-button idea-button-tooltip-avail idea-button-follow-<%= subscribed ? "disabled" : "enabled" %>' href="<%=followIdea.toString() %>"></a></div>
          <div><span><liferay-ui:message key="lbl_following"/></span></div>
          <div class="participation-details">
	          <span><%=subs.size() %></span>
	          <i class="ftn-sum_users"></i>
          </div>
	      </div>
        <div class="span3 text-center">
          <portlet:actionURL var="toggleURL" name="toggleUserParticipation">
            <portlet:param name="mvcPath" value="/html/idea/asset/full_content.jsp" />
            <portlet:param name="ideaId" value="<%=String.valueOf(idea.getIdeaId()) %>" />
            <portlet:param name="userId" value="<%=String.valueOf(user.getUserId()) %>" />
          </portlet:actionURL>
          <div>
			<c:choose>
				<c:when test="<%=isOwner%>">
					<a title="<liferay-ui:message key="lbl_tooltip_participate_owner"/>"
						class='idea-button idea-button-tooltip-avail idea-button-participate-<%=participates ? "disabled" : "enabled"%>'
						href="#"></a>
				</c:when>
				<c:otherwise>
					<a title="<liferay-ui:message key="lbl_tooltip_participate"/>"
						class='idea-button idea-button-tooltip-avail idea-button-participate-<%=participates ? "disabled" : "enabled"%>'
						href="<%=toggleURL.toString()%>"></a>
				</c:otherwise>
			</c:choose>
          </div>
          <div><span><liferay-ui:message key="lbl_participating"/></span></div>
          <div class="participation-details">
            <span><%=users.size() %></span>
            <i class="ftn-sum_users"></i>
          </div>
        </div>
      </div>
    </c:if>
</div>
 
 <liferay-ui:panel-container accordion="true" extended="true">
  <liferay-ui:panel state="closed" collapsible="true" id="discussion" title='<%= LanguageUtil.get(locale, "lbl_discussion") %>'>
    <%
    boolean discussionEnabled = Utils.discussionEnabled(idea, renderRequest);
    %>
    <div class="row-fluid">
    	<span class="span12 idea-creator text-right">
      	<liferay-ui:message key="lbl_discussionExpiration" arguments="<%=new String[]{DateFormatFactoryUtil.getDate(locale).format(idea.discussionDeadline())}%>" />  
      </span>
    </div>
    <div class='row-fluid discussion-container'>
      <portlet:actionURL name="addComment" var="discussionURL">
        <!-- workaround to invoke liferary class that manage comment/discussion -->
        <portlet:param name="struts_action"
          value="/asset_publisher/edit_entry_discussion" />
      </portlet:actionURL>
    <liferay-ui:discussion hideControls="<%=!discussionEnabled %>" className="<%=Idea.class.getName()%>" 
      classPK="<%=idea.getIdeaId()%>" formAction="<%=discussionURL%>"
      formName="discussionForm" ratingsEnabled="<%=true %>" redirect="<%=currentURL%>"
      subject="<%=idea.getTitle()%>" userId="<%=idea.getUserId()%>" />
    </div>    
  </liferay-ui:panel>
  <liferay-ui:panel state="closed" collapsible="true" id="participants" title='<%= LanguageUtil.get(locale, "lbl_participants") %>'>
        
        <% 
        	int count = 0; 
        	for (User participant: users) {
        %>
        <c:if test="<%= count == 0 %>">
        <div class="row-fluid">
        </c:if>
        	<div class="span2">
        		<liferay-ui:user-display userId="<%=participant.getUserId() %>" userName="<%=participant.getScreenName() %>" displayStyle="0"></liferay-ui:user-display>
        		<c:if test="<%=participant.getUserId() == owner.getUserId() %>">
        		<span class="user-role"><liferay-ui:message key="lbl_author"/></span>
        		</c:if>
        	</div>
        <c:if test="<%= count == 5 %>">
        </div>
        </c:if>
        <%
        		count++;
        		if(count > 5) {
	        		count = 0;
  	      	}
    	    } 
        %>
  </liferay-ui:panel>
  <liferay-ui:panel state="closed" collapsible="true" id="state" title='<%= LanguageUtil.get(locale, "lbl_state") %>'>
    <div class="row-fluid"> 
    <div class="idea-state-container span2 text-center"><div><a class='idea-state state-proposed<%=Constants.IDEA_STATE_PROPOSED.equals(state) ? "active" : "" %>'></a></div><div><liferay-ui:message key="lbl_state_proposed"/></div></div>
    <div class="idea-state-container span2 text-center"><div><a class='idea-state state-waiting<%=Constants.IDEA_STATE_WAIT_FOR_EVAL.equals(state) ? "active" : "" %>'></a></div><div><liferay-ui:message key="lbl_state_waiting"/></div></div>
    <% if (Constants.IDEA_STATE_REJECTED.equals(state)) {%>
    <div class="idea-state-container span2 text-center"><div><a class='idea-state state-rejectedactive'></a></div><div><liferay-ui:message key="lbl_state_rejected"/></div></div>
    <% } else {%>
    <div class="idea-state-container span2 text-center"><div><a class='idea-state state-accepted<%=Constants.IDEA_STATE_ACCEPTED.equals(state) ? "active" : "" %>'></a></div><div><liferay-ui:message key="lbl_state_accepted"/></div></div>
    <% } %>
    <div class="idea-state-container span2 text-center"><div><a class='idea-state state-signed<%=Constants.IDEA_STATE_SIGNED.equals(state) ? "active" : "" %>'></a></div><div><liferay-ui:message key="lbl_state_signed"/></div></div>
    <div class="idea-state-container span2 text-center"><div><a class='idea-state state-exec<%=Constants.IDEA_STATE_EXEC.equals(state) ? "active" : "" %>'></a></div><div><liferay-ui:message key="lbl_state_exec"/></div></div>
    <div class="idea-state-container span2 text-center"><div><a class='idea-state state-complete<%=Constants.IDEA_STATE_COMPLETE.equals(state) ? "active" : "" %>'></a></div><div><liferay-ui:message key="lbl_state_complete"/></div></div>
    </div>
    <c:if test='<%=Validator.isNotNull(idea.getStateJudgement())%>'>
    <div class="row-fluid">
      <div class="state-judgement-title">
	      <liferay-ui:message key="lbl_state_judgement_title"/>
      </div>
      <div class="state-judgement-body">
      <%= idea.getStateJudgement() %>
      </div>
    </div>
    </c:if>
  </liferay-ui:panel>
</liferay-ui:panel-container>
