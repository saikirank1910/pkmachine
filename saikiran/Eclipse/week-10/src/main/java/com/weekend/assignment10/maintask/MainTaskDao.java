package com.weekend.assignment10.maintask;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.weekend.assignment10.dbsettings.ConnectionFactory;
import com.weekend.assignment10.dbsettings.DBSettings;

public class MainTaskDao {
	public int saveMainTask(MainTask mainTask) throws IOException, SQLException {
		Connection con = null;
		CallableStatement cstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION, DBSettings.DB_USERNAME,
					DBSettings.DB_PASSWORD);
			cstmt = con.prepareCall("{CALL insert_mainTask2(main_task_seq.nextval,?,?,?,?,?,?)}");
			cstmt.setString(1, mainTask.getProjectTitle());
			cstmt.setString(2, mainTask.getProjectDescriptio());
			cstmt.setInt(3, mainTask.getNoOfTeamsInMainTask());
			cstmt.setInt(4, mainTask.getPersid());
			cstmt.setString(5,mainTask.getStartDate());
			cstmt.setString(6,mainTask.getEndDate());
			int result = cstmt.executeUpdate();
			con.close();
			if (result == 0)
				return 0;
		} catch (SQLException se) {
			return 0;
			// se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				// se.printStackTrace();
				return 0;
			}
			try {
				if (cstmt != null)
					cstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return 1;
	}

	public ArrayList<MainTask> mainTaskPrint() throws IOException, ClassNotFoundException, SQLException {
		ResultSet rs = null;
		int flag = 0;
		ArrayList<MainTask> listOfJson = new ArrayList<MainTask>();
		try {
			Connection con = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION, DBSettings.DB_USERNAME,
					DBSettings.DB_PASSWORD);
			Statement stmt = con.createStatement();
			String sql;
			sql = "select *from maintask2";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MainTask mainTask = new MainTask(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(1), rs.getString(6), rs.getString(7));
				listOfJson.add(mainTask);
				flag = 1;
			}
			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		if (flag == 1)
			return listOfJson;
		return null;
	}

}
