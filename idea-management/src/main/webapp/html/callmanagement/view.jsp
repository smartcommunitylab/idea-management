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

<portlet:renderURL var="addCallUrl">
	<portlet:param name="mvcPath" value="/html/callmanagement/edit_call.jsp"/>
</portlet:renderURL>

<c:if test='<%= CallModelPermission.contains(permissionChecker, scopeGroupId, "ADD_CALL") %>'>

<aui:button-row>
	<aui:button href="<%=addCallUrl.toString() %>" value='<%= LanguageUtil.get(locale, "btn_add_call") %>'></aui:button>
</aui:button-row>

</c:if>

<div class="idea-slider row-fluid">
<% for(Call call : list) {%>
        <portlet:renderURL var="viewCall">
          <portlet:param name="REDIRECT" value="/html/callmanagement/asset/full_content.jsp" />
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
  <span class="span12">
      <a class="idea" href="<%=viewCall.toString()%>">
          <div class="thumbnail" style="border-left-color: <%=color %>;">
              <h6 class="idea-cat, pull-left" style="color: <%=color %>;"><%=catTitle %></h6>
              <p class="pull-right"><%= LanguageUtil.get(locale, "lbl_deadline") %>: <%
              out.print(dateFormatter.format(call.getDeadline()));
              %></p>
              <h4><%=call.getTitle() %></h4>
              <p><%=call.getDescription() %></p>
              <p class="pull-right"><%=countIdeaByCall %></p>
          </div>
      </a>
  </span>
<% } %>  
  <div class="row-fluid">
    <div class="span12 pull-right">
      <% 
      PortletURL portletURL = renderResponse.createRenderURL();
      portletURL.setParameter("viewAll", "true"); 
      String showAllURL = baseUrl + "?" + HttpUtil.getQueryString(portletURL.toString());
      %>
      <a href="<%=showAllURL%>"><liferay-ui:message key="lbl_showAll"/></a>
    </div>
  </div>
</div>
