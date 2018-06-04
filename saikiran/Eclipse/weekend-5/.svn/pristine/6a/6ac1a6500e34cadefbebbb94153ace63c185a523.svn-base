package com.weekend.assignment5.mainconsole;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.weekend.assignment5.dbsettings.ConnectionFactory;
import com.weekend.assignment5.dbsettings.DBSettings;

public class DeleteTable {
	@SuppressWarnings("resource")
	public void deleteTask(int mainTaskId) throws IOException, SQLException {
		Connection con = null;
		PreparedStatement deleteStatement = null;
		try{
			con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD);
			con.setAutoCommit(false);
			
			System.out.println("deleting a task details whose id is "+mainTaskId+" in maintask and subtask");
			deleteStatement = con.prepareStatement("delete from subtask where maintaskid=?");
			deleteStatement.setInt(1,mainTaskId); 
			deleteStatement.executeUpdate();
			deleteStatement.close();
			
			deleteStatement = con.prepareStatement("delete from maintask where maintaskid=?");
			deleteStatement.setInt(1,mainTaskId); 
			deleteStatement.executeUpdate();
			con.commit();
			con.close();
			deleteStatement.close();
		 }
		 catch(SQLException se)
		 {
		    se.printStackTrace();
		 }
		 catch(Exception e){
			
		    e.printStackTrace();
		 }
		 finally{
		    try{
		       if(con!=null)
		          con.close();
		    }
		    catch(SQLException se)
		    {
		       se.printStackTrace();
		    }
		    try{
			   if(deleteStatement!=null)
			    	   deleteStatement.close();
			    }
			    catch(SQLException se)
			    {
			       se.printStackTrace();
			    }
		   
		 }
	}
}
