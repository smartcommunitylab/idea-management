package it.smartcommunitylab.platform.idea.beans;

public class CategoryBean {
	private String name;
	private String color;

	public CategoryBean(String name, String color) {
		super();
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
