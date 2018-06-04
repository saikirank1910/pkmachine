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
	<br>
	<div align="center">
		<a href="shopCart.jsp" align="left"><input type="button"
			class="button" value="home"></a> <a href="Logout" align="right"><input
			type="button" class="button" value="logout"></a>
	</div>
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
			<tr>
				<td>1</td>
				<td align="right" name="nameOfProduct" value="product 1">product
					1</td>
				<td><output type="text">121</output></td>
				<td><select name="noOfItems">
						<option value="0">zero</option>
						<option value="1">one</option>
						<option value="2">two</option>
						<option value="3">three</option>
						<option value="4">four</option>
				</select></td>
				<td><input type="checkbox" name="cost" value="121"></td>
			</tr>
			<tr>
				<td>2</td>
				<td align="right" name="nameOfProduct">product 2</td>
				<td><output type="text">110</output></td>
				<td><select name="noOfItems">
						<option value="0">zero</option>
						<option value="1">one</option>
						<option value="2">two</option>
						<option value="3">three</option>
						<option value="4">four</option>
				</select></td>
				<td><input type="checkbox" name="cost" value="110"></td>
			</tr>
			<tr>
				<td>3</td>
				<td align="right" name="nameOfProduct">product 3</td>
				<td><output type="text">212</output></td>
				<td><select name="noOfItems">
						<option value="0">zero</option>
						<option value="1">one</option>
						<option value="2">two</option>
						<option value="3">three</option>
						<option value="4">four</option>
				</select></td>
				<td><input type="checkbox" name="cost" value="212"></td>
			</tr>
			<tr>
				<td>4</td>
				<td align="right" name="nameOfProduct">product 4</td>
				<td><output type="text">122</output></td>
				<td><select name="noOfItems">
						<option value="0">zero</option>
						<option value="1">one</option>
						<option value="2">two</option>
						<option value="3">three</option>
						<option value="4">four</option>
				</select></td>
				<td><input type="checkbox" name="cost" value="122"></td>

			</tr>
			<tr>
				<td>5</td>
				<td align="right" name="nameOfProduct">product 5</td>
				<td><output type="text">999</output></td>
				<td><select name="noOfItems">
						<option value="0">zero</option>
						<option value="1">one</option>
						<option value="2">two</option>
						<option value="3">three</option>
						<option value="4">four</option>
				</select></td>
				<td><input type="checkbox" name="cost" value="999"></td>
			</tr>
			<tr>
				<td>6</td>
				<td align="right" name="nameOfProduct">product 6</td>
				<td><output type="text">90</output></td>
				<td><select name="noOfItems">
						<option value="0">zero</option>
						<option value="1">one</option>
						<option value="2">two</option>
						<option value="3">three</option>
						<option value="4">four</option>
				</select></td>
				<td><input type="checkbox" name="cost" value="90"></td>
			</tr>
			<tr>
				<td colspan="5" align="center"><input type="submit"
					class="button" value="Checkout" /></td>
			</tr>
		</table>
</body>
</html>
</body>
</html>