package com.weekend.assignment3;

import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class MainConsole {
	public static void main(String[] args) throws IOException,AgeHandleException {
		Scanner read = new Scanner(System.in);
		FileWriter fileWritter = new FileWriter("Input.txt",true);
	    FileReader fileReader = new FileReader("Input.txt");
	    BufferedWriter writeData = new BufferedWriter(fileWritter);
		String countNumber[]= {"first","second","third","fourth","fifth","sixth","eighth","nineth","tenth"};
		Scanner integerRead = new Scanner(System.in);
		String projectTitle="",projectHeadName="",nameOfTeamHead,nameOfSubTask="",nameOfTeamMember,mainTaskDescription,subTaskDescription;
		int noOfTeamsInMainTask=0,ageOfProjectHead=0,ageOfTeamHead = 0,time,subTaskTime=0,mainTaskTime=0;
		List<MainTask> listOfMainTasks = null;
		ArrayList<ArrayList<SubTask>> listOfSubTasks = null;
		int n=1;
		while(n!=5) {
			System.out.println("1)To Create a main Task 2)Display the tasks which are created 3)single threaded process 4)multi thread process 5)exit..!");
			n=integerRead.nextInt();
			if(n==1) {
			    mainTaskTime = 0;
				listOfMainTasks = new ArrayList<MainTask>();
				listOfSubTasks = new ArrayList<ArrayList<SubTask>>();
				System.out.println("ENTER THE MAIN TASK TITLE:");
				projectTitle = read.nextLine(); 
				System.out.println("Enter the main task head name:");
				projectHeadName = read.nextLine();
				System.out.println("Enter the main task description:");
				mainTaskDescription = read.nextLine();
				System.out.println("enter the age of "+projectHeadName);
				try {
					ageOfProjectHead = integerRead.nextInt();
					if(ageOfProjectHead<30) {
						throw new AgeHandleException();
					}
				}
				catch(AgeHandleException e) 
				{
					System.out.println(projectHeadName+" is not eligible to handle the main task "+e);
					ExceptionCheck(ageOfProjectHead,projectHeadName);
				}
				System.out.println("How many sub-jobs do you wanted to create for this main-task?");
					noOfTeamsInMainTask = integerRead.nextInt();
					for(int i=0;i<noOfTeamsInMainTask;i++) {
						System.out.println("enter the name of the "+countNumber[i]+" Team head:");
						nameOfTeamHead = read.nextLine();
						System.out.println("enter the age of the "+countNumber[i]+" person");
						try {
							ageOfTeamHead = integerRead.nextInt();
							
							if(ageOfTeamHead<20 || ageOfTeamHead>30)
							{
								throw new AgeHandleException(); 
							}
						}
						catch(AgeHandleException e){
							System.out.println("age conditions did not meet"+e);
							if(ageOfTeamHead<20 || ageOfTeamHead>30) {
							System.out.println(nameOfTeamHead+" is not eligible to handle the main task please assign another person:");
							i--;
							continue;
							}
						}
						System.out.println("Enter the sub task name:");
						nameOfSubTask = read.nextLine();
						System.out.println("Enter the main task description:");
						subTaskDescription = read.nextLine();	
						MainTask a1 =new MainTask(i,nameOfTeamHead,ageOfTeamHead,nameOfSubTask,projectTitle,projectHeadName,noOfTeamsInMainTask,ageOfProjectHead);
						listOfMainTasks.add(a1);
					}
					for(int i=0;i<noOfTeamsInMainTask;i++) {
						listOfMainTasks.get(i).printDetails();
						System.out.println("enter the time for "+countNumber[i]+" sub task: ");
						subTaskTime = integerRead.nextInt();
						System.out.println("time taken for Sub-Task is: "+subTaskTime);
						mainTaskTime = mainTaskTime + subTaskTime;
						listOfMainTasks.get(i).setTime(subTaskTime);
					}	
					System.out.println("time taken for Main-Task is: "+mainTaskTime); 
				    String projectDetails,mainTaskDetails="",subTaskDetails="",finalDetails;
				    projectDetails= projectTitle + " is the main-task name and the main-task head is " + projectHeadName +" it consists of "+ noOfTeamsInMainTask +" sub-tasks\n";   
				    for(int i=0;i<noOfTeamsInMainTask;i++) {
						mainTaskDetails = mainTaskDetails + listOfMainTasks.get(i).returnDetails()+"\n";
						mainTaskDetails = mainTaskDetails + listOfMainTasks.get(i).returnHours()+"\n";
					}
					subTaskDetails = subTaskDetails+"Time taken for Main-Task is: "+mainTaskTime+"\n";
					finalDetails = projectDetails+mainTaskDetails+subTaskDetails;
					writeData.append("!"+"\n");
					writeData.append(projectTitle+"\n");
					writeData.append(finalDetails+"\n");
					writeData.flush();
				}
			if(n==2) {
				BufferedReader readData = new BufferedReader(fileReader);
				FileOperations add = new FileOperations();
				add.add(readData);
			}
			if(n==3) {
				SingleThreadProcess obj = new SingleThreadProcess();
				Thread singleThreadProcess = new Thread(obj);
				singleThreadProcess.start();
		        
			}
			if(n==4) {
				FileWriter fileWritter1 = new FileWriter("D:\\saikiran\\newEclipse\\week-end-assignment\\src\\main\\resources\\textfile\\MultiThread.csv",true);
				BufferedWriter bw = new BufferedWriter(fileWritter1);
				
				String taskDetails = "S.No. Main_Task_Title Main_Task_Description Project_head Sub_Task_Title EstimateHours Person_Name Person_Age"+"\n";
				fileWritter1.write(taskDetails);
				int timeTakenForMultiThread=0;
				ExecutorService executor = Executors.newFixedThreadPool(5);
				  List<Future<Integer>> list = new ArrayList<Future<Integer>>();
			        Callable<Integer> callable = new MultiThreadProcess();
			        for(int i=0; i<2; i++){
			            Future<Integer> future = executor.submit(callable);
			            list.add(future);
			        }
			        for(Future<Integer> abcd : list){
			                try {
								timeTakenForMultiThread = timeTakenForMultiThread + abcd.get();
								//System.out.println(abcd.get());
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (ExecutionException e) {
								e.printStackTrace();
							}
			        }
			        System.out.println("Time taken for Multi Thread is: "+timeTakenForMultiThread+" milliseconds");
				executor.shutdown();
			}
			if(n==5) {
				System.out.println("bbye....!");
				break;
			}
		}
		read.close();
		integerRead.close();
		fileReader.close();
		fileWritter.close();
		writeData.close();
	}
	public static void ExceptionCheck(int ageOfProjectHead,String projectHeadName) {
		Scanner read = new Scanner(System.in);
		Scanner integerRead = new Scanner(System.in);
		while(ageOfProjectHead<30) {
			System.out.println("Enter the project head name:");
			projectHeadName = read.nextLine();
			
			System.out.println("enter the age of "+projectHeadName);
			ageOfProjectHead = integerRead.nextInt();
			try {
				throw new AgeHandleException();
			}
			catch(AgeHandleException e1) {
				System.out.println(projectHeadName+" is not eligible to handle the main task"+e1);
				ExceptionCheck(ageOfProjectHead, projectHeadName);
			}
		}
		read.close();
		integerRead.close();
	}
}