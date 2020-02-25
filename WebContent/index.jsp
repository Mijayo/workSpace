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
<title>LOGIN</title>
</head>
<body>

	<div class="cajaCon">
	<h1>Bienvenido a NO Amaz�n</h1>
		<div class="caja">
			<div>
				<ul>
					<li><a href="GestionLibros?option=libros">ver libros</a></li>
					<li><a href="GestionCarrito?option=carrito">ver carrito</a></li>
					<li><a href="login.jsp">iniciar sesi�n</a></li>
				</ul>
			</div>
		</div>
		<div id="out">
			<c:out value="${requestScope.mensaje_error}" />
		</div>
	</div>

	<script>
		function control() {

			var x = document.getElementById("demo").value;

			if (x <= 0 || isNaN(x)) {
				alert("Introduzca valores adecuados al tipo de dato");
			}

			document.getElementById("demo").innerHTML = "";
		};
	</script>

</body>
</html>