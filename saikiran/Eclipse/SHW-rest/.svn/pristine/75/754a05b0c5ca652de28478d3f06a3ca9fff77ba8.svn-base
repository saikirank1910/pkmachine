package com.prokarma.sourcerer.utils;

import org.springframework.stereotype.Component;

import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.dto.Email;
import com.prokarma.sourcerer.dto.PanelAssignee;
import com.prokarma.sourcerer.dto.PanelEvaluation;
import com.prokarma.sourcerer.dto.User;

@Component
public class GenerateMessage {
	private String toEmail = "noreplyshw@gmail.com";

	public Email sendEmailToCandidate(Candidate candidate) {
		Email email = new Email();
		email.setTo(candidate.getEmail());
		email.setFrom(toEmail);
		email.setSubject("Successfully Registered for the Interview process @ProKarma");
		email.setMessage("<body>Dear " + candidate.getCname()
				+ ",<br><br> Congratulations!! We are glad to inform you that you have been successfully registered for attending the interview process in our Company.<br>Please <a href=http://172.16.201.248:9000/#!/self"
				+ ">Click here</a> and submit your self-evaluation\"<br> The token ID which is mandatory to attend the interview process is "
				+ candidate.getToken() + ".<br><br>Regards,Team Prokarma.</body>");

		return email;
	}

	public Email sendEmailToPanel(PanelAssignee panelAssignee) {
		Email email = new Email();
		email.setTo(panelAssignee.getPanelEmail());
		email.setFrom(toEmail);
		email.setSubject("Interview scheduled");
		email.setMessage("<body>Hi " + panelAssignee.getPanelName()
				+ ", <br><br>You are requested to take the interview for the Candidate <br><br>Please <a href=http://172.16.201.248:9000/#!/panelMemberAuthentication?email="
				+ panelAssignee.getPanelEmail() + "&token=" + panelAssignee.getToken() + ">Click here</a> and submit your feedback"
				+ "</body>");
		return email;
	}

	public Email sendForgotPasswordToUser(User user) {
		Email email = new Email();
		email.setTo(user.getEmail());
		email.setFrom(toEmail);
		email.setSubject("request for password");
		email.setMessage("<body>Hi user, <br> <br>You just requested for password. Your password is "
				+ user.getPassword() + "</body>");
		return email;
	}

	public Email sendMailToSuperAdminRegardingPanelFeedbackStatus(PanelEvaluation panelEvaluation) {
		Email email = new Email();
		email.setTo("skataram@prokarma.com");
		email.setFrom(toEmail);
		email.setSubject("Panel Member submited feedback");
		email.setMessage("<body>Hi Admin,<br><br>" + panelEvaluation.getPanelMemberName() + " submitted feedback on "
				+ panelEvaluation.getCandidateName() + "<br>STATUS is " + panelEvaluation.getStatus()
				+ " <br><br> COMMENTS are " + panelEvaluation.getComment() + "</body>");
		return email;
	}

	public Email sendEmailToAdminRegardingResgistration(User user) {
		Email email = new Email();
		email.setTo(user.getEmail());
		email.setFrom(toEmail);
		email.setSubject("Greetings!! You are an admin now");
		email.setMessage("<body>Hi " + user.getFirstName() + ",<br><br> Your credentials are USERNAME: "
				+ user.getUserName() + " PASSWORD: " + user.getPassword() + "</body>");
		return email;
	}
}
