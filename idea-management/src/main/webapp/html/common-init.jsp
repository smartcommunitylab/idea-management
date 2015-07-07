<%@page import="java.text.DateFormat"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="theme" %>
<%@ taglib prefix="liferay-portlet" uri="http://liferay.com/tld/portlet" %>

<%@ page import="java.util.Locale"%>
<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portal.util.PortalUtil"%>
<%@ page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>

<%@ page import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil"%>
<%@ page import="com.liferay.portlet.asset.model.AssetEntry"%>
<%@ page import="com.liferay.portlet.asset.service.AssetTagLocalServiceUtil"%>
<%@ page import="com.liferay.portlet.asset.model.AssetTag"%>

<%@ page import="it.smartcommunitylab.platform.idea.portlet.Constants"%>



<portlet:defineObjects />
<theme:defineObjects/>

<%
DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, locale);
%>