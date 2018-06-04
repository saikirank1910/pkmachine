package com.weekend.assignment5.maintask;

import com.weekend.assignment5.mainconsole.AbstractTask;

public class MainTask implements AbstractTask{
	private String nameOfTeamHead,nameOfSubTask,projectTitle,projectHeadName,projectDescription,designation="";
	private int ageOfTeamHead=0,noOfTeamsInMainTask,ageOfProjectHead,count,time=0;
	String countNumber[]= {"first","second","third","fourth","fifth","sixth","eighth","nineth","tenth"};
	
	
	
	public MainTask(String projectTitle, String projectDescription, int noOfTeamsInMainTask,String projectHeadName,int ageOfProjectHead,String designation) {
		this.projectTitle = projectTitle;
		this.projectDescription = projectDescription;
		this.noOfTeamsInMainTask = noOfTeamsInMainTask;
		this.projectHeadName = projectHeadName;
		this.ageOfProjectHead = ageOfProjectHead;
		this.designation = designation;
	}
	public int getTime() {
		return time;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getProjectHeadName() {
		return projectHeadName;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	
	public int getAgeOfProjectHead() {
		return ageOfProjectHead;
	}
	public String getNameOfTeamHead() {
		return nameOfTeamHead;
	}
	public String getNameOfSubTask() {
		return nameOfSubTask;
	}
	public void setTime(int time) {
		this.time = time;
	}
	void CalculateHours() {
		System.out.println("Time taken for Subtask is : "+time);
	}
	public void printDetails() {
		System.out.println("Project title is "+projectTitle+" and the project head name is "+projectHeadName+" "+(countNumber[count])+" subteam is headed by "+nameOfTeamHead);
	}

	public String returnDetails() {
		return "subteam is headed by "+nameOfTeamHead+" and his "+countNumber[count]+" team name is "+nameOfSubTask;
	}
	String returnHours() {
		return "Time taken for "+countNumber[count]+" Subtask is : "+time;
	}
	public int getNoOfTeamsInMainTask() {
		return noOfTeamsInMainTask;
	}
}

