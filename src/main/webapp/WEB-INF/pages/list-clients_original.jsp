<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lister client</title>
</head>
<body>

	<table>
		<tbody>
			<tr>
				<th>Id</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Modifier</th>
				<th>Supprimer</th>
			</tr>

			<c:forEach items="${ personnes}" var="personne">

				<tr>
					<td>${personne.id }</td>

					<td>${personne.nom }</td>

					<td>${personne.prenom }</td>
					<td>
					   <a href="edit.html?id=${personne.id}">Modifier</a>
					</td>
					<td>
					   <a href="delete.html?id=${personne.id}">Supr</a>
					</td>

				</tr>
			</c:forEach>
		</tbody>

	</table>


<a href="edit.html">Ajouter une nouvelle Personne </a>
</body>
</html>