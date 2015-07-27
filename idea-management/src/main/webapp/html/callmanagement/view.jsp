<%@page import="java.text.DateFormat"%>
<%@ page import="it.smartcommunitylab.platform.idea.permission.CallPermission"%>
<%@ page import="it.smartcommunitylab.platform.idea.permission.CallModelPermission"%>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@page import="com.liferay.portal.security.permission.ActionKeys"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.WindowState" %>
<%@ page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>

<%@ include file="/html/common-init.jsp" %>

<%
String listType = GetterUtil.getString(portletPreferences.getValue("listType", Constants.PREF_CALLLISTTYPE_OPEN));
int delta = GetterUtil.getInteger(portletPreferences.getValue("elementInPage", ""+Constants.PAGINATION_CALL_ELEMENTS_IN_PAGE));
String viewType = GetterUtil.getString(portletPreferences.getValue("viewType", Constants.PREF_VIEWTYPE_COMPLEX));


java.util.Map<String,String> CC = IdeaLocalServiceUtil.getCategoryColors(scopeGroupId);

String baseUrl = Utils.getBaseURL(request);
Map<String,Object> params = new HashMap<String,Object>();
params.put("mvcPath", "/html/callmanagement/add_call.jsp");
String addCallUrl = Utils.generateRenderURL(renderResponse, baseUrl, params, WindowState.MAXIMIZED);
%>

<c:if test='<%= listType.equals(Constants.PREF_CALLLISTTYPE_OPEN) && Utils.callAddEnabled(renderRequest) %>'>

<aui:button-row  cssClass="idea-button-row" >
	<aui:button cssClass="addidea-button"  href="<%=addCallUrl.toString() %>" value='<%= LanguageUtil.get(locale, "btn_add_call") %>'></aui:button>
</aui:button-row>

</c:if>

<div class="calls-title">
<c:if test='<%=listType.equals(Constants.PREF_CALLLISTTYPE_OPEN) %>'><liferay-ui:message key="lbl_calls_title_open"/></c:if>
<c:if test='<%=listType.equals(Constants.PREF_CALLLISTTYPE_INDISCUSSION) %>'><liferay-ui:message key="lbl_calls_title_indiscussion"/></c:if>
<c:if test='<%=listType.equals(Constants.PREF_CALLLISTTYPE_CLOSED) %>'><liferay-ui:message key="lbl_calls_title_closed"/></c:if>
<c:if test='<%=listType.equals(Constants.PREF_CALLLISTTYPE_RECENT) %>'><liferay-ui:message key="lbl_calls_title_recent"/></c:if>
</div>

<c:if test='<%=viewType.equals(Constants.PREF_VIEWTYPE_SIMPLE) %>'>
<jsp:include page="view-list-simple.jsp"/>
</c:if>
<c:if test='<%=viewType.equals(Constants.PREF_VIEWTYPE_COMPLEX) %>'>
<jsp:include page="view-list-complex.jsp"/>
</c:if>
