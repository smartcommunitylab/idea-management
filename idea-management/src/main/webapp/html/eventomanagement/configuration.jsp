<%@include file="/html/common-init.jsp" %>
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<%
String listType_cfg = GetterUtil.getString(portletPreferences.getValue("type","list"));
%>

<aui:form action="<%= configurationURL %>" method="post" name="fm">
    <aui:input name="<%= com.liferay.portal.kernel.util.Constants.CMD %>" type="hidden" value="<%= com.liferay.portal.kernel.util.Constants.UPDATE %>" />

  <aui:select name="preferences--type--" label="lbl_listType">
    <aui:option value='<%= "list" %>' label="list" selected='<%= listType_cfg.equals("list") %>'></aui:option>
    <aui:option value='<%=  "calendar"%>' label="calendar" selected='<%= listType_cfg.equals("calendar") %>'></aui:option>
  </aui:select>

    <aui:button-row>
       <aui:button type="submit" />
    </aui:button-row>
</aui:form>