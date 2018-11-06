<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.suchocki.bookfair.config.Constant"%>
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
<body onload="scrollToElement('scrollToMe','collapseMenu')" class="body">


	<%@ include file="fragment/navigation.jspf"%>

	<c:choose>
		<c:when test="${queriedBooks!=null}">
			<c:set var="formId" value="dontScrollToMe" scope="page" />
			<c:set var="resultsId" value="scrollToMe" scope="page" />
		</c:when>

	</c:choose>

	<div class="container">

		<form:form id="${formId}" action="processSearchBookForm" method="get"
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
						<form:options items="${Constant.PRICE_RANGES}" />
					</form:select>
				</div>
				<div class="col col-xs-6 col-lg-4">
					Stan:
					<form:select class="form-control" path="desiredBook.condition">
						<form:option value="${Constant.ALL_BOOK_STATE_VALUE}"
							label="${Constant.ALL_BOOK_STATE_LABEL}" />
						<form:options items="${Constant.BOOK_STATES}" />
					</form:select>
				</div>
			</div>
			<div class="row bottom-spaced-row">
				<div class="col col-xs-6 col-lg-4">
					Typ szkoły:
					<form:select class="form-control" path="desiredBook.schoolType">
						<form:option value="${Constant.ALL_SCHOOL_TYPE_VALUE}"
							label="${Constant.ALL_SCHOOL_TYPE_LABEL}" />
						<form:options items="${Constant.SCHOOL_TYPES}" />
					</form:select>
				</div>
				<div class="col col-xs-6 col-lg-4">
					Klasa:
					<form:select class="form-control" path="desiredBook.schoolClass">
						<form:options items="${Constant.SCHOOL_CLASSES}" />
					</form:select>
				</div>
			</div>
			<div class="row bottom-spaced-row">

				<!--  Może path=owner.school ???-->
				<div class="col col-xs-6 col-lg-4">
					Miejsce odbioru:
					<form:select class="form-control" path="desiredBook.owner.school">
						<form:option value="${Constant.ALL_SCHOOL_VALUE}"
							label="${Constant.ALL_SCHOOL_LABEL}" />
						<form:options items="${schoolOptionList}" itemLabel="name" />
					</form:select>
				</div>
				<div class="col col-xs-6 col-lg-4">
					Przedmiot:
					<form:select class="form-control" path="desiredBook.topic">
						<form:option value="${Constant.ALL_TOPIC_VALUE}"
							label="${Constant.ALL_TOPIC_LABEL}" />
						<form:options items="${Constant.TOPICS}" />
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




		<h2 id="${resultsId}">Wyniki(${fn:length(queriedBooks)})</h2>

		<c:forEach items="${queriedBooks}" var="book">

			<div class="row bottom-spaced-row">
				<div class="col col-md-3 col-sm-6">
					<img class="img-fluid img-thumbnail"
						src="${pageContext.request.contextPath}/browse/getBookPicture/${book.id}" />
				</div>
				<div class="col col-md-8 col-sm-6">
					<h2>
						<a class="linked-title"
							href="${pageContext.request.contextPath}/browse/book/${book.id}">${book.title}</a>
					</h2>
					<p>
						<b>Stan: </b>${Constant.getBookState(book.condition)} <br> <b>Cena:
						</b>${book.price}
					</p>
					<p>
						<b>Sprzedawca: </b><a
							href="${pageContext.request.contextPath}/browse/user/${book.owner.username}">${book.owner.username}</a>
					</p>
					<p>
						<b>Szkoła: </b>${Constant.getSchoolType(book.schoolType)} <br>
						<b>Klasa: </b>${Constant.getSchoolClass(book.schoolClass)} <br>
						<b>Przedmiot: </b> ${Constant.getTopic(book.topic)}
					</p>

					<a class="btn btn-success"
						href="${pageContext.request.contextPath}/order/orderBook/${book.id}">Zamów</a>

				</div>

			</div>
		</c:forEach>
	</div>









	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/basicLayoutManagement.js"></script>
</body>
</html>