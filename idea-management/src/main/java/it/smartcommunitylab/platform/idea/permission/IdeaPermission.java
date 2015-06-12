package it.smartcommunitylab.platform.idea.permission;

import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

public class IdeaPermission {
	public static void check(PermissionChecker permissionChecker,
			long ideaId, String actionId) throws PortalException,
			SystemException {

		if (!contains(permissionChecker, ideaId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(PermissionChecker permissionChecker,
			long ideaId, String actionId) throws PortalException,
			SystemException {

		Idea idea = IdeaLocalServiceUtil.getIdea(ideaId);

		return permissionChecker.hasPermission(idea.getGroupId(),
				Idea.class.getName(), idea.getIdeaId(), actionId);

	}
}
