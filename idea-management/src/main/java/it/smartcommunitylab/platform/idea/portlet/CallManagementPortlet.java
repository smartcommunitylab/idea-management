package it.smartcommunitylab.platform.idea.portlet;

import it.smartcommunitylab.platform.idea.beans.CallBean;
import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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

		CallBean bean = new CallBean();
		bean.setTitle(title);
		bean.setDescription(desc);
		bean.setDeadline(calculateDeadline(req, "d"));
		bean.setPublicationDeadline(calculateDeadline(req, "pd"));

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

		CallBean bean = new CallBean();
		bean.setId(id);
		bean.setTitle(title);
		bean.setDescription(desc);
		bean.setDeadline(calculateDeadline(req, "d"));
		bean.setPublicationDeadline(calculateDeadline(req, "pd"));

		CallLocalServiceUtil.updateCall(bean, serviceContext);
	}

	public void deleteEntry(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Call.class.getName(), req);

		long id = ParamUtil.getLong(req, "entryId");
		CallLocalServiceUtil.deleteCall(id, serviceContext);

	}

	private Date calculateDeadline(ActionRequest req, String prefix) {
		String day = ParamUtil.getString(req, prefix + "day");
		String month = ParamUtil.getString(req, prefix + "month");
		String year = ParamUtil.getString(req, prefix + "year");

		month = String.valueOf(Integer.valueOf(month) + 1);

		String datePattern = "ddMMyyyyHm";
		String dateString = (day.length() == 1 ? "0" + day : day)
				+ (month.length() == 1 ? "0" + month : month)
				+ String.valueOf(year) + "00";
		try {
			return new SimpleDateFormat(datePattern).parse(dateString);
		} catch (ParseException e) {
			return new Date();
		}
	}

	@Override
	protected void checkPath(String path) throws PortletException {
		super.checkPath(path);
	}

	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {

		Enumeration<String> f = renderRequest.getAttributeNames();
		while (f.hasMoreElements()) {
			System.out.println(f.nextElement());
		}

		super.doView(renderRequest, renderResponse);
	}
}
