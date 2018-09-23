<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Moje konto</title>
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
		<h2>Moje konto</h2>


		<div class="success">${passwordEditedMsg}</div>


		<h3>Informacje</h3>
		<div class="row bottom-spaced-row">
			<div class="col col-sm-8 col-lg-6">
				<b>Login: </b>
				<security:authentication property="principal.username" />
				<br> <b>Imię: </b>
				<security:authentication property="principal.firstName" />
				<br> <b>Nazwisko: </b>
				<security:authentication property="principal.lastName" />
				<br> <b>E-mail: </b>
				<security:authentication property="principal.email" />
				<br> <b>Szkoła sprzedaży: </b>
				<security:authentication property="principal.school" />
				<br> <a href="${pageContext.request.contextPath}/editForm"
					class="btn btn-info">Edytuj</a> <a
					href="${pageContext.request.contextPath}/editPasswordForm"
					class="btn btn-secondary">Zmień hasło </a>
			</div>
		</div>




		<security:authentication property="principal.possessedBooks"
			var="possessedBooks" scope="page" />

		<c:set var="length" scope="page" value="${fn:length(possessedBooks)}" />
		<h3>Wystawiane (${length}):</h3>
		<c:if test="${length==0}">
			<i>Brak wystawianych książek.</i>
			<br>
		</c:if>

		<c:forEach items="${possessedBooks}" var="book">

			<div class="row bottom-spaced-row">
				<div class="col col-md-3 col-sm-6">
					<img class="img-fluid img-thumbnail"
						src="${pageContext.request.contextPath}/resources/przykladowa-ksiazka.jpeg" />
				</div>
				<div class="col col-md-8 col-sm-6">
					<h2>${book.title}</h2>
					<p>
						<b>Stan: </b>${book.condition} <br> <b>Cena: </b>${book.price}
					</p>
					<p>
						<b>Szkoła: </b>${book.schoolType} <br> <b>Klasa: </b>${book.schoolClass}
						<br> <b>Przedmiot: </b> ${book.topic}
					</p>

					<a class="btn btn-info">Edytuj</a> <a class="btn btn-danger">Usuń</a>
				</div>

			</div>
		</c:forEach>




	</div>






	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>