<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<form:form class="form-signin" action="processNewBookForm"
					method="post" modelAttribute="book">
					<h2 class="form-signin-heading">Wystaw książkę</h2>
					<form:errors path="title" class="error-message" />
					<form:input class="form-control" path="title" placeholder="Tytuł"
						/>
					<form:errors path="author" class="error-message" />
					<form:input class="form-control" path="author" placeholder="Autor"
						 />
					<form:textarea class="form-control" rows="5" path="description" placeholder="Opis"/>
					Stan: <form:select class="form-control" path="condition" >
						<form:option value="new" label="Nowa" />
						<form:option value="secondhand" label="Używana" />
					</form:select>
					<form:errors path="price" class="error-message" />
					<form:input class="form-control" path="price"
						placeholder="Cena (zł)"  />
					<form:errors path="schoolType" class="error-message" />
						Typ szkoły:<form:select class="form-control" path="schoolType"
						>
						<form:option value="" label="---Wybierz typ szkoły---" />
						<form:option value="primarySchool" label="Podstawowa" />
						<form:option value="highSchool" label="Liceum" />
					</form:select> Klasa:<form:select class="form-control" path="schoolClass"
						>
						<form:option value="" label="--Wybierz klasę--" />
						<option value="1" label="1" />
						<option value="2" label="2" />
						<option value="3" label="3" />
						<option value="4" label="4" />
						<option value="5" label="5" />
						<option value="6" label="6" />
						<option value="7" label="7" />
						<option value="8" label="8" />
						<option value="-1" label="Nie dotyczy/dotyczy kilku" />
					</form:select>
					Przedmiot:<form:select class="form-control" path="topic">
						<option value="math" label="Matematyka"/>
						<option value="polish" label="Język polski"/>
						<option value="english" label="Język angielski"/>
						<option value="french" label="Język francuski"/>
						<option value="all" selected label="Dowolny"/>
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