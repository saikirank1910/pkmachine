package com.weekend.assignment3;

public class MainTask extends AbstractTask{
	private String nameOfTeamHead,nameOfSubTask,projectTitle,projectHeadName,projectDescription;
	private int ageOfTeamHead,noOfTeamsInMainTask,ageOfProjectHead,count,time=0;
	String countNumber[]= {"first","second","third","fourth","fifth","sixth","eighth","nineth","tenth"};
	
	
	
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
	public MainTask(int count,String nameOfTeamHead,int ageOfTeamHead,String nameOfSubTask,String projectTitle,String projectHeadName,int noOfTeamsInMainTask,int ageOfProjectHead) {
		this.count = count;
		this.nameOfTeamHead = nameOfTeamHead;
		this.nameOfSubTask = nameOfSubTask;
		this.projectTitle = projectTitle;
		this.projectHeadName = projectHeadName;
		this.ageOfTeamHead = ageOfTeamHead;
		this.noOfTeamsInMainTask = noOfTeamsInMainTask;
		this.ageOfProjectHead = ageOfProjectHead;
	}
	void CalculateHours() {
		System.out.println("Time taken for Subtask is : "+time);
	}
	void printDetails() {
		System.out.println("Project title is "+projectTitle+" and the project head name is "+projectHeadName+" "+(countNumber[count])+" subteam is headed by "+nameOfTeamHead);
	}
	
	
	String returnDetails() {
		return "subteam is headed by "+nameOfTeamHead+" and his "+countNumber[count]+" team name is "+nameOfSubTask;
	}
	String returnHours() {
		return "Time taken for "+countNumber[count]+" Subtask is : "+time;
	}
}

