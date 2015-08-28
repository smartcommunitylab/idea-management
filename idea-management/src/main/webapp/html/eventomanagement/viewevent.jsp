<%@page import="com.liferay.util.portlet.PortletProps"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQuery"%>
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
<%@page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>

<%@ include file="/html/common-init.jsp" %>

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
	
  //category
	String categoryName = null;
	String categoryColor = null;
	String contextName = null;
	String contextLabel = null;
	String contextType = null;
	
  Map<String,String> CC = IdeaLocalServiceUtil.getCategoryColors(scopeGroupId);
  AssetEntry calAssetEntry = AssetEntryLocalServiceUtil.getEntry(CalendarBooking.class.getName(), event.getCalendarBookingId());
  java.util.List<AssetCategory> calCategories = calAssetEntry.getCategories();
  if (Validator.isNotNull(ideaId)) {
		Idea idea = IdeaLocalServiceUtil.getIdea(ideaId);
		contextName = idea.getTitle();
		contextLabel = LanguageUtil.get(locale, "evento_context_idea");
	  contextType = LanguageUtil.get(locale, "evento_context_type_idea");
		//redirectUrl = portalUrl + "/web" + siteUrl + layoutUrl + "/-/idea/" + ideaId + "/view";
	} else if (Validator.isNotNull(callId)) {
		Call call = CallLocalServiceUtil.getCall(callId);
		contextName = call.getTitle();
	  contextLabel = LanguageUtil.get(locale, "evento_context_call");
	  contextType = LanguageUtil.get(locale, "evento_context_type_call");
	  //redirectUrl = portalUrl + "/web" + siteUrl + layoutUrl + "/-/call/" + callId + "/view";
	}
  String mainCategoryColor = (calCategories != null && calCategories.size() > 0) ? CC.get(""+calCategories.get(0).getCategoryId()) : "";
  
  //redirect url
  String portalUrl = themeDisplay.getPortalURL();
  String layoutUrl = layout.getFriendlyURL();
  String siteUrl = layout.getGroup().getFriendlyURL();
	String redirectUrl = null;
	
	long userGroupId = event.getGroupId();
	DynamicQuery dynamicQueryIdea = IdeaLocalServiceUtil.dynamicQuery();
	dynamicQueryIdea.add(RestrictionsFactoryUtil.eq("userGroupId", userGroupId));
	List<Idea> ideaList = (List<Idea>) IdeaLocalServiceUtil.dynamicQuery(dynamicQueryIdea);
	if((ideaList != null) && (ideaList.size() > 0)) {
		String redirectPage = PortletProps.get("detail.page");
		redirectUrl = portalUrl + "/web" + siteUrl + "/" + redirectPage + "/-/idea/-/" 
			+ ideaList.get(0).getIdeaId() + "/view";
        contextLabel = LanguageUtil.get(locale, "evento_context_idea");
        contextType = LanguageUtil.get(locale, "evento_context_type_idea");
        contextName = ideaList.get(0).getTitle();
	} else {
		DynamicQuery dynamicQueryCall = CallLocalServiceUtil.dynamicQuery();
		dynamicQueryCall.add(RestrictionsFactoryUtil.eq("userGroupId", userGroupId));
		List<Call> callList = (List<Call>) CallLocalServiceUtil.dynamicQuery(dynamicQueryCall);
		if((callList != null) && (callList.size() > 0)) {
			String redirectPage = PortletProps.get("call.page");
			redirectUrl = portalUrl + "/web" + siteUrl + "/" + redirectPage + "/-/call/-/" 
				+ callList.get(0).getCallId() + "/view";
            contextLabel = LanguageUtil.get(locale, "evento_context_call");
            contextType = LanguageUtil.get(locale, "evento_context_type_call");
            contextName = callList.get(0).getTitle();
		}
	}
	
	boolean isOfficial = Utils.isOfficialEvent(event, renderRequest);
%>

<div class="event-view-container" style='border-left-color: <%=mainCategoryColor%>;'>
  <div class="event-view-container-body">
  <div class="event-view-category">
    <%  for (AssetCategory ac : calCategories) { 
    	categoryColor = CC.get(""+ac.getCategoryId());
    	categoryName = ac.getTitle(locale);
    %>
    <span style='color: <%=categoryColor%>;'><%=categoryName %></span>
    <% } %>
  </div>
  <c:if test='<%=Validator.isNotNull(contextName)%>'>
  	<div class="event-view-context"><span><liferay-ui:message key="<%= contextType %>"/>: </span><%=contextName%></div>
  </c:if>
  <div class="event-view-header">
   <c:if test='<%=isOfficial %>'>
     <div class="event-view-official"><liferay-ui:message key="lbl_event_official"/></div>
   </c:if>
    <div class="event-view-title"><%=event.getTitle(locale)%></div>
  </div>
  <c:if test='<%= Validator.isNotNull(location) %>'>
    <div class="event-view-location"><liferay-ui:message key="evento_view_location" />: <%=location %></div>
  </c:if>
  <div class="event-view-date"><liferay-ui:message key="evento_view_from" />: <%=startDate%> <%=startTime%> </div>    
  <div class="event-view-date"><liferay-ui:message key="evento_view_to" />: <%=endDate%> <%=endTime%> </div>
  <div class="event-view-description"><%=event.getDescription(locale)%></div>
  </div>
  <aui:button-row  cssClass="formbutton-row">
    <aui:button cssClass="formbutton-cancel" type="cancel" onClick="Liferay.Util.getWindow().hide();" value="lbl_close"></aui:button>
    <%-- <aui:button cssClass="formbutton-primary" type="button" value="Vedi idea"></aui:button> --%>
    <c:if test="<%= Validator.isNotNull(redirectUrl)%>">
      <aui:button cssClass="formbutton-primary" onClick="javascript:window.redirectPortlet();" 
       value='<%= contextLabel %>'></aui:button>
    </c:if>
  </aui:button-row>
</div>

<aui:script>
Liferay.provide(window, 'redirectPortlet', 
	function() {
		var url = '<%= redirectUrl %>';
		Liferay.Util.getOpener().changeContextPortlet(url);
	},
	['aui-dialog','aui-dialog-iframe']
);

window.onload = function() {
	 document.getElementsByClassName('event-view-container-body')[0].style['min-height'] = (window.innerHeight - 113)+'px';
};  

</aui:script>