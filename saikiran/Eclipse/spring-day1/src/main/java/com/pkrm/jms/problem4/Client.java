package com.pkrm.jms.problem4;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.pkrm.jms.problem1and2.Consumer;

public class Client {
	final static Logger logger = Logger.getLogger(Client.class);

	private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	private static String QUEUE_NAME = "SAMPLE_QUEUE";

	public String retrieve(String id) {
		TextMessage textMessage = null;
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(QUEUE_NAME);

			MessageProducer producer = session.createProducer(destination);

			TextMessage message = session.createTextMessage();
			message.setText(id);

			TemporaryQueue temporaryQueue = session.createTemporaryQueue();
			message.setJMSReplyTo(temporaryQueue);

			logger.info(message);

			producer.send(message);

			MessageConsumer consumer = session.createConsumer(temporaryQueue);

			Message responseMessage = consumer.receive(60 * 1000);
			textMessage = (TextMessage) responseMessage;
			logger.info("response message test " + responseMessage);
		} 
		catch (JMSException exception) {
			logger.error("error :" + exception.getMessage());
		}
		finally {
			try {
				return textMessage.getText();
			} catch (JMSException exception) {
				logger.error("error :" + exception.getMessage());
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Client client = new Client();
		logger.info("Received message " + client.retrieve("EMP102"));
	}
}
