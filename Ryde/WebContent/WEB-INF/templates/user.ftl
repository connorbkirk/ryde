<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="view-style.css"/>
		<title>Ryde</title>
	</head>

	<body>
		<div id="nav">
			<a href="index.html" class="logo">Ryde</a></span>
			<ul>
				<#if userId??>
					<li><a href="Servlet?req=logout">LOGOUT</a></li>
					<li><a href="Servlet?req=user&id=${userId}">MY ACCOUNT</a></li>
					<li><a href="Servlet?req=add">POST A CAR</a></li>
				<#else>
					<li><a href="login.html">LOGIN</a></li>
					<li><a href="#">INFO</a></li>
				</#if>
			</ul>
		</div>

		<div id="main">
			<#if user??>
				<div id="info">
					${user.firstName} ${user.lastName}
					<br/>
					${user.username}
				</div>
				<#if cars??>
					<div id="cars">
						<h3>${user.firstName}'s cars</h3>
						<table>
							<#list cars as car>
								<tr>
								<td>
									<a href="Servlet?req=car&id=${car.id}">${car.year} ${car.make} ${car.model}</a>
								</td>
								
								<#if same??>
									<td><a href="Servlet?req=edit&id=${car.id}">Edit</a></td>
									<td><a href="Servlet?req=delete&id=${car.id}">Delete</a></td>
								</#if>
								
								</tr>
							</#list>
						</table>
					</div>
				</#if>
			<#else>
				<h3>User not found</h3>
			</#if>
		</div>
	</body>


</html>