package it.smartcommunitylab.platform.evento.portlet;

import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarImporterLocalServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class EventoManagementPortlet
 */
public class EventoManagementPortlet extends MVCPortlet {
	private static SimpleDateFormat sdfQueryDate = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);		Date queryDate = null;
		//String languageId = themeDisplay.getLanguageId();
		Locale locale = themeDisplay.getLocale();
		
		String queryDateString = ParamUtil.getString(renderRequest, "date");
		if(isNull(queryDateString)) {
			queryDate = new Date();
		} else {
			try {
				queryDate = sdfQueryDate.parse(queryDateString);
			} catch (ParseException e) {
				queryDate = new Date();
			}
		}
		//next date
		Calendar nextDateCalendar = GregorianCalendar.getInstance();
		nextDateCalendar.setTime(queryDate);
		nextDateCalendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		nextDateCalendar.clear(Calendar.MINUTE);
		nextDateCalendar.clear(Calendar.SECOND);
		nextDateCalendar.clear(Calendar.MILLISECOND);
		nextDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
		nextDateCalendar.add(Calendar.MONTH, 1);
		String nextDate = sdfQueryDate.format(nextDateCalendar.getTime());
		System.out.println("nextDate:" + nextDate);
		renderRequest.setAttribute("nextDate", nextDate);
		//previous date
		Calendar prevDateCalendar = GregorianCalendar.getInstance();
		prevDateCalendar.setTime(queryDate);
		prevDateCalendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		prevDateCalendar.clear(Calendar.MINUTE);
		prevDateCalendar.clear(Calendar.SECOND);
		prevDateCalendar.clear(Calendar.MILLISECOND);
		prevDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
		prevDateCalendar.add(Calendar.MONTH, -1);
		String prevDate = sdfQueryDate.format(prevDateCalendar.getTime());
		System.out.println("prevDate:" + prevDate);
		renderRequest.setAttribute("prevDate", prevDate);
		
		//title data
		SimpleDateFormat sdfTitleDate = new SimpleDateFormat("MMMM yy", locale);
		String titleData = sdfTitleDate.format(queryDate);
		renderRequest.setAttribute("titleData", titleData);
		
		long categoryId = ParamUtil.getLong(renderRequest, "categoryId");
		long ideaId = ParamUtil.getLong(renderRequest, "ideaId");
		long callId = ParamUtil.getLong(renderRequest, "callId");
