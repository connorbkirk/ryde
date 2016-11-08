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
				<li><a href="#">LOGIN</a></li>
				<li><a href="#">INFO</a></li>
			</ul>
		</div>

		<div id="jumbotron">
			<span class="bottom" >Displaying X Images</span>
		</div>

		<div id="sidebar">
			From<input type="textfield" name="fromData" placeholder="XX/XX/XX XX:XX XM"/><br/>
			Until<input type="textfield" name="fromData" placeholder="XX/XX/XX XX:XX XM"/><br/>
			Total Price: $XXX<br/>
			
			<a href="" class="button">BOOK NOW</a>

			<div class="owner">
				Owned By<br/>
				John Doe
				${car.ownerId}
			</div>

		</div>

		<div id="main">
			<div id="info">
				${car.make}
				<br/>
				${car.model}
				<br/>
				${car.year}
			</div>
			<div id="description">
				${car.description} <br/>
			</div>
		</div>
	</body>


</html>