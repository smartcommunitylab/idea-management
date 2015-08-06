<%@page import="it.smartcommunitylab.platform.idea.portlet.Constants"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.calendar.model.CalendarBooking"%>
<%@page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ taglib uri="http://liferay.com/tld/security"
	prefix="liferay-security"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<%@include file="/html/eventomanagement/common_event_scripts.jsp" %>

<%-- <jsp:useBean id="searchEvents" class="com.liferay.portal.kernel.dao.search.SearchContainer" scope="request"/>
<jsp:useBean id="searchResources" class="com.liferay.portal.kernel.dao.search.SearchContainer" scope="request"/>
 --%>
<%-- <br/>
<liferay-ui:search-iterator searchContainer="<%= searchEvents %>" />
<br/>
<liferay-ui:search-iterator searchContainer="<%= searchResources %>" />
<br/> --%>

<%
	List<CalendarBooking> eventList = (List<CalendarBooking>) request.getAttribute("eventList");
	String titleData = (String) request.getAttribute("titleData");
	String prevDate = (String) request.getAttribute("prevDate");
	String nextDate = (String) request.getAttribute("nextDate");
	String categoryId = (String) request.getAttribute("categoryId");
	String callId = (String) request.getAttribute("callId");
	String ideaId = (String) request.getAttribute("ideaId");
	String formTitle = LanguageUtil.get(locale, "evento_title_add_eventi");
	java.text.SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", locale);
	
	String viewType = GetterUtil.getString(portletPreferences.getValue("viewType", Constants.PREF_CAL_VIEWTYPE_LIST));
%>

<%
	if(viewType.equals(Constants.PREF_CAL_VIEWTYPE_CALENDAR)){
%>

	<jsp:include page="view_calendar.jsp"/>
	
<%
	} else {
%>
<portlet:renderURL var="viewPrevURL">
	<portlet:param name="date" value="<%=prevDate%>" />
</portlet:renderURL>
<portlet:renderURL var="viewNextURL">
	<portlet:param name="date" value="<%=nextDate%>" />
</portlet:renderURL>

<div class="event-container">
	<div class="event-container-header">
		<span class="event-container-title"><liferay-ui:message
				key="evento_title_eventi" /> <%=titleData%></span>
		<div class="event-container-header-buttons">
			<c:if test='<%=Utils.eventAddEnabled(renderRequest)%>'>
				<portlet:renderURL var="addEventURL"
					windowState="<%=LiferayWindowState.POP_UP.toString()%>">
					<portlet:param name="mvcPath"
						value="/html/eventomanagement/add.jsp" />
					<portlet:param name="categoryId" value="<%=categoryId%>" />
					<portlet:param name="callId" value="<%=callId%>" />
					<portlet:param name="ideaId" value="<%=ideaId%>" />
				</portlet:renderURL>
				<a class="event-container-button event-container-button-add"
					id="add-event-link"
					title="<liferay-ui:message key="evento_add_tooltip"/>"
					onClick="javascript:window.showPopup('<%=addEventURL.toString()%>','<liferay-ui:message key="evento_title_add_eventi"/>')"><span>+</span></a>
			</c:if>
			<a class="event-container-button event-container-button-prev"
				href='<%=viewPrevURL%>'><span>&lt;</span></a> <a
				class="event-container-button event-container-button-next"
				href='<%=viewNextURL%>'><span>&gt;</span></a>
		</div>
	</div>
	<ul class="event-container-list">
		<%
			for (CalendarBooking event : eventList) {
		%>
		<li class="event-container-element"><portlet:renderURL
				var="viewEventURL"
				windowState="<%=LiferayWindowState.POP_UP.toString()%>">
				<portlet:param name="mvcPath"
					value="/html/eventomanagement/viewevent.jsp" />
				<portlet:param name="categoryId" value="<%=categoryId%>" />
				<portlet:param name="callId" value="<%=callId%>" />
				<portlet:param name="ideaId" value="<%=ideaId%>" />
				<portlet:param name="eventId"
					value="<%=String.valueOf(event.getCalendarBookingId())%>" />
			</portlet:renderURL>
			<div class="span10"
				onClick="javascript:window.showPopup('<%=viewEventURL.toString()%>','<liferay-ui:message key="evento_title_view_eventi"/>')">
				<div class="element-start">
					<%=sdf.format(new java.util.Date(event.getStartTime()))%>
				</div>
				<div class="element-title"><%=event.getTitle(locale)%></div>
				<div class="element-description"><%=event.getDescription(locale)%></div>
			</div>
			<div class="span2 element-controls">
				<c:if test='<%=Utils.eventEditEnabled(event, renderRequest)%>'>
					<portlet:renderURL var="editEventURL"
						windowState="<%=LiferayWindowState.POP_UP.toString()%>">
						<portlet:param name="mvcPath"
							value="/html/eventomanagement/edit.jsp" />
						<portlet:param name="categoryId" value="<%=categoryId%>" />
						<portlet:param name="callId" value="<%=callId%>" />
						<portlet:param name="ideaId" value="<%=ideaId%>" />
						<portlet:param name="eventId"
							value="<%=String.valueOf(event.getCalendarBookingId())%>" />
					</portlet:renderURL>
					<a
						onClick="javascript:window.showPopup('<%=editEventURL.toString()%>','<liferay-ui:message key="evento_title_edit_eventi"/>')"><i
						class="icon-pencil"></i></a>
				</c:if>
				<c:if test='<%=Utils.eventDeleteEnabled(event, renderRequest)%>'>
					<portlet:actionURL var="deleteURL" name="deleteEvent">
						<portlet:param name="eventId"
							value="<%=String.valueOf(event.getCalendarBookingId())%>" />
						<portlet:param name="categoryId" value="<%=categoryId%>" />
						<portlet:param name="callId" value="<%=callId%>" />
						<portlet:param name="ideaId" value="<%=ideaId%>" />
					</portlet:actionURL>
					<liferay-ui:icon-delete message="lbl_delete"
						url="<%=deleteURL.toString()%>" />
				</c:if>
			</div></li>
		<%
			}
		%>
		<c:if test='<%=eventList.isEmpty()%>'>
			<span class="empty-events"><liferay-ui:message
					key="lbl_noeventresults" /></span>
		</c:if>
	</ul>
</div>


<%
	}
%>

