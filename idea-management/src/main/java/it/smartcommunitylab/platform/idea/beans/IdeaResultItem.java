package it.smartcommunitylab.platform.idea.beans;

import java.util.List;

public class IdeaResultItem {
	private String title;
	private String creationDate;
	private List<CategoryBean> cats;
	private double avgRating;
	private String detailURL;
	private String deleteURL;
	private int comments;
	private long callId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public String getDetailURL() {
		return detailURL;
	}

	public void setDetailURL(String detailURL) {
		this.detailURL = detailURL;
	}

	public String getDeleteURL() {
		return deleteURL;
	}

	public void setDeleteURL(String deleteURL) {
		this.deleteURL = deleteURL;
	}

	public List<CategoryBean> getCats() {
		return cats;
	}

	public void setCats(List<CategoryBean> cats) {
		this.cats = cats;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public long getCallId() {
		return callId;
	}

	public void setCallId(long callId) {
		this.callId = callId;
	}
}
