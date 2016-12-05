<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>
		<link rel="stylesheet" type="text/css" href="styles/search-style.css"/>
		<link rel="stylesheet" type="text/css" href="styles/jquery-ui.min.css"/>
		<script src="scripts/jquery-3.1.1.min.js"></script>
		<script src="scripts/jquery-ui.min.js"></script>
		<script src="scripts/auto.js"></script>
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
			<form action="Servlet?req=search" method="GET">
				<input type="hidden" name="req" value="search" />
			
				Vehicle Make <br />
		        <input name="make" id="make" class="text-entry" placeholder="Vehicle Make" <#if make??>value="${make}"</#if> /> <br />
				
				Vehicle Model <br />
				<input name="model" id="model" class="text-entry" placeholder="Vehicle Model" <#if model??>value="${model}"</#if> /> <br />
				
				Vehicle Type <br />
				<input name="type" id="type" class="text-entry" placeholder="Vehicle Type" <#if type??>value="${type}"</#if> /> <br />

				
				<input type="submit" class="button">
			</form>
		</div>

		<div id="main">
			<#if cars?size !=0>
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
							</span>
						</div>
					</div>
					</a>
				</#list>
			<#else>
				<p class="large">Sorry, no cars were found.</p>
			</#if>
		</div>
	</body>


</html>