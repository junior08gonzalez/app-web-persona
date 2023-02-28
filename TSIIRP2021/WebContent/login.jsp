<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="CSS/estilos.css"/>
</head>
<body>
	<%@include file="navbar" %>
	<h3>Login</h3>
	<hr>
	<!--<p>Error: <c:out value="${error}"/></p> -->
	<c:if test="${error!=null}">
		<p>Error: <c:out value="${error}"/></p>
	</c:if>
	<form action="Controlador" method="post">		
		<table class="login">
			<tr>
				<td>Usuario</td>
				<td><input type="text" name="usuario"/></td>
			</tr>
			<tr>
				<td>Contraseña</td>
				<td><input type="password" name="clave"/></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" name="accion" value="Aceptar"/>
					<!-- <input type="submit" name="accion" value="inicio"/> -->
				</td>
			</tr>
		</table>
	</form>

</body>
</html>