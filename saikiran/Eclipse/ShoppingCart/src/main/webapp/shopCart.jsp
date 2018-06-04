<%@ page import="javax.servlet.ServletContext"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
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
</head>
<body>
	<%
		String name = (String) session.getAttribute("name");
		out.println("Welcome " + name);
	%>
	<br>
	<div align="center">
		<a href="shopCart.jsp" align="left"><input type="button"
			class="button" value="home"></a> <a href="Logout" align="right"><input
			type="button" class="button" value="logout"></a>
	</div>
	<%
		ArrayList<String> productName = new ArrayList<String>();
		ArrayList<Integer> productCost = new ArrayList<Integer>();
		ArrayList<Integer> productId = new ArrayList<Integer>();
		ServletContext servletContext = getServletContext();
		Connection con = (Connection) servletContext.getAttribute("con");
		String sql = "select id,productname,productcost from product";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(2));
			productName.add(rs.getString(2));
			productCost.add(rs.getInt(3));
			productId.add(rs.getInt(1));
		}
		int i;
	%>
	<form method="POST" action="checkOut.jsp">
		<table align="center" cellspacing="5px" cellpadding="5%" align="left"
			border="1" padding="30px">
			<tr>
				<th colspan="5">SHOP CART</th>
			</tr>
			<tr>
				<td>s.no</td>
				<td align="right">product name</td>
				<td>cost of the product</td>
				<td>no of items</td>
				<td>Tick the items</td>
			</tr>
			<%
				for (i = 0; i < productName.size(); i++) {
			%>

			<tr>
				<td><%=i + 1%></td>
				<td align="right"><%=productName.get(i)%></td>
				<td><output type="text"><%=productCost.get(i)%></output></td>
				<td><select name="noOfItems">
						<option value="0">zero</option>
						<option value="1">one</option>
						<option value="2">two</option>
						<option value="3">three</option>
						<option value="4">four</option>
						<option value="5">five</option>
						<option value="6">six</option>
						<option value="7">seven</option>

				</select></td>
				<td><input type="checkbox" name="cost"
					value="<%=productId.get(i)%>" /></td>
			</tr>

			<%
				}
			%>
			<tr>
				<td colspan="5" align="center"><input type="submit"
					class="button" value="Checkout" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
</body>
</html>