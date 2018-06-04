package com.weekend.assignment11.maintask;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTaskBO {

	public int insertMainTaskDetails(String projectTitle, String projectDescription, String noOfTeamsInMainTask,
			String persid, String startDate, String endDate) throws IOException, SQLException {
		int noOfsubTasks,personId;
		try {
			noOfsubTasks = Integer.parseInt(noOfTeamsInMainTask);
			personId = Integer.parseInt(persid);
		} catch (NumberFormatException e) {
			return 2;
		}
		if (projectTitle.isEmpty() || projectDescription.isEmpty() || noOfsubTasks == 0 || personId == 0 || startDate.isEmpty()|| endDate.isEmpty()) {
			return 3;
		}
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");

		MainTask mainTask = new MainTask(projectTitle, projectDescription, noOfsubTasks, personId, 0, startDate,
				endDate);
		MainTaskDao mainTaskDao = (MainTaskDao) applicationContext.getBean("mainTaskDao");
		int result = mainTaskDao.saveMainTask(mainTask);
		if (result == 0) {
			return 1;
		}
		return 0;
	}

	public ArrayList<MainTask> getMainTaskDetails() throws ClassNotFoundException, IOException, SQLException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
		MainTaskDao mainTaskDao = (MainTaskDao) applicationContext.getBean("mainTaskDao");
		ArrayList<MainTask> result = mainTaskDao.mainTaskPrint();
		return result;
	}
}
