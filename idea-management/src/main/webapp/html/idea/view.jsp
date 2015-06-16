<%@page import="it.smartcommunitylab.platform.idea.portlet.Constants"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil" %>

<%@ include file="/html/common-init.jsp" %>

<%
boolean hidePortlet_view = GetterUtil.getBoolean(portletPreferences.getValue("hidePortlet", StringPool.FALSE));
Long categoryId = (Long) request.getAttribute("categoryId");
%>

<c:if test='<%= !hidePortlet_view%>'>
<aui:button-row>
	<portlet:renderURL var="addIdea">
		<portlet:param name="mvcPath" value="/html/idea/edit_idea.jsp" />
		<portlet:param name="categoryId" value="<%=  String.valueOf(categoryId) %>" />
	</portlet:renderURL>
	<aui:button name="addidea" value='<%= LanguageUtil.get(locale, "btn_add_idea") %>' onClick="<%=addIdea.toString()%>" />
</aui:button-row>

<%-- TEMP - USED FOR TEST 
<c:if test='<%= request.getAttribute("categoryId") != null %>'>
 <p><strong>CATEGORY SELECTED <%= request.getAttribute("categoryId") %></strong></p>
</c:if>
<c:if test='<%= request.getAttribute("categoryId") == null %>'>
 <p><strong>NO CATEGORY</strong></p>
</c:if>
--%>

<script type="text/javascript">
    function <portlet:namespace/>doSearch() {
    	document.<portlet:namespace />filter.submit();
    }
</script>

<%
String filterBy = (String) request.getAttribute("filterBy");
%>

<portlet:actionURL
	name='filter'
	var="filterURL"></portlet:actionURL>

<aui:form id="filter" name="filter" action="<%=filterURL.toString() %>">
	<aui:fieldset label="lbl_filter_by">
		<aui:input checked="<%= filterBy == null|| filterBy.equals(Constants.FILTER_BY_ALL) %>" onChange="_ideamanagement_WAR_ideamanagement_doSearch()" type="radio" name="filterBy" id="filterBy" value="<%= Constants.FILTER_BY_ALL %>" label="lbl_filter_all"/>
		<aui:input onChange="_ideamanagement_WAR_ideamanagement_doSearch()" type="radio" name="filterBy" id="filterBy" value="<%= Constants.FILTER_BY_CREATION %>" label="lbl_filter_newer"/>
		<aui:input onChange="_ideamanagement_WAR_ideamanagement_doSearch()" type="radio" name="filterBy" id="filterBy" value="<%= Constants.FILTER_BY_POPOLARITY %>" label="lbl_filter_famous"/>
	</aui:fieldset>
</aui:form>


<liferay-ui:search-container>
    <liferay-ui:search-container-results
    results='<%= (List) request.getAttribute("ideas") %>' />

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
</c:if>
