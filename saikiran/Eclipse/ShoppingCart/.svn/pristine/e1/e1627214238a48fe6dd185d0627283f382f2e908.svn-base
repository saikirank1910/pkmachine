package com.servlet.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultSet rs = null;
		String email = request.getParameter("lemail");
		String pass = request.getParameter("lpassword");
		ServletContext servletContext = getServletContext();
		try {
			String dbpass = "",name="";
			Connection con = (Connection) servletContext.getAttribute("con");
			String sql = "select password,username from usershop where emailid=?";

			PreparedStatement preparedStatement = con.prepareStatement(sql);

			preparedStatement.setString(1, email);
			rs = preparedStatement.executeQuery();
			if (!rs.isBeforeFirst()) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("Invalid email ID! please register");
				RequestDispatcher rs1 = request.getRequestDispatcher("loginPage.jsp");
				rs1.include(request, response);
			} else {
				while (rs.next()) {
					dbpass = rs.getString(1);
					name = rs.getString(2);
				}
				if (dbpass.equals(pass)) {
					String userNameSession = request.getParameter("lemail");

					// creating a new session

					HttpSession session = request.getSession();
					System.out.println("session " + session);
					if (session != null) {
						session = request.getSession(false);
						session.setAttribute("name", userNameSession);
						System.out.println("new session created");
					}
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					//out.println("Welcome "+name);
					RequestDispatcher rs1 = request.getRequestDispatcher("shopCart.jsp");
					rs1.include(request, response);

				} else {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("Wrong Password!!");
					RequestDispatcher rs1 = request.getRequestDispatcher("loginPage.jsp");
					rs1.include(request, response);
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

}
