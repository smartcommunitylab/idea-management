<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil"%>
<%@page import="com.liferay.portlet.asset.model.AssetEntry"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>


<%@  include file="/html/common-init.jsp"%>

<%
	Call call = (Call) request.getAttribute("gb_call");
	call = call.toEscapedModel();
%>

<%
	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			Call.class.getName(), call.getCallId());
%>

<div>
<%=HtmlUtil.unescape(call.getDescription())%>
</div>
<div style="clear: both; margin-top: 5em;">
<portlet:renderURL var="addIdeaUrl">
	<portlet:param name="mvcPath" value="/html/callmanagement/edit_call.jsp"/>
	<portlet:param name="callId" value="<%= String.valueOf(call.getCallId()) %>"/>
</portlet:renderURL>

<%-- <aui:button href="<%=addIdeaUrl.toString() %>" value="Idea Proposal"></aui:button> --%>

</div>

<liferay-ui:asset-links
	assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
	className="<%=Call.class.getName()%>"
	classPK="<%=call.getCallId()%>" />