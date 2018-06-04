package com.pkrm.person.MainConsole;

import java.io.IOException;
import java.util.function.Consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkrm.person.Person;
import com.pkrm.person.service.PersonService;

public class Listener implements MessageListener {
	final static Logger logger = Logger.getLogger(Consumer.class);
	public void onMessage(Message message) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-context.xml");
		PersonService personService = (PersonService) applicationContext.getBean("personService");
		try {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String jsonString = textMessage.getText();
				ObjectMapper mapper = new ObjectMapper();
				Person person = null;
				try {
					person = mapper.readValue(jsonString, Person.class);
				} catch (IOException e) {
					logger.error("IO error "+e);
				}
				try {
					personService.createPerson(person);
				} catch (DataAccessException e) {
					logger.error("error while inserting into person table..!" + e.getMessage());
				}
				try {
					personService.insertIntoRole();
				} catch (DataAccessException e) {
					logger.error("error while inserting into usermaprole table..!" + e.getMessage());
				}

			}
		} catch (JMSException e) {
			logger.error("Jms exception "+e);
		}

	}
}