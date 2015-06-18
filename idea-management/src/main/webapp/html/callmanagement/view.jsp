<%@page import="it.smartcommunitylab.platform.idea.permission.CallPermission"%>
<%@page import="it.smartcommunitylab.platform.idea.permission.CallModelPermission"%>
<%@page import="com.liferay.portal.security.permission.ActionKeys"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@ include file="/html/common-init.jsp" %>

<portlet:renderURL var="addCallUrl">
	<portlet:param name="mvcPath" value="/html/callmanagement/edit_call.jsp"/>
</portlet:renderURL>

<c:if test='<%= CallModelPermission.contains(permissionChecker, scopeGroupId, "ADD_CALL") %>'>

<aui:button-row>
	<aui:button href="<%=addCallUrl.toString() %>" value='<%= LanguageUtil.get(locale, "btn_add_call") %>'></aui:button>
</aui:button-row>

</c:if>

<liferay-ui:search-container>

  <liferay-ui:search-container-results
    results="<%= CallLocalServiceUtil.getCalls(PortalUtil.getUserId(request))%>" />
 <liferay-ui:search-container-row
        className="it.smartcommunitylab.platform.idea.model.Call"
        modelVar="entry"
    >
        <liferay-ui:search-container-column-text property="title" />
        <liferay-ui:search-container-column-text property="description" />
        <liferay-ui:search-container-column-jsp path="/html/callmanagement/call_actions.jsp"></liferay-ui:search-container-column-jsp>
    
    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator></liferay-ui:search-iterator>
</liferay-ui:search-container>