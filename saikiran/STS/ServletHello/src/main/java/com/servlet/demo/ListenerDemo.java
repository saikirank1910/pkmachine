package com.servlet.demo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ListenerDemo
 *
 */
public class ListenerDemo implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ListenerDemo() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         ServletContext servletContext = sce.getServletContext();
         String dburl = servletContext.getInitParameter("dburl");
         String username = servletContext.getInitParameter("user");
         String password = servletContext.getInitParameter("password");
         try {
         Connection con = ConnectionFactory.getConnection(dburl, username, password);
         System.out.println("DB connection established");
         servletContext.setAttribute("dbConnection",con);
         }
         catch (Exception e) {
			e.getMessage();
		}
    }
	
}
