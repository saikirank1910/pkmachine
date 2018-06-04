package com.weekend.assignment5.subtask;

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

public class SubTaskDaoImpl implements SubTaskDao{
	public int saveSubTask(SubTask subTask) throws IOException, SQLException {
		 Connection con=null;
		 Statement stmt=null;
		 ResultSet rs=null;
		 ResultSet rs1=null;
		 CallableStatement cstmt=null;
		 try{
			con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD);
			con.setAutoCommit(false);
				stmt= con.createStatement();
				int personId=0;
				rs = stmt.executeQuery("SELECT persid FROM pers where name='"+subTask.getNameOfTeamHead()+"' and age='"+subTask.getAgeOfTeamHead()+"'");
				if (rs!=null && rs.next()) {
				 personId = rs.getInt(1);			
				 	rs.close();
				}
				stmt.close(); 	
				stmt= con.createStatement();
				int mainTaskId=0;
				rs1 = stmt.executeQuery("SELECT maintaskid from maintask where maintaskname='"+subTask.getProjectTitle()+"'");
				if (rs1!=null && rs1.next()) {
				 mainTaskId = rs1.getInt(1);			
				 	rs1.close();
				}	
				stmt.close();
				cstmt = con.prepareCall("{CALL insert_subTask(?,sub_task_seq.nextval,?,?,?,?)}");
				cstmt.setInt(1,mainTaskId);
				cstmt.setString(2,subTask.getNameOfSubTask());
				cstmt.setString(3,subTask.getNameOfSubTaskDescription());
				cstmt.setInt(4, personId);
				cstmt.setInt(5,subTask.getTime());
				int result = cstmt.executeUpdate();
				if(result==0)
					return 0;
				con.commit();
			    con.close();
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
					       if(rs1!=null)
					          rs1.close();
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
	public int updateSubTaskDetailsInDb(int taskid,String taskTitle,String taskDescription,int timeTaken) throws IOException, ClassNotFoundException{
		Connection con=null;
		PreparedStatement updateStatement=null;
		 try{
			con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD); 
			con.setAutoCommit(false);
			updateStatement = con.prepareStatement("update subtask set subtaskname=? , subtaskdesc=? ,time_estimated=? where subtaskid=?");
			updateStatement.setString(1,taskTitle); 
			updateStatement.setString(2,taskDescription); 
			updateStatement.setInt(3,timeTaken); 
			updateStatement.setInt(4,taskid); 
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
