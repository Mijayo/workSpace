<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="style.css">
<title>Ver Temas</title>
</head>
<body>
	<form action="GestionLibros?option=libros" method="post">
		<p>Escoja un tema y pulse enviar</p>

		<c:forEach items="${requestScope.temas}" var="tema">
			<input type="radio" name="idTema" value="${tema.idTema}" checked>
			${tema.descTema}
			</br>
		</c:forEach>
		<input type="submit" class="btn-dark" name="boton" value="Enviar"></br>
		</br>
	</form>
	<a href="index.jsp">inicio</a>
	</br>

</body>
</html>