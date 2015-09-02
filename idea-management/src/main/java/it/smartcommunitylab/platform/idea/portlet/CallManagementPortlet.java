package it.smartcommunitylab.platform.idea.portlet;

import it.smartcommunitylab.platform.idea.beans.CallBean;
import it.smartcommunitylab.platform.idea.beans.CallResultItem;
import it.smartcommunitylab.platform.idea.beans.CategoryBean;
import it.smartcommunitylab.platform.idea.beans.Pagination;
import it.smartcommunitylab.platform.idea.beans.ResultWrapper;
import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import com.google.gson.Gson;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.service.AssetCategoryServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class CallManagementPortlet
 */
public class CallManagementPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		long callId = ParamUtil.getLong(renderRequest, "callId", 0);
		if (callId > 0) {
			// Utils.clearPublicRenderParameter(renderRequest, "callId");
			include("/html/callmanagement/asset/full_content.jsp",
					renderRequest, renderResponse);
		} else {
			super.doView(renderRequest, renderResponse);
		}
	}

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
		bean.setRealizationDeadline(calculateDeadline(req, "ed"));

		Call call = CallLocalServiceUtil.createCall(serviceContext.getUserId(),
				bean, serviceContext);
		req.setAttribute("call", call);
	}

	public void showEntry(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {
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
		bean.setRealizationDeadline(calculateDeadline(req, "ed"));

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
		if (day == null || day.trim().isEmpty())
			return null;

		month = String.valueOf(Integer.valueOf(month) + 1);

		String datePattern = "ddMMyyyyHm";
		String dateString = (day.length() == 1 ? "0" + day : day)
				+ (month.length() == 1 ? "0" + month : month)
				+ String.valueOf(year) + "00";
		try {
			return new SimpleDateFormat(datePattern).parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {

		String resID = resourceRequest.getResourceID();

		switch (resID) {
		case "loadSimple":
			loadAjaxViewSimple(resourceRequest, resourceResponse);
			break;

		default:
			break;
		}

		super.serveResource(resourceRequest, resourceResponse);
	}

	private void loadAjaxViewSimple(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException {
		int begin = -1, end = -1, currentPage = -1;

		// cannot load portletpreferences..seems not work with resourceRequest

		String listType = ParamUtil.getString(resourceRequest, "listType");
		boolean pagination = ParamUtil
				.getBoolean(resourceRequest, "pagination");
		int delta = ParamUtil.getInteger(resourceRequest, "delta");

		if (pagination) {
			if (resourceRequest.getParameter("begin") != null
					&& resourceRequest.getParameter("end") != null) {
				begin = ParamUtil.getInteger(resourceRequest, "begin");
				end = ParamUtil.getInteger(resourceRequest, "end");
			} else {
				currentPage = ParamUtil.getInteger(resourceRequest, "cur", 1);
				begin = (currentPage - 1) * delta;
				end = begin + delta;
			}
		}

		try {
			List<Call> list = new ArrayList<Call>();
			// result already ordered by creation date DESC for default
			switch (listType) {
			case Constants.PREF_CALLLISTTYPE_OPEN:
				list = CallLocalServiceUtil.getOpenCalls(begin, end);
				break;
			case Constants.PREF_CALLLISTTYPE_INDISCUSSION:
				list = CallLocalServiceUtil.getInDiscussionCalls(begin, end);
				break;
			case Constants.PREF_CALLLISTTYPE_CLOSED:
				list = CallLocalServiceUtil.getClosedCalls(begin, end);
				break;
			default:
				list = CallLocalServiceUtil.getCalls(begin, end);
				break;
			}

			Pagination pag = new Pagination();
			pag.setCurrentPage(currentPage);
			pag.setElementInPage(delta);
			// next URL
			ResourceURL nextURL = resourceResponse.createResourceURL();
			nextURL.setResourceID("loadSimple");
			nextURL.setParameter("cur", Integer.toString(currentPage + 1));
			nextURL.setParameter("pagination", Boolean.toString(pagination));
			nextURL.setParameter("delta", Integer.toString(delta));
			nextURL.setParameter("listType", listType);
			pag.setNextURL(nextURL.toString());

			// prev URL
			ResourceURL prevURL = resourceResponse.createResourceURL();
			prevURL.setResourceID("loadSimple");
			prevURL.setParameter("cur",
					Integer.toString(currentPage > 1 ? currentPage - 1 : 1));
			prevURL.setParameter("pagination", Boolean.toString(pagination));
			prevURL.setParameter("delta", Integer.toString(delta));
			prevURL.setParameter("listType", listType);
			pag.setPrevURL(prevURL.toString());

			ResultWrapper rw = new ResultWrapper();
			rw.setResult(prepareResult(list, resourceRequest, resourceResponse));
			rw.setSize(list.size());
			pag.setResult(rw);

			Gson gson = new Gson();
			String json = gson.toJson(pag);
			System.out.println(json);
			resourceResponse.setContentType("application/json");
			resourceResponse.getWriter().write(json);
			resourceResponse.getWriter().flush();
		} catch (SystemException e) {

		}
	}

	private List<CallResultItem> prepareResult(List<Call> list,
			PortletRequest req, MimeResponse resp) {
		List<CallResultItem> result = null;
		if (list != null) {
			result = new ArrayList<CallResultItem>();
			Map<String, String> catColors = new HashMap<String, String>();
			try {
				catColors = IdeaLocalServiceUtil.getCategoryColors(PortalUtil
						.getScopeGroupId(req));
			} catch (PortalException | SystemException e) {
				e.printStackTrace();
			}
			for (Call entry : list) {
				CallResultItem entryRes = new CallResultItem();
				entryRes.setTitle(entry.getTitle());
				if (entry.getDeadline() != null) {
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",
							req.getLocale());
					entryRes.setDeadline(formatter.format(entry.getDeadline()));
				}
				try {
					entryRes.setIdeas(IdeaLocalServiceUtil.getIdeasByCall(
							entry.getCallId(), -1, -1).size());
				} catch (SystemException e1) {
					e1.printStackTrace();
				}

				// set category data
				String[] catIds = entry.getCategoryIds() != null ? entry
						.getCategoryIds().split(",") : new String[0];
				entryRes.setCats(new ArrayList<CategoryBean>());
				for (String catId : catIds) {

					String color = catColors.get(catId);

					AssetCategory cat = null;
					try {
						cat = AssetCategoryServiceUtil.getCategory(Long
								.valueOf(catId));
						CategoryBean cb = new CategoryBean(GetterUtil.get(
								cat.getName(), ""), color);
						entryRes.getCats().add(cb);
					} catch (NumberFormatException | PortalException
							| SystemException e) {
						e.printStackTrace();
					}
				}
				// view URL
				try {
					entryRes.setDetailURL(Utils.getPageUrl(
							PortalUtil.getHttpServletRequest(req), "call")
							+ "/-/call/-/" + entry.getCallId() + "/view");
				} catch (SystemException | PortalException e) {
					e.printStackTrace();
				}

				// delete URL
				if (Utils.callDeleteEnabled(entry, req)) {
					PortletURL deleteURL = resp.createActionURL();
					deleteURL.setParameter(ActionRequest.ACTION_NAME,
							"deleteEntry");
					deleteURL.setParameter("entryId",
							String.valueOf(entry.getCallId()));
					entryRes.setDeleteURL(deleteURL.toString());

				}
				result.add(entryRes);
			}
		}

		return result;
	}
}
