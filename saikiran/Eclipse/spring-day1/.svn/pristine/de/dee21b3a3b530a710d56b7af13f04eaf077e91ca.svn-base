package com.pkrm.jms.problem5;

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

import com.pkrm.jms.problem1and2.Consumer;
import com.pkrm.jms.problem1and2.Producer;

public class Publisher {
	final static Logger logger = Logger.getLogger(Publisher.class);

	private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	private static String TOPIC_NAME = "SAMPLE_TOPIC";

	public void sendMessage(String messageText) {
		Connection connection = null;
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
			connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createTopic(TOPIC_NAME);

			MessageProducer producer = session.createProducer(destination);

			TextMessage message = session.createTextMessage(messageText);
			
			producer.send(message);
			logger.info("Sent message " + message.getText());
		} catch (JMSException exception) {
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
		Publisher publisher = new Publisher();
		publisher.sendMessage("JMS publisher Sent a Text Message 2");
	}

}
