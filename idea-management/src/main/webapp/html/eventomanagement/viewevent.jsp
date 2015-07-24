<%@page import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@page import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil"%>
<%@page import="com.liferay.portlet.asset.model.AssetEntry"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@page import="java.util.Map"%>
<%@page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@page import="com.liferay.portlet.asset.model.AssetCategory"%>
<%@page import="com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.calendar.service.CalendarBookingLocalServiceUtil"%>
<%@page import="com.liferay.calendar.model.CalendarBooking"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@page import="com.liferay.portal.kernel.util.Validator"%>

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
	
	long categoryId = ParamUtil.getLong(request, "categoryId");
	long callId = ParamUtil.getLong(request, "callId");
	long ideaId = ParamUtil.getLong(request, "ideaId");
	java.text.SimpleDateFormat dfDateStart = new SimpleDateFormat("dd MMMM yyyy", locale);
	java.text.SimpleDateFormat dfDateEnd = new SimpleDateFormat("dd MMMM yyyy", locale);
	DateFormat dfTimeStart = DateFormat.getTimeInstance(DateFormat.SHORT, locale);
	DateFormat dfTimeEnd = DateFormat.getTimeInstance(DateFormat.SHORT, locale);
	String startDate = dfDateStart.format(new Date(event.getStartTime()));
	String startTime = dfTimeStart.format(new Date(event.getStartTime()));
	String endDate = dfDateStart.format(new Date(event.getEndTime()));
	String endTime = dfTimeStart.format(new Date(event.getEndTime()));
	String location = event.getLocation();
	
	String categoryName = null;
	String categoryColor = null;
	String contextName = null;
	String contextLabel = null;
	String contextUrl = null; 
	
  Map<String,String> CC = IdeaLocalServiceUtil.getCategoryColors(scopeGroupId);
  AssetEntry calAssetEntry = AssetEntryLocalServiceUtil.getEntry(CalendarBooking.class.getName(), event.getCalendarBookingId());
  java.util.List<AssetCategory> calCategories = calAssetEntry.getCategories();
  if (Validator.isNotNull(ideaId)) {
		Idea idea = IdeaLocalServiceUtil.getIdea(ideaId);
		contextName = idea.getTitle();
		long groupId = idea.getUserGroupId();
		contextLabel = LanguageUtil.get(locale, "evento_context_idea");
	} else if (Validator.isNotNull(callId)) {
		Call call = CallLocalServiceUtil.getCall(callId);
		contextName = call.getTitle();
	  contextLabel = LanguageUtil.get(locale, "evento_context_call");
	}
  String mainCategoryColor = CC.get(""+calCategories.get(0).getCategoryId());
%>

<div class="event-view-container" style='border-left-color: <%=mainCategoryColor%>;'>
  <div class="event-view-category">
    <%  for (AssetCategory ac : calCategories) { 
    	categoryColor = CC.get(""+ac.getCategoryId());
    	categoryName = ac.getTitle(locale);
    %>
    <span style='color: <%=categoryColor%>;'><%=categoryName %></span>
    <% } %>
  </div>
  <c:if test='<%=Validator.isNotNull(contextName)%>'>
  	<div class="event-view-context"><%=contextName%></div>
  </c:if>
  <div class="event-view-header">
    <span class="event-view-title"><%=event.getTitle(locale)%></span>
  </div>
  <div class="event-view-location"><liferay-ui:message key="evento_view_location" />: <%=Validator.isNotNull(location) ? location : "" %></div>
  <div class="event-view-date"><liferay-ui:message key="evento_view_from" />: <%=startDate%> <%=startTime%> </div>    
  <div class="event-view-date"><liferay-ui:message key="evento_view_to" />: <%=endDate%> <%=endTime%> </div>
  <div class="event-view-description"><liferay-ui:message key="evento_view_desc" />: <%=event.getDescription(locale)%></div>
  <div>
  <aui:button-row  cssClass="formbutton-row">
    <aui:button cssClass="formbutton-cancel" type="cancel" onClick="Liferay.Util.getWindow().hide();" value="Close"></aui:button>
    <%-- <aui:button cssClass="formbutton-primary" type="button" value="Vedi idea"></aui:button> --%>
  </aui:button-row>
  </div>
</div>