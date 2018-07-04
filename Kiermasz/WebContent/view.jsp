<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>Przeglądaj książki</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/styles.min.css" rel="stylesheet">
<link href="css/not-included-in-bootstrap.css" rel="stylesheet">
</head>
<body class="body">


	<%@ include file="WEB-INF/fragment/navigation.jspf"%>


	<div class="container">

		<form action="#" method="post">

			<div class="row">
				<div class="col col-sm-10 col-lg-6">
					<input type="text" class="form-control" placeholder="Tytuł" />
				</div>
				<div class="col col-sm-2">
					<button class="btn btn-secondary" type="button">Szukaj</button>
				</div>
			</div>
			<div class="row bottom-spaced-row">
				<div class="col col-xs-4 col-lg-4">
					Cena <select class="form-control" name="price" required>
						<option value="15">5-15</option>
						<option value="30">5-30</option>
						<option value="50">5-50</option>
					</select>
				</div>
				<div class="col col-xs-4 col-lg-4">
					Stan: <select class="form-control" name="condition" required>
						<option value="new">Nowa</option>
						<option value="secondhand">Używana</option>
					</select>
				</div>
				<div class="col col-xs-4 col-lg-4">
					Miejsce odbioru: <select class="form-control" name="reception" required>
						<option disabled selected> -- Wybierz szkołę odbioru -- </option>
						<option value="LO28JanaKOchanowskiegio">28 LO Wiktorska 99</option>
						<option value="Reytan">Reytan</option>
						<option value="Wladyslaw4">Władysław IV</option>
						<option value="PowstancyWarszawy">Powstańców Warszawy</option>
						<option value="PowstancyWarszawy">Wszystkie</option>
					</select>
				</div>
			</div>
			
		</form>





		<h2>Wyniki</h2>
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