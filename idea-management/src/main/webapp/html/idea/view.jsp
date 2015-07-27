<%@ page import="java.util.Arrays"%>
<%@ page import="it.smartcommunitylab.platform.idea.portlet.Constants"%>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil" %>
<%@ page import="it.smartcommunitylab.platform.idea.permission.IdeaModelPermission" %>
<%@ page import="javax.portlet.WindowState" %>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>
<%@ page import="com.liferay.portal.kernel.util.DateUtil"%>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@ page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>

<%@ include file="/html/common-init.jsp" %>

<%
boolean hidePortlet_view = GetterUtil.getBoolean(portletPreferences.getValue("hidePortlet", StringPool.FALSE));
boolean hideAddIdea_view = GetterUtil.getBoolean(portletPreferences.getValue("hideAddIdea", StringPool.FALSE));
boolean hideFilters_view = GetterUtil.getBoolean(portletPreferences.getValue("hideFilters", StringPool.FALSE));
boolean hideList_view = GetterUtil.getBoolean(portletPreferences.getValue("hideList", StringPool.FALSE));
boolean pagination_view = GetterUtil.getBoolean(portletPreferences.getValue("activatePagination", StringPool.TRUE));
Long categoryId = (Long) request.getAttribute("categoryId");
if (categoryId == null) categoryId = ParamUtil.getLong(renderRequest, "categoryId");
String viewType = GetterUtil.getString(portletPreferences.getValue("viewType", Constants.PREF_VIEWTYPE_SIMPLE));
String listType = GetterUtil.getString(portletPreferences.getValue("listType", Constants.PREF_LISTTYPE_RECENT));

if (pagination_view) {
	int delta = GetterUtil.getInteger(portletPreferences.getValue("elementInPage",String.valueOf(Constants.PAGINATION_ELEMENTS_IN_PAGE)));
	int currentPage = ParamUtil.getInteger(request, "cur", 1);
	String baseUrl = Utils.getBaseURL(request);
	request.setAttribute("_baseUrl", baseUrl);
	request.setAttribute("_currentPage", currentPage);
	request.setAttribute("_delta", delta);
}		

java.util.Map<String,String> CC = IdeaLocalServiceUtil.getCategoryColors(scopeGroupId);

Long callId = ParamUtil.getLong(request, "callId");
boolean callExpired = false;
List<AssetTag> categoryTags = java.util.Collections.emptyList();

if(callId > 0) {
    Call call = CallLocalServiceUtil.getCall(callId);
    callExpired = DateUtil.compareTo(DateUtil.newDate(), call.getDeadline()) > 0;   
    categoryTags = AssetTagLocalServiceUtil.getTags(Call.class.getName(), call.getCallId());
    if (categoryId==0) {
    	  AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
    		      Call.class.getName(), call.getCallId());
    	  List<AssetCategory> categories = assetEntry.getCategories();
    	  AssetCategory category = null;
    	  if (categories != null && categories.size() > 0) {
    	    category = categories.get(0);
    	    categoryId = category.getCategoryId();
    	  }
    }
} 

String categoryColor = "#DDD";
if (categoryId > 0) {
   categoryColor = CC.get(""+categoryId);
}

long[] tagSelected = (long[]) request.getAttribute("tagSelected");

if (categoryId > 0) {
	  categoryTags = IdeaLocalServiceUtil.getCategoryTags(new long[]{categoryId}, scopeGroupId);  
	} else {
	  String[] publicParamTagNames = ParamUtil.getParameterValues(renderRequest, "tag");
	  if (publicParamTagNames != null && publicParamTagNames.length > 0){
	      long[] ids = AssetTagLocalServiceUtil.getTagIds(scopeGroupId, publicParamTagNames);
	      categoryTags = new java.util.ArrayList<AssetTag>();
	      for (long id : ids) {
	        categoryTags.add(AssetTagLocalServiceUtil.getAssetTag(id));
	      }
	  }
	}

boolean addEnabled = IdeaModelPermission.contains(permissionChecker, scopeGroupId, "ADD_IDEA") ;

%>

<c:if test='<%= !hidePortlet_view%>'>

<c:if test='<%=!hideAddIdea_view && !callExpired %>'>
<aui:button-row cssClass="idea-button-row" >
<%-- 	<portlet:renderURL var="addIdea">
		<portlet:param name="categoryId" value="<%=  String.valueOf(categoryId) %>" />
	</portlet:renderURL>
 --%>	
  <%
	String baseUrl = (String) request.getAttribute("_baseUrl");
  Map<String,Object> params = new HashMap<String,Object>();
  params.put("categoryId", categoryId);
  params.put("callId", callId);
  params.put("mvcPath", "/html/idea/add_idea.jsp");
  String addIdea = Utils.generateRenderURL(renderResponse, baseUrl, params, WindowState.MAXIMIZED);
  if (!addEnabled) addIdea = "javascript:document.getElementById('"+renderResponse.getNamespace()+"addButtonDisableMsg').style.display = 'block';";
	%>
	<aui:button cssClass='<%=addEnabled ? "addidea-button" : "addidea-button-disabled"%>' name="addidea" value='<%= LanguageUtil.get(locale, "btn_add_idea") %>' onClick="<%=addIdea.toString()%>" />
