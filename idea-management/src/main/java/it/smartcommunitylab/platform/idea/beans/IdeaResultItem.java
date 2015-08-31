package it.smartcommunitylab.platform.idea.beans;

public class IdeaResultItem {
	private String title;
	private long creationTs;
	private String category;
	private String categoryColor;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryColor() {
		return categoryColor;
	}

	public void setCategoryColor(String categoryColor) {
		this.categoryColor = categoryColor;
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
}
