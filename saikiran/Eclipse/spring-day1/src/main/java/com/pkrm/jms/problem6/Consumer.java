package com.pkrm.jms.problem6;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

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
		Session session =null;
		try {
			connection = connectionFactory.createConnection();
			connection.start();

			session= connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(QUEUE_NAME);
			MessageConsumer consumer = session.createConsumer(destination);
			Message message = consumer.receive();

			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				text = textMessage.getText();
			}
			throw new IllegalStateException();
		} 
		catch(IllegalStateException e) {
			if(session!=null) {
				try {
					session.rollback();
				} catch (JMSException e1) {
					logger.error(e1.getMessage());
				}
			}
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
		logger.info("Received message from producer is: " + consumer.recieveMessage());
	}

}