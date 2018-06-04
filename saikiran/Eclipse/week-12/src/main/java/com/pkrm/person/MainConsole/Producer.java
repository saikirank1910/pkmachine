package com.pkrm.person.MainConsole;

import java.util.function.Consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkrm.person.Person;

public class Producer {
	final static Logger logger = Logger.getLogger(Producer.class);
	private final JmsTemplate template;

	public Producer(JmsTemplate template) {
		this.template = template;
	}

	public void sendMessage(Person person) {
		template.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = null;
				try {
					jsonInString = mapper.writeValueAsString(person);
				} catch (JsonProcessingException e) {
					logger.error(e.getMessage());
				}
				//System.out.println(jsonInString);
				return session.createTextMessage(jsonInString);
			}
		});
	}
}