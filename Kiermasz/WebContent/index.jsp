<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>Strona główna</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/styles.min.css" rel="stylesheet">
<link href="css/not-included-in-bootstrap.css" rel="stylesheet">
</head>
<body class="body">


	<%@ include file="WEB-INF/fragment/navigation.jspf"%>



	<div class="container">

		<h2>Ostatnio dodane</h2>
		<div class="row bottom-spaced-row">
			<div class="col col-md-4 col-sm-6">
				<img class="img-responsive" height="200px" width="200px"
					src="przykladowa-ksiazka.jpeg" />
			</div>
			<div class="col col-md-8 col-sm-6">
				<h1>Tytuł</h1>
				<p>Cena:</p>
			</div>
		</div>

		<div class="row bottom-spaced-row">
			<div class="col col-md-4 col-sm-6">
				<img class="img-responsive" height="200px" width="200px"
					src="przykladowa-ksiazka.jpeg" />
			</div>
			<div class="col col-md-8 col-sm-6">
				<h1>Tytuł</h1>
				<p>Cena:</p>
			</div>
		</div>
		<div class="row bottom-spaced-row">
			<div class="col col-md-4 col-sm-6">
				<img class="img-responsive" height="200px" width="200px"
					src="ksiazka-pan-tadeusz.jpg" />
			</div>
			<div class="col col-md-8 col-sm-6">
				<h1>Tytuł</h1>
				<p>Cena:</p>
			</div>
		</div>

		<div class="row bottom-spaced-row">
			<div class="col col-md-4 col-sm-6">
				<img class="img-responsive" height="200px" width="200px"
					src="ksiazka-pan-tadeusz.jpg" />
			</div>
			<div class="col col-md-8 col-sm-6">
				<h1>Tytuł</h1>
				<p>Cena:</p>
			</div>
		</div>


	</div>






	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>