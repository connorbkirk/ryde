<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="styles/default-style.css"/>
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
			<h1>Your request has been completed.<br/></h1>
		</div>
	</body>


</html>