package it.smartcommunitylab.platform.idea.portlet;

import it.smartcommunitylab.platform.idea.beans.IdeaBean;
import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletContainerUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.persistence.PortletUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class IdeaManagementPortlet
 */
public class IdeaManagementPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest req, RenderResponse res)
			throws PortletException, IOException {

		try {
			List<Idea> idea = IdeaLocalServiceUtil.getIdeas(0,
					IdeaLocalServiceUtil.getIdeasCount()).subList(0,
					IdeaLocalServiceUtil.getIdeasCount());
			req.setAttribute("ideas", idea);
		} catch (SystemException e) {

		}
		super.render(req, res);
	}

	@Override
	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {

		ServiceContext serviceContext;
		try {
			serviceContext = ServiceContextFactory.getInstance(
					Idea.class.getName(), actionRequest);
			PortletContainerUtil.processAction(PortalUtil
					.getHttpServletRequest(actionRequest), PortalUtil
					.getHttpServletResponse(actionResponse), PortletUtil
					.fetchByC_P(serviceContext.getCompanyId(),
							"idea management"));
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
