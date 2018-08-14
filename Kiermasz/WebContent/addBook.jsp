<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/styles.min.css" rel="stylesheet">
<link href="css/not-included-in-bootstrap.css" rel="stylesheet">
<title>Wystaw książkę</title>
</head>
<body>

	<%@ include file="WEB-INF/fragment/navigation.jspf"%>

	<div class="container">
		<div class="row">
			<div class="col col-sm-6">
				<form class="form-signin" action="addBook" method="post">
					<h2 class="form-signin-heading">Wystaw książkę</h2>
					<input class="form-control" type="text" name="title"
						placeholder="Tytuł" required/>
					<input class="form-control" type="text"
						name="author" placeholder="Autor" required />
					<textarea class="form-control" rows="5" name="description">Opis</textarea>
					Stan: <select class="form-control" required>
						<option value="new">Nowa</option>
						<option value="secondhand">Używana</option>
					</select>
					<input class="form-control" type="text" name="price"
						placeholder="Cena (zł)" required/>
					Typ szkoły:<select class="form-control" name="schoolType" required>
						<option disabled selected value="">"--Wybierz typ szkoły--"</option>
						<option value="primarySchool">Podstawowa</option>
						<option value="highSchool">Liceum</option>
					</select>
					Klasa:<select class="form-control" name="class" required>
						<option selected disabled value="">--Wybierz klasę--</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="none">Nie dotyczy/dotyczy kilku</option>
					</select>
					
					<input class="form-control" type="file" accept="image/png, image/jpg, image/jpeg" required/>
									
					<button class="btn btn-success" type="submit">Wystaw</button>
				
				</form>


			</div>


		</div>



	</div>


	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>