package com.weekend.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.concurrent.Callable;

public class MultiThreadProcess implements Callable<Integer>{
	public Integer call() {
		long timeTakenForMultiThread,startTimeOfMultiThread=0,endTimeOfMultiThread=0;
	    	try {
				String taskDetails="";
				startTimeOfMultiThread = System.currentTimeMillis();
				FileWriter fileWritter = new FileWriter("D:\\saikiran\\newEclipse\\week-end-assignment\\src\\main\\resources\\textfile\\MultiThread.csv",true);
				BufferedWriter bw = new BufferedWriter(fileWritter);
				
				for(int i=1;i<5000;i++) {
					taskDetails ="";
					System.out.println(Thread.currentThread().getName());
					MainTask task = new MainTask(0,"saikiran", 23, "Prepare_Cement_Mixture_on_Day","Building_a_flyover","Ravi",2,33);
					task.setTime(8);
					task.setProjectDescription("Buliding_a_flyover_between_JNTU_to_Forum");
					taskDetails = taskDetails +" ";
					taskDetails = taskDetails + task.getProjectTitle()+" ";
					taskDetails = taskDetails + task.getProjectDescription()+" ";
					taskDetails = taskDetails + task.getProjectHeadName()+" ";
					taskDetails = taskDetails + task.getNameOfSubTask()+"_ ";
					taskDetails = taskDetails + task.getTime()+" ";
					taskDetails = taskDetails + task.getNameOfTeamHead()+" ";
					taskDetails = taskDetails + task.getAgeOfProjectHead()+" "+"\n";
					//System.out.println(taskDetails);
					fileWritter.write(taskDetails);
				}
				endTimeOfMultiThread = System.currentTimeMillis();
				bw.close();
			}
			catch (Exception e) {
	            e.printStackTrace();
	        }
	    	timeTakenForMultiThread = (endTimeOfMultiThread-startTimeOfMultiThread);
			return (int)timeTakenForMultiThread;
	    }
}
