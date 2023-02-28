<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro De Pais</title>
</head>
<body>
	<h3>Registro De Pais</h3>
	<form action="Controlador" method="post">
		<table border=1>
			<tr>
				<th>Id:</th>
				<th>Nombre:</th>
				<th>Accion</th>
			</tr>
			<c:forEach items="${lispais}" var="pais">
				<tr>
					<td>${pais.idPais}</td>
					<td>${pais.nombrePais}</td>
					<td>
					<a href="Controlador?accion=editarPais&idPais=${pais.idPais}">Actualizar</a>
					<!--  <a href="Controlador?accion=eliminar&ci=${p.ci}">Eliminar</a>-->
					</td>
				</tr>
			</c:forEach>
			
		</table>
		<br>
		<input type="submit" name="accion" value="inicio"/>
	</form>

</body>
</html>