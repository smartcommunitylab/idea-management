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

  java.util.Set<String> tagSet = new java.util.HashSet<String>();
  for (AssetTag tag : assetTags) {
    tagSet.add(tag.getName());
  }

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
    <c:if test='<%= CallModelPermission.contains(permissionChecker, scopeGroupId, "ADD_CALL") %>'>
      <portlet:renderURL var="editCall" windowState="maximized">
        <portlet:param name="mvcPath" value="/html/callmanagement/edit_call.jsp" />
        <portlet:param name="callId" value="<%=String.valueOf(call.getCallId()) %>" />
      </portlet:renderURL>
      <a href="<%=editCall.toString()%>"><i class="icon-pencil"></i></a>
    </c:if>
    </div>
    <% for (AssetCategory ac : categories) {
    	String categoryColor = CC.get(""+ac.getCategoryId());
    %>
    <div class="call-cattitle" style="background-color: <%=categoryColor %>;">
    <%=ac.getTitle(locale) %>  
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
      <i class="icon-tags icon-white"></i>
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