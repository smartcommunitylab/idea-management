package it.smartcommunitylab.platform.idea.asset;

import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.permission.CallPermission;
import it.smartcommunitylab.platform.idea.portlet.Constants;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

public class CallAssetRenderer extends BaseAssetRenderer {

	private Call call;

	public CallAssetRenderer(Call idea) {

		this.call = idea;
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {

		long callId = call.getCallId();

		boolean contains = false;

		try {
			contains = CallPermission.contains(permissionChecker, callId,
					ActionKeys.UPDATE);
		} catch (PortalException pe) {
			_log.error(pe.getLocalizedMessage());
		} catch (SystemException se) {
			_log.error(se.getLocalizedMessage());
		}

		return contains;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {

		long callId = call.getCallId();

		boolean contains = false;

		try {
			contains = CallPermission.contains(permissionChecker, callId,
					ActionKeys.VIEW);
		} catch (PortalException pe) {
			_log.error(pe.getLocalizedMessage());
		} catch (SystemException se) {
			_log.error(se.getLocalizedMessage());
		}

		return contains;
	}

	@Override
	public String getClassName() {
		return Call.class.getName();
	}

	@Override
	public long getClassPK() {
		return call.getCallId();
	}

	@Override
	public long getGroupId() {
		return call.getGroupId();
	}

	@Override
	public String getSummary(Locale locale) {
		return "";
	}

	@Override
	public String getTitle(Locale locale) {
		return call.getTitle();
	}

	@Override
	public long getUserId() {

		return call.getUserId();
	}

	@Override
	public String getUserName() {
		return call.getUserName();
	}

	@Override
	public String getUuid() {
		return call.getUuid();
	}

	@Override
	public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse) throws Exception {
		return null;
	}

	@Override
	public PortletURL getURLView(LiferayPortletResponse liferayPortletResponse,
			WindowState windowState) throws Exception {
		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
				Constants.CALL_PORTLET_ID, PortletRequest.RENDER_PHASE);
		portletURL.setParameter("mvcPath",
				"/html/callmanagement/asset/full_content.jsp");
		portletURL.setWindowState(windowState);
		portletURL.setParameter("callId", String.valueOf(call.getCallId()));
		return portletURL;
	}

	@Override
	public String getURLViewInContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			String noSuchEntryRedirect) throws Exception {
		return "call/-/call/-/" + call.getCallId() + "/view";
	}

	@Override
	public String getDiscussionPath() {
		return "edit_entry_discussion";
	}
	
	@Override
	public String render(RenderRequest renderRequest,
			RenderResponse renderResponse, String template) throws Exception {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			renderRequest.setAttribute("call", call);
			return "/html/callmanagement/asset/" + template + ".jsp";
		} else {
			return null;
		}
	}

	private Log _log;

}
