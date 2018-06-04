package com.pkrm.jms.problem5;

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

import com.pkrm.jms.problem4.AsynchronousListener;

public class Subscriber1 extends Thread{
	final static Logger logger = Logger.getLogger(Subscriber1.class);

	private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	private static String TOPIC_NAME = "SAMPLE_TOPIC";

	public String recieveMessage() {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
		Connection connection = null;
		String text = null;
		try {
			connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createTopic(TOPIC_NAME);
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new AsynchronousListener(session));

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

}
