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
		<table class="caja">
			<form method="post" action="Login?option=validar">
				<tr>
					<td><label>Usuario:</label></td>
				</tr>
				<tr>
					<td><input id="demo" type="text" name="usuario" placeholder="ID usuario"
						required></td>
				</tr>
				<tr>
					<td><br><label>Contraseña:</label></td>
				</tr>
				<tr>
					<td><input  id="demo" type="password" name="pwd" placeholder="1234"
						required></td>
				</tr>
				
				<tr>
				
					<td><br><input type="submit" value="login" class="btn-light"></td>
				</tr>
			</form>
		</table>
		<br>
		<a href="index.jsp">atrás</a>
		<div id="out">
			<c:out value="${requestScope.mensaje_error}" />
		</div>
	</div>
	
</body>
</html>