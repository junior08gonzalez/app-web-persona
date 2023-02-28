<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro Pais</title>
</head>
<body>
<h3>Editar Pais</h3>
	<hr>
	<form action="Controlador" method="post">		
		<table>
			<tr>
				
				<td><input name="idPais" value="${pais.idPais}" type="hidden"/></td>
			</tr>
			<tr>
				<td>Nombre</td>
				<td><input type="text" name="nombrePais" value="${pais.nombrePais}"/></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" name="accion" value="EditarPais"/>
					<input type="submit" name="accion" value="inicio"/>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>