<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="edit-style.css"/>
		<script src="jquery-3.1.1.min.js"></script>
		<script src="edit.js"></script>
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
					<li><a href="Servlet?req=search">BROWSE</a></li>
				</#if>
			</ul>
		</div>

		<div id="main">
			<h1>Image Upload</h1>
			<div id="image-options">
				<form id="image-upload" action="Servlet?req=uploadImage" method="POST" enctype="multipart/form-data">
					<input hidden name="id" value="${carId}"/>
					
					<label for="upload" class="button"><img src="images/upload.png" class="thumb" />Upload</label>
					
					<input type="file" name="image" id="upload" accept="image/*" /><br />
				</form>
				<ul id="current-images"></ul>
			</div>
			
			<div class="right">
				<a href="Servlet?req=car&id=${carId}" class="button">Done</a>
			</div>
		</div>
	</body>


</html>