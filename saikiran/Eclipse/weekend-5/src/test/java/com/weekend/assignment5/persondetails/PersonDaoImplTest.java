package com.weekend.assignment5.persondetails;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.weekend.assignment5.dbsettings.ConnectionFactory;
import com.weekend.assignment5.dbsettings.DBSettings;

public class PersonDaoImplTest {

	@Test
	public void testAddIntoDb() throws SQLException {
		Person person=new Person("testdata","testdata",32);
		PersonDaoImpl enterPersonDetails = new PersonDaoImpl();
		int result = enterPersonDetails.savePersonDetails(person);
		assertEquals(1,result);
		Connection con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION,DBSettings.DB_USERNAME,DBSettings.DB_PASSWORD);
		Statement stmt= con.createStatement();
		int personId=0;
		ResultSet rs = stmt.executeQuery("SELECT persid FROM pers where name='testdata' and age=32");
		if (rs!=null && rs.next()) {
		 personId = rs.getInt(1);			
		}
		rs=stmt.executeQuery("delete from pers where persid='"+personId+"'");
		con.close();
		rs.close();
		stmt.close();
	}

}
