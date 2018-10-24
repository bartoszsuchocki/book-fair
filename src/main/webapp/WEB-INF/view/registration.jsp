<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/styles.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/not-included-in-bootstrap.css" rel="stylesheet">
	<title>Zarejestruj się</title>
</head>
<body>
	<%@ include file="fragment/navigation.jspf" %>
	
	<div class = "container">
		<div class = "col col-xs-6 col-md-4">
		<!-- action="costam" przeniesie do strony Kiermasz/costam - trzeba w web.xml zdefiniowac tam odpowiedni servlet -->
			<form:form class = "form-signin" action="${pageContext.request.contextPath}/processRegistrationForm" method="post"
				modelAttribute="user">
				
				<h2 class="form-signin-heading">Zarejestruj się</h2>
				<form:errors class="error-message" path="username"/>
				<form:input class="form-control" placeholder="Login" path="username" /> 
				<form:errors class="error-message" path="firstName"/>
				<form:input class="form-control" placeholder="Imię" path="firstName" />
				<form:errors class="error-message" path="lastName"/>	
				<form:input class="form-control" placeholder="Nazwisko" path="lastName"/>
				<form:errors class="error-message" path="password"/>
				<form:password class="form-control" placeholder="Password" path="password"/>
				<form:errors class="error-message" path="email"/>
				<form:input class="form-control" placeholder="e-mail" path="email"/>
				<form:errors class="error-message" path="school"/>
				<form:select class="form-control" path="school">
					<form:options items="${schoolOptionList}" itemLabel="name"/>
				</form:select>
				<button class="btn btn-block btn-primary" type="submit">Zarejestruj</button>
			
			</form:form> 
		
		
		
		</div>
	</div>
	
	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>