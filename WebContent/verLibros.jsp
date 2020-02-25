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
<title>LIBROS</title>
</head>
<body>

	<form action="GestionLibros?option=addLibro" method="post">
		<div class="cajaCon">
			<h1>¿Qué libros quiere?</h1>
			<table>

				<c:forEach items="${requestScope.libros}" var="libro">

					<tr>
						<td><input class="mgLibro" type="checkbox" name="isbn"
							value="${libro.isbn}"><span class="libroTitulo">${libro.titulo}</span></td>
						<td><select id="carritoList" name="cantidad${libro.isbn }">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
						</select>
						</td>
					</tr>

				</c:forEach>

				<tr>
					<td><br> <input type="submit" class="btn-light"
						name="carrito" value="carrito" ></td>
				</tr>
			</table>
			
			<br> <a href="index.jsp">atrás</a>
			<div id="out">
				<c:out value="${requestScope.mensaje_carrito}" />
			</div>
		</div>
	</form>


</body>
</html>