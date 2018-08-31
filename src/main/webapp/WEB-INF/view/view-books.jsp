<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

		<form:form action="processSearchBookForm" method="post" modelAttribute="searchedBook">
			<h2 class="form-signin-heading">Przeglądaj książki</h2>
			<div class="row">
				<div class="col col-sm-10 col-lg-6">
					<form:input class="form-control" placeholder="Tytuł" path="title"/>
				</div>
				<div class="col col-sm-2">
					<button class="btn btn-secondary" type="button">Szukaj</button>
				</div>
			</div>
			<div class="row bottom-spaced-row">
				<div class="col col-xs-6 col-lg-4">
					Cena: <form:select class="form-control" path="price">
						<option value="15" label="5-15"/>
						<option value="30" label="5-30"/>
						<option value="50" label="5-50" selected/>
					</form:select>
				</div>
				<div class="col col-xs-6 col-lg-4">
					Stan: <form:select class="form-control" path="condition">
						<option value="new" label="Nowa"/>
						<option value="secondhand" label="Używana"/>
					</form:select>
				</div>
			</div>
			<div class="row bottom-spaced-row">
				<div class="col col-xs-6 col-lg-4">
					Typ szkoły:<form:select class="form-control" path="schoolType">
						<option value="primarySchool" label="Podstawowa"/>
						<option value="highSchool" label="Liceum"/>
						<option value="all" label="Wszystkie" selected/>
					</form:select>
				</div>
				<div class="col col-xs-6 col-lg-4">
					Klasa:<form:select class="form-control" path="schoolClass"
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
				</div>
			</div>
			<div class="row bottom-spaced-row">

				<!-- Może path=owner.school ??? <div class="col col-xs-6 col-lg-4">
					Miejsce odbioru: <select class="form-control" name="reception"
						required>
						<option value="LO28JanaKOchanowskiegio">28 LO Wiktorska
							99</option>
						<option value="Reytan">Reytan</option>
						<option value="Wladyslaw4">Władysław IV</option>
						<option value="PowstancyWarszawy">Powstańców Warszawy</option>
						<option value="all" selected>Wszystkie</option>
					</select>
				</div> -->
				<div class="col col-xs-6 col-lg-4">
					<!-- Zrobić listy wyboru w oparciu o bazę danych!!! -->
					Przedmiot:<form:select class="form-control" path="topic">
						<option value="math" label="Matematyka"/>
						<option value="polish" label="Język polski"/>
						<option value="english" label="Język angielski"/>
						<option value="french" label="Język francuski"/>
						<option value="all" selected label="Dowolny"/>
					</form:select>
				</div>

			</div>

		</form:form>





		<h2>Wyniki</h2>
		<c:forEach items="${queriedBooks}" var="book">
			
		<div class="row bottom-spaced-row ">
			<div class="col col-md-3 col-sm-6">
				<img class="img-fluid img-thumbnail"
					src="${pageContext.request.contextPath}/resources/przykladowa-ksiazka.jpeg" />
			</div>
			<div class="col col-md-8 col-sm-6">
				<h1>${book.title}</h1>
				<p>Cena: ${book.price}</p>
			</div>
		</div>
		</c:forEach>
		
		<div class="row bottom-spaced-row">
			<div class="col col-md-3 col-sm-6">
				<img class="img-fluid img-thumbnail"
					src="${pageContext.request.contextPath}/resources/przykladowa-ksiazka.jpeg" />
			</div>
			<div class="col col-md-8 col-sm-6">
				<h1>Tytuł</h1>
				<p>Cena:</p>
			</div>
		</div>
		<div class="row bottom-spaced-row">
			<div class="col col-md-3 col-sm-6">
				<img class="img-fluid img-thumbnail"
					src="${pageContext.request.contextPath}/resources/ksiazka-pan-tadeusz.jpg" />
			</div>
			<div class="col col-md-8 col-sm-6">
				<h1>Tytuł</h1>
				<p>Cena:</p>
			</div>
		</div>

		<div class="row bottom-spaced-row">
			<div class="col col-md-3 col-sm-6">
				<img class="img-fluid img-thumbnail"
					src="${pageContext.request.contextPath}/resources/ksiazka-pan-tadeusz.jpg" />
			</div>
			<div class="col col-md-8 col-sm-6">
				<h1>Tytuł</h1>
				<p>Cena:</p>
			</div>
		</div>


	</div>






	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>