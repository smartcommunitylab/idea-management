<%@page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil"%>
<%@ include file="/html/common-init.jsp" %>
<liferay-ui:search-container>
    <liferay-ui:search-container-results
    results='<%= (List) request.getAttribute("ideas") %>' />

    <liferay-ui:search-container-row
        className="it.smartcommunitylab.platform.idea.model.Idea"
        modelVar="entry"
    >
  <portlet:renderURL var="viewIdea" windowState="maximized">
    <portlet:param name="mvcPath" value="/html/idea/asset/full_content.jsp" />
    <portlet:param name="ideaId" value="<%=String.valueOf(entry.getIdeaId()) %>" />
  </portlet:renderURL>

        <liferay-ui:search-container-column-text property="title" href="<%=viewIdea.toString() %>">
        
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text property="longDesc" />
        <liferay-ui:search-container-column-text value='<%= String.valueOf(MBMessageLocalServiceUtil.getDiscussionMessagesCount(Idea.class.getName(), entry.getIdeaId(), WorkflowConstants.STATUS_APPROVED))%>'></liferay-ui:search-container-column-text>
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>