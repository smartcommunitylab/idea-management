<%@include file="/html/common-init.jsp" %>
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<%
String viewType_cfg = GetterUtil.getString(portletPreferences.getValue("viewType","list"));
%>

<aui:form action="<%= configurationURL %>" method="post" name="fm">
    <aui:input name="<%= com.liferay.portal.kernel.util.Constants.CMD %>" type="hidden" value="<%= com.liferay.portal.kernel.util.Constants.UPDATE %>" />

  <aui:select name="preferences--viewType--" label="lbl_viewType">
    <aui:option value='<%= Constants.PREF_CAL_VIEWTYPE_LIST %>' label="lbl_cal_viewtype_list" selected='<%= viewType_cfg.equals(Constants.PREF_CAL_VIEWTYPE_LIST) %>'></aui:option>
    <aui:option value='<%=  Constants.PREF_CAL_VIEWTYPE_CALENDAR%>' label="lbl_cal_viewtype_calendar" selected='<%= viewType_cfg.equals(Constants.PREF_CAL_VIEWTYPE_CALENDAR) %>'></aui:option>
  </aui:select>

    <aui:button-row>
       <aui:button type="submit" />
    </aui:button-row>
</aui:form>