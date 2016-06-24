<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title><spring:message code="address" /></title>

<!-- Bootstrap Core CSS -->
<link href="../resources/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../resources/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="../resources/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<jsp:include page="nav.jsp"></jsp:include>

	<!-- Page Content -->
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						<spring:message code="list" />
						-
						<spring:message code="address" />
					</h1>
					<a href="edit-adresse.html" class="btn btn-default"> <spring:message
							code="add.adresse.proposition" />
					</a>
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th>Id</th>
								<th><spring:message code="clients" /></th>
								<th><spring:message code="number" /></th>
								<th><spring:message code="street" /></th>


								<th><spring:message code="city" /></th>
								<th><spring:message code="country" /></th>
								<th><spring:message code="modify" /></th>
								<th><spring:message code="delete" /></th>
							</tr>

							<c:forEach items="${adresses}" var="adresse">

								<tr>
									<td>${adresse.id }</td>
									
									<td><a href="../clients/list-client.html?id=${adresse.id}"
										class="btn btn-default"> <spring:message code="clients" />
									</a></td>

									<td>${adresse.numero }</td>

									<td>${adresse.rue }</td>

									<td>${adresse.ville }</td>

									<td>${adresse.pays }</td>

									<td><a href="edit-adresse.html?id=${adresse.id}"
										class="btn btn-default"> <spring:message code="modify" />
									</a></td>
									<td><a href="delete-adresse.html?id=${adresse.id}"
										class="btn btn-default"> <spring:message code="delete" />
									</a></td>

								</tr>
							</c:forEach>
						</tbody>

					</table>
					<a href="edit-adresse.html" class="btn btn-default"> <spring:message
							code="add.adresse.proposition" />
					</a>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</div>

</body>

</html>
