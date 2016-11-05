<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		
		<link rel="stylesheet" type="text/css" href="search-style.css"/>
		<title>Ryde</title>
	</head>

	<body>

		<div id="nav">
			<a href="index.html" class="logo">Ryde</a></span>
			<ul>
				<li><a href="#">LOGIN</a></li>
				<li><a href="#">INFO</a></li>
			</ul>
		</div>

		<div id="sidebar">
			<form action="Servlet?req=search" method="GET">
				<input type="hidden" name="req" value="search"/>
				From<input type="textfield" name="fromDate" placeholder="XX/XX/XX XX:XX XM"/><br/>
				Until<input type="textfield" name="toDate" placeholder="XX/XX/XX XX:XX XM"/><br/>
				Price<br/>
				Slider here
				<br/>
				Vehicle Type <br/> 
				<select name="type">
					<option value="all" >All Vehicles</option>
				</select><br/>
	
				Vehicle Make <br/> 
				<select name="make">
					<option value="all" >All Makes</option>
				</select><br/>
	
				Sort By<br/> 
				<select name="sortBy">
					<option value="revelance" >Revelance</option>
				</select>
				<input type="submit">
			</form>
		</div>

		<div id="main">
			<#if cars??>
			<#list cars as car>
				<div class="entry">
					<span class="overlayLeft">
						<ul>
							<li>${car.make}</li>
							<li>${car.model}</li>
							<li>${car.year}</li>
						</ul>
					</span>
					<span class="overlayRight">
						$${car.price}/Day
					</span>
				</div>
			</#list>
			<#else>
				<p>Sorry, no cars were found.</p>
			</#if>
		</div>
	</body>


</html>