package it.smartcommunitylab.platform.idea.asset;

import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.permission.IdeaPermission;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

public class AssetRenderer extends BaseAssetRenderer {

	private Idea idea;

	public AssetRenderer(Idea idea) {

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
		return "Name: " + idea.getTitle();
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
	public String render(RenderRequest renderRequest,
			RenderResponse renderResponse, String template) throws Exception {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			renderRequest.setAttribute("gb_idea", idea);

			return "/html/idea/" + template + ".jsp";
		} else {
			return null;
		}
	}

	private Log _log;

}
