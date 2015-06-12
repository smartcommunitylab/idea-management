package it.smartcommunitylab.platform.idea.permission;

import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

public class CallPermission {
	public static void check(PermissionChecker permissionChecker, long callId,
			String actionId) throws PortalException, SystemException {

		if (!contains(permissionChecker, callId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(PermissionChecker permissionChecker,
			long callId, String actionId) throws PortalException,
			SystemException {

		Call call = CallLocalServiceUtil.getCall(callId);

		return permissionChecker.hasPermission(call.getGroupId(),
				Call.class.getName(), call.getCallId(), actionId);

	}
}
