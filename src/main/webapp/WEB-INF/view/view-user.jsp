<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>${searchedUser.username}</title>
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

		<h2>
			Użytkownik <i>${searchedUser.username}</i>
		</h2>
		<div class="row bottom-spaced-row">
			<div class="col col-sm-10 col-lg-6">
				<b>Imię:</b> ${searchedUser.firstName} <br>
				<b>Nazwisko:</b> ${searchedUser.lastName} <br>
				<b>E-mail:</b> ${searchedUser.email} <br>
				<b>Szkoła sprzedaży:</b> ${searchedUser.school.name} ${searchedUser.school.address}


			</div>
		</div>
	</div>


	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>