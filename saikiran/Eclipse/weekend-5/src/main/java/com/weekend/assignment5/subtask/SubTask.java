package com.weekend.assignment5.subtask;

import com.weekend.assignment5.mainconsole.AbstractTask;
public class SubTask implements AbstractTask {
	private String nameOfTeamMember,nameOfSubTask,nameOfSubTaskDescription,projectTitle,nameOfTeamHead;
	String countNumber[]= {"first","second","third","fourth","fifth","sixth","eighth","nineth","tenth"};
    private int time,ageOfTeamHead;
	
	public SubTask(String nameOfSubTask, String nameOfSubTaskDescription, int time,String projectTitle,String nameOfTeamHead,int ageOfTeamHead) {
		this.nameOfSubTask = nameOfSubTask;
		this.nameOfSubTaskDescription = nameOfSubTaskDescription;
		this.time = time;
		this.projectTitle = projectTitle;
		this.nameOfTeamHead = nameOfTeamHead;
		this.ageOfTeamHead = ageOfTeamHead;
	}
	public void printDetails() {
		System.out.println(" name is "+nameOfTeamMember+" time alloted to him is "+time);
	}
	
	public String returnDetails() {
		return "name is "+nameOfTeamMember+" time alloted to him is "+time;
	}
	
	
	public String getNameOfTeamMember() {
		return nameOfTeamMember;
	}
	public String getNameOfSubTask() {
		return nameOfSubTask;
	}
	public String getNameOfSubTaskDescription() {
		return nameOfSubTaskDescription;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public String getNameOfTeamHead() {
		return nameOfTeamHead;
	}
	public String[] getCountNumber() {
		return countNumber;
	}
	public int getTime() {
		return time;
	}
	public int getAgeOfTeamHead() {
		return ageOfTeamHead;
	}
}