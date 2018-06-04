package com.prokarma.sourcerer.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.sourcerer.dto.Email;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class SMTPTest {
	
	@Autowired
	private SMTP smtp;
	
	@Mock 
	private JavaMailSender javaMailSender;
	
	@Test
	public void Email_test() {
		Email email = new Email();
		email.setTo("saikirankataram1910@gmail.com");
		email.setFrom("skataram@prokarma.com");
		email.setSubject("test");
		email.setMessage("test");
		boolean result=smtp.sendMail(email);
		assertEquals(true, result);
	}
	
//	@Test
//	public void Email_fail() {
//		Email email = new Email();
//		email.setTo("saikirankataram1910@gmail.com");
//		email.setFrom("skataram@prokarma.com");
//		email.setSubject("test");
//		email.setMessage("test");
//	}

}
