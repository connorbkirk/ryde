<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="styles/payment-style.css"/>
		<script src="scripts/jquery-3.1.1.min.js"></script>
		<script src="scripts/check.js"></script>
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
			
		  	<h1 class="large">One last step!</h1>
		  	<h1 class="large">Enter in your billing information!</h1>
			<form action="Servlet?req=confirmPayment" method="POST">
				<input type="hidden" name="carId" value="${car.id}" />
				<input type="hidden" name="from" value="${from}" />
				<input type="hidden" name="to" value="${to}" />
				<input type="text" name="firstName" placeholder="First Name" class="text-entry" />
				<input type="text" name="lastName" placeholder="Last Name" class="text-entry" /><br /><br />
				<input type="text" name="address" placeholder="Billing Address" class="text-entry" />
				<input type="text" name="city" placeholder="City" class="text-entry" />
				<input type="text" name="state" placeholder="State" class="text-entry" /><br /><br />
				<input type="text" name="creditCard" placeholder="Credit Card Number" class="text-entry" /> <br />
				<br/>
				<input type="submit" class="disabled button" disabled="true"/>
			</form>
	  </div>
	</body>


</html>

