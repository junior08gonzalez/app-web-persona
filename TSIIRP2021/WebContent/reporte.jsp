<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro de Personas</title>
<link rel="stylesheet" href="CSS/estilos.css"/>
</head>
<body>
	<%@include file="navbar" %>
	<h3>Generar PDF</h3>
	<hr/>
	<form action="Controlador" target="_blank" method="post">
		<table>
			<tr>
				<td>Ordenar por:</td>
				<td>
					<select name="pdf">
						<option value="ci">Nro. CI</option>
						<option value="apellido">Apellido</option>
						<option value="email">Email</option>
						<option value="fechaNac">Fecha de Nacimiento</option>
					</select>
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td style="font-weight:bold;">Por Año</td>
			</tr>
			<tr>
				<td>Año Inicial</td>
				<td><input type="text" name="fechaNacIni" required  placeholder="Por ejemplo: 1999"/></td>
			</tr>
			<tr>
				<td>Año Final</td>
				<td><input type="text" name="fechaNacFin" required placeholder="Por ejemplo: 2010" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="accion" value="Generar"></td>
			</tr> 
		</table>
	</form>
</body>
</html>