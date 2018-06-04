package com.weekend.assignment5.persondetails;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.weekend.assignment5.dbsettings.ConnectionFactory;
import com.weekend.assignment5.dbsettings.DBSettings;

public class PersonDaoImpl implements PersonDao{
	
	public int savePersonDetails(Person person) throws SQLException {
		Connection con=null;
		CallableStatement cstmt = null;
		try{
			con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD); 
			con.setAutoCommit(false);
			 cstmt = con.prepareCall("{CALL insert_pers(per_id.nextval,?,?,?)}");
			cstmt.setString(1,person.getName());
			cstmt.setInt(2,person.getAge());
			cstmt.setString(3,person.getDesignation());
			int result = cstmt.executeUpdate();
			con.commit();
		    con.close();
		    if(result==0)
		    	return 0;
	    }
		catch(SQLException se)
		{
		   con.rollback();
		   se.printStackTrace();
		}
		finally
		{
		    try
		    {
		       if(con!=null)
		          con.close();
		    }
		    catch(SQLException se)
		    {
		       se.printStackTrace();
		    }
		    try
		    {
		       if(cstmt!=null)
		    	   cstmt.close();
		    }
		    catch(SQLException se)
		    {
		       se.printStackTrace();
		    }
		}
		return 1;
	}
}
