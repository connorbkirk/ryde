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
		<#if id??>
			<h1>Your car has been added.<br/>
			<a href="Servlet?req=car&id=${id}">Click here to view.</a></h1>
		<#else>
			<form action="Servlet?req=add" method="POST">
				<input type="textfield" placeholder="Make" name="make"/><br/>
				<input type="textfield" placeholder="Model" name="model"/><br/>
				<input type="textfield" placeholder="Year" name="year"/><br/>
				<input type="textfield" placeholder="Color" name="color"/><br/>
				<input type="textfield" placeholder="Price per Day" name="price"/><br/>
				<input type="textfield" placeholder="Body type" name="type"/><br/>
				<textarea placeholder="Description" name="description"></textarea><br/>
	
				<input type="submit"/>
			
			</form>
		</#if>
		</div>
	</body>


</html>