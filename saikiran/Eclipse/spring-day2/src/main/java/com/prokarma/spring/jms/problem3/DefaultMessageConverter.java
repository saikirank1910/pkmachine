package com.prokarma.spring.jms.problem3;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class DefaultMessageConverter implements MessageConverter {

	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		TextMessage textMessage = (TextMessage) message;
		return textMessage.getText();
	}

	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		TextMessage message = session.createTextMessage(object.toString());
		return message;
	}

}