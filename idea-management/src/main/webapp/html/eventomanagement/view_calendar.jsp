<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.calendar.model.CalendarBooking"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<%
	List<CalendarBooking> eventList = (List<CalendarBooking>) request.getAttribute("eventList");

	StringBuffer stringBuf = new StringBuffer();
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	stringBuf.append("[");
	for(CalendarBooking event : eventList) {
		stringBuf.append("{");
		stringBuf.append(String.format("title:\"%s\",",event.getTitle(locale)));
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(event.getStartTime());
		stringBuf.append(String.format("start:'%s',", dateFormatter.format(cal.getTime())));
		cal = Calendar.getInstance();
		cal.setTimeInMillis(event.getEndTime());
		stringBuf.append(String.format("end:'%s'", dateFormatter.format(cal.getTime())));
		stringBuf.append("},");
	}
	stringBuf.append("]");
	
	String events = stringBuf.toString();
%>





<p><%=events%></p>
<div id="calendar"></div>

<script type="text/javascript">
$(document).ready(function() {

    // page is now ready, initialize the calendar...

	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			//right: 'month,basicWeek,basicDay'
		},
		defaultDate: '2015-02-12',
		editable: false,
		eventLimit: true, // allow "more" link when too many events
        firstDay:1,
        eventClick: function(calEvent, jsEvent, view) {

        alert('Event: ' + calEvent.title);
        alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
        alert('View: ' + view.name);

        // change the border color just for fun
        $(this).css('border-color', 'red');

        },
		events: <%=events%>
	});

});
</script>