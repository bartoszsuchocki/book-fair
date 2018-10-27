<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>Zmień Hasło</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/styles.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/not-included-in-bootstrap.css"
	rel="stylesheet">
</head>
<body class="body">

	<%@ include file="fragment/navigation.jspf"%>

	<div class="container">

		<div class="col col-sm-10 col-lg-6">

			<form class="form-signin"
				action="${pageContext.request.contextPath}/userFunctions/processEditPasswordForm"
				method="post">

				<h2 class="form-signin-heading">Edytuj hasło</h2>

				<div class="error-message">${errorEditPasswordMessage}</div>

				<b>Aktualne hasło: </b><input class="form-control" type="password"
					name="currentPassword" /> <b>Nowe hasło: </b><input
					class="form-control" type="password" name="newPassword1" /> <b>Powtórz
					nowe hasło: </b><input class="form-control" type="password"
					name="newPassword2" />

				<!-- CSRF handling-->
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<button class="btn btn-primary" type="submit">Zapisz</button>
				<a href="${pageContext.request.contextPath}/userFunctions/myAccount"
					class="btn btn-secondary">Anuluj</a>

			</form>
		</div>


	</div>





	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>