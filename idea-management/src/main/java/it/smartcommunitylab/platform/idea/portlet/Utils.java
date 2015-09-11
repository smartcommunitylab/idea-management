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

package it.smartcommunitylab.platform.idea.portlet;

import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;

/**
 * @author raman
 *
 */
public class Utils {

	public static List<AssetCategory> getOrderedCategories(String categoryIds,
			AssetEntry entry) throws SystemException {
		if (entry == null)
			return Collections.emptyList();
		List<AssetCategory> unorderedList = entry.getCategories();
		if (entry == null || unorderedList == null || unorderedList.isEmpty())
			return Collections.emptyList();
		if (categoryIds == null || categoryIds.isEmpty()) {
			return unorderedList;
		} else {
			long[] ids = StringUtil.split(categoryIds, 0L);
			List<AssetCategory> result = new ArrayList<AssetCategory>();
			Map<Long, AssetCategory> map = new HashMap<Long, AssetCategory>();
			for (AssetCategory ac : unorderedList)
				map.put(ac.getCategoryId(), ac);
			for (long id : ids)
				if (map.containsKey(id))
					result.add(map.get(id));
			return result;
		}
	}

	public static boolean eventAddEnabled(PortletRequest req) {
		long ideaId = ParamUtil.getLong(req, "ideaId");
		ThemeDisplay themeDisplay = (ThemeDisplay) req
				.getAttribute(WebKeys.THEME_DISPLAY);
		if (ideaId > 0) {
			// only idea owner can create idea events
			try {
				Idea idea = IdeaLocalServiceUtil.getIdea(ideaId);
				return idea.getUserId() == themeDisplay.getUserId();
			} catch (Exception e) {
				return false;
			}
		} else {
			// otherwise only site content reviewer can add events
			boolean hasRole = themeDisplay.getPermissionChecker()
					.isContentReviewer(themeDisplay.getCompanyId(),
							themeDisplay.getScopeGroupId());
			return hasRole;
		}
	}

