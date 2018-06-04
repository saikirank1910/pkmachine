<%@page import="com.weekend.assignment10.maintask.MainTask"%>
<%@page import="java.util.*"%>
<%@page import="com.weekend.assignment10.maintask.MainTaskBO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
td, th {
	border: 1px solid #ddd;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #ddd;
}

table, th, td {
	border: 1px solid black;
}

table {align ="center", border="1", cellpadding="5" ,cellspacing="5";
	
}

.button {
	background-color: rgb(120, 122, 120);
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 12px;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<%
			MainTaskBO mainTaskBO = new MainTaskBO();
			ArrayList<MainTask> list =new ArrayList<MainTask>(); 
			list = mainTaskBO.getMainTaskDetails();
			if(list==null){
				out.print("The list is empty");
			}else{
			out.print("<table>");
			out.print("<thead>The Available MainTask Details Are...!</thead>");
			out.print("<tr>");
			out.print("<th>Task ID</th>");
			out.print("<th>Task Title</th>");
			out.print("<th>Task Description</th>");
			out.print("<th>No Of Sub Tasks</th>");
			out.print("<th>Person ID</th>");
			out.print("<th>start date</th>");
			out.print("<th>end date</th>");
			out.print("</tr>");
			for(int i=0;i<list.size();i++){
				out.print("<tr>");
				out.print("<td>"+list.get(i).getTaskId()+"</td>");
				out.print("<td>"+list.get(i).getProjectTitle()+"</td>");
				out.print("<td>"+list.get(i).getProjectDescriptio()+"</td>");
				out.print("<td>"+list.get(i).getNoOfTeamsInMainTask()+"</td>");
				out.print("<td>"+list.get(i).getPersid()+"</td>");
				out.print("<td>"+list.get(i).getStartDate()+"</td>");
				out.print("<td>"+list.get(i).getStartDate()+"</td>");
				
				out.print("</tr>");
			}
			out.print("</table>");
			}
		%>
		<a href="registerPage.jsp"><input type="button" Value="Register"
			class="button"> </a>
	</div>

</body>
</html>