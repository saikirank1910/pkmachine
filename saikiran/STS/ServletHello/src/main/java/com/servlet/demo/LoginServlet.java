package com.servlet.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Httpdemoo
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rs= request.getRequestDispatcher("ValidUser");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		ServletConfig sc = getServletConfig();
//		System.out.println(sc.getInitParameter("email"));
//		ServletContext st=getServletContext(); 
//		System.out.println(st.getInitParameter("driver"));
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("username", username);
		
		
		//if(username.equals("sai") && password.equals("kiran"))
		{
			response.sendRedirect("ValidUser");
			//rs.forward(request,response);
		}
//		else
//		{
//			out.println("<font color='red'><b>wrong </b></font>");
//			RequestDispatcher rs1=request.getRequestDispatcher("index.jsp");
//			rs1.include(request, response);
//		}
	}

}
