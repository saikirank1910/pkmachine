package com.weekend.assignment5.maintask;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.weekend.assignment5.dbsettings.ConnectionFactory;
import com.weekend.assignment5.dbsettings.DBSettings;

public class MainTaskDaoImpl implements MainTaskDao{
	public int saveMainTask(MainTask mainTask) throws IOException, SQLException {
		 Connection con=null;
		 Statement stmt=null;
		 ResultSet rs=null;
		 CallableStatement cstmt=null;
		 try{
				con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD);
				con.setAutoCommit(false);
				//System.out.println(projectHeadName+" name"+ageOfProjectHead+" age");
				stmt= con.createStatement();
				int personId=0;
			    rs = stmt.executeQuery("SELECT persid FROM pers where name='"+mainTask.getProjectHeadName()+"' and age='"+mainTask.getAgeOfProjectHead()+"'");
				if (rs!=null && rs.next()) {
				 personId = rs.getInt(1);			
				 	rs.close();
				}
				//System.out.println(personId+" of main task");		
				stmt.close(); 
				cstmt = con.prepareCall("{CALL insert_mainTask(main_task_seq.nextval,?,?,?,?)}");
				cstmt.setString(1,mainTask.getProjectTitle());
				cstmt.setString(2,mainTask.getProjectDescription());
				cstmt.setInt(3,mainTask.getNoOfTeamsInMainTask());
				cstmt.setInt(4,personId);
				int result = cstmt.executeUpdate();
				con.commit();
			    con.close();
			    stmt.close();
			    rs.close();
			    if(result==0)
			    	return 0;
		    }
			 catch(SQLException se)
			 {
				 con.rollback();
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
			    catch(SQLException se){
			       se.printStackTrace();
			    }
			    try{
				       if(stmt!=null)
				          stmt.close();
				    }
				    catch(SQLException se){
				       se.printStackTrace();
				    }
			    try{
				       if(rs!=null)
				          rs.close();
				    }
				    catch(SQLException se){
				       se.printStackTrace();
				    }
			    try{
				       if(cstmt!=null)
				    	   cstmt.close();
				    }
				    catch(SQLException se){
				       se.printStackTrace();
				    }
			 }
		return 1;
	}
	public int updateMainTask(int taskid,String taskTitle,String taskDescription) throws IOException, ClassNotFoundException {
		 Connection con=null;
		 PreparedStatement updateStatement=null;
		 try{
			con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD);	
			con.setAutoCommit(false);
			updateStatement = con.prepareStatement("update maintask set maintaskname=? , maintaskdesc=? where maintaskid=?");
			updateStatement.setString(1,taskTitle); 
			updateStatement.setString(2,taskDescription); 
			updateStatement.setInt(3,taskid); 
			int result = updateStatement.executeUpdate();  
			con.commit();
			con.close();
			if(result==0)
				return 0;	
		}
		 catch (SQLException e) {
			 System.out.println(e);
		}
		 finally{   
			    try{
			       if(con!=null)
			          con.close();
			    }
			    catch(SQLException se){
			       se.printStackTrace();
			    }
			    try{
				      if(updateStatement!=null)
				    	   updateStatement.close();
				    }
				    catch(SQLException se){
				       se.printStackTrace();
				    }
		}
		return 1;
	}
}
