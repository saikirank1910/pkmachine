package com.pkrm.jms.problem5;

import org.apache.log4j.Logger;

public class SubscriberMain {
	final static Logger logger = Logger.getLogger(SubscriberMain.class);
	
	public static void main(String[] args) {
		Subscriber1 subscriber1 = new Subscriber1();
		Subscriber2 subscriber2 = new Subscriber2();
		Thread t1 = new Thread(subscriber1);
		Thread t2 = new Thread(subscriber2);
		t1.start();
		t2.start();
		logger.info("Received message from publisher is: 1 "+subscriber1.recieveMessage());	
		logger.info("Received message from publisher is: 2 "+subscriber2.recieveMessage());
	}
}
