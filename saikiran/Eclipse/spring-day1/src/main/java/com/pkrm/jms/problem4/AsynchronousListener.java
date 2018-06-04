package com.pkrm.jms.problem4;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class AsynchronousListener implements MessageListener {
	private Session session;

	public AsynchronousListener(Session session) {
		this.session = session;
	}

	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {

				TextMessage textMessage = (TextMessage) message;
				String id = textMessage.getText();
				System.out.println("ID" + id);
				String replyMessage = "Successfully Processed " + id;
				MessageProducer createProducer = session.createProducer(message.getJMSReplyTo());

				createProducer.send(session.createTextMessage(replyMessage));

				createProducer.setTimeToLive(6000);

			}
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}