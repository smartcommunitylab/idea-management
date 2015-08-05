<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>

<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.calendar.model.CalendarBooking"%>
<%@page import="java.util.List"%>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<%
	List<CalendarBooking> eventList = (List<CalendarBooking>) request.getAttribute("eventList");

	String baseUrl = Utils.getBaseURL(request);  

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
		stringBuf.append(String.format("end:'%s',", dateFormatter.format(cal.getTime())));
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("eventId", String.valueOf(event.getCalendarBookingId()));
		params.put("mvcPath", "/html/eventomanagement/viewevent.jsp");
		String detailURL = Utils.generateRenderURL(renderResponse, baseUrl, params, LiferayWindowState.POP_UP);
		
		stringBuf.append(String.format("detailURL:'%s'", detailURL));
		stringBuf.append("},");
	}
	stringBuf.append("]");
	
	String events = stringBuf.toString();
%>





<p><%=events%></p>
<div id="calendar"></div>

<script type="text/javascript">
$(document).ready(function() {

	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			//right: 'month,basicWeek,basicDay'
		},
		defaultDate: new Date(),
		editable: false,
		eventLimit: true, // allow "more" link when too many events
        firstDay:1,
        eventClick: function(calEvent, jsEvent, view) {
            javascript:window.showPopup(calEvent.detailURL,'<liferay-ui:message key="evento_title_view_eventi"/>')
        },
		events: <%=events%>
	});

});
</script>

<aui:script>
	Liferay.provide(window, 'showPopup', function(url, title) {
		var instance = this;

		Liferay.Util.openWindow({
			cache : false,
			dialog : {
				destroyOnClose : true,
				align : Liferay.Util.Window.ALIGN_CENTER,
				width : 550,
				height : 800,
				cssClass : 'event-ui-dialog',
				constrain2view : true,
				modal : true
			},
			dialogIframe : {
				id : 'showEventFormIframe',
				uri : url
			},
			title : title,
			uri : url
		});
	}, [ 'liferay-util-window' ]);

	Liferay.provide(window, 'refreshPortlet', function() {
		var curPortlet = '#p_p_id<portlet:namespace/>';
		Liferay.Portlet.refresh(curPortlet);
	}, [ 'aui-dialog', 'aui-dialog-iframe' ]);
</aui:script>