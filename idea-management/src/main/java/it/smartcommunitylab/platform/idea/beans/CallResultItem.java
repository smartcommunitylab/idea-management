package it.smartcommunitylab.platform.idea.beans;

import java.util.List;

public class CallResultItem {
	private String title;
	private int ideas;
	private String deadline;
	private List<CategoryBean> cats;
	private String detailURL;
	private String deleteURL;
	private int comments;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIdeas() {
		return ideas;
	}

	public void setIdeas(int ideas) {
		this.ideas = ideas;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public List<CategoryBean> getCats() {
		return cats;
	}

	public void setCats(List<CategoryBean> cats) {
		this.cats = cats;
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

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

}
