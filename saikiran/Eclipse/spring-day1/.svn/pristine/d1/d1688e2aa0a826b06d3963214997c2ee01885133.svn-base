package com.pkrm.jms.problem3;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;


public class Consumer {
	final static Logger logger = Logger.getLogger(Consumer.class);
	
	private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	private static String QUEUE_NAME = "SAMPLE_QUEUE";

	public String recieveMessage() {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
		Connection connection = null;
		String text = null;
		try {
			connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(QUEUE_NAME);
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new AsynchronousListener());

			logger.info("waiting for messages");
			for (int i = 0; i < 100; i++) {
				try {
					Thread.sleep(2000);
				} 
				catch (InterruptedException exception) {
					logger.error("error" + exception.getMessage());
				}
				System.out.print(".");
			}
			System.out.println();
		} catch (JMSException exception) {
			logger.error("error :" + exception.getMessage());
		} finally {
			try {
				connection.close();
			} catch (JMSException exception) {
				logger.error("error :" + exception.getMessage());
			}
		}
		return text;
	}

	public static void main(String[] args) {
		Consumer consumer = new Consumer();
		System.out.println("Received message from producer is: " + consumer.recieveMessage());
	}

}