<%@page import="com.weekend.assignment11.maintask.MainTaskBO"%>
<%@page import="com.weekend.assignment11.maintask.MainTask"%>
<%@page import="org.springframework.context.*"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%@include file="registerPage.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		int flag = 0, result = -1;
		String projectTitle = request.getParameter("title");
		String projectDescription = request.getParameter("description");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String noOfsubTasks = request.getParameter("noOfSubTasks");
		String persId = request.getParameter("persId");

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
		MainTaskBO mainTask = (MainTaskBO) applicationContext.getBean("mainTaskBo");
		result = mainTask.insertMainTaskDetails(projectTitle, projectDescription, noOfsubTasks, persId, startDate,
				endDate);
		System.out.println(result);
		if (result == 0) {
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.forward(request, response);
			out.print("data has been inserted");
		}
		if (result == 1) {
			out.print("data has not been inserted");
		}
		if (result == 3) {
			out.print("please fill all fields");
		}
		if (result == 2) {
			out.print("please enter number in place of number of subtasks or person id's");
		}
	%>
</body>
</html>