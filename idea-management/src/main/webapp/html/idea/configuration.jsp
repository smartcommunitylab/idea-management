<%@include file="/html/common-init.jsp" %>
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<%
boolean hidePortlet_cfg = GetterUtil.getBoolean(portletPreferences.getValue("hidePortlet", StringPool.FALSE));
%>

<aui:form action="<%= configurationURL %>" method="post" name="fm">
    <aui:input name="<%= com.liferay.portal.kernel.util.Constants.CMD %>" type="hidden" value="<%= com.liferay.portal.kernel.util.Constants.UPDATE %>" />

    <aui:input name="preferences--hidePortlet--" type="checkbox" label="lbl_hidePortlet" value="<%= hidePortlet_cfg %>" />

    <aui:button-row>
       <aui:button type="submit" />
    </aui:button-row>
</aui:form>