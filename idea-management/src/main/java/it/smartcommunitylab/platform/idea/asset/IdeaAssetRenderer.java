package it.smartcommunitylab.platform.idea.asset;

import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.permission.IdeaPermission;
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
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

public class IdeaAssetRenderer extends BaseAssetRenderer {

	private Idea idea;

	public IdeaAssetRenderer(Idea idea) {

		this.idea = idea;
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {

		long ideaId = idea.getIdeaId();

		boolean contains = false;

		try {
			contains = IdeaPermission.contains(permissionChecker, ideaId,
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

		long ideaId = idea.getIdeaId();

		boolean contains = false;

		try {
			contains = IdeaPermission.contains(permissionChecker, ideaId,
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
		return Idea.class.getName();
	}

	@Override
	public long getClassPK() {
		return idea.getIdeaId();
	}

	@Override
	public long getGroupId() {
		return idea.getGroupId();
	}

	@Override
	public String getSummary(Locale locale) {
		return idea.getShortDesc() != null ? HtmlUtil.stripHtml(idea
				.getShortDesc()) : idea.getTitle();
	}

	@Override
	public String getTitle(Locale locale) {
		return idea.getTitle();
	}

	@Override
	public long getUserId() {

		return idea.getUserId();
	}

	@Override
	public String getUserName() {
		return idea.getUserName();
	}

	@Override
	public String getUuid() {
		return idea.getUuid();
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
				Constants.IDEA_PORTLET_ID, PortletRequest.RENDER_PHASE);
		portletURL.setParameter("mvcPath", "/html/idea/asset/full_content.jsp");
		// portletURL.setWindowState(WindowState.MAXIMIZED);
		portletURL.setParameter("ideaId", String.valueOf(idea.getIdeaId()));
		return portletURL;
	}

	@Override
	public String getURLViewInContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			String noSuchEntryRedirect) throws Exception {
		return "detail/-/idea/-/" + idea.getIdeaId() + "/view";
	}

	@Override
	public String getDiscussionPath() {
		return "edit_entry_discussion";
	}

	@Override
	public String render(RenderRequest renderRequest,
			RenderResponse renderResponse, String template) throws Exception {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			renderRequest.setAttribute("idea", idea);
			return "/html/idea/asset/" + template + ".jsp";
		} else {
			return null;
		}
	}

	private Log _log;

}
