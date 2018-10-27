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

		<div class="col col-sm-10 col-lg-6">

			<form:form class="form-signin"
				action="${pageContext.request.contextPath}/userFunctions/processEditAccountForm"
				method="post" modelAttribute="loggedUser">

				<h2 class="form-signin-heading">Edytuj konto</h2>
				<form:errors class="error-message" path="firstName" />
				<form:input class="form-control" placeholder="Imię" path="firstName" />
				<form:errors class="error-message" path="lastName" />
				<form:input class="form-control" placeholder="Nazwisko"
					path="lastName" />
				<form:errors class="error-message" path="email" />
				<form:input class="form-control" placeholder="e-mail" path="email" />
				<form:errors class="error-message" path="school" />
				<form:select class="form-control" path="school">
					<form:options items="${schoolOptionList}" itemLabel="name"/>
				</form:select>
				<form:hidden path="username" />
				<form:hidden path="password" />

				<button class="btn btn-primary" type="submit">Zapisz</button>
				<a href="${pageContext.request.contextPath}/userFunctions/myAccount"
					class="btn btn-secondary">Anuluj</a>

			</form:form>
		</div>


	</div>





	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>