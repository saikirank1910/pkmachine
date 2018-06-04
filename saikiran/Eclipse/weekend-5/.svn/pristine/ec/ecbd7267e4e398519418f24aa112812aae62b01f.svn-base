package com.weekend.assignment5.mainconsole;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.weekend.assignment5.maintask.MainTask;
import com.weekend.assignment5.maintask.MainTaskBO;
import com.weekend.assignment5.maintask.MainTaskDaoImpl;
import com.weekend.assignment5.persondetails.Person;
import com.weekend.assignment5.persondetails.PersonBO;
import com.weekend.assignment5.persondetails.PersonDaoImpl;
import com.weekend.assignment5.subtask.SubTask;
import com.weekend.assignment5.subtask.SubTaskBO;
import com.weekend.assignment5.subtask.SubTaskDaoImpl;
public class MainConsole {
	public static void main(String[] args) throws IOException,AgeHandleException, ClassNotFoundException, SQLException {
		Scanner read = new Scanner(System.in);
		String countNumber[]= {"first","second","third","fourth","fifth","sixth","eighth","nineth","tenth"};
		Scanner integerRead = new Scanner(System.in);
		String projectTitle="",projectHeadName="",nameOfTeamHead,nameOfSubTask="",nameOfTeamMember,mainTaskDescription,subTaskDescription="";
		int noOfTeamsInMainTask=0,ageOfProjectHead=0,ageOfTeamHead = 0,time,subTaskTime=0,mainTaskTime=0;
		List<MainTask> listOfMainTasks = null;
		AgeValidator checkAge = new AgeValidator();
		int n=1;
		while(n!=5) {
			System.out.println("1)To Create a main Task 2)Display the tasks which are created 3)update a task 4)delete a task 5)exit..!");
			n=integerRead.nextInt();
			if(n==1) {
			    mainTaskTime = 0;
				listOfMainTasks = new ArrayList<MainTask>();
				System.out.println("ENTER THE MAIN TASK TITLE:");
				projectTitle = read.nextLine(); 
				System.out.println("Enter the main task description:");
				mainTaskDescription = read.nextLine();
				System.out.println("Enter the main task head name:");
				projectHeadName = read.nextLine();
				System.out.println("enter the age of "+projectHeadName);
				ageOfProjectHead = integerRead.nextInt();
				int checkAgeOfPerson = checkAge.checkAgeForMainTask(ageOfProjectHead);
				while(checkAgeOfPerson!=1) {
					try {
						if(checkAgeOfPerson==0)
							throw new AgeHandleException();
					}
					catch(AgeHandleException e1) {
						System.out.println(projectHeadName+" is not eligible to handle the main task"+e1);
					}
					System.out.println("Enter the main task head name:");
					projectHeadName = read.nextLine();
					System.out.println("enter the age of "+projectHeadName);
					ageOfProjectHead = integerRead.nextInt();
					checkAgeOfPerson = checkAge.checkAgeForMainTask(ageOfProjectHead);
				}
				Person person = new Person(projectHeadName,"MAIN-TASK-HEAD",ageOfProjectHead);
				PersonBO personBo = new PersonBO();
				personBo.setPersonDao(new PersonDaoImpl());
				personBo.insertIntoPerson(person);
				System.out.println("How many sub-tasks do you wanted to create for this main-task?");
					noOfTeamsInMainTask = integerRead.nextInt();
					MainTask mainTaskDetails =new MainTask(projectTitle,mainTaskDescription,noOfTeamsInMainTask,projectHeadName,ageOfProjectHead,"MAIN-TASK-HEAD");
					MainTaskBO mainBo = new MainTaskBO();
					mainBo.setMainTaskDao(new MainTaskDaoImpl());
					mainBo.insertMainTaskDetails(mainTaskDetails);
					for(int i=0;i<noOfTeamsInMainTask;i++) {
						System.out.println("enter the name of the "+countNumber[i]+" Team person:");
						nameOfTeamHead = read.nextLine();
						System.out.println("enter the age of the "+countNumber[i]+" person");
						ageOfTeamHead = integerRead.nextInt();	
						checkAgeOfPerson = checkAge.checkAgeForSubTask(ageOfTeamHead);
						try {
							if(checkAgeOfPerson==0)
							{
								throw new AgeHandleException(); 
							}
						}
						catch(AgeHandleException e){
							System.out.println("age conditions did not meet"+e);
							System.out.println(nameOfTeamHead+" is not eligible to handle the main task please assign another person:");
							i--;
							continue;
						}
						Person enterSubTaskDetails = new Person(nameOfTeamHead,"SUB-TASK-MEMBER",ageOfTeamHead);
						
						personBo.setPersonDao(new PersonDaoImpl());
						personBo.insertIntoPerson(enterSubTaskDetails);
						
						System.out.println("Enter the sub task name:");
						nameOfSubTask = read.nextLine();
						System.out.println("Enter the sub task description:");
						subTaskDescription = read.nextLine();	
						MainTask a3 =new MainTask(projectTitle,mainTaskDescription,noOfTeamsInMainTask,projectHeadName,ageOfProjectHead,"MAIN-TASK-HEAD");
						listOfMainTasks.add(a3);
						System.out.println("enter the time for "+countNumber[i]+" sub task: ");
						subTaskTime = integerRead.nextInt();
						System.out.println("time taken for Sub-Task is: "+subTaskTime);
						mainTaskTime = mainTaskTime + subTaskTime;
						listOfMainTasks.get(i).setTime(subTaskTime);
						SubTask sub =new SubTask(nameOfSubTask,subTaskDescription,subTaskTime,projectTitle,nameOfTeamHead,ageOfTeamHead);
						SubTaskBO subTaskBo = new SubTaskBO();
						subTaskBo.setSubTaskDao(new SubTaskDaoImpl());
						subTaskBo.insertSubTaskDetails(sub);	
					}	
					System.out.println("time taken for Main-Task is: "+mainTaskTime); 
				}
			if(n==2) {
				DisplayTaskDetails display =  new DisplayTaskDetails();
				System.out.println("MAIN TASK DETAILS");
				display.mainTaskPrint();
				System.out.println();
				System.out.println("SUB TASK DETAILS");
				display.subTaskPrint();
			}
			if(n==3) {
				int k=0;
				while(k!=3) {
					int updatetaskid,updatedTimeTaken;
					String updateProjectTitle,updateTaskDescription;
					System.out.println("1).Update Main Task Details 2).Update Sub Task Details 3).to quit update window");
					k = integerRead.nextInt();
					if(k==1) {
						System.out.println("enter the main task id to up update...!");
						updatetaskid=integerRead.nextInt();
						System.out.println("ENTER THE MAIN TASK TITLE:");
						updateProjectTitle = read.nextLine(); 
						System.out.println("Enter the main task description:");
						updateTaskDescription = read.nextLine();
						MainTaskBO mainBo = new MainTaskBO();
						mainBo.setMainTaskDao(new MainTaskDaoImpl());
						mainBo.updateMainTaskDetails(updatetaskid,updateProjectTitle,updateTaskDescription);
					}
					if(k==2) {
						System.out.println("enter the main task id to up update...!");
						updatetaskid=integerRead.nextInt();
						System.out.println("ENTER THE MAIN TASK TITLE:");
						updateProjectTitle = read.nextLine(); 
						System.out.println("Enter the main task description:");
						updateTaskDescription = read.nextLine();
						System.out.println("Enter the time for "+updateProjectTitle+" subtask");
						updatedTimeTaken=integerRead.nextInt();
						SubTaskBO subBo = new SubTaskBO();
						subBo.setSubTaskDao(new SubTaskDaoImpl());
						subBo.updateSubTaskDetails(updatetaskid,updateProjectTitle,updateTaskDescription,updatedTimeTaken);
					}
					if(k==3) {
						System.out.println("Returning to main Menu");
						break;
					}
				}
			}
			if(n==4) {
				int mainTaskId;
				System.out.println("Enter the main task id to delete the task");
				mainTaskId=read.nextInt();
				DeleteTable dt = new DeleteTable();
				dt.deleteTask(mainTaskId);
			}
			if(n==5) {
				System.out.println("bbye....!");
				break;
			}
		}
		read.close();
		integerRead.close();
	}
}