package com.weekend.assignment5.mainconsole;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.weekend.assignment5.dbsettings.ConnectionFactory;
import com.weekend.assignment5.dbsettings.DBSettings;

public class DisplayTaskDetails {
	public int mainTaskPrint() throws IOException, ClassNotFoundException, SQLException {	
		ResultSet rs=null;
		int flag=0;
		try{
			Connection con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD);
			Statement stmt =con.createStatement();
				String sql;
				sql = "select m.maintaskid,m.maintaskname,m.maintaskdesc,m.NOOFSUBTASKS,p.name,p.age from pers p,maintask m where  m.persid=p.persid" ;
				rs = stmt.executeQuery(sql);
				System.out.println("main task ID |||| task name ||||| task description ||||| no of sub tasks ||||| person name ||||| age ");
				 while(rs.next()){
					 System.out.print( rs.getInt(1));
				     System.out.print("--"+rs.getString(2));
				     System.out.print("--" + rs.getString(3));
				     System.out.print("--" + rs.getInt(4));
				     System.out.print("--" +rs.getString(5));
				     System.out.println("--" + rs.getInt(6));
				     flag=1;
				 }
				 con.close();
				 stmt.close();
				 rs.close();
			}
			catch(SQLException e) {
				System.out.println(e);
			}
			finally {
				if(rs!=null) {
					rs.close();
				}
			}
		if(flag==1)
			return 1;
		return 0;
	}
	public int subTaskPrint() throws IOException, ClassNotFoundException, SQLException {
		    Connection con = null;
		    Statement stmt =null;
		    ResultSet rs = null;
		    int flag=0;
			try{
				con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD);
				
				stmt=con.createStatement();
				String sql;
				sql = "select  s.maintaskid,s.subtaskid,s.subtaskname,s.subtaskdesc,s.time_estimated,p.name,p.age from pers p,subtask s where s.persid=p.persid";
				
				rs = stmt.executeQuery(sql);
				System.out.println("main task ID |||| sub task id |||||sub task name |||||sub  task description ||||| time estimated ||||| person name ||||| age");
				 while(rs.next()){
					 System.out.print( rs.getInt(1));
					 System.out.print("--" + rs.getInt(2));
				     System.out.print("--"+rs.getString(3));
				     System.out.print("--" + rs.getString(4));
				     System.out.print("--" + rs.getInt(5));
				     System.out.print("--" +rs.getString(6));
				     System.out.println("--" + rs.getInt(7));
				     flag=1;
				 }
				 con.close();
			}
			catch(SQLException e) {
				con.close();
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
			 }
			if(flag==1)
				return 1;
			return 0;
	}

}
