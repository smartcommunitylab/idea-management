package it.smartcommunitylab.platform.idea.portlet;

import it.smartcommunitylab.platform.idea.beans.CategoryBean;
import it.smartcommunitylab.platform.idea.beans.IdeaBean;
import it.smartcommunitylab.platform.idea.beans.IdeaResultItem;
import it.smartcommunitylab.platform.idea.beans.Pagination;
import it.smartcommunitylab.platform.idea.beans.ResultWrapper;
import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.service.AssetCategoryServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.ratings.model.RatingsStats;
import com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class IdeaManagementPortlet
 */
public class IdeaManagementPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		long ideaId = ParamUtil.getLong(renderRequest, "ideaId", 0);
		if (ideaId > 0) {
			// Utils.clearPublicRenderParameter(renderRequest, "ideaId");
			include("/html/idea/asset/full_content.jsp", renderRequest,
					renderResponse);
		} else {
			super.doView(renderRequest, renderResponse);
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

		case "delete":
			try {
				ajaxDeleteEntry(resourceRequest, resourceResponse);
			} catch (PortalException | SystemException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

		super.serveResource(resourceRequest, resourceResponse);
	}

	private void ajaxDeleteEntry(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws PortalException,
			SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Idea.class.getName(), resourceRequest);

		long ideaId = ParamUtil.getLong(resourceRequest, "entryId");
		IdeaLocalServiceUtil.deleteIdea(serviceContext.getUserId(), ideaId,
				serviceContext);
	}

	private void loadAjaxViewSimple(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException {
		int begin = -1, end = -1, currentPage = -1;

		// cannot load portletpreferences..seems not work with resourceRequest

		String listType = ParamUtil.getString(resourceRequest, "listType");
		boolean pagination = ParamUtil
				.getBoolean(resourceRequest, "pagination");
		int delta = ParamUtil.getInteger(resourceRequest, "delta");
		Long categoryId = ParamUtil.getLong(resourceRequest, "categoryId");
		Long callId = ParamUtil.getLong(resourceRequest, "callId");

		// FIXME used by filter
		long[] tagIds = new long[0];

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
			List<Idea> ideas = new ArrayList<Idea>();
			// result already ordered by creation date DESC for default
			switch (listType) {
			case Constants.PREF_LISTTYPE_RECENT:
				ideas = IdeaLocalServiceUtil.searchByCallAndCategoryAndTags(
						categoryId, callId, tagIds, begin, end);
				break;
			case Constants.PREF_LISTTYPE_POPULAR:
				ideas = IdeaLocalServiceUtil
						.searchPopularByCallAndCategoryAndTags(categoryId,
								callId, tagIds, begin, end);
				break;
			default:
				break;
			}

			Pagination pag = new Pagination();
			pag.setCurrentPage(currentPage);
			pag.setElementInPage(delta);

			Map<String, String[]> params = new HashMap<String, String[]>();
			params.put("pagination",
					new String[] { Boolean.toString(pagination) });
			params.put("listType", new String[] { listType });
			params.put("delta", new String[] { Integer.toString(delta) });
			params.put("categoryId", new String[] { Long.toString(categoryId) });
			params.put("callId", new String[] { Long.toString(callId) });

			// next URL
			ResourceURL nextURL = resourceResponse.createResourceURL();
			nextURL.setResourceID("loadSimple");

			params.put("cur",
					new String[] { Integer.toString(currentPage + 1) });
			nextURL.setParameters(params);
			pag.setNextURL(nextURL.toString());

			// prev URL
			ResourceURL prevURL = resourceResponse.createResourceURL();
			prevURL.setResourceID("loadSimple");
			params.put("cur", new String[] { Integer
					.toString(currentPage > 1 ? currentPage - 1 : 1) });
			prevURL.setParameters(params);
			pag.setPrevURL(prevURL.toString());

			ResultWrapper rw = new ResultWrapper();
			rw.setResult(prepareResult(ideas, resourceRequest, resourceResponse));
			rw.setSize(ideas.size());
			pag.setResult(rw);

			Gson gson = new Gson();
			String json = gson.toJson(pag);
			// System.out.println(json);
			resourceResponse.setContentType("application/json");
			resourceResponse.getWriter().write(json);
			resourceResponse.getWriter().flush();
		} catch (SystemException e) {

		}
	}

	private List<IdeaResultItem> prepareResult(List<Idea> ideas,
			PortletRequest req, MimeResponse resp) {
		List<IdeaResultItem> result = null;
		if (ideas != null) {
			result = new ArrayList<IdeaResultItem>();
			Map<String, String> catColors = new HashMap<String, String>();
			try {
				catColors = IdeaLocalServiceUtil.getCategoryColors(PortalUtil
						.getScopeGroupId(req));
			} catch (PortalException | SystemException e) {
				e.printStackTrace();
			}
			for (Idea i : ideas) {
				IdeaResultItem ideaRes = new IdeaResultItem();
				ideaRes.setTitle(i.getTitle());
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",
						req.getLocale());
				ideaRes.setCreationDate(formatter.format(i.getCreateDate()));
				ideaRes.setCallId(i.getCallId());
				try {
					ideaRes.setComments(MBMessageLocalServiceUtil
							.getDiscussionMessagesCount(Idea.class.getName(),
									i.getIdeaId(),
									WorkflowConstants.STATUS_APPROVED));
				} catch (SystemException e1) {
					e1.printStackTrace();
				}

				// set starts avg
				RatingsStats stat = null;
				try {
					stat = RatingsStatsLocalServiceUtil.getStats(
							Idea.class.getName(), i.getIdeaId());
				} catch (SystemException e) {
					e.printStackTrace();
				}
				if (stat != null) {
					ideaRes.setAvgRating(stat.getAverageScore());
				}
				// set category data
				String[] catIds = i.getCategoryIds() != null ? i
						.getCategoryIds().split(",") : new String[0];
				ideaRes.setCats(new ArrayList<CategoryBean>());
				for (String catId : catIds) {

					String color = catColors.get(catId);

					AssetCategory cat = null;
					try {
						cat = AssetCategoryServiceUtil.getCategory(Long
								.valueOf(catId));
						CategoryBean cb = new CategoryBean(GetterUtil.get(
								cat.getName(), ""), color);
						ideaRes.getCats().add(cb);
					} catch (NumberFormatException | PortalException
							| SystemException e) {
						e.printStackTrace();
					}
				}
				// view URL
				try {
					ideaRes.setDetailURL(Utils.getPageUrl(
							PortalUtil.getHttpServletRequest(req), "detail")
							+ "/-/idea/-/" + i.getIdeaId() + "/view");
				} catch (SystemException | PortalException e) {
					e.printStackTrace();
				}

				// delete URL
				if (Utils.ideaDeleteEnabled(i, req)) {
					PortletURL deleteURL = resp.createActionURL();
					deleteURL.setParameter(ActionRequest.ACTION_NAME,
							"deleteEntry");
					deleteURL.setParameter("entryId",
							String.valueOf(i.getIdeaId()));
					deleteURL
							.setParameter("categoryId", String
									.valueOf(catIds.length > 0 ? catIds[0]
											: "0"));
					deleteURL.setParameter("callId",
							String.valueOf(i.getCallId()));
					ideaRes.setDeleteURL(deleteURL.toString());

					// ResourceURL deleteURL = resp.createResourceURL();
					// deleteURL.setResourceID("deleteEntry");
					// deleteURL.setParameter("entryId",
					// String.valueOf(i.getIdeaId()));
					// ideaRes.setDeleteURL(deleteURL.toString());
				}
				result.add(ideaRes);
			}
		}

		return result;
	}

	@Override
	public void render(RenderRequest req, RenderResponse res)
			throws PortletException, IOException {
		long ideaId = ParamUtil.getLong(req, "ideaId", 0);
		if (ideaId > 0) {
			super.render(req, res);
		} else {

			int begin = -1, end = -1;

			PortletPreferences preferences = req.getPreferences();
			boolean pagination = GetterUtil.getBoolean(preferences.getValue(
					"activatePagination", StringPool.TRUE));
			int delta = GetterUtil.getInteger(preferences.getValue(
					"elementInPage",
					String.valueOf(Constants.PAGINATION_ELEMENTS_IN_PAGE)));
			String listType = preferences.getValue("listType",
					Constants.PREF_LISTTYPE_RECENT);

			if (pagination) {
				if (req.getParameter("begin") != null
						&& req.getParameter("end") != null) {
					begin = ParamUtil.getInteger(req, "begin");
					end = ParamUtil.getInteger(req, "end");
				} else {
					int currentPage = ParamUtil.getInteger(req, "cur", 1);
					begin = (currentPage - 1) * delta;
					end = begin + delta;
				}
			}
			if (req.getAttribute("listType") != null) {
				listType = (String) req.getAttribute("listType");
			}
			if (req.getParameter("listType") != null) {
				listType = ParamUtil.getString(req, "listType");
			}
			req.setAttribute("listType", listType);

			// search by category
			Long categoryId = ParamUtil.getLong(req, "categoryId");
			req.setAttribute("categoryId", categoryId);

			// search by call
			Long callId = ParamUtil.getLong(req, "callId");
			req.setAttribute("callId", callId);

			// filter by tags
			long[] tagIds = new long[0];
			try {
				List<AssetTag> tags = Collections.emptyList();
				if (callId != null && callId > 0) {
					tags = IdeaLocalServiceUtil.getCallTags(callId);
				} else if (categoryId != null && categoryId > 0) {
					tags = IdeaLocalServiceUtil.getCategoryTags(
							new long[] { categoryId },
							PortalUtil.getScopeGroupId(req));
				}

				String[] passed = ParamUtil.getParameterValues(req, "tag");
				if (passed != null && passed.length > 0) {
					ServiceContext serviceContext = ServiceContextFactory
							.getInstance(Idea.class.getName(), req);
					tagIds = AssetTagLocalServiceUtil.getTagIds(
							serviceContext.getScopeGroupId(), passed);
				} else {
					for (AssetTag tag : tags) {
						long tagSel = ParamUtil.getLong(req, "filterByTags"
								+ tag.getTagId() + "Checkbox");
						if (tagSel > 0) {
							tagIds = ArrayUtil.append(tagIds, tag.getTagId());
						}
					}
				}
			} catch (SystemException e1) {
				e1.printStackTrace();
			} catch (PortalException e) {
				e.printStackTrace();
			}

			req.setAttribute("tagSelected", tagIds);

			try {
				List<Idea> ideas = new ArrayList<Idea>();
				// result already ordered by creation date DESC for default
				switch (listType) {
				case Constants.PREF_LISTTYPE_RECENT:
					ideas = IdeaLocalServiceUtil
							.searchByCallAndCategoryAndTags(categoryId, callId,
									tagIds, begin, end);
					break;
				case Constants.PREF_LISTTYPE_POPULAR:
					ideas = IdeaLocalServiceUtil
							.searchPopularByCallAndCategoryAndTags(categoryId,
									callId, tagIds, begin, end);
					break;
				default:
					break;
				}
				req.setAttribute("ideas", ideas);
				req.setAttribute("ideaCount", ideas.size());
			} catch (SystemException e) {

			}
			super.render(req, res);
		}
	}

	public void filter(ActionRequest req, ActionResponse res) {
		// only to perform action
		String fType = req.getParameter("listType");
		res.setRenderParameter("listType", fType);
		res.setRenderParameter("tag", "");
	}

	public void addComment(ActionRequest req, ActionResponse res) {
		Long ideaId = ParamUtil.getLong(req, "classPK");
		try {
			Idea idea = IdeaLocalServiceUtil.fetchIdea(ideaId);
			if (Utils.discussionEnabled(idea, req)) {
				invokeTaglibDiscussion(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showIdea(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {
	}

	public void addNewIdea(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Idea.class.getName(), req);

		Long callId = ParamUtil.getLong(req, "callId");

		String name = ParamUtil.getString(req, "title");
		String shortDesc = ParamUtil.getString(req, "shortDesc");
		String longDesc = ParamUtil.getString(req, "longDesc");
		String deadlineConstraints = ParamUtil.getString(req,
				"deadlineConstraints");
		int discussionLimit = ParamUtil.getInteger(req, "discussionLimit");
		IdeaBean ideaBean = new IdeaBean();
		ideaBean.setTitle(name);
		ideaBean.setShortDesc(shortDesc);
		ideaBean.setLongDesc(longDesc);
		ideaBean.setCallId(callId);
		if (discussionLimit > 0) {
			ideaBean.setDiscussionLimit(discussionLimit);
		} else {
			ideaBean.setDiscussionLimit(Constants.DEFAULT_DISCUSSION_LIMIT);
		}
		ideaBean.setDeadlineConstraints(deadlineConstraints);
		Idea idea = IdeaLocalServiceUtil.addIdea(serviceContext.getUserId(),
				ideaBean, serviceContext);
		SubscriptionLocalServiceUtil.addSubscription(
				serviceContext.getUserId(), idea.getGroupId(),
				Idea.class.getName(), idea.getIdeaId());
		req.setAttribute("idea", idea);
	}

	public void deleteEntry(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Idea.class.getName(), req);

		long ideaId = ParamUtil.getLong(req, "entryId");
		IdeaLocalServiceUtil.deleteIdea(serviceContext.getUserId(), ideaId,
				serviceContext);
	}

	public void toggleUserParticipation(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {

		long ideaId = ParamUtil.getLong(req, "ideaId");
		long userId = ParamUtil.getLong(req, "userId");
		IdeaLocalServiceUtil.toggleUserParticipation(ideaId, userId);
	}

	public void updateIdea(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException, IOException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Idea.class.getName(), req);

		long id = ParamUtil.getLong(req, "ideaId");
		String name = ParamUtil.getString(req, "title");
		String shortDesc = ParamUtil.getString(req, "shortDesc");
		String longDesc = ParamUtil.getString(req, "longDesc");
		String deadlineConstraints = ParamUtil.getString(req,
				"deadlineConstraints");
		int discussionLimit = ParamUtil.getInteger(req, "discussionLimit");

		IdeaBean ideaBean = new IdeaBean();
		ideaBean.setId(id);
		ideaBean.setTitle(name);
		ideaBean.setShortDesc(shortDesc);
		ideaBean.setLongDesc(longDesc);
		if (discussionLimit > 0) {
			ideaBean.setDiscussionLimit(discussionLimit);
		} else {
			ideaBean.setDiscussionLimit(Constants.DEFAULT_DISCUSSION_LIMIT);
		}
		ideaBean.setDeadlineConstraints(deadlineConstraints);

		IdeaLocalServiceUtil.updateIdea(serviceContext.getUserId(), ideaBean,
				serviceContext);
	}

	public void followIdea(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException, IOException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Idea.class.getName(), req);
		Long ideaId = ParamUtil.getLong(req, "ideaId");
		Idea idea = IdeaLocalServiceUtil.fetchIdea(ideaId);
		boolean subscribed = ParamUtil.getBoolean(req, "subscribed");
		// subscribe idea
		if (!subscribed) {
			SubscriptionLocalServiceUtil.addSubscription(
					serviceContext.getUserId(), idea.getGroupId(),
					Idea.class.getName(), ideaId);
		} else {
			SubscriptionLocalServiceUtil.deleteSubscription(
					serviceContext.getUserId(), Idea.class.getName(), ideaId);
		}
	}

}