//		if(groupId == 0) {
//			groupId = themeDisplay.getScopeGroupId();
//			System.out.println("groupId:" + groupId);
//		}
		List<CalendarBooking> eventList = new ArrayList<CalendarBooking>();
		if(ideaId > 0) {
			try {
				eventList = getEventsByIdeaId(queryDate, ideaId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (callId > 0) {
			try {
				eventList = getEventsByCallId(queryDate, callId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(categoryId > 0) {
			try {
				eventList = getEventsByCategoryId(queryDate, categoryId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				eventList = getEventsByDate(queryDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		getSearchIteratorData(renderRequest, renderResponse, eventList);
		
		//events as list of map attribute
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", locale);
		List<Map<String, String>> eventMapList = new ArrayList<Map<String, String>>();
		for(CalendarBooking event : eventList) {
			Map<String, String> eventMap = new HashMap<String, String>();
			eventMap.put("title", event.getTitle(locale));
			eventMap.put("description", event.getDescription(locale));
			eventMap.put("startDate", sdf.format(new Date(event.getStartTime())));
			eventMapList.add(eventMap);
		}
		renderRequest.setAttribute("eventList", eventMapList);
		super.doView(renderRequest, renderResponse);
	}

	@SuppressWarnings("unchecked")
	public List<CalendarBooking> getEventsByCallId(Date queryDate, long callId) throws SystemException, PortalException {
		List<CalendarBooking> eventList;
		Call call = CallLocalServiceUtil.getCall(callId);
		long groupId = call.getUserGroupId();
		DynamicQuery dynamicQuery = CalendarBookingLocalServiceUtil.dynamicQuery();
		Criterion criterionGroup = RestrictionsFactoryUtil.eq("groupId", groupId);
		Criterion criterionStatus = RestrictionsFactoryUtil.eq("status", WorkflowConstants.STATUS_APPROVED);
		Long[] range = getTimeInterval(queryDate);
		Criterion criterionTime = RestrictionsFactoryUtil.between("startTime", range[0], range[1]);
		dynamicQuery.add(RestrictionsFactoryUtil.and(criterionTime, RestrictionsFactoryUtil.and(criterionGroup, criterionStatus)));
		dynamicQuery.addOrder(OrderFactoryUtil.asc("startTime"));
		dynamicQuery.setLimit(0, 5);
		eventList = (List<CalendarBooking>)CalendarBookingLocalServiceUtil.dynamicQuery(dynamicQuery);
		System.out.println("event count:" + eventList.size());
		return eventList;
	}

	@SuppressWarnings("unchecked")
	public List<CalendarBooking> getEventsByDate(Date queryDate) throws SystemException {
		List<CalendarBooking> eventList;
		DynamicQuery dynamicQuery = CalendarBookingLocalServiceUtil.dynamicQuery();
		Criterion criterionStatus = RestrictionsFactoryUtil.eq("status", WorkflowConstants.STATUS_APPROVED);
		Long[] range = getTimeInterval(queryDate);
		Criterion criterionTime = RestrictionsFactoryUtil.between("startTime", range[0], range[1]);
		dynamicQuery.add(RestrictionsFactoryUtil.and(criterionTime, criterionStatus));
		dynamicQuery.addOrder(OrderFactoryUtil.asc("startTime"));
		dynamicQuery.setLimit(0, 5);
		eventList = (List<CalendarBooking>)CalendarBookingLocalServiceUtil.dynamicQuery(dynamicQuery);
		System.out.println("event count:" + eventList.size());
		return eventList;
	}

	@SuppressWarnings("unchecked")
	public List<CalendarBooking> getEventsByCategoryId(Date queryDate, long categoryId) throws SystemException, PortalException {
		List<CalendarBooking> eventList = new ArrayList<CalendarBooking>();
		DynamicQuery dynamicQuery = CalendarBookingLocalServiceUtil.dynamicQuery();
		Criterion criterionStatus = RestrictionsFactoryUtil.eq("status", WorkflowConstants.STATUS_APPROVED);
		//Criterion criterionIds = RestrictionsFactoryUtil.in("calendarBookingId", ids);
		Long[] range = getTimeInterval(queryDate);
		Criterion criterionTime = RestrictionsFactoryUtil.between("startTime", range[0], range[1]);
		dynamicQuery.add(RestrictionsFactoryUtil.and(criterionTime, criterionStatus));
		dynamicQuery.addOrder(OrderFactoryUtil.asc("startTime"));
		dynamicQuery.setLimit(0, 100);
		List<CalendarBooking> queryEventList = (List<CalendarBooking>)CalendarBookingLocalServiceUtil.dynamicQuery(dynamicQuery);
		
		int count = 0;
		for(CalendarBooking event : queryEventList) {
			AssetEntry entry = AssetEntryLocalServiceUtil.getEntry(CalendarBooking.class.getName(), event.getCalendarBookingId());
			long[] categoryIds = entry.getCategoryIds();
			if(ArrayUtil.contains(categoryIds, categoryId)) {
				eventList.add(event);
				count++;
			}
			if(count >= 5) {
				break;
			}
		}
		System.out.println("event count:" + eventList.size());
		return eventList;
	}
	
	@SuppressWarnings("unchecked")
	public List<CalendarBooking> getEventsByIdeaId(Date queryDate, long ideaId) throws SystemException, PortalException {
		List<CalendarBooking> eventList;
		Idea idea = IdeaLocalServiceUtil.getIdea(ideaId);
		long groupId = idea.getUserGroupId();
		DynamicQuery dynamicQuery = CalendarBookingLocalServiceUtil.dynamicQuery();
		Criterion criterionGroup = RestrictionsFactoryUtil.eq("groupId", groupId);
		Criterion criterionStatus = RestrictionsFactoryUtil.eq("status", WorkflowConstants.STATUS_APPROVED);
		Long[] range = getTimeInterval(queryDate);
		Criterion criterionTime = RestrictionsFactoryUtil.between("startTime", range[0], range[1]);
		dynamicQuery.add(RestrictionsFactoryUtil.and(criterionTime, RestrictionsFactoryUtil.and(criterionGroup, criterionStatus)));
		dynamicQuery.addOrder(OrderFactoryUtil.asc("startTime"));
		dynamicQuery.setLimit(0, 5);
		eventList = (List<CalendarBooking>)CalendarBookingLocalServiceUtil.dynamicQuery(dynamicQuery);
		System.out.println("event count:" + eventList.size());
		return eventList;
	}

	private static Long[] getTimeInterval(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy mm:ss");
		
		Calendar calendarStart = GregorianCalendar.getInstance();
		calendarStart.setTime(date);
		calendarStart.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		calendarStart.clear(Calendar.MINUTE);
		calendarStart.clear(Calendar.SECOND);
		calendarStart.clear(Calendar.MILLISECOND);
		calendarStart.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(sdf.format(calendarStart.getTime()));
		
		Calendar calendarEnd = GregorianCalendar.getInstance();
		calendarEnd.setTime(date);
		calendarEnd.set(Calendar.HOUR_OF_DAY, 23); // ! clear would not reset the hour of day !
		calendarEnd.set(Calendar.MINUTE, 59);
		calendarEnd.set(Calendar.SECOND, 59);
		calendarEnd.clear(Calendar.MILLISECOND);
		calendarEnd.set(Calendar.DAY_OF_MONTH, calendarStart.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println(sdf.format(calendarEnd.getTime()));
		
		Long[] result = new Long[2];
		result[0] = calendarStart.getTimeInMillis();
		result[1] = calendarEnd.getTimeInMillis();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public void addEvent(ActionRequest request, ActionResponse response) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		User user = themeDisplay.getUser();
		long userId = user.getUserId();
		long groupId = 0;
		long categoryId = ParamUtil.getLong(request, "categoryId");
		long ideaId = ParamUtil.getLong(request, "ideaId");
		long callId = ParamUtil.getLong(request, "callId");
		long[] categoryIds = null;
		
		//set groupId and categoryIds
		if(categoryId > 0) {
			groupId = themeDisplay.getScopeGroupId();
			categoryIds = new long[] {categoryId};
		} else if(ideaId > 0) {
			Idea idea = IdeaLocalServiceUtil.getIdea(ideaId);
			groupId = idea.getUserGroupId();
			AssetEntry entry = AssetEntryLocalServiceUtil.getEntry(Idea.class.getName(), idea.getIdeaId());
			categoryIds = entry.getCategoryIds();
		} else if(callId > 0) {
			Call call = CallLocalServiceUtil.getCall(callId);
			groupId = call.getUserGroupId();
			AssetEntry entry = AssetEntryLocalServiceUtil.getEntry(Call.class.getName(), call.getCallId());
			categoryIds = entry.getCategoryIds();
		}
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(CalendarBooking.class.getName(), request);
		serviceContext.setScopeGroupId(groupId);
		//get calendar by groupId
		DynamicQuery dynamicQuery = CalendarLocalServiceUtil.dynamicQuery();
		Criterion criterionGroup = RestrictionsFactoryUtil.eq("groupId", groupId);
		dynamicQuery.add(criterionGroup);
		List<com.liferay.calendar.model.Calendar> calendarList = CalendarLocalServiceUtil.dynamicQuery(dynamicQuery);
		if(!calendarList.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
			Date actualTime = new Date();
			Map<Locale, String> titleMap = new HashMap<>();
			titleMap.put(locale, "test " + sdf.format(actualTime));
			Map<Locale, String> descriptionMap = new HashMap<>();
			descriptionMap.put(locale, "test");
			com.liferay.calendar.model.Calendar calendar = calendarList.get(0);
			CalendarBooking event = CalendarBookingLocalServiceUtil.addCalendarBooking(userId, calendar.getCalendarId(), 
					new long[] {}, 0L, titleMap, descriptionMap, null, actualTime.getTime(), (actualTime.getTime() + 1000*60*60), 
					false, null, 0L, null, 0L, null, serviceContext);
			System.out.println("new event id:" + event.getCalendarBookingId());
			AssetEntryLocalServiceUtil.updateEntry(userId, groupId, CalendarBooking.class.getName(), event.getCalendarBookingId(), 
					categoryIds, new String[] {});
		}
		response.setRenderParameter("jspPage", "/html/eventomanagement/add.jsp");
	}
	
	private boolean isNull(String string) {
		if((string == null) || (string.isEmpty()))
			return true;
		return false;
	}

	@SuppressWarnings("unused")
	private void getSearchIteratorData(RenderRequest renderRequest, RenderResponse renderResponse,
			List<CalendarBooking> eventList) {
		//search-iterator tag
		List<String> headersEvent = new ArrayList<String>();
		headersEvent.add("title");
		headersEvent.add("description");
		headersEvent.add("status");
		headersEvent.add("calendar-name");
		headersEvent.add("calendar-id");
		headersEvent.add("calendar-resource-id");
		headersEvent.add("start-date");
		headersEvent.add("end-date");
		headersEvent.add("group-id");
		headersEvent.add("user-name");
		PortletURL portletURL = renderResponse.createRenderURL();
		SearchContainer<CalendarBooking> searchEvents = new SearchContainer<CalendarBooking>(renderRequest, null, null,
				SearchContainer.DEFAULT_CUR_PARAM, 5, portletURL, headersEvent, "Empty resultset");
		searchEvents.setTotal(eventList.size());
		
		List<String> headersResource = new ArrayList<String>();
		headersResource.add("resource-id");
		headersResource.add("class-name-id");
		headersResource.add("class-name");
		headersResource.add("class-uuid");
		headersResource.add("code");
		headersResource.add("name");
		headersResource.add("description");
		SearchContainer<CalendarResource> searchResources = new SearchContainer<CalendarResource>(renderRequest, null, null,
				SearchContainer.DEFAULT_CUR_PARAM, 5, portletURL, headersResource, "Empty resultset");
		searchResources.setTotal(eventList.size());

		for (int i = searchEvents.getStart(); i < searchEvents.getEnd() && i < searchEvents.getTotal(); i++) {
			CalendarBooking event = eventList.get(i);
			try {
				ResultRow rowEvent = new ResultRow(event, event.getCalendarBookingId(), i, false);
				rowEvent.addText(event.getTitle("en_US"));
				rowEvent.addText(event.getDescription("en_US"));
				rowEvent.addText(Integer.toString(event.getStatus()));
				rowEvent.addText(event.getCalendar().getName());
				rowEvent.addText(Long.toString(event.getCalendar().getCalendarId()));
				rowEvent.addText(Long.toString(event.getCalendar().getCalendarResourceId()));
				rowEvent.addDate(new Date(event.getStartTime()));
				rowEvent.addDate(new Date(event.getEndTime()));
				rowEvent.addText(Long.toString(event.getGroupId()));
				rowEvent.addText(event.getUserName());
				searchEvents.getResultRows().add(rowEvent);
				
				CalendarResource resource = event.getCalendarResource();
				ResultRow rowResource = new ResultRow(resource, resource.getCalendarResourceId(), i, false);
				rowResource.addText(Long.toString(resource.getCalendarResourceId()));
				rowResource.addText(Long.toString(resource.getClassNameId()));
				rowResource.addText(resource.getClassName());
				rowResource.addText(resource.getClassUuid());
				rowResource.addText(resource.getCode());
				rowResource.addText(resource.getName("en_US"));
				rowResource.addText(resource.getDescription("en_US"));
				searchResources.getResultRows().add(rowResource);
			} catch (PortalException e) {
				e.printStackTrace();
			} catch (SystemException e) {
				e.printStackTrace();
			}
		}
		renderRequest.setAttribute("searchEvents", searchEvents);
		renderRequest.setAttribute("searchResources", searchResources);
	}
	
	public static void main(String[] args) {
		Date actulaDate = new Date();
		EventoManagementPortlet.getTimeInterval(actulaDate);
		//next date
		Calendar nextDateCalendar = GregorianCalendar.getInstance();
		nextDateCalendar.setTime(actulaDate);
		nextDateCalendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		nextDateCalendar.clear(Calendar.MINUTE);
		nextDateCalendar.clear(Calendar.SECOND);
		nextDateCalendar.clear(Calendar.MILLISECOND);
		nextDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
		nextDateCalendar.add(Calendar.MONTH, 1);
		String nextDate = sdfQueryDate.format(nextDateCalendar.getTime());
		System.out.println("nextDate:" + nextDate);
		//previous date
		Calendar prevDateCalendar = GregorianCalendar.getInstance();
		prevDateCalendar.setTime(actulaDate);
		prevDateCalendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		prevDateCalendar.clear(Calendar.MINUTE);
		prevDateCalendar.clear(Calendar.SECOND);
		prevDateCalendar.clear(Calendar.MILLISECOND);
		prevDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
		prevDateCalendar.add(Calendar.MONTH, -1);
		String prevDate = sdfQueryDate.format(prevDateCalendar.getTime());
		System.out.println("prevDate:" + prevDate);
		
	}
}
