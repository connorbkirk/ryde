<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="search-style.css"/>
		<script src="search.js"></script>
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
		<div id="blocker"></div>
		<div id="sidebar">
			<form action="Servlet?req=search" method="GET">
				<input type="hidden" name="req" value="search"/>
				From<input class="text-entry" type="textfield" name="fromDate" placeholder="XX/XX/XX XX:XX XM"/><br/>
				Until<input class="text-entry" type="textfield" name="toDate" placeholder="XX/XX/XX XX:XX XM"/><br/>
				Price<br/>
				<input type="range" name="price" id="price"/>
				<br/>
				Vehicle Type <br/> 
				<select name="type">
					<option value="all" >All Vehicles</option>
					<#list types as type>
						<option value="${type}">${type}</option>
					</#list>
				</select><br/>
	
				Vehicle Make <br/> 
				<select name="make">
					<option value="all" >All Makes</option>
					<#list makes as make>
						<option value="${make}">${make}</option>
					</#list>
				</select><br/>
				
				Vehicle Model <br/> 
				<select name="model">
					<option value="all" >All Models</option>
					<#list models as model>
						<option value="${model}">${model}</option>
					</#list>
				</select><br/>
	
				Sort By<br/> 
				<select name="sortBy">
					<option value="revelance" >Relevance</option>
				</select>
				<input type="submit">
			</form>
		</div>

		<div id="main">
			<#if cars??>
			<#list cars as car>
				<div class="entry" <#if car.images??>style="background-image:url(${car.images[0]!'images/default_car.png'});"</#if>>
					<div class="overlay">
					<span class="overlay-left">
						<ul class="car-info">
							<li><a href="Servlet?req=car&id=${car.id}">View</a></li>
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
			</#list>
			<#else>
				<p>Sorry, no cars were found.</p>
			</#if>
		</div>
	</body>


</html>
