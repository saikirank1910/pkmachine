package com.prokarma.sourcerer.dto;

public class Subtechnology {
	private String technology;
	private int id;
	private String subTechnologyName;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getSubTechnology() {
		return subTechnologyName;
	}

	public void setSubTechnology(String subTechnology) {
		this.subTechnologyName = subTechnology;
	}
}