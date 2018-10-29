<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.suchocki.bookfair.config.Constant"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

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

<title>Zaloguj się</title>
</head>
<body>
	<%@ include file="fragment/navigation.jspf"%>

	<div class="container">

		<div class="col col-sm-6 col-md-4">
			<form:form class="form-signin"
				action="${pageContext.request.contextPath}/authenticateTheUser"
				method="post">

				<c:if test="${redirectionPerformed==true}">
					<div class="error-message">${Constant.MUST_LOG_IN_MESSAGE}</div>
				</c:if>

				<h2 class="form-signin-heading">Zaloguj się</h2>

				<c:if test="${param.error != null}">
					<div class="error-message">${Constant.AUTHENTICATION_ERROR_MESSAGE}</div>
				</c:if>

				<input class="form-control" name="username" type="text"
					placeholder="login" />
				<input class="form-control" name="password" type="password"
					placeholder="hasło" required>

				<button type="submit" class="btn btn-lg btn-primary btn-block">Zaloguj</button>

			</form:form>
			<a href="showRegistrationForm">Zarejestruj się</a>

		</div>
	</div>


	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>