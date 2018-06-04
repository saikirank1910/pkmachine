<%@ page import="javax.servlet.ServletContext"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
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
		String name = (String) session.getAttribute("name");
		out.println("Welcome " + name);
	%>
	<%
		if (request.getParameterValues("cost") == null) {
			out.println("please check any values...!");
			RequestDispatcher rs1 = request.getRequestDispatcher("shopCart.jsp");
			rs1.include(request, response);
		}
		ResultSet rs = null;
		String id[] = request.getParameterValues("cost");
		String Items[] = request.getParameterValues("noOfItems");
		ArrayList<Integer> productId = new ArrayList<Integer>();
		for (String sum : id) {
			productId.add(Integer.parseInt(sum));
		}
		ArrayList<Integer> noOfItems = new ArrayList<Integer>();
		for (String count : Items) {
			if (Integer.parseInt(count) != 0) {
				noOfItems.add(Integer.parseInt(count));
			}
		}

		ArrayList<String> productName = new ArrayList<String>();
		ArrayList<Integer> costOfOrderedItems = new ArrayList<Integer>();

		ServletContext servletContext = getServletContext();
		Connection con = (Connection) servletContext.getAttribute("con");
		for (int j = 0; j < productId.size(); j++) {
			String sql = "select productname,productcost from product where id=?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, productId.get(j));
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				productName.add(rs.getString(1));
				costOfOrderedItems.add(rs.getInt(2));
			}
		}

		int costOfProducts = 0;
		out.print("<table>");
		out.print("<thead>The Ordered Details are!</thead>");
		out.print("<tr>");
		out.print("<th>product name</th>");
		out.print("<th>Cost Of Product</th>");
		out.print("<th>No Of Items</th>");
		out.print("<th>Total</th></tr>");
		for (int i = 0; i < noOfItems.size(); i++) {
			out.print("<tr>");
			out.print("<td>" + productName.get(i) + "</td>");
			out.print("<td>" + costOfOrderedItems.get(i) + "</td>");
			out.print("<td>" + noOfItems.get(i) + "</td>");
			out.print("<td>" + costOfOrderedItems.get(i) * noOfItems.get(i) + "</td></tr>");
			costOfProducts = costOfProducts + costOfOrderedItems.get(i) * noOfItems.get(i);
		}
		out.print("</table>");
	%>
	<%
		session.setAttribute("bill", costOfProducts);
		out.print("The Cost Of all the products is " + costOfProducts);
	%>
	<br>
	<a href="billing.jsp"><input align="right" type="button"
		class="button" value="PayBill"></a>
</body>
</html>