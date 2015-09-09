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

int delta = GetterUtil.getInteger(portletPreferences.getValue("elementInPage",String.valueOf(Constants.PAGINATION_ELEMENTS_IN_PAGE)));
String baseUrl = Utils.getBaseURL(request);

/*
if (pagination_view) {
  int currentPage = ParamUtil.getInteger(request, "cur", 1);
  request.setAttribute("_baseUrl", baseUrl);
  request.setAttribute("_currentPage", currentPage);
  request.setAttribute("_delta", delta);
}   
*/
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

java.util.Set<Long> selectedTagIds = new java.util.HashSet<Long>();

if (categoryId > 0) {
    categoryTags = IdeaLocalServiceUtil.getCategoryTags(new long[]{categoryId}, scopeGroupId);  
} else {
    String[] publicParamTagNames = ParamUtil.getParameterValues(renderRequest, "tag");
    if (publicParamTagNames != null && publicParamTagNames.length > 0){
        long[] ids = AssetTagLocalServiceUtil.getTagIds(scopeGroupId, publicParamTagNames);
        categoryTags = new java.util.ArrayList<AssetTag>();
        for (long id : ids) {
          categoryTags.add(AssetTagLocalServiceUtil.getAssetTag(id));
          selectedTagIds.add(id);
        }
    }
}

boolean addEnabled = IdeaModelPermission.contains(permissionChecker, scopeGroupId, "ADD_IDEA") ;

String addIdeaLabel = "btn_add_idea";
if(callId > 0) {
  addIdeaLabel = "btn_add_idea_in_call";
}

%>

<c:if test='<%= !hidePortlet_view%>'>

<c:if test='<%=!hideAddIdea_view && !callExpired %>'>
<aui:button-row cssClass="idea-button-row" >
<%--  <portlet:renderURL var="addIdea">
    <portlet:param name="categoryId" value="<%=  String.valueOf(categoryId) %>" />
  </portlet:renderURL>
 --%> 
  <%
  Map<String,Object> params = new HashMap<String,Object>();
  params.put("categoryId", categoryId);
  params.put("callId", callId);
  params.put("mvcPath", "/html/idea/add_idea.jsp");
  String addIdea = Utils.generateRenderURL(renderResponse, baseUrl, params, WindowState.MAXIMIZED);
  if (!addEnabled) addIdea = "javascript:document.getElementById('"+renderResponse.getNamespace()+"addButtonDisableMsg').style.display = 'block';";
  %>
  <aui:button cssClass='<%=addEnabled ? "addidea-button" : "addidea-button-disabled"%>' name="addidea" value='<%= LanguageUtil.get(locale, addIdeaLabel) %>' onClick="<%=addIdea.toString()%>" />
  <div id="<portlet:namespace/>addButtonDisableMsg" class="row-fluid add-button-disabled-msg" style="display:none;">
    <span><liferay-ui:message key="msg_access_to_add"/></span>
  </div>
</aui:button-row>
</c:if>


