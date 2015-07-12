<%@page import="java.text.DateFormat"%>
<%@ page import="it.smartcommunitylab.platform.idea.permission.CallPermission"%>
<%@ page import="it.smartcommunitylab.platform.idea.permission.CallModelPermission"%>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@page import="com.liferay.portal.security.permission.ActionKeys"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.WindowState" %>

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
} else {
    list =  CallLocalServiceUtil.getClosedCalls(begin, end);
}
  java.util.Map<String,String> CC = IdeaLocalServiceUtil.getCategoryColors(scopeGroupId);
  
  String baseUrl = HttpUtil.getProtocol((String)request.getAttribute("CURRENT_COMPLETE_URL"))+"://"+HttpUtil.getDomain((String)request.getAttribute("CURRENT_COMPLETE_URL"))+request.getAttribute("FRIENDLY_URL");
%>

<%-- <portlet:renderURL var="addCallUrl" windowState="maximized"> --%>
<%-- 	<portlet:param name="mvcPath" value="/html/callmanagement/edit_call.jsp"/> --%>
<%-- </portlet:renderURL> --%>
  <%
  PortletURL portletURL = renderResponse.createRenderURL();
  portletURL.setParameter("mvcPath", "/html/callmanagement/edit_call.jsp");
  portletURL.setWindowState(WindowState.MAXIMIZED);
  String addCallUrl = baseUrl + "?" + HttpUtil.getQueryString(portletURL.toString());
  %>

<c:if test='<%= listType.equals(Constants.PREF_CALLLISTTYPE_OPEN) && CallModelPermission.contains(permissionChecker, scopeGroupId, "ADD_CALL") %>'>

<aui:button-row  cssClass="idea-button-row" >
	<aui:button cssClass="addidea-button"  href="<%=addCallUrl.toString() %>" value='<%= LanguageUtil.get(locale, "btn_add_call") %>'></aui:button>
</aui:button-row>

</c:if>

<div class="calls-title">
<c:if test='<%=listType.equals(Constants.PREF_CALLLISTTYPE_OPEN) %>'><liferay-ui:message key="lbl_calls_title_open"/></c:if>
<c:if test='<%=listType.equals(Constants.PREF_CALLLISTTYPE_INDISCUSSION) %>'><liferay-ui:message key="lbl_calls_title_indiscussion"/></c:if>
<c:if test='<%=listType.equals(Constants.PREF_CALLLISTTYPE_CLOSED) %>'><liferay-ui:message key="lbl_calls_title_closed"/></c:if>
</div>
<div class="calls">
<% for(Call call : list) {%>
        <portlet:renderURL var="viewCall">
          <portlet:param name="callId" value="<%=String.valueOf(call.getCallId()) %>" />
        </portlet:renderURL>
        <% 
        long classPK = call.getCallId();
        AssetEntry curEntry = AssetEntryLocalServiceUtil.getEntry(Call.class.getName(),classPK);
        List<AssetCategory> categories = curEntry.getCategories();   
        String color = categories.size() > 0 ? CC.get(""+categories.get(0).getCategoryId()) : "";
        String catTitle = categories.size() > 0 ? categories.get(0).getTitle(locale): "";
        int countIdeaByCall = IdeaLocalServiceUtil.getIdeasByCall(call.getCallId(), -1, -1).size();
        %>
  <div class="row-fluid">
    <div class="call">
      <a href="<%=viewCall.toString()%>">
          <div class="call-card" style="border-left-color: <%=color %>;">
              <div class="call-card-header">
                <div class="span6"><span class="call-card-cat" style="color: <%=color %>;"><%=catTitle %></span></div>
                <div class="span6"><span class="call-card-date"><liferay-ui:message key="lbl_call_card_deadline" arguments="<%=dateFormatter.format(call.getDeadline()) %>"/></span></div>
              </div>
              <h4><%=call.getTitle() %></h4>
              <div class="call-card-abstract"><%=call.getDescription() %></div>
              <div class="call-card-footer">
                <div class="span12"><span class="call-card-ideas"><%=countIdeaByCall %></span></div>
              </div>
          </div>
      </a>
    </div>
  </div>
<% } %>
  
  <c:if test='<%=list.size() > 0 && !viewAll %>'>
  <div class="row-fluid call-paging">
    <div class="span12">
      <% 
      portletURL = renderResponse.createRenderURL();
      portletURL.setParameter("viewAll", "true"); 
      String showAllURL = baseUrl + "?" + HttpUtil.getQueryString(portletURL.toString());
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
