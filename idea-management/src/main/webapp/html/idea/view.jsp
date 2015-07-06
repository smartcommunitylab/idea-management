<%@page import="java.util.Arrays"%>
<%@page import="it.smartcommunitylab.platform.idea.portlet.Constants"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil" %>
<%@ page import="it.smartcommunitylab.platform.idea.permission.IdeaModelPermission" %>
<%@ page import="javax.portlet.WindowState" %>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>

<%@ include file="/html/common-init.jsp" %>

<%
boolean hidePortlet_view = GetterUtil.getBoolean(portletPreferences.getValue("hidePortlet", StringPool.FALSE));
boolean hideAddIdea_view = GetterUtil.getBoolean(portletPreferences.getValue("hideAddIdea", StringPool.FALSE));
boolean hideFilters_view = GetterUtil.getBoolean(portletPreferences.getValue("hideFilters", StringPool.FALSE));
boolean pagination_view = GetterUtil.getBoolean(portletPreferences.getValue("activatePagination", StringPool.TRUE));
Long categoryId = (Long) request.getAttribute("categoryId");
String viewType = GetterUtil.getString(portletPreferences.getValue("viewType", Constants.PREF_VIEWTYPE_SIMPLE));
String listType = GetterUtil.getString(portletPreferences.getValue("listType", Constants.PREF_LISTTYPE_RECENT));
List<AssetTag> categoryTags = IdeaLocalServiceUtil.getCategoryTags(new long[]{categoryId}, scopeGroupId);  
long[] tagSelected = (long[]) request.getAttribute("tagSelected");

if (pagination_view) {
	int delta = GetterUtil.getInteger(portletPreferences.getValue("elementInPage",String.valueOf(Constants.PAGINATION_ELEMENTS_IN_PAGE)));
	int currentPage = ParamUtil.getInteger(request, "cur", 1);
	String baseUrl = HttpUtil.getProtocol((String)request.getAttribute("CURRENT_COMPLETE_URL"))+"://"+HttpUtil.getDomain((String)request.getAttribute("CURRENT_COMPLETE_URL"))+request.getAttribute("FRIENDLY_URL");
	request.setAttribute("_baseUrl", baseUrl);
	request.setAttribute("_currentPage", currentPage);
	request.setAttribute("_delta", delta);
}		
%>

<c:if test='<%= !hidePortlet_view%>'>

<c:if test='<%= IdeaModelPermission.contains(permissionChecker, scopeGroupId, "ADD_IDEA") && !hideAddIdea_view %>'>

<aui:button-row>
	<portlet:renderURL var="addIdea" windowState="maximized">
		<portlet:param name="mvcPath" value="/html/idea/edit_idea.jsp" />
		<portlet:param name="categoryId" value="<%=  String.valueOf(categoryId) %>" />
	</portlet:renderURL>
	<aui:button name="addidea" value='<%= LanguageUtil.get(locale, "btn_add_idea") %>' onClick="<%=addIdea.toString()%>" />
</aui:button-row>

</c:if>


<script type="text/javascript">
    function <portlet:namespace/>doSearch() {
    	document.<portlet:namespace />filter.submit();
    }
    
    function <portlet:namespace/>fixCheckbox(elem) {
    	var hideValueName = elem.name.slice(0,-8);
    	var hideElem = document.getElementById(hideValueName);
    	var realValue = hideValueName.substring('<portlet:namespace/>filterByTags'.length);
    	hideElem.value = realValue;
    }
</script>

<%
if (request.getAttribute("listType") != null) listType = (String) request.getAttribute("listType");
%>

<portlet:actionURL
	name='filter'
	var="filterURL"></portlet:actionURL>

<c:if test='<%= !hideFilters_view %>'>
<aui:form id="filter" name="filter" action="<%=filterURL.toString() %>">
    <div class="row-fluid">
    <liferay-ui:message key="lbl_filter_by"/>  
		<aui:input inlineField="true" checked="<%= listType.equals(Constants.PREF_LISTTYPE_RECENT) %>" onChange='<%= renderResponse.getNamespace()+"doSearch()"%>' type="radio" name="listType" id="listType" value="<%= Constants.PREF_LISTTYPE_RECENT %>" label="lbl_filter_newer"/>
    <aui:input inlineField="true" checked="<%= listType.equals(Constants.PREF_LISTTYPE_POPULAR) %>" onChange='<%= renderResponse.getNamespace()+"doSearch()"%>' type="radio" name="listType" id="listType" value="<%= Constants.PREF_LISTTYPE_POPULAR %>" label="lbl_filter_famous"/>
		</div>
	<c:if test='<%= !categoryTags.isEmpty() %>'>
    <div class="row-fluid">
    <liferay-ui:message key="lbl_filter_by_tags"/>
    <% for (AssetTag tag: categoryTags) {%>
    <aui:input inlineField="true"  checked='<%=Arrays.binarySearch(tagSelected, tag.getTagId()) >= 0 %>' onClick='<%= renderResponse.getNamespace()+"fixCheckbox(this)"%>' onChange='<%= renderResponse.getNamespace()+"doSearch()"%>' type="checkbox" name='<%="filterByTags" + tag.getTagId() %>'  id='<%="filterByTags" + tag.getTagId() %>'  value="<%= tag.getTagId() %>"   label='<%=tag.getName() %>'/>
    <% } %>
    </div>
    </c:if>
</aui:form>
</c:if>

<c:if test='<%=viewType.equals(Constants.PREF_VIEWTYPE_SIMPLE) %>'>
<jsp:include page="view-list-simple.jsp"/>
</c:if>
<c:if test='<%=viewType.equals(Constants.PREF_VIEWTYPE_COMPLEX) %>'>
<jsp:include page="view-list-complex.jsp"/>
</c:if>

</c:if>
