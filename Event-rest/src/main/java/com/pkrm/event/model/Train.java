package com.pkrm.event.model;

import java.util.Date;

public class Train {
	private int tId;
	private String trainId;
	private int trainSection;
	private Date trainDate;
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public int getTrainSection() {
		return trainSection;
	}
	public void setTrainSection(int trainSection) {
		this.trainSection = trainSection;
	}
	public Date getTrainDate() {
		return trainDate;
	}
	public void setTrainDate(Date trainDate) {
		this.trainDate = trainDate;
	}

}
