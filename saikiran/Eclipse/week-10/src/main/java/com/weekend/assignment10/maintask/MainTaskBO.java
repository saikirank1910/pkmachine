package com.weekend.assignment10.maintask;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MainTaskBO {

	public int insertMainTaskDetails(String projectTitle, String projectDescription, int noOfTeamsInMainTask, int persid,String startDate,String endDate)
			throws IOException, SQLException{
		MainTask mainTask = new MainTask(projectTitle, projectDescription, noOfTeamsInMainTask, persid, 0,startDate,endDate);
		MainTaskDao mainTaskDao = new MainTaskDao();
		int result = mainTaskDao.saveMainTask(mainTask);
		if (result == 0) {
			return 1;
		}
		return 0;
	}


	public ArrayList<MainTask> getMainTaskDetails() throws ClassNotFoundException, IOException, SQLException {
		MainTaskDao mainTaskDao = new MainTaskDao();
		ArrayList<MainTask> result = mainTaskDao.mainTaskPrint();
			return result;
	}
}
