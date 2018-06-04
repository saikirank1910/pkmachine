<%@page import="com.servlet.demo.SimpleClass"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	10*10 is<%=10*10 %>
	<% int a=10+12;
		out.println("out put of two numbers is "+a);
	%>

	<%!
	public int multiplication(int a,int b){
		return a*b;
	}
	%>
	multiplication of two numbers is<%=multiplication(10,20) %>
<%!SimpleClass obj= new SimpleClass(); %>
	
	<%=obj.add(10,20) %>
</body>
</html>