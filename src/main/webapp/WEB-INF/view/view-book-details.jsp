<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.suchocki.bookfair.config.Constant"%>

<html>
<head>
<title>${searchedBook.title}</title>
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

		<h2>${searchedBook.title}</h2>
		<div class="row bottom-spaced-row">

			<div class="col col-sm-6 col-lg-3">
				<img class="img-fluid img-thumbnail"
					src="${pageContext.request.contextPath}/resources/przykladowa-ksiazka.jpeg" /><br>
				<a class="btn btn-success"
					href="${pageContext.request.contextPath}/order/orderBook/${searchedBook.id}">Zamów</a>
			</div>

			<div class="col col-sm-6 col-lg-4">
				<p>
					<b>Autor: </b>${searchedBook.author}<br>
				</p>
				<p>
					<b>Opis: </b>${searchedBook.description}
				</p>
				<p>
					<b>Cena: </b>${searchedBook.price} <br> <b>Stan: </b>${Constant.getBookState(searchedBook.condition)}
				</p>
				<p>
					<b>Sprzedawca: </b><a
						href="${pageContext.request.contextPath}/browse/user/${searchedBook.owner.username}">${searchedBook.owner.username}</a>
				</p>
				<p>
					<b>Typ szkoły: </b>${Constant.getSchoolType(searchedBook.schoolType)}<br>
					<b>Klasa: </b>${Constant.getSchoolClass(searchedBook.schoolClass)}<br>
					<b>Przedmiot: </b>${Constant.getTopic(searchedBook.topic)}
				</p>
				<p>
					<b>Data dodania: </b>${searchedBook.creationDate}
				</p>
			</div>
		</div>
	</div>


	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>