package com.servlet.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextDBListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	ResultSet rs = null;
    	ServletContext servletContext = servletContextEvent.getServletContext();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String username= servletContext.getInitParameter("DB_USERNAME");
		String password= servletContext.getInitParameter("DB_PASSWORD");
		String url= servletContext.getInitParameter("DB_CONNECTION");
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		servletContext.setAttribute("con", con);
    	 //System.out.println("Database connection initialized for Application.");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
    	Connection con =(Connection)ctx.getAttribute("con");
    	try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	//System.out.println("Database connection closed for Application.");
    	
    }
	
}
