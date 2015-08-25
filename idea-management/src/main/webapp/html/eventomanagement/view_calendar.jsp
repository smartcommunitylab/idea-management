<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.calendar.model.CalendarBooking"%>
<%@page import="java.util.List"%>
<%@ page import="javax.portlet.WindowState" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%@ include file="/html/common-init.jsp" %>

<%@include file="/html/eventomanagement/common_event_scripts.jsp" %>

<%
dateFormatter = new SimpleDateFormat("yyyy-MM-dd",locale);
String activeDate = ParamUtil.getString(renderRequest, "date", dateFormatter.format(new Date()));
%>


<portlet:resourceURL var="calendarAjaxUrl">
</portlet:resourceURL>

<div id="calendar"></div>

<script type="text/javascript">
$(document).ready(function() {

	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,basicWeek,basicDay'
		},
		defaultDate: '<%= activeDate%>',
		lang: 'it',
        timeFormat: 'H:mm',
		editable: false,
		eventLimit: true, // allow "more" link when too many events
        firstDay:1,
        eventClick: function(calEvent, jsEvent, view) {
            javascript:window.showPopup(calEvent.detailURL,'<liferay-ui:message key="evento_title_view_eventi"/>')
        },
		events: '<%= calendarAjaxUrl %>',
		startParam: '<portlet:namespace/>start',
		endParam: '<portlet:namespace/>end'
	});
    
});
</script>