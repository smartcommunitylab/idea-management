package it.smartcommunitylab.platform.idea.asset;

import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.permission.IdeaPermission;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

public class IdeaAssetRendererFactory extends BaseAssetRendererFactory {
	public static final String CLASS_NAME = Idea.class.getName();

	public static final String TYPE = "idea";

	@Override
	public IdeaAssetRenderer getAssetRenderer(long classPK, int type)
			throws PortalException, SystemException {

		Idea idea = IdeaLocalServiceUtil.getIdea(classPK);

		return new IdeaAssetRenderer(idea);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean hasPermission(PermissionChecker permissionChecker,
			long classPK, String actionId) throws Exception {

		return IdeaPermission.contains(permissionChecker, classPK, actionId);
	}

	@Override
	public boolean isLinkable() {
		return _LINKABLE;
	}

	private static final boolean _LINKABLE = true;

}
