package com.prokarma.sourcerer.dto;

public class PanelEvaluation {
	private int panelId;
	private int candidateId;
	private String comment;
	private String skillLevel;
	private String overall_rating;
	private String status;
	private String panelMemberName;
	private String candidateName;
	public int getPanelId() {
		return panelId;
	}
	public void setPanelId(int panelId) {
		this.panelId = panelId;
	}
	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOverall_rating() {
		return overall_rating;
	}
	public void setOverall_rating(String overall_rating) {
		this.overall_rating = overall_rating;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	public String getPanelMemberName() {
		return panelMemberName;
	}
	public void setPanelMemberName(String panelMemberName) {
		this.panelMemberName = panelMemberName;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
}