<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="add-style.css" />
		<link rel="stylesheet" type="text/css" href="jquery-ui.min.css" />
		<script src="jquery-3.1.1.min.js"></script>
		<script src="jquery-ui.min.js"></script>
		<script src="auto.js"></script>
		<script src="check.js"></script>
		<title>Ryde</title>
	</head>

	<body>
		<div id="nav">
			<a href="Servlet?req=search" class="logo">Ryde</a></span>
			<ul>
				<#if userId??>
					<li><a href="Servlet?req=logout">LOGOUT</a></li>
					<li><a href="Servlet?req=user&id=${userId}">MY ACCOUNT</a></li>
					<li><a href="Servlet?req=add">POST A CAR</a></li>
					<li><a href="Servlet?req=search">BROWSE</a></li>
				<#else>
					<li><a href="login.html">LOGIN</a></li>
					<li><a href="Servlet?req=search">BROWSE</a></li>
				</#if>
			</ul>
		</div>

		<div id="main">
			<#if id??>
				<h1>Your car has been added.<br />
				<a href="Servlet?req=car&id=${id}">Click here to view.</a></h1>
			</#if>
			<form action="Servlet?req=add" method="POST">
				<input type="textfield" placeholder="Make" name="make" class="text-entry" id="make" />
				<input type="textfield" placeholder="Model" name="model" class="text-entry" id="model" />
				<input type="textfield" placeholder="Year" name="year" class="text-entry" /><br />
				<input type="textfield" placeholder="Color" name="color" class="text-entry" />
				<input type="textfield" placeholder="Price per Day" name="price" class="text-entry" />
				<input type="textfield" placeholder="Body type" name="type" class="text-entry" id="type"/>
				<textarea placeholder="Description" name="description" class="text-entry text-box"></textarea><br />
				<div class="right">
					<input type="submit" class="button disabled" value="Next" disabled="true"/>
				</div>
			</form>
	
			</div>
	</body>


</html>