package com.servlet.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForgotPassword
 */
public class PasswordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultSet rs = null;
		String email = request.getParameter("femail");
		
		ServletContext servletContext = getServletContext();
		try {
			String dbpass = "";
			Connection con = (Connection) servletContext.getAttribute("con");
			String sql = "select password from usershop where emailid=?";

			PreparedStatement preparedStatement = con.prepareStatement(sql);

			preparedStatement.setString(1, email);
			rs = preparedStatement.executeQuery();
			if (!rs.isBeforeFirst()) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("Invalid email ID! please enter valid email id");
				RequestDispatcher rs1 = request.getRequestDispatcher("forgotPassword.jsp");
				rs1.include(request, response);
			} else {
				while (rs.next()) {
					dbpass = rs.getString(1);
				}
				String from = "shopassignmentgtp6b@gmail.com";
				final String username = "shopassignmentgtp6b@gmail.com";
				final String password = "Shop123";
			    	Properties props = new Properties();
			    	 props.setProperty("mail.smtp.host", "smtp.gmail.com");
			    	 props.setProperty("mail.smtp.starttls.enable", "true");
			    	 props.setProperty("mail.smtp.port", "587");
			    	 props.setProperty("mail.smtp.auth", "true");

			    	Authenticator authenticator = null;
			    	authenticator = new Authenticator() {
			    		private PasswordAuthentication pa = new PasswordAuthentication(username, password);
			    		@Override
			    		public PasswordAuthentication getPasswordAuthentication() {
			    			return pa;
			    		}
			    	};
			    	
			    	Session session = Session.getInstance(props, authenticator);
			    	session.setDebug(true);

			    	MimeMessage message = new MimeMessage(session);
			    	try {
			    		String toAddress = email;
			    	    message.setFrom(new InternetAddress(from));
			    	    InternetAddress[] address = {new InternetAddress(toAddress)};
			    	    message.setRecipients(Message.RecipientType.TO, address);
			    	    message.setSubject("Password Reset");
			    	    message.setSentDate(new Date());
			    	    message.setText("your password is: "+dbpass);
			    	    Transport.send(message);
			    	    PrintWriter pw = response.getWriter();
			    		pw.append("eMail sent successfully to "+toAddress);
			    	} catch (MessagingException ex) {
			    	    ex.printStackTrace();
			    	}
			    }
				
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

}
