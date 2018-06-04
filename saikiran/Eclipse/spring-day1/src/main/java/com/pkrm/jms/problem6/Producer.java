package com.pkrm.jms.problem6;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

public class Producer {
	final static Logger logger = Logger.getLogger(Producer.class);

	private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	private static String QUEUE_NAME = "SAMPLE_QUEUE";

	public void sendMessage(String messageText) {
		Connection connection = null;
		Session session = null;
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
			connection = connectionFactory.createConnection();
			connection.start();

			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(QUEUE_NAME);
			
			MessageProducer producer = session.createProducer(destination);

			TextMessage message = session.createTextMessage(messageText);
			
			producer.send(message);
			
			logger.info("Sent message " + message.getText());
			throw new IllegalStateException();
			
		} catch (IllegalStateException e) {
			if (session != null) {
				try {
					session.rollback();
				} catch (JMSException e1) {
					logger.error(e1.getMessage());
				}
			}
		}

		catch (JMSException exception) {
			logger.error("error :" + exception.getMessage());
		} finally {
			try {
				connection.close();
			} catch (JMSException exception) {
				logger.error("error :" + exception.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		Producer producer = new Producer();
		producer.sendMessage("JMS Producer Sent a Text Message 2");
	}

}