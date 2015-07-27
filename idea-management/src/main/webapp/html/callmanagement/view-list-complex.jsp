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
boolean viewAll = ParamUtil.getBoolean(request, "viewAll", false);

int begin = 0, end = viewAll ? -1 : begin+delta;
List<Call> list = null;
if (listType.equals(Constants.PREF_CALLLISTTYPE_OPEN)) {
  list =  CallLocalServiceUtil.getOpenCalls(begin, end);
} else if (listType.equals(Constants.PREF_CALLLISTTYPE_INDISCUSSION)) {
    list =  CallLocalServiceUtil.getInDiscussionCalls(begin, end);
} else if (listType.equals(Constants.PREF_CALLLISTTYPE_CLOSED)){
    list =  CallLocalServiceUtil.getClosedCalls(begin, end);
} else {
    list =  CallLocalServiceUtil.getCalls(begin, end);
}
java.util.Map<String,String> CC = IdeaLocalServiceUtil.getCategoryColors(scopeGroupId);
  
String baseUrl = Utils.getBaseURL(request);
Map<String,Object> params = new HashMap<String,Object>();
params.put("mvcPath", "/html/callmanagement/add_call.jsp");
%>
<div class="calls">
<% for(Call call : list) {%>
        <portlet:renderURL var="viewCall">
          <portlet:param name="callId" value="<%=String.valueOf(call.getCallId()) %>" />
          <portlet:param name="mvcPath" value="/html/callmanagement/asset/full_content.jsp" />
        </portlet:renderURL>
        <% 
        long classPK = call.getCallId();
        AssetEntry curEntry = AssetEntryLocalServiceUtil.getEntry(Call.class.getName(),classPK);
        List<AssetCategory> categories = Utils.getOrderedCategories(call.getCategoryIds(), curEntry);   
        String primaryColor = categories.size() > 0 ? CC.get(""+categories.get(0).getCategoryId()): ""; 

//         categories.size() > 0 ? categories.get(0).getTitle(locale): "";
        int countIdeaByCall = IdeaLocalServiceUtil.getIdeasByCall(call.getCallId(), -1, -1).size();
        %>
  <div class="row-fluid">
    <div class="call">
          <div onClick="javascript:window.location = '<%=viewCall.toString()%>';" class="call-card" style="border-left-color: <%=primaryColor %>;">
              <div class="call-card-header">
                <div class="span6">
                  <% for (AssetCategory ac : categories) { 
                	  String color = CC.get(""+ac.getCategoryId()); 
                	  String catTitle = ac.getTitle(locale);
                	%>  
                  <span class="call-card-cat" style="color: <%=color %>;"><%=catTitle %></span>
                  <% } %>
                </div>
                <div class="span6">
                  <c:if test='<%= Utils.callDeleteEnabled(call, renderRequest) %>'>
                    <portlet:actionURL var="deleteURL" name="deleteEntry">
                      <portlet:param name="entryId" value="<%=String.valueOf(call.getCallId()) %>" />
                    </portlet:actionURL>
                    <liferay-ui:icon-delete message="lbl_delete" url="<%=deleteURL.toString()%>"/>
                  </c:if>
                  <span class="call-card-date">
                  <c:if test='<%=call.getDeadline() != null %>'>
                  <liferay-ui:message key="lbl_call_card_deadline" arguments="<%=dateFormatter.format(call.getDeadline()) %>"/>
                  </c:if>
                  </span>
	              </div>
              </div>
              <h4><%=call.getTitle() %></h4>
              <div class="call-card-abstract"><%=call.getDescription() %></div>
              <div class="call-card-footer">
                <div class="span12"><span class="call-card-ideas"><%=countIdeaByCall %></span></div>
              </div>
          </div>
    </div>
  </div>
<% } %>
  
  <c:if test='<%=list.size() > 0 && !viewAll %>'>
  <div class="row-fluid call-paging">
    <div class="span12">
      <% 
      params.put("mvcPath", "/html/callmanagement/view.jsp");
      params.put("viewAll", "true"); 
      String showAllURL = Utils.generateRenderURL(renderResponse, baseUrl, params);
      %>
      <a href="<%=showAllURL%>"><liferay-ui:message key="lbl_showAll"/></a>
    </div>
  </div>
  </c:if>
  
  <c:if test='<%=list.size() ==0 %>'>
  <div class="row-fluid">
  <span class="empty-results"><liferay-ui:message key="lbl_noresults"/></span>
  </div>
  </c:if>
</div>
