<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="jquery-ui.min.css" />
		<link rel="stylesheet" type="text/css" href="car-style.css" />
		<script src="jquery-3.1.1.min.js"></script>
		<script src="jquery-ui.min.js"></script>
		<script src="jquery.zoom.min.js"></script>
		<script src="car.js"></script>
		<script src="calendar.js"></script>
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

		<div id="blocker"></div>
		<div id="sidebar">
			<form action="Servlet?req=rentDate" method="POST">
				<input type="hidden" name="id" id="carId" value="${car.id}" />
				From<input class="text-entry" type="textfield" id="from" name="from" placeholder="MM/DD/YYYY"/><br/>
				Until<input class="text-entry" type="textfield" id="to" name="to" placeholder="MM/DD/YYYY"/><br/>
				Total Price: <span id="priceBox"></span><br/>
				
				<input type="submit" class="button" value="Book Now" />
			</form>
			<br>
			<div class="owner">
				Owned By<br/>
				<a href="Servlet?req=user&id=${owner.id}" class="animate">${owner.firstName} ${owner.lastName}</a>
			</div>

		</div>

		<div id="jumbotron">
			<#if car.images?? >
				<a id="btn-left">&#10094;</a>
				<a id="btn-right">&#10095;</a>
				<#if car.images?size == 0>
					<img src="images/default_car.png" class="fit" />
				<#else>
					<#list car.images as image>
						<img src="${image.image}" class="images fit" />
					</#list>
				</#if>
			</#if>
		</div>
		
		<div id="main">
			<div class="info">
				<table>
					<tr>
						<td class="header">Make</td>
						<td class="support">${car.make}</td>
					</tr>
					<tr>
						<td class="header">Model</td>
						<td class="support">${car.model}</td>
					</tr>
					<tr>
						<td class="header">Year</td>
						<td class="support">${car.year?c}</td>
					</tr>
				</table>
			</div>
			<div id="description">
				<span class="header">Description</span><br>
				<p>${car.description}</p>
			</div>
			<div class="info">
				<table>
					<tr>
						<td class="header">Body Type</td>
						<td class="support">${car.carType}</td>
					</tr>
					<tr>
						<td class="header">Color</td>
						<td class="support">${car.color}</td>
					</tr>
					<tr>
						<td class="header">Price</td>
						<td class="support"><span id="price">${car.price}</span>/day</td>
					</tr>
				</table>
			</div>
			<#if same??>
				<br />
				<a href="Servlet?req=edit&id=${car.id}" class="button">Edit Listing</a>
			</#if>
		</div>
	</body>


</html>