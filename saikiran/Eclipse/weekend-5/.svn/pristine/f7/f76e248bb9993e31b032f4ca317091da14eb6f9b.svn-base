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

public interface MainTaskDao {
	int saveMainTask(MainTask mainTask) throws IOException, SQLException;
	int updateMainTask(int id,String name,String description) throws IOException, ClassNotFoundException;
}
