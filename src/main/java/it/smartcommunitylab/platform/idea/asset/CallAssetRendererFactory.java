package it.smartcommunitylab.platform.idea.asset;

import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.permission.CallPermission;
import it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

public class CallAssetRendererFactory extends BaseAssetRendererFactory {
	public static final String CLASS_NAME = Call.class.getName();

	public static final String TYPE = "call";

	@Override
	public CallAssetRenderer getAssetRenderer(long classPK, int type)
			throws PortalException, SystemException {

		Call call = CallLocalServiceUtil.getCall(classPK);

		return new CallAssetRenderer(call);
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

		return CallPermission.contains(permissionChecker, classPK, actionId);
	}

	@Override
	public boolean isLinkable() {
		return _LINKABLE;
	}

	private static final boolean _LINKABLE = true;

}
