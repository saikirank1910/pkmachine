package com.weekend.assignment5.maintask;

import java.io.IOException;
import java.sql.SQLException;

public class MainTaskBO {
	private MainTaskDao mainTaskDao;
	public MainTaskDao getMainTaskDao() {
		return mainTaskDao;
	}
	public void setMainTaskDao(MainTaskDao mainTaskDao) {
		this.mainTaskDao = mainTaskDao;
	}
	public boolean insertMainTaskDetails(MainTask mainTask) throws IOException, SQLException{
		int result = getMainTaskDao().saveMainTask(mainTask);
		if(result==1)
			return true;
		return false;
	}
	public boolean updateMainTaskDetails(int updatetaskid, String updateProjectTitle, String updateTaskDescription) throws IOException, SQLException, ClassNotFoundException{
		int result = getMainTaskDao().updateMainTask(updatetaskid,updateProjectTitle,updateTaskDescription);
		if(result==1)
			return true;
		return false;
	}
}
