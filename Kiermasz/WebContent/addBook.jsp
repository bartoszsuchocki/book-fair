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
						placeholder="Tytuł" />
					<input class="form-control" type="text"
						name="author" placeholder="Autor" />
					<textarea class="form-control" rows="5" name="description">Opis</textarea>
					Stan: <select class="form-control">
						<option>Nowa</option>
						<option>Używana</option>
					</select>
					<input class="form-control" type="text" name="price"
						placeholder="Cena (zł)"/>
				</form>


			</div>


		</div>



	</div>


	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>