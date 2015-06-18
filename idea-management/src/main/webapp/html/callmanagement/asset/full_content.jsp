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
<%@page import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil"%>
<%@page import="com.liferay.portlet.asset.model.AssetEntry"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@page import="it.smartcommunitylab.platform.idea.portlet.Constants"%>
<%@page import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>

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


PortletURL ipcURL = PortletURLFactoryUtil.create(request, Constants.IDEA_PORTLET_ID, themeDisplay.getPlid(),PortletRequest.RENDER_PHASE);
ipcURL.setParameter("mvcPath", "/html/idea/edit_idea.jsp");
ipcURL.setParameter("callId", String.valueOf(call.getCallId()));
%>

<div>
<%=HtmlUtil.unescape(call.getDescription())%>
</div>

<liferay-ui:asset-links
	assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
	className="<%=Call.class.getName()%>"
	classPK="<%=call.getCallId()%>" />