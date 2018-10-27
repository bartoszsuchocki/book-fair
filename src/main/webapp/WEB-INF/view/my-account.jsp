<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.suchocki.bookfair.config.Constant"%>
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


		<div class="success">${myAccountSuccessMsg}</div>

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
				<security:authentication property="principal.school.name" />
				<security:authentication property="principal.school.address" />
				<br> <a href="${pageContext.request.contextPath}/userFunctions/editForm"
					class="btn btn-info">Edytuj</a> <a
					href="${pageContext.request.contextPath}/userFunctions/editPasswordForm"
					class="btn btn-secondary">Zmień hasło </a>
			</div>
		</div>



		<security:authentication property="principal.possessedBooks"
			var="possessedBooks" scope="page" />

		<c:set var="possessedBooksLength" scope="page"
			value="${fn:length(possessedBooks)}" />
		<h3 class="clickable" onclick="hideOrShowElement('possessed')">Wystawiane
			(${possessedBooksLength}):</h3>

		<div id="possessed">
			<c:if test="${possessedBooksLength==0}">
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
						<c:choose>
							<c:when test="${book.purchaser!=null}">
								<c:set var="bookViewClass" scope="page" value="ordered-item" />
								<div class="${bookViewClass}">
									Zamówiona przez: <a
										href="${pageContext.request.contextPath}/browse/user/${book.purchaser.username}">${book.purchaser.username}</a>
								</div>
							</c:when>
							<c:otherwise>
								<c:set var="bookViewClass" scope="page"
									value="noclasswithsuchname" />
							</c:otherwise>
						</c:choose>
						<h2 class="${bookViewClass}">${book.title}</h2>
						<p>
							<b class="${bookViewClass}">Stan: </b>${Constant.getBookState(book.condition)}
							<br> <b class="${bookViewClass}">Cena: </b>${book.price}
						</p>
						<p>
							<b class="${bookViewClass}">Szkoła: </b>${Constant.getSchoolType(book.schoolType)}
							<br> <b class="${bookViewClass}">Klasa: </b>${Constant.getSchoolClass(book.schoolClass)}
							<br> <b class="${bookViewClass}">Przedmiot: </b>
							${Constant.getTopic(book.topic)}
						</p>

						<a class="btn btn-info"
							href="${pageContext.request.contextPath}/bookManagement/editBook/${book.id}">Edytuj</a>
						<a class="btn btn-danger"
							href="${pageContext.request.contextPath}/bookManagement/deleteBook/${book.id}"
							onclick="return confirm('${Constant.DELETE_CONFIRMATION_MSG}')">Usuń</a>

					</div>

				</div>
			</c:forEach>
		</div>

		<security:authentication property="principal.orderedBooks"
			var="orderedBooks" scope="page" />

		<c:set var="orderedBooksLength" scope="page"
			value="${fn:length(orderedBooks)}" />
		<h3 class="clickable" onclick="hideOrShowElement('ordered')">
			Zamawiane (${orderedBooksLength}):
		</h3>

		<div id="ordered">
			<c:if test="${orderedBooksLength==0}">
				<i>Brak zamówionych książek.</i>
				<br>
			</c:if>

			<c:forEach items="${orderedBooks}" var="book">

				<div class="row bottom-spaced-row">
					<div class="col col-md-3 col-sm-6">
						<img class="img-fluid img-thumbnail"
							src="${pageContext.request.contextPath}/resources/przykladowa-ksiazka.jpeg" />
					</div>
					<div class="col col-md-8 col-sm-6">
						<h2>${book.title}</h2>
						<p>
							<b>Stan: </b>${Constant.getBookState(book.condition)} <br> <b>Cena:
							</b>${book.price}
						</p>
						<p>
							<b>Szkoła: </b>${Constant.getSchoolType(book.schoolType)} <br>
							<b>Klasa: </b>${Constant.getSchoolClass(book.schoolClass)} <br>
							<b>Przedmiot: </b> ${Constant.getTopic(book.topic)}
						</p>
						<p>
							<b>Wystawiający: </b><a
								href="${pageContext.request.contextPath}/browse/user/${book.owner.username}">${book.owner.username}</a>
						</p>
					</div>

				</div>
			</c:forEach>
		</div>


	</div>






	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/basicLayoutManagement.js"></script>
</body>
</html>