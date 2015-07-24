<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.calendar.service.CalendarBookingLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.calendar.model.CalendarBooking"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<portlet:defineObjects />
<liferay-theme:defineObjects />


<c:choose>
	<c:when test='<%=SessionMessages.contains(renderRequest, "requestProcessed")%>'>
		<aui:script>
			Liferay.Util.getOpener().refreshPortlet();
			// Mandatory to close the dialog
			Liferay.Util.getWindow().hide();
		</aui:script>
	</c:when>
	<c:otherwise>
		<liferay-ui:error key="evento_form_check_date_error" message="evento_form_check_date_error"></liferay-ui:error>	
		<%
			long eventId = ParamUtil.getLong(request, "eventId");
			if(Validator.isNull(eventId)) {
				eventId = (Long) request.getAttribute("eventId");
			}
			CalendarBooking event = CalendarBookingLocalServiceUtil.getCalendarBooking(eventId);
			String title = event.getTitle(locale);
			String description = event.getDescription(locale);
			String location = event.getLocation();
		%>
		<portlet:actionURL name="updateEvent" var="updateEventURL" />
		<portlet:renderURL var="redirectURL">
			<portlet:param name="mvcPath" value="/html/eventomanagement/edit.jsp" />
		</portlet:renderURL>
		<aui:form cssClass="idea-form" id="updateEvent" name="updateEvent"
			action="<%=updateEventURL%>" method="post">
			<aui:fieldset cssClass="simple-field">
        <aui:input placeholder='<%=LanguageUtil.get(locale, "evento_form_title") %>' label="" name="title" 
        value="<%=title%>"></aui:input>
      </aui:fieldset>

		  <aui:field-wrapper>
		    <aui:input resizable="true" placeholder='<%=LanguageUtil.get(locale, "evento_form_desc") %>' name="description" 
		    type="textarea" label="" value="<%=description%>"></aui:input>  
		  </aui:field-wrapper>
			
		  <aui:field-wrapper>
		    <aui:input resizable="true" placeholder='<%=LanguageUtil.get(locale, "evento_form_location") %>' name="location" 
		    type="textarea" label="" value="<%=Validator.isNotNull(location) ? location : \"\" %>"></aui:input>  
		  </aui:field-wrapper>
		  
        <%
        GregorianCalendar startCal = new GregorianCalendar(locale);
        startCal.setTimeInMillis(event.getStartTime());
        int startDay = startCal.get(Calendar.DAY_OF_MONTH);
        int startMonth = startCal.get(Calendar.MONTH); 
        int startYear = startCal.get(Calendar.YEAR);
        int startHour = startCal.get(Calendar.HOUR_OF_DAY);
        int startMinute = startCal.get(Calendar.MINUTE);
        int startAmPm = 0;
        
        GregorianCalendar endCal = new GregorianCalendar(locale);
        endCal.setTimeInMillis(event.getEndTime());
        int endDay = endCal.get(Calendar.DAY_OF_MONTH);
        int endMonth = endCal.get(Calendar.MONTH); 
        int endYear = endCal.get(Calendar.YEAR);
        int endHour = endCal.get(Calendar.HOUR_OF_DAY);
        int endMinute = endCal.get(Calendar.MINUTE);
        int endAmPm = 0;
        %>
      <div class="row-fluid">
        <div class="span6"> 
					<aui:fieldset label="evento_form_startDate">
						<liferay-ui:input-date name="startDate" dayParam="sdday" monthParam="sdmonth" yearParam="sdyear" 
							dayValue="<%=startDay%>" monthValue="<%=startMonth%>" yearValue="<%=startYear%>"></liferay-ui:input-date>
						<liferay-ui:input-time name="startTime" hourParam="sdhour" minuteParam="sdmin" amPmParam="sampm" 
							hourValue="<%=startHour%>" minuteValue="<%=startMinute%>" amPmValue="<%=startAmPm%>"></liferay-ui:input-time>
					</aui:fieldset>
        </div>
        <div class="span6"> 
					<aui:fieldset label="evento_form_endDate">		
						<liferay-ui:input-date name="endDate" dayParam="edday" monthParam="edmonth" yearParam="edyear" 
							dayValue="<%=endDay%>" monthValue="<%=endMonth%>" yearValue="<%=endYear%>"></liferay-ui:input-date>
						<liferay-ui:input-time name="endTime" hourParam="edhour" minuteParam="edmin" amPmParam="eampm" 
							hourValue="<%=endHour%>" minuteValue="<%=endMinute%>" amPmValue="<%=endAmPm%>"></liferay-ui:input-time>
		      </aui:fieldset>
		    </div>
		  </div>    
			<aui:input name="eventId" value="<%=eventId%>" type="hidden" />
			<aui:input name="redirect" value="<%=redirectURL%>" type="hidden" />
			<aui:button-row  cssClass="formbutton-row">
		    <aui:button cssClass="formbutton-cancel" type="cancel" onClick="Liferay.Util.getWindow().hide();"></aui:button>
		    <aui:button cssClass="formbutton-primary" type="submit"></aui:button>
			</aui:button-row>
		</aui:form>	
	</c:otherwise>
