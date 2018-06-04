<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
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
	<form method="Post" action="LoginAuthentication">
		<table align="center" border="1" cellpadding="5" cellspacing="5"
			id="id02">
			<tbody>
				<tr>
					<th colspan="2">Login Page</th>
				</tr>
				<tr>
					<td align="right" class="style1">Email-id:</td>
					<td><input type="email" name="lemail" /></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td><input type="password" name="lpassword" /></td>
				</tr>
				<tr>
					<td align="right"><input type="submit" class="button"
						value="login" /></td>
					<td align="right">Forgot password?<a href="forgotPassword.jsp">Click
							here</a></td>
				</tr>
				<tr>
					<td align="center" colspan="2">New user <a
						href="registerPage.jsp">Click here </a></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>