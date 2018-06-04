package com.pkrm.event.model;

import java.util.Date;

public class EmployeeInformation {
	private int eventId;
	private int employeeId;
	private String employeeName;
	private String position;
	private Date dateOnDuty;
	private String timezone;
	private String direction;
	private String lastJobBreifing;
	private String suspended;
	private String regulation;
	private String isHeOperatingLongerThanSixMonths;
	private Date timeOnDuty;
	private Date hoursOnDuty;
	private Date previousRest;
	


	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getDateOnDuty() {
		return dateOnDuty;
	}

	public void setDateOnDuty(Date dateOnDuty) {
		this.dateOnDuty = dateOnDuty;
	}

	public String getTimeZone() {
		return timezone;
	}

	public void setTimeZone(String timeZone) {
		this.timezone = timeZone;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLastJobBreifing() {
		return lastJobBreifing;
	}

	public void setLastJobBreifing(String lastJobBreifing) {
		this.lastJobBreifing = lastJobBreifing;
	}

	public String getSuspended() {
		return suspended;
	}

	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}

	public String getRegulation() {
		return regulation;
	}

	public void setRegulation(String regulation) {
		this.regulation = regulation;
	}

	public String getIsHeOperatingLongerThanSixMonths() {
		return isHeOperatingLongerThanSixMonths;
	}

	public void setIsHeOperatingLongerThanSixMonths(String isHeOperatingLongerThanSixMonths) {
		this.isHeOperatingLongerThanSixMonths = isHeOperatingLongerThanSixMonths;
	}

	public Date getTimeOnDuty() {
		return timeOnDuty;
	}

	public void setTimeOnDuty(Date timeOnDuty) {
		this.timeOnDuty = timeOnDuty;
	}

	public Date getHoursOnDuty() {
		return hoursOnDuty;
	}

	public void setHoursOnDuty(Date hoursOnDuty) {
		this.hoursOnDuty = hoursOnDuty;
	}

	public Date getPreviousRest() {
		return previousRest;
	}

	public void setPreviousRest(Date previousRest) {
		this.previousRest = previousRest;
	}

	@Override
	public String toString() {
		return "EmployeeInformation [eventId=" + eventId + ", employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", position=" + position + ", dateOnDuty=" + dateOnDuty + ", timeZone=" + timezone
				+ ", direction=" + direction + ", lastJobBreifing=" + lastJobBreifing + ", suspended=" + suspended
				+ ", regulation=" + regulation + ", isHeOperatingLongerThanSixMonths="
				+ isHeOperatingLongerThanSixMonths + ", timeOnDuty=" + timeOnDuty + ", hoursOnDuty=" + hoursOnDuty
				+ ", previousRest=" + previousRest + "]";
	}
	
	

}
