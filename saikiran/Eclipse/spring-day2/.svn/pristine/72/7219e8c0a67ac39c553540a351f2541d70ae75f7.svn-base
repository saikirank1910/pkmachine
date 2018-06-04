package com.prokarma.spring.jms.problem3;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.prokarma.spring.jms.problem1and2.Consumer;

public class MessageClient {
	final static Logger logger = Logger.getLogger(MessageClient.class);

	private final JmsTemplate template;

	private Destination responseQueue;

	public MessageClient(JmsTemplate template, Destination responseQueue) {
		this.template = template;
		this.responseQueue = responseQueue;
	}

	public void sendMessage(final String messageText) throws JMSException {
		template.send(new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				logger.info("Sent message '" + messageText + "'");

				TextMessage createTextMessage = session.createTextMessage(messageText);
				createTextMessage.setJMSReplyTo(responseQueue);
				return createTextMessage;
			}

		});

		TextMessage receive = (TextMessage) template.receive(responseQueue);
		logger.info(receive.getText());
	}

	public static void main(String[] args) throws InterruptedException, JMSException {
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("client-context.xml");

		MessageClient obj = (MessageClient) context.getBean("client");
		obj.sendMessage("Spring JMS Message Client");
		Thread.sleep(1000);
		context.close();

	}

}