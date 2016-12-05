<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="styles/user-style.css"/>
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
		<#if user??>
			<div id="blocker"></div>
			<div id="sidebar">
				
				<div id="info">
					<h1>${user.firstName} ${user.lastName}'s Profile</h1>
					<p>${user.email}<br />${user.phone}</p>
					<#if same??>
						<a href="Servlet?req=myRentals" class="button">View Rentals</a>
					</#if>
				</div>
			</div>
		
			<div id="main">
				<#if cars?size != 0>
					<div id="cars">
						<h1 class="info">${user.firstName}'s cars</h1>
						<#list cars as car>
							<a href="Servlet?req=car&id=${car.id}">
							<div class="entry" <#if car.images[0]??>style="background-image:url(${car.images[0].image!'images/default_car.png'});"</#if>>
								<div class="overlay">
									<span class="overlay-left">
										<ul class="car-info">
											<li>${car.make}</li>
											<li>${car.model}</li>
											<li>${car.year?c}</li>
										</ul>
									</span>
									<span class="overlay-right">
										$${car.price}/Day
										<!--<#if same??>
											<button href="Servlet?req=edit&id=${car.id}" class="no-button">Edit</button>
											<button href="Servlet?req=delete&id=${car.id}" class="no-button">Delete</button>
										<#else>
											$${car.price}/Day
										</#if>-->
								
									</span>
								</div>
							</div>
							</a>
						</#list>
					</div>
				<#else>
					<div id="cars">
						<h1 class="info">${user.firstName} has no cars for rent</h1>
						<div class="blank"></div>
					</div>
				</#if>
			</div>
		<#else>
			<h1 class="info">User not found.</h1>
		</#if>
	</body>


</html>