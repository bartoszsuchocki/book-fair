<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/styles.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/not-included-in-bootstrap.css" rel="stylesheet">
<title>O serwisie</title>
</head>
<body>

	<%@ include file="fragment/navigation.jspf"%>
	<!-- Zastanowić się nad lepszym linkiem z wykorzystaniem ${pageContext.request.contextPath} -->

	<div class="container">
		<h1>Kiermasze szkolne</h1>
		<p>Serwis ma wspomóc organizację i przeprowadzanie kiermaszów
			szkolnych.</p>
		<p>Uczniowie mogą wystawiać lub zamawiać podręczniki od innych
			uczniów.</p>
		<p>Zamówiona książka/podręcznik zostanie sprzedana w szkole ucznia
			wystawiającego książkę.</p>
	</div>

	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>