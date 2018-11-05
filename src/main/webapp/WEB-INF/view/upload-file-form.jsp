<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>Upload File</title>
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


	<form:form method="post"
		action="${pageContext.request.contextPath}/userFunctions/processUploadFileForm?${_csrf.parameterName}=${_csrf.token}"
		enctype="multipart/form-data"
		modelAttribute="tmpAuth">
		
		<form:input path="name" label="name"/> <br>
		<form:input path="description" label="description"/> <br>
		Name: <input type="text" name="name" />
		<br>
		File: <input type="file" name="file" />
		<br>
		<input type="submit" value="Upload" />
	</form:form>


	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>