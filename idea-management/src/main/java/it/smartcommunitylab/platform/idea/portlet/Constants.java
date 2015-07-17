package it.smartcommunitylab.platform.idea.portlet;

import java.util.HashMap;
import java.util.Map;

import com.liferay.portal.kernel.workflow.WorkflowConstants;

public class Constants {

	public static final String IDEA_PORTLET_ID = "ideamanagement_WAR_ideamanagement";
	public static final String CALL_PORTLET_ID = "callmanagement_WAR_ideamanagement";

	public static final String IDEA_CATEGORY_TYPE_NAME = "Idea Category";

	public static final int PAGINATION_ELEMENTS_IN_PAGE = 5;
	public static final int PAGINATION_CALL_ELEMENTS_IN_PAGE = 5;

	public static final String PREF_VIEWTYPE_SIMPLE = "simple";
	public static final String PREF_VIEWTYPE_COMPLEX = "complex";

	public static final String PREF_LISTTYPE_POPULAR = "popular";
	public static final String PREF_LISTTYPE_RECENT = "recent";
	public static final String PREF_LISTTYPE_RELATED = "related";
	public static final String PREF_LISTTYPE_SIMILAR = "similar";

	public static final String PREF_CALLLISTTYPE_OPEN = "open";
	public static final String PREF_CALLLISTTYPE_INDISCUSSION = "discussion";
	public static final String PREF_CALLLISTTYPE_CLOSED = "closed";

	public static final String IDEA_STATE_PROPOSED = "proposed";
	public static final String IDEA_STATE_ACCEPTED = "accepted";
	public static final String IDEA_STATE_EXEC = "exec";
	public static final String IDEA_STATE_COMPLETE = "complete";
	public static final String IDEA_STATE_REJECTED = "rejected";

	public static final String IDEA_STATE_WAIT_FOR_EVAL = "waiting";
	public static final String IDEA_STATE_SIGNED = "signed";
	public static final String IDEA_STATE_REQUIRES_INTEGRATION = "integration_required";
	public static final String IDEA_STATE_REQUIRES_VALIDATION = "validation_required";

	public static final int DEFAULT_DISCUSSION_LIMIT = 30;
	public static final int[] DISCUSSION_LIMITS = new int[] { 30, 15, 7 };

	public static final String IDEA_STATE_BLOCKED_DUPLICATED = "duplicated";
	public static final String IDEA_STATE_BLOCKED_ABUSIVE = "abusive";

	public static final String BLACKLIST_ROLE_NAME = "Blacklisted";

	public static Map<Integer, String> STATE_MAPPING = new HashMap<Integer, String>();

	static {
		STATE_MAPPING.put(100, IDEA_STATE_BLOCKED_DUPLICATED);
		STATE_MAPPING.put(101, IDEA_STATE_BLOCKED_ABUSIVE);
		STATE_MAPPING.put(WorkflowConstants.STATUS_APPROVED, "");
		STATE_MAPPING
				.put(WorkflowConstants.STATUS_PENDING, IDEA_STATE_PROPOSED);
		STATE_MAPPING.put(WorkflowConstants.STATUS_DRAFT, IDEA_STATE_PROPOSED);
	}

}
