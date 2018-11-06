<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<html>
<head>
<title>Strona główna</title>
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

		<h2>Ostatnio dodane</h2>

		<c:forEach items="${lastAddedBooks}" var="book">

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
</body>
</html>