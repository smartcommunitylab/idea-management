<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.security.permission.ActionKeys"%>
<%@page import="it.smartcommunitylab.platform.idea.permission.CallPermission"%>
<%@ include file="/html/common-init.jsp"%>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Call entry = (Call) row.getObject(); 
%>

<liferay-ui:icon-menu>

	<c:if
		test="<%=CallPermission.contains(permissionChecker,
							entry.getCallId(), ActionKeys.UPDATE)%>">
		<portlet:renderURL var="editURL">
			<portlet:param name="entryId"
				value="<%=String.valueOf(entry.getCallId())%>" />
			<portlet:param name="mvcPath" value="/html/callmanagement/edit_call.jsp" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" message="Edit"
			url="<%=editURL.toString()%>" />
	</c:if>
	<c:if
		test="<%=CallPermission.contains(permissionChecker,
							entry.getCallId(), ActionKeys.DELETE)%>">
		<portlet:actionURL var="deleteURL" name="deleteEntry">
			<portlet:param name="entryId"
				value="<%=String.valueOf(entry.getCallId())%>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete message="Delete"
			url="<%=deleteURL.toString()%>" />
	</c:if>
	
	
</liferay-ui:icon-menu>