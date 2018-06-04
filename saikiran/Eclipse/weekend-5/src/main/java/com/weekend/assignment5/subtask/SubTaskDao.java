package com.weekend.assignment5.subtask;

import java.io.IOException;
import java.sql.SQLException;

public interface SubTaskDao {
	 int updateSubTaskDetailsInDb(int id, String title,String description,int timeTaken) throws IOException, ClassNotFoundException;
	 int saveSubTask(SubTask subTask) throws IOException, SQLException;
}
