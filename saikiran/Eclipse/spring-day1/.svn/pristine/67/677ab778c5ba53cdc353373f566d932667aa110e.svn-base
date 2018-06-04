package com.pkrm.jms.problem4;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.pkrm.jms.problem1and2.Consumer;

public class Service {
	final static Logger logger = Logger.getLogger(Service.class);
	
	private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	private static String QUEUE_NAME = "SAMPLE_QUEUE";

	public void processMessage() throws JMSException, InterruptedException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(QUEUE_NAME);
		MessageConsumer consumer = session.createConsumer(destination);
		consumer.setMessageListener(new AsynchronousListener(session));
		logger.info("waiting for messages");
		for (int i = 0; i < 100; i++) {
			Thread.sleep(1000);
			logger.info(".");
		}
		System.out.println();

		connection.close();

	}

	public static void main(String[] args) throws JMSException, InterruptedException {
		Service consumer = new Service();
		consumer.processMessage();
	}

}