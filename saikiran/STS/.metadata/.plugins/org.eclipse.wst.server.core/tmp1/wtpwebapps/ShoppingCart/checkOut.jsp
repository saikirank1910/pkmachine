<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
</head>
<body>
	<div align="center">
		<a href="shopCart.jsp" align="left"><input type="button"
			class="button" value="home"></a> <a href="Logout" align="right"><input
			type="button" class="button" value="logout"></a>
	</div>
	<br>
	<%
		String cost[] = request.getParameterValues("cost");
		String Items[] = request.getParameterValues("noOfItems");
		ArrayList<Integer> costOfOrderedItems = new ArrayList<Integer>();
		for (String sum : cost) {
			costOfOrderedItems.add(Integer.parseInt(sum));
		}
		ArrayList<Integer> noOfItems = new ArrayList<Integer>();
		for (String count : Items) {
			if (Integer.parseInt(count) != 0) {
				noOfItems.add(Integer.parseInt(count));
			}
		}
		int costOfProducts=0;
		out.print("<table>");
		out.print("<thead>The Ordered Details are!</thead>");
		out.print("<tr>");
		out.print("<th>Cost Of Product</th>");
		out.print("<th>No Of Items</th>");
		out.print("<th>Total</th></tr>");
		for (int i = 0; i < noOfItems.size(); i++) {
			out.print("<tr>");
			out.print("<td>" + costOfOrderedItems.get(i) + "</td>");
			out.print("<td>" + noOfItems.get(i) + "</td>");
			out.print("<td>" + costOfOrderedItems.get(i) * noOfItems.get(i) + "</td></tr>");
			costOfProducts=costOfProducts+costOfOrderedItems.get(i) * noOfItems.get(i);
		}
		out.print("</table>");
	%>
	<%out.print("The Cost Of all the products is "+costOfProducts); %>
	<br>
	<a href="billing.jsp"><input align="right" type="button" class="button"
		value="PayBill"></a>
</body>
</html>