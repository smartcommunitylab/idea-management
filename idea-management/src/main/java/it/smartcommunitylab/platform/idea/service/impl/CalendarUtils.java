/*******************************************************************************
 * Copyright 2015 Fondazione Bruno Kessler
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 ******************************************************************************/

package it.smartcommunitylab.platform.idea.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.service.ServiceContext;

/**
 * @author raman
 *
 */
public class CalendarUtils {

	public static Calendar createCalendar(long groupId, long userId, String title, ServiceContext serviceContext, long classNameId, long classPK, String classUuid) throws PortalException, SystemException {
		Calendar c = findCalendar(groupId);
		if (c == null) {
			Map<Locale, String> nameMap = new HashMap<Locale, String>();
			nameMap.put(serviceContext.getLocale(), title);
			nameMap.put(LocaleUtil.getDefault(), title);
			CalendarResource calendarResource = CalendarResourceLocalServiceUtil.addCalendarResource(userId, groupId, 
					classNameId, classPK, classUuid, 
					"", nameMap, null, true, serviceContext);
			long calendarResourceId = calendarResource.getCalendarResourceId();
			return CalendarLocalServiceUtil.addCalendar(userId, groupId, calendarResourceId, nameMap, null, 0, false, false, false, serviceContext);
		}
		return c;
	}
	
	public static void deleteCalendar(long groupId) throws SystemException, PortalException {
		Calendar c = findCalendar(groupId);
		if (c != null) {
//			CalendarResourceLocalServiceUtil.deleteCalendarResource(c.getCalendarResourceId());
			CalendarLocalServiceUtil.deleteCalendar(c.getCalendarId());
		}
	}
	
	public static Calendar findCalendar(long groupId) throws SystemException {
		DynamicQuery dynamicQuery = CalendarLocalServiceUtil.dynamicQuery();
		Criterion criterionGroup = RestrictionsFactoryUtil.eq("groupId", groupId);
		dynamicQuery.add(criterionGroup);
		@SuppressWarnings("unchecked")
		List<Calendar> calendarList = CalendarLocalServiceUtil.dynamicQuery(dynamicQuery);
		if (calendarList != null && ! calendarList.isEmpty()) return calendarList.get(0);
		return null;
	}
}