<aui:script use="aui-base, aui-node">
    Liferay.provide(window, '<portlet:namespace/>ajaxFilter', function() {
        var A = new AUI();
        var listType = A.one('input[name=<%=renderResponse.getNamespace() %>listType]:checked').val();
        var tags = A.all('input[id$=Checkbox]:checked');
        var tagIds = [];
        tags.each(function(){
          tagIds.push(this.val());
        });
              
        var url = Liferay.PortletURL.createResourceURL();
        url.setPortletId('<%= Constants.IDEA_PORTLET_ID %>');
        url.setResourceId("loadSimple");
      url.setParameter("cur", "1");
        url.setParameter("pagination",'<%= Boolean.toString(pagination_view) %>');
        url.setParameter("delta",'<%= Integer.toString(delta) %>');
      url.setParameter("categoryId", '<%= Long.toString(categoryId) %>');
      url.setParameter("callId", '<%= Long.toString(callId) %>');
      
      // filters
        url.setParameter("listType", listType);
      url.setParameter("tag", tagIds.join() );
      
      <portlet:namespace/>paginateIdeas(url.toString());
    });
    
    
    A.all('input[name=<%=renderResponse.getNamespace() %>listType]').on('click', function(e) {
        <portlet:namespace/>ajaxFilter();
    });
    
    A.all('[id$=Checkbox]').each(function(){
         var label = A.one('label[for='+this.get('id')+']');
           if(!this.attr('checked')){
                label.setStyle('backgroundColor','');
            } else {
                var borderStyle = label.getStyle('borderColor');
                label.setStyle('backgroundColor', borderStyle);
            }
  });
  
     A.all('[id$=Checkbox]').on('click', function(e) {
        var node = e.currentTarget;
        var label = A.one('label[for='+node.get('id')+']');
           if(!node.attr('checked')){
                node.removeAttribute('checked');
                label.setStyle('backgroundColor','');
            } else {
                node.setAttribute('checked', true);
                var borderStyle = label.getStyle('borderColor');
                label.setStyle('backgroundColor', borderStyle);
            }
        
        <portlet:namespace/>ajaxFilter();
    });
  
</aui:script>


<%
if (request.getAttribute("listType") != null) listType = (String) request.getAttribute("listType");
%>

<portlet:resourceURL id="loadSimple" var="ajaxFilterURL">
  <portlet:param name="categoryId" value="<%=String.valueOf(categoryId) %>" />
    <portlet:param name="callId" value="<%=String.valueOf(callId) %>" />
    <!-- reset idea id to clear in navigation --> 
    <portlet:param name="mvcPath" value="/html/idea/view.jsp" />
    <portlet:param name="ideaId" value="0" />
</portlet:resourceURL>

<c:if test='<%= !hideFilters_view %>'>
<aui:form cssClass="filter-panel" id="filter" name="filter">
    <div class="row-fluid">
    <span class="filter-label"><liferay-ui:message key="lbl_filter_by"/></span>
    <div class="control-group control-group-inline form-inline">
      <label class="radio inline" for="listTypeRecent">
      <input class="field idea-type-filter" <%= listType.equals(Constants.PREF_LISTTYPE_RECENT)?"checked":"" %> type="radio" name="<%=renderResponse.getNamespace() %>listType" id="listTypeRecent" value="<%= Constants.PREF_LISTTYPE_RECENT %>"/>
      <span><liferay-ui:message key="lbl_filter_newer"/></span>
      </label> 
      
    </div>  
    <div class="control-group control-group-inline form-inline">
      <label class="radio inline" for="listTypePopular">
      <input class="field idea-type-filter" <%= listType.equals(Constants.PREF_LISTTYPE_POPULAR)?"checked":"" %>  type="radio" name="<%=renderResponse.getNamespace() %>listType" id="listTypePopular" value="<%= Constants.PREF_LISTTYPE_POPULAR %>"/>
      <span><liferay-ui:message key="lbl_filter_famous"/></span>
      </label> 
    </div>  
    </div>
  <c:if test='<%= !categoryTags.isEmpty() %>'>
    <div class="row-fluid tag-filters">
    <span class="filter-label"><liferay-ui:message key="lbl_filter_by_tags"/></span>
    <% for (AssetTag tag: categoryTags) { String cId = renderResponse.getNamespace()+"filterByTags" + tag.getTagId(); %>
    <div class="control-group control-group-inline form-inline">
      <label style='border-color: <%=categoryColor %>' class="checkbox inline" for='<%=cId %>Checkbox'>
      <%-- <input id="<%=cId %>" name="<%=cId %>" type="hidden" value="<%=tag.getTagId() %>"> --%>
      <input class="field idea-tag-filter" <%=selectedTagIds.contains(tag.getTagId()) ? "checked=\"true\"" : "" %> id="<%=cId %>Checkbox" name="<%=cId %>Checkbox"  type="checkbox" value="<%=tag.getTagId() %>">
      <%=tag.getName() %>
      </label> 
    </div>  
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
