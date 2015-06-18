package it.smartcommunitylab.platform.idea.portlet;

import it.smartcommunitylab.platform.idea.beans.IdeaBean;
import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class IdeaManagementPortlet
 */
public class IdeaManagementPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest req, RenderResponse res)
			throws PortletException, IOException {

		int begin = -1, end = -1;

		PortletPreferences preferences = req.getPreferences();
		boolean pagination = GetterUtil.getBoolean(preferences.getValue(
				"activatePagination", StringPool.TRUE));
		int delta = GetterUtil.getInteger(preferences.getValue("elementInPage",
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

		// pagination

		// search by category
		Long categoryId = ParamUtil.getLong(req, "categoryId");
		boolean searchByCat = categoryId > 0;
		req.setAttribute("categoryId", categoryId);

		// search by call
		Long callId = ParamUtil.getLong(req, "callId");
		boolean searchByCall = callId > 0;
		req.setAttribute("callId", callId);

		try {
			List<Idea> ideas = new ArrayList<Idea>();
			// result already ordered by creation date DESC for default
			switch (listType) {
			case Constants.PREF_LISTTYPE_RECENT:
				if (searchByCat) {
					ideas = IdeaLocalServiceUtil.getIdeasByCat(categoryId,
							begin, end);
				} else if (searchByCall) {
					ideas = IdeaLocalServiceUtil.getIdeasByCall(callId, begin,
							end);

				} else {
					ideas = IdeaLocalServiceUtil.getIdeas(begin, end);
				}
				break;
			case Constants.PREF_LISTTYPE_POPULAR:
				if (searchByCat) {
					ideas = IdeaLocalServiceUtil.getIdeasByRating(categoryId,
							begin, end);
				} else if (searchByCall) {
					ideas = IdeaLocalServiceUtil.getIdeasByCallAndRating(
							callId, begin, end);
				} else {
					ideas = IdeaLocalServiceUtil.getIdeasByRating(begin, end);
				}
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

	public void filter(ActionRequest req, ActionResponse res) {
		// only to perform action
		String fType = req.getParameter("listType");
		res.setRenderParameter("listType", fType);

	}

	public void addComment(ActionRequest req, ActionResponse res) {
		try {
			invokeTaglibDiscussion(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addNewIdea(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Idea.class.getName(), req);

		Long categoryId = ParamUtil.getLong(req, "categoryId");

		String name = ParamUtil.getString(req, "title");
		String shortDesc = ParamUtil.getString(req, "shortDesc");
		String longDesc = ParamUtil.getString(req, "longDesc");
		IdeaBean ideaBean = new IdeaBean();
		ideaBean.setTitle(name);
		ideaBean.setShortDesc(shortDesc);
		ideaBean.setLongDesc(longDesc);
		ideaBean.setCategoryId(categoryId);
		IdeaLocalServiceUtil.addIdea(serviceContext.getUserId(), ideaBean,
				serviceContext);
		SessionMessages.add(req, "addedIdea");
	}

	public void deleteEntry(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {

		long ideaId = ParamUtil.getLong(req, "entryId");
		IdeaLocalServiceUtil.deleteIdea(ideaId);
		SessionMessages.add(req, "deleteIdea");
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
		IdeaBean ideaBean = new IdeaBean();
		ideaBean.setId(id);
		ideaBean.setTitle(name);
		ideaBean.setShortDesc(shortDesc);
		ideaBean.setLongDesc(longDesc);
		IdeaLocalServiceUtil.updateIdea(serviceContext.getUserId(), ideaBean,
				serviceContext);
		SessionMessages.add(req, "editedIdea");
	}
}
