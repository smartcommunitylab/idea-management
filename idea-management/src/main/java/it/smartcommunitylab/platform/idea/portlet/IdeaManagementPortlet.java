package it.smartcommunitylab.platform.idea.portlet;

import it.smartcommunitylab.platform.idea.beans.IdeaBean;
import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
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

			// System.err.println(String.format("PARAMETERS: [listType = %s, begin = %s, end = %s]",
			// listType, begin, end));

			/*
			 * Enumeration<String> f = req.getAttributeNames(); while
			 * (f.hasMoreElements()) { System.out.println(f.nextElement()); }
			 */

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
					ServiceContext serviceContext = ServiceContextFactory.getInstance(
							Idea.class.getName(), req);
					tagIds = AssetTagLocalServiceUtil.getTagIds(serviceContext.getScopeGroupId(), passed);
				} else {
					for (AssetTag tag : tags) {
						long tagSel = ParamUtil.getLong(req,
								"filterByTags" + tag.getTagId() + "Checkbox");
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

		Long categoryId = ParamUtil.getLong(req, "categoryId");
		Long callId = ParamUtil.getLong(req, "callId");

		String name = ParamUtil.getString(req, "title");
		String shortDesc = ParamUtil.getString(req, "shortDesc");
		String longDesc = ParamUtil.getString(req, "longDesc");
		String deadlineConstraints = ParamUtil.getString(req, "deadlineConstraints");
		int discussionLimit = ParamUtil.getInteger(req, "discussionLimit");
		IdeaBean ideaBean = new IdeaBean();
		ideaBean.setTitle(name);
		ideaBean.setShortDesc(shortDesc);
		ideaBean.setLongDesc(longDesc);
		ideaBean.setCategoryId(categoryId);
		ideaBean.setCallId(callId);
		if (discussionLimit > 0) {
			ideaBean.setDiscussionLimit(discussionLimit);
		} else {
			ideaBean.setDiscussionLimit(Constants.DEFAULT_DISCUSSION_LIMIT);
		}
		ideaBean.setDeadlineConstraints(deadlineConstraints);
		Idea idea = IdeaLocalServiceUtil.addIdea(serviceContext.getUserId(),
				ideaBean, serviceContext);
		SubscriptionLocalServiceUtil.addSubscription(serviceContext.getUserId(), idea.getGroupId(), Idea.class.getName(), idea.getIdeaId());
		req.setAttribute("idea", idea);
	}

	public void deleteEntry(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {

		long ideaId = ParamUtil.getLong(req, "entryId");
		IdeaLocalServiceUtil.deleteIdea(ideaId);
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
		String deadlineConstraints = ParamUtil.getString(req, "deadlineConstraints");
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
			throws PortalException, SystemException, IOException 
	{
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Idea.class.getName(), req);
		Long ideaId = ParamUtil.getLong(req, "ideaId");
		Idea idea = IdeaLocalServiceUtil.fetchIdea(ideaId);
		boolean subscribed = ParamUtil.getBoolean(req, "subscribed");
		// subscribe idea
		if (!subscribed) {
			SubscriptionLocalServiceUtil.addSubscription(serviceContext.getUserId(), idea.getGroupId(), Idea.class.getName(), ideaId);
		} else {
			SubscriptionLocalServiceUtil.deleteSubscription(serviceContext.getUserId(), Idea.class.getName(), ideaId);
		}
	}

}
