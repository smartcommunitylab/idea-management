<%@page import="com.sun.corba.se.impl.orbutil.closure.Constant"%>
<%@include file="/html/common-init.jsp" %>
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<%
String viewType_cfg = GetterUtil.getString(portletPreferences.getValue("viewType",Constants.PREF_VIEWTYPE_SIMPLE));
boolean hidePortlet_cfg = GetterUtil.getBoolean(portletPreferences.getValue("hidePortlet", StringPool.FALSE));
boolean hideAddIdea_cfg = GetterUtil.getBoolean(portletPreferences.getValue("hideAddIdea", StringPool.FALSE));
boolean hideFilters_cfg = GetterUtil.getBoolean(portletPreferences.getValue("hideFilters", StringPool.FALSE));
boolean activatePagination_cfg = GetterUtil.getBoolean(portletPreferences.getValue("activatePagination", StringPool.TRUE));
Integer elementInPage_cfg = GetterUtil.getInteger(portletPreferences.getValue("elementInPage",String.valueOf(Constants.PAGINATION_ELEMENTS_IN_PAGE)));
%>

<script type="text/javascript">

function <portlet:namespace />disableText() {
		var t = document.getElementById('<portlet:namespace />elementInPage');
		t.disabled = !t.disabled;
}

</script>


<aui:form action="<%= configurationURL %>" method="post" name="fm">
    <aui:input name="<%= com.liferay.portal.kernel.util.Constants.CMD %>" type="hidden" value="<%= com.liferay.portal.kernel.util.Constants.UPDATE %>" />

	<aui:select name="preferences--viewType--" label="lbl_viewType">
		<aui:option value="<%=Constants.PREF_VIEWTYPE_SIMPLE %>" label="lbl_viewType_simple" selected="<%= viewType_cfg.equals(Constants.PREF_VIEWTYPE_SIMPLE) %>"></aui:option>
		<aui:option value="<%=Constants.PREF_VIEWTYPE_COMPLEX %>" label="lbl_viewType_complex" selected="<%= viewType_cfg.equals(Constants.PREF_VIEWTYPE_COMPLEX) %>"></aui:option>
	</aui:select>

    <aui:input name="preferences--hidePortlet--" type="checkbox" label="lbl_hidePortlet" value="<%= hidePortlet_cfg %>" />
    
    <aui:input name="preferences--hideAddIdea--" type="checkbox" label="lbl_hideIdeaButton" value="<%= hideAddIdea_cfg %>" />
    
    <aui:input name="preferences--hideFilters--" type="checkbox" label="lbl_hideFilters" value="<%= hideFilters_cfg %>" />
    
    <aui:input name="preferences--activatePagination--" type="checkbox" label="lbl_activatePagination" value="<%= activatePagination_cfg %>" onChange="_86_disableText();"/>

	<aui:input disabled="<%=!activatePagination_cfg %>" name="preferences--elementInPage--" type="text" label="lbl_elementInPage" value="<%= elementInPage_cfg %>" />
    <aui:button-row>
       <aui:button type="submit" />
    </aui:button-row>
</aui:form>