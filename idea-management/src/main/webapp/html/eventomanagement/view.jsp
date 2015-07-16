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
%>

<portlet:renderURL var="viewPrev">
	<portlet:param name="date" value="<%= prevDate %>" />
</portlet:renderURL>
<portlet:renderURL var="viewNext">
	<portlet:param name="date" value="<%= nextDate %>" />
</portlet:renderURL>

<div id="eventList" class="eventListClass">
	<label class="main"><liferay-ui:message key="evento_title_eventi" /> <%=titleData%></label>
	<input type="button" class="prevButton"  value="prevDate" 
		onClick="location.href = '<%= viewPrev %>';" />
	<input type="button" class="nextButton"  value="nextDate" 
		onClick="location.href = '<%= viewNext %>';" />
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