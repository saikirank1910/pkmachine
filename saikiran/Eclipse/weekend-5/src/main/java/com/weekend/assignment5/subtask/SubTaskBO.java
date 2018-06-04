package com.weekend.assignment5.subtask;

import java.io.IOException;
import java.sql.SQLException;

import com.weekend.assignment5.maintask.MainTask;

public class SubTaskBO {
	private SubTaskDao subTaskDao;
	public SubTaskDao getSubTaskDao() {
		return subTaskDao;
	}
	public void setSubTaskDao(SubTaskDao subTaskDao) {
		this.subTaskDao = subTaskDao;
	}
	public boolean insertSubTaskDetails(SubTask subTask) throws IOException, SQLException{
		int result = getSubTaskDao().saveSubTask(subTask);
		if(result==1)
			return true;
		return false;
	}
	public boolean updateSubTaskDetails(int updatetaskid,String updateProjectTitle,String updateTaskDescription,int updatedTimeTaken) throws IOException, SQLException, ClassNotFoundException{
		int result = getSubTaskDao().updateSubTaskDetailsInDb(updatetaskid,updateProjectTitle,updateTaskDescription,updatedTimeTaken);
		if(result==1)
			return true;
		return false;
	}
}
