<%@page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@page import="java.util.Locale"%>
<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ include file="/html/common-init.jsp" %>


<aui:button-row>
	<portlet:renderURL var="addIdea">
		<portlet:param name="mvcPath" value="/html/idea/edit_idea.jsp" />
	</portlet:renderURL>
	<aui:button name="addidea" value='<%= LanguageUtil.get(locale, "btn_add_idea") %>' onClick="<%=addIdea.toString()%>" />
</aui:button-row>



<liferay-ui:search-container>
    <liferay-ui:search-container-results
    results="<%= IdeaLocalServiceUtil.getIdeas(0,IdeaLocalServiceUtil.getIdeasCount()) %>" />

    <liferay-ui:search-container-row
        className="it.smartcommunitylab.platform.idea.model.Idea"
        modelVar="entry"
    >
    <portlet:renderURL var="editIdea">
		<portlet:param name="mvcPath" value="/html/idea/edit_idea.jsp" />
		<portlet:param name="ideaId" value="<%=String.valueOf(entry.getIdeaId()) %>" />
	</portlet:renderURL>

        <liferay-ui:search-container-column-text property="title" href="<%=editIdea.toString() %>">
        
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text property="longDesc" />
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>