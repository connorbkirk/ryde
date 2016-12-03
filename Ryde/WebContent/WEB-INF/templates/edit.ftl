<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">

<html>

	<head>	
		<link rel="stylesheet" type="text/css" href="edit-style.css"/>
		<script src="jquery-3.1.1.min.js"></script>
		<script src="jquery-ui.min.js"></script>
		<script src="auto.js"></script>
		<script src="check.js"></script>
		<script src="image.js"></script>
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
		<#if done??>
			<h1>Your car has been editted.<br/>
			<a href="Servlet?req=car&id=${car.id}">Click here to view.</a></h1>
		</#if>
			<form action="Servlet?req=edit" method="POST">
				<input hidden name="id" value="${car.id}"/>
				<input type="textfield" placeholder="Make" name="make" value="${car.make}" class="text-entry" />
				<input type="textfield" placeholder="Model" name="model" value="${car.model}" class="text-entry" />
				<input type="textfield" placeholder="Year" name="year" value="${car.year?c}" class="text-entry" /><br />
				<input type="textfield" placeholder="Color" name="color" value="${car.color}" class="text-entry" />
				<input type="textfield" placeholder="Price per Day" name="price" value="${car.price?c}" class="text-entry" />
				<input type="textfield" placeholder="Body type" name="type" value="${car.carType}" class="text-entry" /><br />
				<textarea placeholder="Description" name="description" class="text-entry text-box">${car.description}</textarea><br/>
				<input type="submit" class="button" />
			</form>
			<div id="image-options">
				<form id="image-upload" action="Servlet?req=uploadImage" method="POST" enctype="multipart/form-data">
					<input hidden name="id" value="${car.id}"/>
					
					<label for="upload" class="button"><img src="images/upload.png" class="thumb" />Upload</label>
					
					<input type="file" name="image" id="upload" accept="image/*" /><br />
				</form>
				<ul id="current-images">
					<#list car.images as image>
						<li style="background-image:url(${image.image});" id="img-${image.id}">
							<div class="overlay">
								<input type="button" value="X" class="deleteImage" id="${image.id}" />
							</div>
						</li>
					</#list>
				</ul>
			</div>
			<br />
			<a href="Servlet?req=delete&id=${car.id}" class="button">Delete Post</a>
		</div>
	</body>


</html>