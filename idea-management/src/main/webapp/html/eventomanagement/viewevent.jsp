<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.calendar.service.CalendarBookingLocalServiceUtil"%>
<%@page import="com.liferay.calendar.model.CalendarBooking"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<%
	long eventId = ParamUtil.getLong(request, "eventId");
	CalendarBooking event = CalendarBookingLocalServiceUtil.getCalendarBooking(eventId);
	
	String titleData = (String) request.getAttribute("titleData");
	String prevDate = (String) request.getAttribute("prevDate");
	String nextDate = (String) request.getAttribute("nextDate");
	String categoryId = (String) request.getAttribute("categoryId");
	String callId = (String) request.getAttribute("callId");
	String ideaId = (String) request.getAttribute("ideaId");
	java.text.SimpleDateFormat dfDate = new SimpleDateFormat("dd MMMM yyyy", locale);
	DateFormat dfTime =DateFormat.getTimeInstance(DateFormat.SHORT, locale);
	String startDate = dfDate.format(new Date(event.getStartTime()));
	String startTime = dfTime.format(new Date(event.getStartTime()));
%>

<div class="event-view-container">
  <div class="event-view-category">Sostenibilita'</div>
  <div class="event-view-context">Idea 1</div>
  <div class="event-view-header">
    <span class="event-view-title"><%=event.getTitle(locale)%></span>
    <span class="event-viewd-date"><%=startDate%></span>    
  </div>
  <div class="event-view-time"><liferay-ui:message key="evento_form_time" />: <%=startTime%></div>
  <div class="event-view-description"><%=event.getDescription(locale)%></div>
  <div>
  <aui:button-row  cssClass="formbutton-row">
    <aui:button cssClass="formbutton-cancel" type="cancel" onClick="Liferay.Util.getWindow().hide();" value="Close"></aui:button>
    <aui:button cssClass="formbutton-primary" type="button" value="Vedi idea"></aui:button>
  </aui:button-row>
  </div>
</div>