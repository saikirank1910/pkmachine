package com.weekend.assignment10.maintask;

import java.util.Date;

public class MainTask implements AbstractTask{
	private String projectTitle,projectDescriptio,startDate,endDate;
	private int noOfTeamsInMainTask,persid=0,taskId=0;
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public String getProjectDescriptio() {
		return projectDescriptio;
	}
	public void setProjectDescriptio(String projectDescriptio) {
		this.projectDescriptio = projectDescriptio;
	}
	public int getNoOfTeamsInMainTask() {
		return noOfTeamsInMainTask;
	}
	public void setNoOfTeamsInMainTask(int noOfTeamsInMainTask) {
		this.noOfTeamsInMainTask = noOfTeamsInMainTask;
	}
	public int getPersid() {
		return persid;
	}
	public void setPersid(int persid) {
		this.persid = persid;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	public void printDetails() {
		// TODO Auto-generated method stub
		
	}
	
	public String returnDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	public MainTask(String projectTitle, String projectDescriptio, int noOfTeamsInMainTask, int persid, int taskId,String startDate,String endDate) {
		this.projectTitle = projectTitle;
		this.projectDescriptio = projectDescriptio;
		this.noOfTeamsInMainTask = noOfTeamsInMainTask;
		this.persid = persid;
		this.taskId = taskId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}

