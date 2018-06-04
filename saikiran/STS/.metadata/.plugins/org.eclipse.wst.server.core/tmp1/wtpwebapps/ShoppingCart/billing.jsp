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
	<a href="Logout"><input align="right" type="button" class="button" value="logout"></a>
	<br>
	<form action="Logout">
		<table border="1" cellpadding="5" cellspacing="5" id="id02">
			<tr>
				<td>Enter Card Details</td>
				<td><input type="number" maxlength="4" style="width: 50px;" /></td>
				<td><input type="number" maxlength="4" style="width: 50px;" /></td>
				<td><input type="number" maxlength="4" style="width: 50px;" /></td>
				<td><input type="number" maxlength="4" style="width: 50px;" /></td>
			</tr>
			<tr>
				<td colspan="3">Enter Date Of Expiry<input type="number"
					maxlength="4" style="width: 80px;" /></td>
				<td colspan="2">CVV <input type="number" maxlength="3"
					style="width: 70px;" /></td>
			</tr>
			<tr>
				<td colspan="5" align="center"><input type="submit" class="button"
					value="Pay Bill"></td>
			</tr>
		</table>
	</form>
</body>
</html>