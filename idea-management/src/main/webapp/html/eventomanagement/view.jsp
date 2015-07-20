<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.calendar.model.CalendarBooking"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<portlet:defineObjects />

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
%>

<portlet:actionURL name="addEvent" var="addEventURL">
	<portlet:param name="categoryid" value="<%= categoryId %>" />
	<portlet:param name="callid" value="<%= callId %>" />
	<portlet:param name="ideaid" value="<%= ideaId %>" />
</portlet:actionURL>
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
    <a  class="event-container-button event-container-button-add" href = '<%= addEventURL %>'><span>+</span></a>
    <a  class="event-container-button event-container-button-prev" href = '<%= viewPrevURL %>'><span>&lt;</span></a>
    <a  class="event-container-button event-container-button-next" href = '<%= viewNextURL %>'><span>&gt;</span></a>
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