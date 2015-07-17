package it.smartcommunitylab.platform.idea.asset;

import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContext;

public class IdeaWorkflowHandler extends BaseWorkflowHandler {

	public static final String CLASS_NAME = Idea.class.getName();
	public static final String TYPE = "idea";

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getType(Locale locale) {
		return TYPE;
	}

	@Override
	public Object updateStatus(int status,
			Map<String, Serializable> workflowContext) throws PortalException,
			SystemException {

		long userId = GetterUtil.getLong((String) workflowContext
				.get(WorkflowConstants.CONTEXT_USER_ID));
		long entryId = GetterUtil.getLong((String) workflowContext
				.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		ServiceContext serviceContext = (ServiceContext) workflowContext
				.get("serviceContext");

		String comments = (String) workflowContext
				.get(WorkflowConstants.CONTEXT_TASK_COMMENTS);
		serviceContext.setAttribute(WorkflowConstants.CONTEXT_TASK_COMMENTS,
				comments);
		return IdeaLocalServiceUtil.updateStatus(userId, entryId, status,
				serviceContext);
	}

}
