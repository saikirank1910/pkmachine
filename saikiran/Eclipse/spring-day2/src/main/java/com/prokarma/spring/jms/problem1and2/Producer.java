package com.prokarma.spring.jms.problem1and2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class Producer {
	
	final static Logger logger = Logger.getLogger(Producer.class);
	
	private final JmsTemplate template;

	public Producer(JmsTemplate template) {
		this.template = template;
	}

	public void sendMessage(final String messageText){
		template.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				logger.info("Sent message :" +messageText);
				return session.createTextMessage(messageText);
			}
		});
		;
	}

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"producer-context.xml");
		Producer obj = (Producer) context.getBean("producer");
		obj.sendMessage("Hello JMS program 1");
		Thread.sleep(1000);
		context.close();
	}

}