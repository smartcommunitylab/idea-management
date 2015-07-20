package it.smartcommunitylab.platform.idea.beans;

public class IdeaBean {
	private long id;
	private String title;
	private String longDesc;
	private String shortDesc;
	private long createTs;
	private long modifiedTs;
	private long creatorId;
	private String creatorUsername;
	private long userGroupId;
	private long callId;
	private String state;
	private String stateJudgement;
	private String deadlineConstraints;
	private int discussionLimit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public long getCreateTs() {
		return createTs;
	}

	public void setCreateTs(long createTs) {
		this.createTs = createTs;
	}

	public long getModifiedTs() {
		return modifiedTs;
	}

	public void setModifiedTs(long modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorUsername() {
		return creatorUsername;
	}

	public void setCreatorUsername(String creatorUsername) {
		this.creatorUsername = creatorUsername;
	}

	/**
	 * @return the userGroupId
	 */
	public long getUserGroupId() {
		return userGroupId;
	}

	/**
	 * @param userGroupId
	 *            the userGroupId to set
	 */
	public void setUserGroupId(long userGroupId) {
		this.userGroupId = userGroupId;
	}

	public long getCallId() {
		return callId;
	}

	public void setCallId(long callId) {
		this.callId = callId;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the stateJudgement
	 */
	public String getStateJudgement() {
		return stateJudgement;
	}

	/**
	 * @param stateJudgement the stateJudgement to set
	 */
	public void setStateJudgement(String stateJudgement) {
		this.stateJudgement = stateJudgement;
	}

	/**
	 * @return the deadlineConstraints
	 */
	public String getDeadlineConstraints() {
		return deadlineConstraints;
	}

	/**
	 * @param deadlineConstraints the deadlineConstraints to set
	 */
	public void setDeadlineConstraints(String deadlineConstraints) {
		this.deadlineConstraints = deadlineConstraints;
	}

	/**
	 * @return the discussionLimit
	 */
	public int getDiscussionLimit() {
		return discussionLimit;
	}

	/**
	 * @param discussionLimit the discussionLimit to set
	 */
	public void setDiscussionLimit(int discussionLimit) {
		this.discussionLimit = discussionLimit;
	}

}
