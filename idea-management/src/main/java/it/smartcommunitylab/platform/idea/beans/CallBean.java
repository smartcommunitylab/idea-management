package it.smartcommunitylab.platform.idea.beans;

import java.util.Date;

public class CallBean {
	private long id;
	private String title;
	private String description;
	private Date deadline;
	private Date publicationDeadline;
	private Date realizationDeadline;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the publicationDeadline
	 */
	public Date getPublicationDeadline() {
		return publicationDeadline;
	}

	/**
	 * @return the realizationDeadline
	 */
	public Date getRealizationDeadline() {
		return realizationDeadline;
	}

	/**
	 * @param realizationDeadline the realizationDeadline to set
	 */
	public void setRealizationDeadline(Date realizationDeadline) {
		this.realizationDeadline = realizationDeadline;
	}

	/**
	 * @param publicationDeadline the publicationDeadline to set
	 */
	public void setPublicationDeadline(Date publicationDeadline) {
		this.publicationDeadline = publicationDeadline;
	}

}
