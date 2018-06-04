
<%@page import="com.weekend.assignment10.maintask.MainTaskBO"%>
<%@page import="com.weekend.assignment10.maintask.MainTask"%>

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
		int flag = 0,result=-1;
		String projectTitle = request.getParameter("title");
		String projectDescription = request.getParameter("description");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int noOfsubTasks = 0, persId = 0;
		try {
			noOfsubTasks = Integer.parseInt(request.getParameter("noOfSubTasks"));
			persId = Integer.parseInt(request.getParameter("persId"));
		} catch (NumberFormatException e) {
			out.println("please enter numbers over person id or no of subtasks");
			flag = 1;
		}
		if (projectTitle.isEmpty() || projectDescription.isEmpty() || noOfsubTasks == 0 || persId == 0 || startDate.isEmpty()|| endDate.isEmpty()) {
			out.println("don't leave fields empty..!");
			flag = 1;
		}
		if (flag == 0) {
			MainTaskBO mainTask = new MainTaskBO();
			result = mainTask.insertMainTaskDetails(projectTitle, projectDescription, noOfsubTasks, persId,startDate,endDate);
		}
		System.out.println(result);
		if (result == 0) {
			out.println("data has been inserted");
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.forward(request, response);
			flag = -1;
			result = -1;
		}
	%>
</body>
</html>