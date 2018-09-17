<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Przeglądaj książki</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="${pageContext.request.contextPath}/resources/css/not-included-in-bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/styles.min.css"
	rel="stylesheet">

</head>
<body class="body">


	<%@ include file="fragment/navigation.jspf"%>


	<div class="container">

		<form:form action="processSearchBookForm" method="get"
			modelAttribute="bookFilter">
			<h2 class="form-signin-heading">Przeglądaj książki</h2>
			<div class="row">
				<div class="col col-sm-10 col-lg-6">
					<form:input class="form-control" placeholder="Tytuł"
						path="desiredBook.title" />
				</div>
				<div class="col col-sm-2">
					<button class="btn btn-secondary" type="submit">Szukaj</button>
				</div>
			</div>
			<div class="row bottom-spaced-row">
				<div class="col col-xs-6 col-lg-4">
					Cena:
					<form:select class="form-control" path="desiredBook.price">
						<form:option value="50" label="5-50" />
						<form:option value="30" label="5-30" />
						<form:option value="15" label="5-15" />
					</form:select>
				</div>
				<div class="col col-xs-6 col-lg-4">
					Stan:
					<form:select class="form-control" path="desiredBook.condition">
						<form:option value="" label="Wszystkie" />
						<form:option value="new" label="Nowa" />
						<form:option value="secondhand" label="Używana" />
					</form:select>
				</div>
			</div>
			<div class="row bottom-spaced-row">
				<div class="col col-xs-6 col-lg-4">
					Typ szkoły:
					<form:select class="form-control" path="desiredBook.schoolType">
						<form:option value="" label="Wszystkie" />
						<form:option value="primarySchool" label="Podstawowa" />
						<form:option value="highSchool" label="Liceum" />
					</form:select>
				</div>
				<div class="col col-xs-6 col-lg-4">
					Klasa:
					<form:select class="form-control" path="desiredBook.schoolClass">
						<form:option value="" label="Nie dotyczy/dotyczy kilku/wszystkie" />
						<form:option value="1" label="1" />
						<form:option value="2" label="2" />
						<form:option value="3" label="3" />
						<form:option value="4" label="4" />
						<form:option value="5" label="5" />
						<form:option value="6" label="6" />
						<form:option value="7" label="7" />
						<form:option value="8" label="8" />
					</form:select>
				</div>
			</div>
			<div class="row bottom-spaced-row">

				<!--  Może path=owner.school ???-->
				<div class="col col-xs-6 col-lg-4">
					Miejsce odbioru:
					<form:select class="form-control" path="desiredBook.owner.school">
						<form:option value="" label="Wszystkie" />
						<form:option value="LO28JanaKOchanowskiegio"
							label="28 LO Wiktorska 99" />
						<form:option value="Reytan" label="Reytan" />
						<form:option value="Wladyslaw4" label="Władysław IV" />
						<form:option value="PowstancyWarszawy" label="Powstańców Warszawy" />
					</form:select>
				</div>
				<div class="col col-xs-6 col-lg-4">
					<!-- Zrobić listy wyboru w oparciu o bazę danych!!! -->
					Przedmiot:
					<form:select class="form-control" path="desiredBook.topic">
						<form:option value="" label="Dowolny" />
						<form:option value="math" label="Matematyka" />
						<form:option value="polish" label="Język polski" />
						<form:option value="english" label="Język angielski" />
						<form:option value="french" label="Język francuski" />
					</form:select>
				</div>
			</div>

			<div class="row bottom-spaced-row">
				<div class="col col-xs-6 col-lg-4">
					Sortuj:
					<form:select class="form-control" path="bookComparator">
						<form:options items="${comparators}" />
					</form:select>

				</div>

			</div>

		</form:form>




		<h2>Wyniki(${fn:length(queriedBooks)})</h2>

		<c:forEach items="${queriedBooks}" var="book">

			<div class="row bottom-spaced-row">
				<div class="col col-md-3 col-sm-6">
					<img class="img-fluid img-thumbnail"
						src="${pageContext.request.contextPath}/resources/przykladowa-ksiazka.jpeg" />
				</div>
				<div class="col col-md-8 col-sm-6">
					<h2>${book.title}</h2>
					<p>
						<b>Stan: </b>${book.condition} <br> <b>Cena: </b>${book.price}
					</p>
					<p>
						<b>Sprzedawca: </b><a
							href="${pageContext.request.contextPath}/browse/user/${book.owner.username}">${book.owner.username}</a>
					</p>
					<p>
						<b>Szkoła: </b>${book.schoolType} <br> <b>Klasa: </b>${book.schoolClass}
						<br> <b>Przedmiot: </b> ${book.topic}
					</p>


				</div>

			</div>
		</c:forEach>
	</div>









	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>