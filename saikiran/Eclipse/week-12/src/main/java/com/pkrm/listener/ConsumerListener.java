package com.pkrm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerListener implements ServletContextListener {
	
    public ConsumerListener() {
       
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("listener-context.xml");
	}
	
}
