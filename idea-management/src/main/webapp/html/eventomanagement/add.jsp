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
	String callId = (String) request.getParameter("callId");
	String ideaId = (String) request.getParameter("ideaId");
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
		<portlet:actionURL name="addEvent" var="addEventURL" />
		<portlet:renderURL var="redirectURL">
			<portlet:param name="mvcPath" value="/html/eventomanagement/add.jsp" />
		</portlet:renderURL>
		<aui:form cssClass="filter-panel" id="addEvent" name="addEvent"
			action="<%=addEventURL%>" method="post">
			<aui:fieldset>
				<aui:input name="title" label='<%=LanguageUtil.get(locale, "evento_form_title")%>'
					placeholder='<%=LanguageUtil.get(locale, "evento_form_title")%>'/>
				<aui:input name="description" label='<%=LanguageUtil.get(locale, "evento_form_desc")%>'
					placeholder='<%=LanguageUtil.get(locale, "evento_form_desc")%>'/>
				<%
				GregorianCalendar cal = new GregorianCalendar();
		    int initDay = cal.get(Calendar.DAY_OF_MONTH);
		    int initMonth = cal.get(Calendar.MONTH); 
		    int initYear = cal.get(Calendar.YEAR);
		    int initHour = cal.get(Calendar.HOUR_OF_DAY);
		    int initMinute = cal.get(Calendar.MINUTE);
		    int ampm = 0;
		    if(initHour >= 12) {
		    	ampm = 1;
		    }
				%>
				<liferay-ui:message key="evento_form_startDate" />			
				<liferay-ui:input-date name="startDate" dayParam="sdday" monthParam="sdmonth" yearParam="sdyear" 
					dayValue="<%=initDay%>" monthValue="<%=initMonth%>" yearValue="<%=initYear%>"></liferay-ui:input-date>
				<liferay-ui:input-time name="startTime" hourParam="sdhour" minuteParam="sdmin" amPmParam="sampm" 
					hourValue="<%=initHour%>" minuteValue="<%=initMinute%>" amPmValue="<%=ampm%>"></liferay-ui:input-time>
				<liferay-ui:message key="evento_form_endDate" />
				<liferay-ui:input-date name="endDate" dayParam="edday" monthParam="edmonth" yearParam="edyear" 
					dayValue="<%=initDay%>" monthValue="<%=initMonth%>" yearValue="<%=initYear%>"></liferay-ui:input-date>
				<liferay-ui:input-time name="endTime" hourParam="edhour" minuteParam="edmin" amPmParam="eampm" 
					hourValue="<%=initHour%>" minuteValue="<%=initMinute%>" amPmValue="<%=ampm%>"></liferay-ui:input-time>
				<aui:input name="categoryId" value="<%=categoryId%>" type="hidden" />
				<aui:input name="callId" value="<%=callId%>" type="hidden" />
				<aui:input name="ideaId" value="<%=ideaId%>" type="hidden" />
				<aui:input name="redirect" value="<%=redirectURL%>" type="hidden" />
				<aui:button-row>
					<aui:button type="submit" />
				</aui:button-row>
			</aui:fieldset>
		</aui:form>	
	</c:otherwise>
</c:choose>