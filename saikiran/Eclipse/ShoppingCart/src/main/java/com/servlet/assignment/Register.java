package com.servlet.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		String emailid = request.getParameter("email");
		String gender = request.getParameter("gender");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		int result = 0;
		try {
			ServletContext servletContext = getServletContext();
			Connection con = (Connection) servletContext.getAttribute("con");
			String sql = "INSERT INTO usershop(emailid,username,firstname,lastname,gender,password) VALUES (?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, emailid);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, firstname);
			preparedStmt.setString(4, lastname);
			preparedStmt.setString(5, gender);
			preparedStmt.setString(6, pass);
			result = preparedStmt.executeUpdate();

		}  catch (SQLException e) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("user already exists!");
			RequestDispatcher rs1 = request.getRequestDispatcher("registerPage.jsp");
			rs1.include(request, response);
		}
		if (result != 0) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("successfully registered..!");
			RequestDispatcher rs1 = request.getRequestDispatcher("loginPage.jsp");
			rs1.include(request, response);
		}

	}

}
