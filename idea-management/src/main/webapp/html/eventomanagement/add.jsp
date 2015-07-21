<%@page import="com.liferay.portal.kernel.util.Validator"%>
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

<%
	String categoryId = (String) request.getParameter("categoryId");
	if(Validator.isNull(categoryId)) {
		categoryId = (String) request.getAttribute("categoryId");
	}
	
	String callId = (String) request.getParameter("callId");
	if(Validator.isNull(callId)) {
		callId = (String) request.getAttribute("callId");
	}
	
	String ideaId = (String) request.getParameter("ideaId");
	if(Validator.isNull(ideaId)) {
		ideaId = (String) request.getAttribute("ideaId");
	}
%>

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
		<portlet:actionURL name="addEvent" var="addEventURL" />
		<portlet:renderURL var="redirectURL">
			<portlet:param name="mvcPath" value="/html/eventomanagement/add.jsp" />
		</portlet:renderURL>
		<aui:form cssClass="idea-form" id="addEvent" name="addEvent"
			action="<%=addEventURL%>" method="post">
			<aui:fieldset cssClass="simple-field">
        <aui:input placeholder='<%=LanguageUtil.get(locale, "evento_form_title") %>' label="" name="title"></aui:input>
      </aui:fieldset>

		  <aui:field-wrapper>
		    <aui:input resizable="true" placeholder='<%=LanguageUtil.get(locale, "evento_form_desc") %>' name="description" type="textarea" label=""></aui:input>  
		  </aui:field-wrapper>
			
        <%
        GregorianCalendar cal = new GregorianCalendar(locale);
        int initDay = cal.get(Calendar.DAY_OF_MONTH);
        int initMonth = cal.get(Calendar.MONTH); 
        int initYear = cal.get(Calendar.YEAR);
        int initHour = cal.get(Calendar.HOUR_OF_DAY);
        int initMinute = cal.get(Calendar.MINUTE);
        int ampm = 0;
        %>
      <div class="row-fluid">
        <div class="span6"> 
					<aui:fieldset label="evento_form_startDate">
						<liferay-ui:input-date name="startDate" dayParam="sdday" monthParam="sdmonth" yearParam="sdyear" 
							dayValue="<%=initDay%>" monthValue="<%=initMonth%>" yearValue="<%=initYear%>"></liferay-ui:input-date>
						<liferay-ui:input-time name="startTime" hourParam="sdhour" minuteParam="sdmin" amPmParam="sampm" 
							hourValue="<%=initHour%>" minuteValue="<%=initMinute%>" amPmValue="<%=ampm%>"></liferay-ui:input-time>
					</aui:fieldset>
        </div>
        <div class="span6"> 
					<aui:fieldset label="evento_form_endDate">		
						<liferay-ui:input-date name="endDate" dayParam="edday" monthParam="edmonth" yearParam="edyear" 
							dayValue="<%=initDay%>" monthValue="<%=initMonth%>" yearValue="<%=initYear%>"></liferay-ui:input-date>
						<liferay-ui:input-time name="endTime" hourParam="edhour" minuteParam="edmin" amPmParam="eampm" 
							hourValue="<%=initHour%>" minuteValue="<%=initMinute%>" amPmValue="<%=ampm%>"></liferay-ui:input-time>
		      </aui:fieldset>
		    </div>
		  </div>    
			<aui:input name="categoryId" value="<%=categoryId%>" type="hidden" />
			<aui:input name="callId" value="<%=callId%>" type="hidden" />
			<aui:input name="ideaId" value="<%=ideaId%>" type="hidden" />
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
			id: '<portlet:namespace/>addEvent',
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
					body:'',
				  custom: false,
				  fieldName: '<portlet:namespace/>endTime',
					validatorName:'required'
				}				
			]
		});
	}
);
</aui:script>