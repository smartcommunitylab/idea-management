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

<div id="eventList" class="eventListClass">
	<label class="main"><liferay-ui:message key="evento_title_eventi" /> <%=titleData%></label>
	<input type="button" class="prevButton"  value="prevDate" 
		onClick="location.href = '<%= viewPrevURL %>';" />
	<input type="button" class="nextButton"  value="nextDate" 
		onClick="location.href = '<%= viewNextURL %>';" />
	<input type="button" class="addButton" value="add"
		onClick="location.href = '<%= addEventURL %>';" />
	<ul>
		<%
			for (Map<String, String> event : eventList) {
		%>
		<li>
			<div class="startDate"><%=event.get("startDate")%></div>
			<div class="title"><%=event.get("title")%></div>
			<div class="description"><%=event.get("description")%></div>
		</li>
		<%
			}
		%>
	</ul>
</div>