	public static boolean eventEditEnabled(CalendarBooking event,
			PortletRequest req) {
		ThemeDisplay themeDisplay = (ThemeDisplay) req
				.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = event.getGroupId();
		try {
			// owner can modify
			Group group = GroupLocalServiceUtil.getGroup(groupId);
			if (group != null) {
				if (group.getCreatorUserId() > 0
						&& groupId != themeDisplay.getScopeGroupId()
						&& group.getCreatorUserId() == themeDisplay.getUserId()) {
					return true;
				}
				// site content reviewer can modify
				boolean hasRole = themeDisplay.getPermissionChecker()
						.isContentReviewer(themeDisplay.getCompanyId(),
								themeDisplay.getScopeGroupId());
				return hasRole;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public static boolean eventDeleteEnabled(CalendarBooking event,
			PortletRequest req) {
		return eventEditEnabled(event, req);
	}

	public static boolean ideaDeleteEnabled(Idea idea, PortletRequest req) {
		return ideaEditEnabled(idea, req);
	}

	public static boolean ideaEditEnabled(Idea idea, PortletRequest req) {
		// themeDisplay.getUser().getUserUuid().equals(idea.getUserUuid())
		ThemeDisplay themeDisplay = (ThemeDisplay) req
				.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			boolean hasRole = themeDisplay.getPermissionChecker()
					.isContentReviewer(themeDisplay.getCompanyId(),
							themeDisplay.getScopeGroupId());
			return hasRole;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean callAddEnabled(PortletRequest req) {
		// themeDisplay.getUser().getUserUuid().equals(idea.getUserUuid())
		ThemeDisplay themeDisplay = (ThemeDisplay) req
				.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			boolean hasRole = themeDisplay.getPermissionChecker()
					.isContentReviewer(themeDisplay.getCompanyId(),
							themeDisplay.getScopeGroupId());
			return hasRole;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isOfficialEvent(CalendarBooking evt,
			PortletRequest req) {
		ThemeDisplay themeDisplay = (ThemeDisplay) req
				.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			PermissionChecker checker = PermissionCheckerFactoryUtil
					.create(UserLocalServiceUtil.getUser(evt.getUserId()));
			return checker.isContentReviewer(themeDisplay.getCompanyId(),
					themeDisplay.getScopeGroupId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean callEditEnabled(Call call, PortletRequest req) {
		return callAddEnabled(req);
	}

	public static boolean callDeleteEnabled(Call call, PortletRequest req) {
		return callAddEnabled(req);
	}

	public static boolean discussionEnabled(Idea idea, PortletRequest req) {
		ThemeDisplay themeDisplay = (ThemeDisplay) req
				.getAttribute(WebKeys.THEME_DISPLAY);
		if (!themeDisplay.isSignedIn())
			return false;
		if (idea.discussionExpired())
			return false;

		// TODO check states
		return true;
	}

	public static String getBaseURL(HttpServletRequest request)
			throws PortalException, SystemException {
		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
		String baseUrl = themeDisplay.getLayout().getRegularURL(request);
		// String baseUrl =
		// HttpUtil.getProtocol((String)request.getAttribute("CURRENT_COMPLETE_URL"))+"://"+HttpUtil.getDomain((String)request.getAttribute("CURRENT_COMPLETE_URL"))+request.getAttribute("FRIENDLY_URL");
		// String baseUrl = ""+request.getAttribute("FRIENDLY_URL");
		// String baseUrl =
		// "https://"+HttpUtil.getDomain((String)request.getAttribute("CURRENT_COMPLETE_URL"))+request.getAttribute("FRIENDLY_URL");
		return baseUrl;
	}

	public static String getPageUrl(HttpServletRequest request, String pageName)
			throws SystemException, PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
		String url = "";
		if (Validator.isNotNull(pageName)) {
			for (Layout l : LayoutServiceUtil.getLayouts(
					themeDisplay.getScopeGroupId(), false)) {
				if (l.getNameCurrentValue().equalsIgnoreCase(pageName)) {
					url = l.getRegularURL(request);
					break;
				}
			}
		}
		return url;
	}

	public static String generateRenderURL(MimeResponse res, String base,
			Map<String, Object> params) throws WindowStateException {
		return generateRenderURL(res, base, params, null);
	}

	public static String generateRenderURL(MimeResponse res, String base,
			Map<String, Object> params, WindowState ws)
			throws WindowStateException {
		PortletURL url = res.createRenderURL();
		for (String param : params.keySet()) {
			url.setParameter(param, String.valueOf(params.get(param)));
		}
		if (ws != null) {
			url.setWindowState(ws);
		}
		String urlString = url.toString();

		return generateRenderURL(base, urlString);
	}

	public static String generateRenderURL(String base, String url) {
		int indexOfBase = base.indexOf("/-/");
		int indexOfUrl = url.indexOf("/-/");
		// check whether both context paths are the same
		if (indexOfBase >= 0 && indexOfUrl >= 0) {
			if (base.substring(0, indexOfBase).equals(
					url.substring(0, indexOfUrl)))
				return url;
		}
		// if derived is not friendly use the base + query string
		if (indexOfUrl < 0) {
			return base + "?" + HttpUtil.getQueryString(url);
		} else {
			// if derived is friendly and the base is friendly, replace the part
			// before /-/
			if (indexOfBase >= 0) {
				base = base.substring(0, indexOfBase);
			}
			// otherwise use base + new friendly
			return base + url.substring(indexOfUrl);
		}
	}

	public static void clearPublicRenderParameter(RenderRequest renderRequest,
			String paramName) {
		HttpSession session = PortalUtil.getHttpServletRequest(renderRequest)
				.getSession();
		@SuppressWarnings("unchecked")
		Map<Long, Map<String, Map<String, String[]>>> renderParametersPool = (Map<Long, Map<String, Map<String, String[]>>>) session
				.getAttribute("PORTLET_RENDER_PARAMETERS_");

		if (renderParametersPool != null) {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			Map<String, Map<String, String[]>> portletRenderParametersPool = renderParametersPool
					.get(themeDisplay.getLayout().getPlid());
			if (portletRenderParametersPool != null) {
				String id = themeDisplay.getPortletDisplay().getId();
				clearFromElement(paramName, portletRenderParametersPool,
						"PUBLIC_RENDER_PARAMETERS");
				clearFromElement(paramName, portletRenderParametersPool, id);
			}
		}
	}

	private static void clearFromElement(String paramName,
			Map<String, Map<String, String[]>> portletRenderParametersPool,
			String element) {
		Map<String, String[]> params = portletRenderParametersPool.get(element);
		if (params != null) {

			for (String key : new HashSet<String>(params.keySet())) {
				if (key.endsWith("_" + paramName)) {
					params.remove(key);
				}
			}
		}
	}
}
