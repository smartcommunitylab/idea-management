package it.smartcommunitylab.platform.idea.workflow;

import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.portlet.Utils;

import java.util.HashMap;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.util.portlet.PortletProps;

public class WorkflowUtil {
	public static ServiceContext addWorkflowVars(ServiceContext ctx, Idea idea) {
		HashMap<String, Object> workflowContext = new HashMap<String, Object>();

		workflowContext.put(
				WorkflowConstants.CONTEXT_NOTIFICATION_SENDER_ADDRESS,
				PortletProps.get("noreply.email"));
		workflowContext.put(WorkflowConstants.CONTEXT_NOTIFICATION_SENDER_NAME,
				PortletProps.get("noreply.displayname"));

		HashMap<String, Object> context = new HashMap<String, Object>();
		context.put("ideaTitle", idea.getTitle());
		context.put("moderatorEmail", PortletProps.get("moderator.email"));

		// idea URL
		try {
			String ideaURL = Utils.getPageUrl(ctx.getRequest(), "detail")
					+ "/-/idea/-/" + idea.getIdeaId() + "/view";
			context.put("ideaURL", ideaURL);
		} catch (SystemException | PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ctx.setAttribute("context", context);

		ctx.setAttribute("workflowContext", workflowContext);

		return ctx;
	}
}
