package it.smartcommunitylab.platform.idea.workflow;

import java.util.HashMap;

import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContext;

public class WorkflowUtil {
	public static ServiceContext addWorkflowVars(ServiceContext ctx) {
		HashMap<String, Object> workflowContext = new HashMap<String, Object>();

		workflowContext.put(
				WorkflowConstants.CONTEXT_NOTIFICATION_SENDER_ADDRESS,
				"no-reply@local.futuratrento.it");
		workflowContext.put(WorkflowConstants.CONTEXT_NOTIFICATION_SENDER_NAME,
				"FuturaTrento");

		ctx.setAttribute("workflowContext", workflowContext);

		return ctx;
	}
}
