<%@include file="/html/common-init.jsp" %>
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<%
Integer elementInPage_cfg = GetterUtil.getInteger(portletPreferences.getValue("elementInPage",String.valueOf(Constants.PAGINATION_ELEMENTS_IN_PAGE)));
String listType_cfg = GetterUtil.getString(portletPreferences.getValue("listType",Constants.PREF_CALLLISTTYPE_OPEN));
%>

<aui:form action="<%= configurationURL %>" method="post" name="fm">
    <aui:input name="<%= com.liferay.portal.kernel.util.Constants.CMD %>" type="hidden" value="<%= com.liferay.portal.kernel.util.Constants.UPDATE %>" />

  <aui:select name="preferences--listType--" label="lbl_listType">
    <aui:option value="<%=Constants.PREF_CALLLISTTYPE_OPEN %>" label="lbl_calllistType_open" selected="<%= listType_cfg.equals(Constants.PREF_CALLLISTTYPE_OPEN) %>"></aui:option>
    <aui:option value="<%=Constants.PREF_CALLLISTTYPE_INDISCUSSION %>" label="lbl_calllistType_indiscussion" selected="<%= listType_cfg.equals(Constants.PREF_CALLLISTTYPE_INDISCUSSION) %>"></aui:option>
    <aui:option value="<%=Constants.PREF_CALLLISTTYPE_CLOSED %>" label="lbl_calllistType_closed" selected="<%= listType_cfg.equals(Constants.PREF_CALLLISTTYPE_CLOSED) %>"></aui:option>
  </aui:select>

	<aui:input name="preferences--elementInPage--" type="text"  label="lbl_elementInPage" value="<%= elementInPage_cfg %>" />
    
    <aui:button-row>
       <aui:button type="submit" />
    </aui:button-row>
</aui:form>