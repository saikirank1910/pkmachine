package com.prokarma.sourcerer.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.dto.Email;
import com.prokarma.sourcerer.dto.PanelAssignee;
import com.prokarma.sourcerer.dto.PanelEvaluation;
import com.prokarma.sourcerer.dto.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class GenerateMessageTest {
	
	@Autowired
	private GenerateMessage generateMessage;

	@Test
	@Ignore
	public void sendEmailToCandidateTest() {
		Candidate candidate = new Candidate();
		candidate.setEmail("Hello@gmail.com");
		candidate.setToken(1234);
		candidate.setCname("saikiran");
		
		Email email = generateMessage.sendEmailToCandidate(candidate);
		
		Email emailExpected = new Email();
		emailExpected.setTo(candidate.getEmail());
		emailExpected.setFrom("noreplyshw@gmail.com");
		emailExpected.setSubject("Successfully Registered for the Interview process @ProKarma");
		emailExpected.setMessage("<body>Dear " + candidate.getCname()
				+ ",<br><br> Congratulations!! We are glad to inform you that you have been successfully registered for attending the interview process in our Company.<br>Please follow the URL to give your self evaluation process http://localhost:9000/#!/self .<br> <br> The token ID which is mandatory to attend the interview process is "
				+ candidate.getToken() + ".<br><br>Regards,Team Prokarma.</body>");
		
		
		assertEquals(emailExpected.getFrom(),email.getFrom());
		assertEquals(emailExpected.getMessage(),email.getMessage());
		assertEquals(emailExpected.getSubject(),email.getSubject());
		assertEquals(emailExpected.getTo(),email.getTo());
	}
	@Test
	public void sendEmailToPanelTest() {
		PanelAssignee panelAssignee =new PanelAssignee();
		panelAssignee.setPanelName("hello@gmail.com");
		panelAssignee.setToken(1234);
		Email email = generateMessage.sendEmailToPanel(panelAssignee);
		
		Email emailExpected = new Email();
		emailExpected.setTo(panelAssignee.getPanelEmail());
		emailExpected.setFrom("noreplyshw@gmail.com");
		emailExpected.setSubject("Interview scheduled");
		emailExpected.setMessage("<body>Hi " + panelAssignee.getPanelName()
				+ ", <br><br>You are requested to take the interview for the Candidate <br><br>Please <a href=http://172.16.201.248:9000/#!/panelMemberAuthentication?email="
				+ panelAssignee.getPanelEmail() + "&token=" + panelAssignee.getToken() + ">Click here</a> and submit your feedback"
				+ "</body>");
		assertEquals(emailExpected.getFrom(),email.getFrom());
		assertEquals(emailExpected.getMessage(),email.getMessage());
		assertEquals(emailExpected.getSubject(),email.getSubject());
		assertEquals(emailExpected.getTo(),email.getTo());
	}
	
	@Test
	public void sendForgotPasswordToUserTest() {
		User user =new User();
		user.setEmail("Hello@gmail.com");
		user.setPassword("Hello");
		Email email = generateMessage.sendForgotPasswordToUser(user);
		Email emailExpected = new Email();
		emailExpected.setTo(user.getEmail());
		emailExpected.setFrom("noreplyshw@gmail.com");
		emailExpected.setSubject("request for password");
		emailExpected.setMessage("<body>Hi user, <br> <br>You just requested for password. Your password is "
				+ user.getPassword() + "</body>");
		assertEquals(emailExpected.getFrom(),email.getFrom());
		assertEquals(emailExpected.getMessage(),email.getMessage());
		assertEquals(emailExpected.getSubject(),email.getSubject());
		assertEquals(emailExpected.getTo(),email.getTo());
	}
	@Test
	public void sendMailToSuperAdminRegardingPanelFeedbackStatusTest() 
	{
		PanelEvaluation panelEvaluation = new PanelEvaluation();
		panelEvaluation.setPanelMemberName("panel");
		panelEvaluation.setCandidateName("candidate");
		panelEvaluation.setStatus("status");
		panelEvaluation.setComment("comments");
		Email email = generateMessage.sendMailToSuperAdminRegardingPanelFeedbackStatus(panelEvaluation);
		
		
		Email emailExpected = new Email();
		emailExpected.setTo("skataram@prokarma.com");
		emailExpected.setFrom("noreplyshw@gmail.com");
		emailExpected.setSubject("Panel Member submited feedback");
		emailExpected.setMessage("<body>Hi Admin,<br><br>" + panelEvaluation.getPanelMemberName() + " submitted feedback on "
				+ panelEvaluation.getCandidateName() + "<br>STATUS is " + panelEvaluation.getStatus()
				+ " <br><br> COMMENTS are " + panelEvaluation.getComment() + "</body>");
		assertEquals(emailExpected.getFrom(),email.getFrom());
		assertEquals(emailExpected.getMessage(),email.getMessage());
		assertEquals(emailExpected.getSubject(),email.getSubject());
		assertEquals(emailExpected.getTo(),email.getTo());
	}
	@Test 
	public void sendEmailToAdminRegardingResgistration_test() {
		User user =new User();
		user.setFirstName("saikiran");
		user.setEmail("Hello@gmail.com");
		user.setPassword("Hello");
		user.setUserName("qwerty");
		Email email = generateMessage.sendEmailToAdminRegardingResgistration(user);
		
		Email emailExpected = new Email();
		emailExpected.setTo(user.getEmail());
		emailExpected.setFrom("noreplyshw@gmail.com");
		emailExpected.setSubject("Greetings!! You are an admin now");
		emailExpected.setMessage("<body>Hi " + user.getFirstName() + ",<br><br> Your credentials are USERNAME: "
				+ user.getUserName() + " PASSWORD: " + user.getPassword() + "</body>");
		assertEquals(emailExpected.getFrom(),email.getFrom());
		assertEquals(emailExpected.getMessage(),email.getMessage());
		assertEquals(emailExpected.getSubject(),email.getSubject());
		assertEquals(emailExpected.getTo(),email.getTo());
	}

}
