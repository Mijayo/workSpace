<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="style.css">
<title>CARRITO</title>
</head>
<body>

	<c:set var="producto" value="${0.0}"></c:set>
	<c:set var="acumuLadorTotal" value="${0.0}"></c:set>


	<div class="cajaConMov">
		<h2>Carrito de: ${sessionScope.usuario.nombre}</h2>
		<form action="" id="carrito">

			<table border=1 class="cajaMov">
				<tr>
					<th>Libro</th>
					<th>Cantidad</th>
					<th>Precio unitario</th>
					<th>Importe</th>
					<th>Eliminar Libro</th>
				</tr>
				<c:forEach items="${sessionScope.cantidad}" var="ele">
					<c:set var="producto" value="${ele.key.precioUnitario * ele.value}" />
					<tr>
						<td>${ele.key.titulo}</td>
						<td>${ele.value}</td>
						<td>${ele.key.precioUnitario }</td>
						<td>${producto }</td>
						<td><a
							href="GestionCarrito?option=eliminar&isbn=${ele.key.isbn }">Eliminar</a></td>

					</tr>
					<c:set var="acumuLadorTotal"
						value="${acumuLadorTotal + ele.key.precioUnitario * ele.value}" />
				</c:forEach>
			</table>
		</form>
		<h2>Total : ${acumuLadorTotal }</h2>
		<div class="caja">
			<div>
				<ul>
					<li><a href="GestionCarrito?option=comprar">comprar</a></li>
					<li><a href="GestionCarrito?option=salir">cerrar sesion</a></li>
					<li><a href="index.jsp">inicio</a></li>
				</ul>
			</div>
		</div>
	</div>


</body>
</html>