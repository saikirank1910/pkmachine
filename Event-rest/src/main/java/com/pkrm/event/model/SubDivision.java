package com.pkrm.event.model;

public class SubDivision {
	private int subDivisionId;
	private String subDivisionName;
	private String regionName;
	private int milePost;
	private String serviceUnitName;
	
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public int getMilePost() {
		return milePost;
	}
	public void setMilePost(int milePost) {
		this.milePost = milePost;
	}
	public String getServiceUnitName() {
		return serviceUnitName;
	}
	public void setServiceUnitName(String serviceUnitName) {
		this.serviceUnitName = serviceUnitName;
	}
	public int getSubDivisionId() {
		return subDivisionId;
	}
	public void setSubDivisionId(int subDivisionId) {
		this.subDivisionId = subDivisionId;
	}
	public String getSubDivisionName() {
		return subDivisionName;
	}
	public void setSubDivisionName(String subDivisionName) {
		this.subDivisionName = subDivisionName;
	}
	
}
