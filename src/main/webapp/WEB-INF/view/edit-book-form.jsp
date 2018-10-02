<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<form:option value="new" label="Nowa" />
					<form:option value="secondhand" label="Używana" />
				</form:select>
				Cena: <form:errors path="price" class="error-message" />
				<form:input class="form-control" path="price"
					placeholder="Cena (zł)" />
				<form:errors path="schoolType" class="error-message" />
						Typ szkoły:<form:select class="form-control" path="schoolType">
					<form:option value="" label="---Wybierz typ szkoły---" />
					<form:option value="primarySchool" label="Podstawowa" />
					<form:option value="highSchool" label="Liceum" />
				</form:select> Klasa:<form:select class="form-control" path="schoolClass">
					<form:option value="-1" label="Nie dotyczy/dotyczy kilku" />
					<form:option value="1" label="1" />
					<form:option value="2" label="2" />
					<form:option value="3" label="3" />
					<form:option value="4" label="4" />
					<form:option value="5" label="5" />
					<form:option value="6" label="6" />
					<form:option value="7" label="7" />
					<form:option value="8" label="8" />
				</form:select>
					Przedmiot:<form:select class="form-control" path="topic">
					<form:option value="all" label="Dowolny" />
					<form:option value="math" label="Matematyka" />
					<form:option value="polish" label="Język polski" />
					<form:option value="english" label="Język angielski" />
					<form:option value="french" label="Język francuski" />
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