</c:choose>

<aui:script>
AUI().use('aui-base','liferay-form','aui-form-validator',
	function(A) {
		Liferay.Form.register( {
			id: '<portlet:namespace/>updateEvent',
			fieldRules: [
				{
	   	  	body:'',
	        custom: false,
	        fieldName: '<portlet:namespace/>title',
		      validatorName:'required'
				},
				{
			  	body:'',
			    custom: false,
			    fieldName: '<portlet:namespace/>description',
				  validatorName:'required'
				},
				{
					body:'',
				  custom: false,
				  fieldName: '<portlet:namespace/>startDate',
					validatorName:'required'
				},				
				{
					body:'',
				  custom: false,
				  fieldName: '<portlet:namespace/>startTime',
					validatorName:'required'
				},				
				{
					body:'',
				  custom: false,
				  fieldName: '<portlet:namespace/>endDate',
					validatorName:'required'
				},				
				{
					body:function (val, fieldNode, ruleValue) {
						//alert(val + "-" + fieldNode + "-" + ruleValue);
						var startDateString = document.getElementsByName('<portlet:namespace/>startDate')[0].value;
						var startTimeString = document.getElementsByName('<portlet:namespace/>startTime')[0].value;
						var endDateString = document.getElementsByName('<portlet:namespace/>endDate')[0].value;
						var endTimeString = document.getElementsByName('<portlet:namespace/>endTime')[0].value;
						var startDate;
						var endDate;
						if((startTimeString.indexOf("AM") > -1) || (startTimeString.indexOf("PM") > -1) ||
								(endTimeString.indexOf("AM") > -1) || (endTimeString.indexOf("PM") > -1)) {
							//alert("usa:" + startDateString + "-" + startTimeString + "-" + endDateString + "-" + endTimeString);
							
							var dateNumbers = startDateString.match(/\d+/g);
							var timeNumbers = startTimeString.match(/\d+/g);
							if(startTimeString.indexOf("PM") > -1) {
								timeNumbers[0] = parseInt(timeNumbers[0]) + 12; 
							}
							//alert("numbers:" + dateNumbers + "-" + timeNumbers);
							startDate = new Date(dateNumbers[2], dateNumbers[0]-1, dateNumbers[1], timeNumbers[0], timeNumbers[1], 00);
							
							dateNumbers = endDateString.match(/\d+/g);
							timeNumbers = endTimeString.match(/\d+/g);
							if(endTimeString.indexOf("PM") > -1) {
								timeNumbers[0] = parseInt(timeNumbers[0]) + 12;
							}
							//alert("numbers:" + dateNumbers + "-" + timeNumbers);
							endDate = new Date(dateNumbers[2], dateNumbers[0]-1, dateNumbers[1], timeNumbers[0], timeNumbers[1], 00);
						} else {
							//alert("ita:" + startDateString + "-" + startTimeString + "-" + endDateString + "-" + endTimeString);
							
							var dateNumbers = startDateString.match(/\d+/g);
							var timeNumbers = startTimeString.match(/\d+/g);
							startDate = new Date(dateNumbers[2], dateNumbers[1]-1, dateNumbers[0], timeNumbers[0], timeNumbers[1], 00);
							
							dateNumbers = endDateString.match(/\d+/g);
							timeNumbers = endTimeString.match(/\d+/g);
							endDate = new Date(dateNumbers[2], dateNumbers[1]-1, dateNumbers[0], timeNumbers[0], timeNumbers[1], 00);
						}
						//alert(startDate + " - " + endDate);
						//alert(startDate.getTime() + " - " + endDate.getTime());
						if(startDate.getTime() >= endDate.getTime()) {
			      	//alert("Start date cannot be greater than end date");
			        return false;
						}						
            return true;
					},
					custom: true,
					errorMessage: '<liferay-ui:message key="evento_form_check_date_error"/>',
					fieldName: '<portlet:namespace/>endTime',
					validatorName:'customTimeValidator'
				}				
			]
		});
	}
);
</aui:script>