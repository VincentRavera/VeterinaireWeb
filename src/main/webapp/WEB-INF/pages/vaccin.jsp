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

<title><spring:message code="edit" /></title>

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
<%-- <jsp:include page="header.jsp"></jsp:include> --%>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<form:form action="add-vaccin.html" commandName="vaccin"
						method="POST">

						<form:hidden path="id" />
						
						<form:label path="type">
							<spring:message code="type" />
						</form:label>
						<form:input class="form-control" placeholder="Type" path="type"
							id="type" />
						<form:errors path="type" cssclass="error"></form:errors>

						
						<form:label path="obligatoire">
							<spring:message code="mandatory" />
						</form:label>
						<input type="radio" name="obligatoire" id="True" value="true" checked="checked" />
						<spring:message code="true" />
						<input type="radio" name="obligatoire" id="False"  value="false" />
						<spring:message code="false" />
						<p/>
						
						<input type="submit" value="<spring:message code="validate" />"
							class="btn btn-default" />
						<a href="list-adresses.html" class="btn btn-default"> <spring:message
								code="cancel" />
						</a>
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>