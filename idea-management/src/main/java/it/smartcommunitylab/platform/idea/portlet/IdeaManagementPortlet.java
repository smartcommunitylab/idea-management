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
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
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

		/*
		 * Enumeration<String> f = req.getAttributeNames(); while
		 * (f.hasMoreElements()) { System.out.println(f.nextElement()); }
		 */

		// search for category
		Long categoryId = ParamUtil.getLong(req, "categoryId");
		req.setAttribute("categoryId", categoryId);

		// search for filter search

		String filterBy = ParamUtil.getString(req, "filterBy");
		try {
			List<Idea> ideas = new ArrayList<Idea>();
			// result already ordered by creation date DESC for default
			if (filterBy.equals(Constants.FILTER_BY_ALL)
					|| filterBy.equals(Constants.FILTER_BY_CREATION)) {
				if (categoryId <= 0) {
					ideas = IdeaLocalServiceUtil.getIdeas();
				} else {
					ideas = IdeaLocalServiceUtil.getIdeasByCat(categoryId);
				}
			} else if (filterBy.equals(Constants.FILTER_BY_POPOLARITY)) {
				if (categoryId <= 0) {
					ideas = IdeaLocalServiceUtil.getIdeasByRating();
				} else {
					ideas = IdeaLocalServiceUtil.getIdeasByRating(categoryId);
				}
			}

			req.setAttribute("ideas", ideas);
			req.setAttribute("ideaCount", ideas.size());
		} catch (SystemException e) {

		}
		super.render(req, res);
	}

	public void filter(ActionRequest req, ActionResponse res) {
		// only to perform action
		String fType = req.getParameter("filterBy");
		res.setRenderParameter("filterBy", fType);

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

		String name = ParamUtil.getString(req, "title");
		String shortDesc = ParamUtil.getString(req, "shortDesc");
		String longDesc = ParamUtil.getString(req, "longDesc");
		IdeaBean ideaBean = new IdeaBean();
		ideaBean.setTitle(name);
		ideaBean.setShortDesc(shortDesc);
		ideaBean.setLongDesc(longDesc);
		IdeaLocalServiceUtil.addIdea(serviceContext.getUserId(), ideaBean,
				serviceContext);
		SessionMessages.add(req, "addedIdea");
	}

	public void deleteIdea(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {

		long ideaId = ParamUtil.getLong(req, "id");
		IdeaLocalServiceUtil.deleteIdea(ideaId);
		SessionMessages.add(req, "deleteIdea");
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
