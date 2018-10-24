<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.suchocki.bookfair.config.Constant"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Edytuj Konto</title>
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

		<div class="col col-sm-6">
			<form:form class="form-signin"
				action="${pageContext.request.contextPath}/bookManagement/processEditBookForm"
				method="post" modelAttribute="editedBook">
				<h2 class="form-signin-heading">Edytuj książkę</h2>
				<form:errors path="title" class="error-message" />
				Tytuł: <form:input class="form-control" path="title"
					placeholder="Wpisz tytuł" />
				<form:errors path="author" class="error-message" />
				Autor: <form:input class="form-control" path="author"
					placeholder="Wpisz autora" />
				Opis: <form:textarea class="form-control" rows="5"
					path="description" placeholder="Opis" />
					Stan: <form:select class="form-control" path="condition">
					<form:options items="${Constant.BOOK_STATES}" />
				</form:select>
				Cena: <form:errors path="price" class="error-message" />
				<form:input class="form-control" path="price"
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
				<form:hidden path="id" />
				<button class="btn btn-success" type="submit">Edytuj</button>

			</form:form>


		</div>



	</div>





	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>