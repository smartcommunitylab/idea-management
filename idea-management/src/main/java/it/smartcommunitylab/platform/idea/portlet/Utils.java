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

import java.util.HashSet;
import java.util.Map;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

/**
 * @author raman
 *
 */
public class Utils {

	public static String getBaseURL(HttpServletRequest request) {
		String baseUrl = HttpUtil.getProtocol((String)request.getAttribute("CURRENT_COMPLETE_URL"))+"://"+HttpUtil.getDomain((String)request.getAttribute("CURRENT_COMPLETE_URL"))+request.getAttribute("FRIENDLY_URL");
		return baseUrl;
	}
	
	public static String generateRenderURL(RenderResponse res,  String base, Map<String,Object> params) throws WindowStateException {
		return generateRenderURL(res, base, params, null);
	}
	
	public static String generateRenderURL(RenderResponse res,  String base, Map<String,Object> params, WindowState ws) throws WindowStateException {
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
			if (base.substring(0,indexOfBase).equals(url.substring(0, indexOfUrl))) return url;
		} 
		// if derived is not friendly use the base + query string
		if (indexOfUrl < 0) {
			return base + "?" + HttpUtil.getQueryString(url);
		} else {
			// if derived is friendly and the base is friendly, replace the part before /-/
			if (indexOfBase >= 0) {
				base = base.substring(0,indexOfBase);
			}
			// otherwise use base + new friendly
			return base + url.substring(indexOfUrl);
		}
	}
	
	public static void clearPublicRenderParameter(RenderRequest renderRequest, String paramName) {
		HttpSession session = PortalUtil.getHttpServletRequest(renderRequest).getSession();
		@SuppressWarnings("unchecked")
		Map<Long, Map<String, Map<String, String[]>>> renderParametersPool =
				(Map<Long, Map<String, Map<String, String[]>>>)session.getAttribute("PORTLET_RENDER_PARAMETERS_");

	   if (renderParametersPool != null) {
		   ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	     Map<String, Map<String, String[]>> portletRenderParametersPool = renderParametersPool.get(themeDisplay.getLayout().getPlid());
	     if (portletRenderParametersPool != null) {
	    	 String id = themeDisplay.getPortletDisplay().getId();
	    	 clearFromElement(paramName, portletRenderParametersPool,"PUBLIC_RENDER_PARAMETERS");
	    	 clearFromElement(paramName, portletRenderParametersPool,id);
	     }
	   }
	}

	private static void clearFromElement(String paramName,
			Map<String, Map<String, String[]>> portletRenderParametersPool, String element) {
		Map<String, String[]> params = portletRenderParametersPool.get(element);
		 if (params != null) {
			 
			 for (String key : new HashSet<String>(params.keySet())) {
				 if (key.endsWith("_"+paramName)) {
					 params.remove(key);
				 }
			 }
		 }
	}
}
