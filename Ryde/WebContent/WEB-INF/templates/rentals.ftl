<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="styles/default-style.css"/>
		<link rel="stylesheet" type="text/css" href="styles/rentals-style.css"/>
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
			<div id="my-cars">
				<h1 class="header">My cars</h1>
				<table class="table-center">
					<tr class="table-header">
						<th colspan=2>Car</th>
		        		<th>Booked From</th>
		        		<th>To</th>
		        		<th>Renter</th>
					</tr>
					<#list cars as car>
						<tr class="table-entry">
							<td colspan=2><a href="Servlet?req=car&id=${car.id}" class="animate">${car.make} ${car.model}</td>
						
						<#if rentals[car.id?string]?size!=0>
							<#list rentals[car.id?string] as rent>
									<td>${rent.startDate}</td>
									<td>${rent.endDate}</td>
									<td><a href="Servlet?req=user&id=${users[rent.renterID?string].id}" class="animate">${users[rent.renterID?string].firstName}</a></td>
								</tr>
								<tr>
									<td colspan=2></td>
									
							</#list>
								</tr>
						<#else>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							</tr>
						</#if>
					</#list>
						</tr>
				</table>
		  	</div>
		  	
		  	<div id="my-rentals">
		  		<h1 class="header">My Rentals</h1>
				<table class="table-center">
					<tr class="table-header">
						<th colspan=2>Car</th>
		        		<th>Booked From</th>
		        		<th>To</th>
		        		<th>Cancel</th>
					</tr>
	        		<#list userRentals as rent>
	        			<tr>
	        				<td colspan=2><a href="Servlet?req=car&id=${rentalCar[rent.carID?string].id}" class="animate">${rentalCar[rent.carID?string].make} ${rentalCar[rent.carID?string].model}</a></td>
	        				<td>${rent.startDate}</td>
	        				<td>${rent.endDate}</td>
	        				<td><a href="Servlet?req=cancel&id=${rent.id}" class="animate">X</a></td>
	        			</tr>
	        		</#list>
		        		
					</tr>
				</table>
		  	</div>
		</div>
	</body>


</html>
