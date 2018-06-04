package com.weekend.assignment5.subtask;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.weekend.assignment5.dbsettings.ConnectionFactory;
import com.weekend.assignment5.dbsettings.DBSettings;

public class SubTaskDaoImplTest {

	@Test
	public void testAddIntoDb() throws IOException, SQLException {
		SubTask subTask = new SubTask("testdata","abcd abcd abcd",25,"","abcd",25);
		SubTaskDaoImpl enterTaskDetails = new SubTaskDaoImpl();
		int result = enterTaskDetails.saveSubTask(subTask);
		assertEquals(1,result);
		
		
		Connection con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD); 
		Statement stmt= con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT subtaskid FROM subtask where subtaskname='test'");
		int subTaskId=0;
		if (rs!=null && rs.next()) {
		 subTaskId = rs.getInt(1);			
		}
		rs=stmt.executeQuery("delete from maintask where maintaskid='"+subTaskId+"'");
		con.close();
		rs.close();
		stmt.close();
	}

	@Test
	public void testUpdateSubTaskDetailsInDb() throws ClassNotFoundException, IOException, SQLException {
		SubTaskDaoImpl obj = new SubTaskDaoImpl();
		
		Connection con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD); 
		Statement stmt = con.createStatement();
		String sql,taskTitle="",taskDescription="";
		int taskid=11;
		sql = "select subtaskname,subtaskdesc from subtask where subtaskid=2" ;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			taskTitle = rs.getString(1);
			taskDescription = rs.getString(2);
		}	
		
		//testing 
		int result = obj.updateSubTaskDetailsInDb(2, "amsn ba","adjkf",25);
		assertEquals(1,result);
		
		//updating it to original record
		PreparedStatement updateStatement = con.prepareStatement("update subtask set subtaskname=? , subtaskdesc=? where subtaskid=?");
		updateStatement.setString(1,taskTitle); 
		updateStatement.setString(2,taskDescription); 
		updateStatement.setInt(3,taskid); 
		updateStatement.executeUpdate();  
		con.close();
	}

}
