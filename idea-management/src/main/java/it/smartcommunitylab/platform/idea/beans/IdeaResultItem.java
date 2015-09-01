package it.smartcommunitylab.platform.idea.beans;

import java.util.List;

public class IdeaResultItem {
	private String title;
	private long creationTs;
	private List<CategoryBean> cats;
	private double avgRating;
	private String detailURL;
	private String deleteURL;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getCreationTs() {
		return creationTs;
	}

	public void setCreationTs(long creationTs) {
		this.creationTs = creationTs;
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
}
