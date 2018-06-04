package com.prokarma.spring.jms.problem3;

public class ServiceProvider {
	public String process(String message) {

		System.out.println("Service Provider" + message);
		// *Retrieve details from db or some storage and construct reply
		// message
		return "Successfully Processed " + message;

	}


}
