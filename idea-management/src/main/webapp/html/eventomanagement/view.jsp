<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.calendar.model.CalendarBooking"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<%-- <jsp:useBean id="searchEvents" class="com.liferay.portal.kernel.dao.search.SearchContainer" scope="request"/>
<jsp:useBean id="searchResources" class="com.liferay.portal.kernel.dao.search.SearchContainer" scope="request"/>
 --%>
<%-- <br/>
<liferay-ui:search-iterator searchContainer="<%= searchEvents %>" />
<br/>
<liferay-ui:search-iterator searchContainer="<%= searchResources %>" />
<br/> --%>

<%
	List<Map<String, String>> eventList = (List<Map<String, String>>) request.getAttribute("eventList");
	String titleData = (String) request.getAttribute("titleData");
	String prevDate = (String) request.getAttribute("prevDate");
	String nextDate = (String) request.getAttribute("nextDate");
	String categoryId = (String) request.getAttribute("categoryId");
	String callId = (String) request.getAttribute("callId");
	String ideaId = (String) request.getAttribute("ideaId");
	String formTitle = LanguageUtil.get(locale, "evento_title_add_eventi");
%>

<portlet:renderURL var="addEventURL" windowState="<%=LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcPath" value="/html/eventomanagement/add.jsp" />
	<portlet:param name="categoryId" value="<%= categoryId %>" />
	<portlet:param name="callId" value="<%= callId %>" />
	<portlet:param name="ideaId" value="<%= ideaId %>" />
</portlet:renderURL>
<portlet:renderURL var="viewPrevURL">
	<portlet:param name="date" value="<%= prevDate %>" />
</portlet:renderURL>
<portlet:renderURL var="viewNextURL">
	<portlet:param name="date" value="<%= nextDate %>" />
</portlet:renderURL>

<div class="event-container">
  <div class="event-container-header">
    <span class="event-container-title"><liferay-ui:message key="evento_title_eventi" /> <%=titleData%></span>
    <div class="event-container-header-buttons">
    <a class="event-container-button event-container-button-add" id="add-event-link"
    	onClick="javascript:window.showAddEventForm()"><span>+</span></a>
    <a class="event-container-button event-container-button-prev" href='<%= viewPrevURL %>'><span>&lt;</span></a>
    <a class="event-container-button event-container-button-next" href='<%= viewNextURL %>'><span>&gt;</span></a>
    </div>
  </div>
	<ul class="event-container-list">
		<%
			for (Map<String, String> event : eventList) {
		%>
		<li  class="event-container-element">
			<div class="element-start"><%=event.get("startDate")%></div>
			<div class="element-title"><%=event.get("title")%></div>
			<div class="element-description"><%=event.get("description")%></div>
		</li>
		<%
			}
		%>
	</ul>
</div>

<aui:script>
	Liferay.provide(
		window,
		'showAddEventForm',
		function() {
			var instance = this;

			var url='<%= addEventURL.toString() %>';

				Liferay.Util.openWindow(
					{
						cache: false,
						dialog: {
							align: Liferay.Util.Window.ALIGN_CENTER,
							after: {
								render: function(event) {
									this.set('y', this.get('y') + 50);
								}
							},
							width: 820
						},
						dialogIframe: {
							id: 'showAddEventFormIframe',
							uri: url
						},
						title: '<%= formTitle.toString() %>',
						uri: url
					}
				);
		},
		['liferay-util-window']
	);
	</aui:script>