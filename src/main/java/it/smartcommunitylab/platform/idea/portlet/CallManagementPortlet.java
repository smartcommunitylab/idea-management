package it.smartcommunitylab.platform.idea.portlet;

import it.smartcommunitylab.platform.idea.beans.CallBean;
import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil;

import java.text.DateFormat;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class CallManagementPortlet
 */
public class CallManagementPortlet extends MVCPortlet {

	public void addEntry(ActionRequest req, ActionResponse res)
			throws SystemException, PortalException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Call.class.getName(), req);

		String title = ParamUtil.getString(req, "title");
		String desc = ParamUtil.getString(req, "desc");
		Date deadline = ParamUtil.getDate(req, "deadline",
				DateFormat.getDateInstance());

		CallBean bean = new CallBean();
		bean.setTitle(title);
		bean.setDescription(desc);
		bean.setDeadline(deadline);

		CallLocalServiceUtil.createCall(serviceContext.getUserId(), bean,
				serviceContext);

	}

	public void updateEntry(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Call.class.getName(), req);

		long id = ParamUtil.getLong(req, "callId");
		String title = ParamUtil.getString(req, "title");
		String desc = ParamUtil.getString(req, "desc");
		Date deadline = ParamUtil.getDate(req, "deadline",
				DateFormat.getDateInstance());

		CallBean bean = new CallBean();
		bean.setId(id);
		bean.setTitle(title);
		bean.setDescription(desc);
		bean.setDeadline(deadline);

		CallLocalServiceUtil.updateCall(bean, serviceContext);
	}

	public void deleteEntry(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Call.class.getName(), req);

		long id = ParamUtil.getLong(req, "entryId");
		CallLocalServiceUtil.deleteCall(id, serviceContext);

	}

}
