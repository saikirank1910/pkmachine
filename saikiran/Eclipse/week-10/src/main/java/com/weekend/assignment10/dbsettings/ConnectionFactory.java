package com.weekend.assignment10.dbsettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection(String connection, String username, String password) throws ClassNotFoundException {
        Connection resultConnection = null;
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            resultConnection = DriverManager.getConnection(connection, username, password);
        } catch (SQLException e) {
        	
            e.printStackTrace();
        }
        return resultConnection;
    }
}
