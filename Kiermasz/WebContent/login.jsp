<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/styles.min.css" rel="stylesheet">
<link href="css/not-included-in-bootstrap.css" rel="stylesheet">

<title>Zaloguj się</title>
</head>
<body>
	<%@ include file="WEB-INF/fragment/navigation.jspf"%>

	<div class="container">
		<div class="col col-sm-6 col-md-4 col-md-offset-4">
			<form class="form-signin" action="j_security_check" method="post" >
				<h2 class="form-signin-heading">Zaloguj się</h2>
				<input class="form-control" name="j_username" type="text" placeholder="login" required autofocus>
				<input class="form-control" name="j_password" type="password" placeholder="hasło" required>
				<button type="submit" class="btn btn-lg btn-primary btn-block">Zaloguj</button>
			</form>
			<a href="#">Zarejestruj się</a>
		
		</div>
		



	</div>


	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>