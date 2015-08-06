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
	List<CalendarBooking> eventList = (List<CalendarBooking>) request.getAttribute("eventList");
    dateFormatter = new SimpleDateFormat("yyyy-MM-dd",locale);
    
    String activeDate = ParamUtil.getString(renderRequest, "date", dateFormatter.format(new Date()));

	String baseUrl = Utils.getBaseURL(request); 
    Map<String,Object> params = new HashMap<String,Object>();
    params.put("date", (String) request.getAttribute("prevDate"));
	String prevURL = Utils.generateRenderURL(renderResponse, baseUrl, params, WindowState.MAXIMIZED); 
    
    params.put("date", (String) request.getAttribute("nextDate"));
    String nextURL = Utils.generateRenderURL(renderResponse, baseUrl, params, WindowState.MAXIMIZED);
    
    params.put("date", dateFormatter.format(new Date()));
    String todayURL = Utils.generateRenderURL(renderResponse, baseUrl, params, WindowState.MAXIMIZED);
    

	StringBuffer stringBuf = new StringBuffer();
	dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	stringBuf.append("[");
	for(CalendarBooking event : eventList) {
		stringBuf.append("{");
		stringBuf.append(String.format("title:\"%s\",",event.getTitle(locale)));
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(event.getStartTime());
		stringBuf.append(String.format("start:'%s',", dateFormatter.format(cal.getTime())));
		cal = Calendar.getInstance();
		cal.setTimeInMillis(event.getEndTime());
		stringBuf.append(String.format("end:'%s',", dateFormatter.format(cal.getTime())));
		
		params = new HashMap<String,Object>();
		params.put("eventId", String.valueOf(event.getCalendarBookingId()));
		params.put("mvcPath", "/html/eventomanagement/viewevent.jsp");
		String detailURL = Utils.generateRenderURL(renderResponse, baseUrl, params, LiferayWindowState.POP_UP);
		
		stringBuf.append(String.format("detailURL:'%s'", detailURL));
		stringBuf.append("},");
	}
	stringBuf.append("]");
	
	String events = stringBuf.toString();
%>

<div id="calendar"></div>

<script type="text/javascript">
$(document).ready(function() {

	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: ''
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
		events: <%=events%>
	});
    
    // hook to override behaviour of month selector buttons
    $('.fc-prev-button').unbind('click');
    $('.fc-next-button').unbind('click');
    $('.fc-today-button').unbind('click');
    
	$('.fc-prev-button').click(function(e) {
        e.preventDefault();
        window.location = '<%= prevURL %>';
    });
    $('.fc-next-button').click(function(e) {
        e.preventDefault();
        window.location.href = '<%= nextURL %>';
    });
    
    $('.fc-today-button').click(function(e) {
        e.preventDefault();
        window.location.href = '<%= todayURL %>';
    });

});
</script>