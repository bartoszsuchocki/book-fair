<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/styles.min.css" rel="stylesheet">
	<link href="css/not-included-in-bootstrap.css" rel="stylesheet">
	<title>Zarejestruj się</title>
</head>
<body>
	<%@ include file="WEB-INF/fragment/navigation.jspf" %>
	
	<div class = "container">
		<div class = "col col-xs-6 col-md-4">
		<!-- action="costam" przeniesie do strony Kiermasz/costam - trzeba w web.xml zdefiniowac tam odpowiedni servlet -->
			<form class = "form-signin" action="register" method="post">
				<h2 class="form-signin-heading">Zarejestruj się</h2>
				<input class="form-control" type="text" placeholder="Imię" name="name" required autofocus/>
				<input class="form-control" type="text" placeholder="Nazwisko" name="surname" required/>
				<input class="form-control" type="text" placeholder="login" name="login" required/>
				<input class="form-control" type="email" placeholder="e-mail" name="mail" required/>
				<select class="form-control" name="school" required>
					<option disabled selected value> -- Wybierz swoją szkołę -- </option>
					<option value="LO28JanaKOchanowskiegio">28 LO Wiktorska 99</option>
					<option value="Reytan">Reytan</option>
					<option value="Wladyslaw4">Władysław IV</option>
					<option value="PowstancyWarszawy">Powstańców Warszawy</option>
				</select>
				<button class="btn btn-block btn-primary" type="submit">Zarejestruj</button>
			
			</form> 
		
		
		
		</div>
	</div>
	
	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>