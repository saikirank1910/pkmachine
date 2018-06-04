package com.weekend.assignment5.maintask;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.weekend.assignment5.dbsettings.ConnectionFactory;
import com.weekend.assignment5.dbsettings.DBSettings;

public class MainTaskDaoImplTest {

	@Test
	public void testAddIntoDb() throws IOException, SQLException {
		MainTask mainTask = new MainTask("testdata","Description",3,"Head-name", 32,"Main-task-head");
		MainTaskDaoImpl enterMainTask = new MainTaskDaoImpl();
		int result = enterMainTask.saveMainTask(mainTask);
		assertEquals(1,result);
		
		//deleting entered data
		Connection con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD); 
		Statement stmt= con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT maintaskid FROM maintask where maintaskname='testdata'");
		int mainTaskId=0;
		if (rs!=null && rs.next()) {
		 mainTaskId = rs.getInt(1);			
		}
		rs=stmt.executeQuery("delete from maintask where maintaskid='"+mainTaskId+"'");
		con.close();
		rs.close();
		stmt.close();
	}

	@Test
	public void testUpdateMainTaskDetailsInDb() throws ClassNotFoundException, IOException, SQLException {
		MainTaskDaoImpl update = new MainTaskDaoImpl();
		Connection con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD); 
		Statement stmt = con.createStatement();
		String sql,taskTitle="",taskDescription="";
		int taskid=7;
		sql = "select maintaskname,maintaskdesc from mainTask where maintaskid=1" ;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			taskTitle=rs.getString(1);
			taskDescription=rs.getString(2);
		}
		//testing
		int result = update.updateMainTask(1,"afasfdasf","asasdff");
		assertEquals(1,result);

		//updating it to original record
		PreparedStatement updateStatement = con.prepareStatement("update maintask set maintaskname=? , maintaskdesc=? where maintaskid=?");
		updateStatement.setString(1,taskTitle); 
		updateStatement.setString(2,taskDescription); 
		updateStatement.setInt(3,taskid); 
	    updateStatement.executeUpdate();  
		con.close();
	}

}
