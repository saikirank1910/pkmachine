<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Page</title>
<style>
td,th{
    border: 1px solid #ddd;
    padding: 8px;
}

 tr:nth-child(even){
    background-color: #f2f2f2;
}

 tr:hover{
    background-color: #ddd;
}
table, th, td {
    border: 1px solid black;
}
table{
	align="center", border="1", cellpadding="5" ,cellspacing="5";
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
	<form method="POST" action="Register">
		<table align="center">
			<tr>
				<td>
					<table id="id01" cellspacing="5px" cellpadding="5%" ; align="left"
						border="1" padding="30px">
						<tr>
							<th colspan="2">REGISTER</th>
						</tr>
						<tr>
							<td align="right" class="style1">Username:</td>
							<td class="style1"><input type="text" name="username" /></td>
						</tr>
						<tr>
							<td align="right">First Name:</td>
							<td><input type="text" name="firstname" /></td>
						</tr>
						<tr>
							<td align="right">Last Name:</td>
							<td><input type="text" name="lastname" /></td>
						</tr>
						<tr>
							<td align="right">Gender:</td>
							<td><input type="radio" name="gender" value="male" />male <input
								type="radio" name="gender" value="female" />female</td>
						</tr>
						<tr>
							<td align="right">Email-id</td>
							<td><input type="email" name="email" /></td>
						</tr>
						<tr>
							<td align="right">Password</td>
							<td><input type="password" name="password" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								class="button" value="register" /></td>
						</tr>
					</table>
				</td>
				</form>
</body>
</html>