<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="default-style.css"/>
		<script src="jquery-3.1.1.min.js"></script>
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
		  <!-- stuff goes here -->	
		  
		  <table>
		  
		  <tr>
        <th>Make</th>
        <th>Model</th>
        <th>Car Type</th>
		  </tr>
		  
		  <tr>
		  <#list rentedCars as car>
        <td>${car.carType}</td>
        <td>${car.make}</td>
        <td>${car.model}</td>
        <td>${car.carYear}</td>
        <td>${car.color}</td>
        <td>${car.price}</td>
        <td>${car.description}</td>
      </#list>
		  </tr>
		  
		  </table>
	  </div>
	</body>


</html>
