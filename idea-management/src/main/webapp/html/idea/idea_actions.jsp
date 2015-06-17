<%@page import="it.smartcommunitylab.platform.idea.permission.IdeaPermission"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.security.permission.ActionKeys"%>
<%@page import="it.smartcommunitylab.platform.idea.permission.CallPermission"%>
<%@ include file="/html/common-init.jsp"%>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Idea entry = (Idea) row.getObject(); 
%>

<liferay-ui:icon-menu>

	<c:if
		test="<%=IdeaPermission.contains(permissionChecker,
							entry.getIdeaId(), ActionKeys.UPDATE)%>">
		<portlet:renderURL var="editURL">
			<portlet:param name="ideaId"
				value="<%=String.valueOf(entry.getIdeaId())%>" />
			<portlet:param name="mvcPath" value="/html/idea/edit_idea.jsp" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" message="Edit"
			url="<%=editURL.toString()%>" />
	</c:if>
	<c:if
		test="<%=IdeaPermission.contains(permissionChecker,
							entry.getIdeaId(), ActionKeys.DELETE)%>">
		<portlet:actionURL var="deleteURL" name="deleteEntry">
			<portlet:param name="entryId"
				value="<%=String.valueOf(entry.getIdeaId())%>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete message="Delete"
			url="<%=deleteURL.toString()%>" />
	</c:if>
	
	
</liferay-ui:icon-menu>