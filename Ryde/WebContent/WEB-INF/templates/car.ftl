<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="view-style.css"/>
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

		<div id="jumbotron">
			<span class="bottom" >Displaying X Images</span>
		</div>

		<div id="sidebar">
			From<input class="text-entry" type="textfield" name="fromData" placeholder="XX/XX/XX XX:XX XM"/><br/>
			Until<input class="text-entry" type="textfield" name="fromData" placeholder="XX/XX/XX XX:XX XM"/><br/>
			Total Price: $XXX<br/>
			
			<a href="" class="button">BOOK NOW</a>

			<div class="owner">
				Owned By<br/>
				<a href="Servlet?req=user&id=${car.ownerId}">John Doe</a>
			</div>

		</div>

		<div id="main">
			<div id="info">
				${car.make}
				<br/>
				${car.model}
				<br/>
				${car.year?c}
			</div>
			<div id="description">
				${car.description} <br/>
			</div>
			
			<#if same??>
				<a href="Servlet?req=edit&id=${car.id}">Edit Listing</a>
			</#if>
		</div>
	</body>


</html>