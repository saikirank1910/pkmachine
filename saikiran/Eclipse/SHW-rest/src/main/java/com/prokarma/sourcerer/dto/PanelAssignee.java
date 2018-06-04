package com.prokarma.sourcerer.dto;

public class PanelAssignee {
	
	private int panelId;
	private int candidateId;
	private int token;
	private int isVisited; 
	private String panelEmail;
	private String panelName;
	private String modeOfInterview;
	private int round;
	

	public String getPanelName() {
		return panelName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
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
	public int getToken() {
		return token;
	}
	public void setToken(int token) {
		this.token = token;
	}
	public int getIsVisited() {
		return isVisited;
	}
	public void setIsVisited(int isVisited) {
		this.isVisited = isVisited;
	}
	public String getPanelEmail() {
		return panelEmail;
	}
	public void setPanelEmail(String panelEmail) {
		this.panelEmail = panelEmail;
	}
	public String getModeOfInterview() {
		return modeOfInterview;
	}
	public void setModeOfInterview(String modeOfInterview) {
		this.modeOfInterview = modeOfInterview;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	
	

}
