package it.smartcommunitylab.platform.idea.workflow;

import java.util.HashMap;

import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.util.portlet.PortletProps;

public class WorkflowUtil {
	public static ServiceContext addWorkflowVars(ServiceContext ctx) {
		HashMap<String, Object> workflowContext = new HashMap<String, Object>();

		workflowContext.put(
				WorkflowConstants.CONTEXT_NOTIFICATION_SENDER_ADDRESS,
				PortletProps.get("noreply.email"));
		workflowContext.put(WorkflowConstants.CONTEXT_NOTIFICATION_SENDER_NAME,
				PortletProps.get("noreply.displayname"));

		ctx.setAttribute("workflowContext", workflowContext);

		return ctx;
	}
}
