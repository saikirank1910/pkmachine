package com.prokarma.sourcerer.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.prokarma.sourcerer.dto.Email;

@Component
public class SMTP {
	final static org.apache.log4j.Logger FileLogger = Logger.getLogger("logger.file");
	
	@Autowired
	private JavaMailSender mailSender;

    public Boolean sendMail(Email email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            message.setSubject(email.getSubject());
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(email.getFrom());
            helper.setTo(email.getTo());
            helper.setText(email.getMessage(), true);
            mailSender.send(message);
            return true;
        } catch (MessagingException exception) {
        	FileLogger.error("error while sending Mail"+exception.getMessage());
        	return false;
        }
    } 
}