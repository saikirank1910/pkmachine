package com.weekend.assignment3;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class SingleThreadProcess implements Runnable{
	
	public void run() {
		try {
			String taskDetails="";
			long startTimeOfSingleThread = System.currentTimeMillis();
			
			FileWriter fileWritter = new FileWriter("D:\\saikiran\\newEclipse\\week-end-assignment\\src\\main\\resources\\textfile\\SingleThread.csv");
			BufferedWriter bw = new BufferedWriter(fileWritter);
			taskDetails = "S.No. Main_Task_Title Main_Task_Description Project_head Sub_Task_Title EstimateHours Person_Name Person_Age"+"\n";
			fileWritter.write(taskDetails);
			for(int i=1;i<=10000;i++) {
				taskDetails ="";
				MainTask task = new MainTask(0,"saikiran", 23, "Prepare_Cement_Mixture_on_Day","Building_a_flyover","Ravi",2,33);
				task.setTime(8);
				task.setProjectDescription("Buliding_a_flyover_between_JNTU_to_Forum");
				taskDetails = taskDetails + i+" ";
				taskDetails = taskDetails + task.getProjectTitle()+" ";
				taskDetails = taskDetails + task.getProjectDescription()+" ";
				taskDetails = taskDetails + task.getProjectHeadName()+" ";
				taskDetails = taskDetails + task.getNameOfSubTask()+"_"+i+" ";
				taskDetails = taskDetails + task.getTime()+" ";
				taskDetails = taskDetails + task.getNameOfTeamHead()+" ";
				taskDetails = taskDetails + task.getAgeOfProjectHead()+" "+"\n";
				//System.out.println(taskDetails);
				fileWritter.write(taskDetails);
			}
			//System.out.println(taskDetails);
			long endTimeOfSingleThread = System.currentTimeMillis();
			System.out.println("Time taken for Single Thread is: "+(endTimeOfSingleThread-startTimeOfSingleThread)+" milliseconds");
			bw.close();
		}
		catch (Exception e) {
            e.printStackTrace();
        }
	}
}