</aui:button-row>
<div id="<portlet:namespace/>addButtonDisableMsg" class="row-fluid add-button-disabled-msg" style="display:none;">
  <span><liferay-ui:message key="msg_access_to_add"/></span>
</div>
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
	var="filterURL">
    <portlet:param name="categoryId" value="<%=String.valueOf(categoryId) %>" />
    <portlet:param name="callId" value="<%=String.valueOf(callId) %>" />
    <!-- reset idea id to clear in navigation -->	
    <portlet:param name="mvcPath" value="/html/idea/view.jsp" />
    <portlet:param name="ideaId" value="0" />
</portlet:actionURL>

<c:if test='<%= !hideFilters_view %>'>
<aui:form cssClass="filter-panel" id="filter" name="filter" action="<%=filterURL.toString() %>">
    <div class="row-fluid">
    <span class="filter-label"><liferay-ui:message key="lbl_filter_by"/></span>
    <div class="control-group control-group-inline form-inline">
      <label class="radio inline" for="listTypeRecent">
      <input class="field idea-type-filter" <%= listType.equals(Constants.PREF_LISTTYPE_RECENT)?"checked":"" %> onChange='<%= renderResponse.getNamespace()+"doSearch()"%>' type="radio" name="<%=renderResponse.getNamespace() %>listType" id="listTypeRecent" value="<%= Constants.PREF_LISTTYPE_RECENT %>"/>
      <span><liferay-ui:message key="lbl_filter_newer"/></span>
      </label> 
      
    </div>  
    <div class="control-group control-group-inline form-inline">
      <label class="radio inline" for="listTypePopular">
      <input class="field idea-type-filter" <%= listType.equals(Constants.PREF_LISTTYPE_POPULAR)?"checked":"" %> onChange='<%= renderResponse.getNamespace()+"doSearch()"%>' type="radio" name="<%=renderResponse.getNamespace() %>listType" id="listTypePopular" value="<%= Constants.PREF_LISTTYPE_POPULAR %>"/>
      <span><liferay-ui:message key="lbl_filter_famous"/></span>
      </label> 
    </div>  
		</div>
	<c:if test='<%= !categoryTags.isEmpty() %>'>
    <div class="row-fluid tag-filters">
    <span class="filter-label"><liferay-ui:message key="lbl_filter_by_tags"/></span>
    <% for (AssetTag tag: categoryTags) { String cId = renderResponse.getNamespace()+"filterByTags" + tag.getTagId(); boolean checked = Arrays.binarySearch(tagSelected, tag.getTagId()) >= 0; %>
    <div class="control-group control-group-inline form-inline">
      <label style='border-color: <%=categoryColor %>;<%=checked?"background-color:"+categoryColor :"" %>' class="checkbox inline" for='<%=cId %>Checkbox'>
      <input id="<%=cId %>" name="<%=cId %>" type="hidden" value="<%=tag.getTagId() %>">
      <input class="field idea-tag-filter" <%=checked ? "checked" :"" %> id="<%=cId %>Checkbox" name="<%=cId %>Checkbox" onchange='<%= renderResponse.getNamespace()+"doSearch()"%>' onclick="Liferay.Util.updateCheckboxValue(this); <%=renderResponse.getNamespace() %>fixCheckbox(this)" type="checkbox" value="<%=tag.getTagId() %>">
      <%=tag.getName() %>
      </label> 
    </div>  
<%--     <aui:input cssClass="idea-tag-filter" inlineField="true"  checked='<%=Arrays.binarySearch(tagSelected, tag.getTagId()) >= 0 %>' onClick='<%= renderResponse.getNamespace()+"fixCheckbox(this)"%>' onChange='<%= renderResponse.getNamespace()+"doSearch()"%>' type="checkbox" name='<%="filterByTags" + tag.getTagId() %>'  id='<%="filterByTags" + tag.getTagId() %>'  value="<%= tag.getTagId() %>"   label='<%=tag.getName() %>'/> --%>
    <% } %>
    </div>
    </c:if>
</aui:form>
</c:if>

<c:if test='<%=!hideList_view %>'>
	<c:if test='<%=viewType.equals(Constants.PREF_VIEWTYPE_SIMPLE) %>'>
	<jsp:include page="view-list-simple.jsp"/>
	</c:if>
	<c:if test='<%=viewType.equals(Constants.PREF_VIEWTYPE_COMPLEX) %>'>
	<jsp:include page="view-list-complex.jsp"/>
	</c:if>
</c:if>
</c:if>
