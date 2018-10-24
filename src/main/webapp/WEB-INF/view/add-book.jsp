<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.suchocki.bookfair.config.Constant"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>Wystaw książkę</title>
</head>
<body>

	<%@ include file="fragment/navigation.jspf"%>
	<div class="container">
		<div class="row">
			<div class="col col-sm-6">
				<form:form class="form-signin"
					action="${pageContext.request.contextPath}/processNewBookForm"
					method="post" modelAttribute="book">
					<div class="error-message">${customValidationError}</div>
					<h2 class="form-signin-heading">Wystaw książkę</h2>
					<form:errors path="title" class="error-message" />
					Tytuł: <form:input class="form-control" path="title"
						placeholder="Wpisz tytuł" />
					<form:errors path="author" class="error-message" />
					Autor: <form:input class="form-control" path="author"
						placeholder="Wpisz autora" />
					Opis: <form:textarea class="form-control" rows="5"
						path="description" placeholder="Opcjonalny opis" />
					Stan: <form:select class="form-control" path="condition">
						<form:options items="${Constant.BOOK_STATES}"/>
					</form:select>
					<form:errors path="price" class="error-message" />
					Cena: <form:input class="form-control" path="price"
						placeholder="Cena (zł)" />
					<form:errors path="schoolType" class="error-message" />
						Typ szkoły:<form:select class="form-control" path="schoolType">
						<form:options items="${Constant.SCHOOL_TYPES}" />
					</form:select> Klasa:<form:select class="form-control" path="schoolClass">
						<form:options items="${Constant.SCHOOL_CLASSES}" />
					</form:select>
					Przedmiot:<form:select class="form-control" path="topic">
						<form:options items="${Constant.TOPICS}" />
					</form:select>
					<!--  <input class="form-control" type="file"
						accept="image/png, image/jpg, image/jpeg"  />
					-->
					<button class="btn btn-success" type="submit">Wystaw</button>

				</form:form>


			</div>


		</div>



	</div>


	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>