<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="edit-style.css"/>
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
					<li><a href="Servlet?req=search">BROWSE</a></li>
				<#else>
					<li><a href="login.html">LOGIN</a></li>
					<li><a href="Servlet?req=search">BROWSE</a></li>
				</#if>
			</ul>
		</div>

		<div id="main">
		<#if done??>
			<h1>Your car has been editted.<br/>
			<a href="Servlet?req=car&id=${car.id}">Click here to view.</a></h1>
		</#if>
			<form action="Servlet?req=edit" method="POST">
				<input hidden name="id" value="${car.id}"/>
				<input type="textfield" placeholder="Make" name="make" value="${car.make}"/><br/>
				<input type="textfield" placeholder="Model" name="model" value="${car.model}"/><br/>
				<input type="textfield" placeholder="Year" name="year" value="${car.year?c}"/><br/>
				<input type="textfield" placeholder="Color" name="color" value="${car.color}"/><br/>
				<input type="textfield" placeholder="Price per Day" name="price" value="${car.price?c}"/><br/>
				<input type="textfield" placeholder="Body type" name="type" value="${car.carType}"/><br/>
				<textarea placeholder="Description" name="description">${car.description}</textarea><br/>
				<input type="submit"/>
			</form>
			<ul id="images">
				<#list car.images as image>
					<li style="background-image:url(${image.image});">
						<div class="overlay">
							<a href="Servlet?req=deleteImage&id=${image.id}">X</a>
						</div>
					</li>
				</#list>
			</ul>
			<a href="Servlet?req=delete&id=${car.id}">Delete</a>
		</div>
	</body>


</html>