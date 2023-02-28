<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Persona</title>
<link rel="stylesheet" href="CSS/estilos.css"/>

</head>
<body>
	<%@include file="navbar" %>
	<h3>Nueva Persona</h3>
	<hr>
	<c:if test="${error!=null}">
		<p>Error: <c:out value="${error}"/></p>
	</c:if>
	<form action="Controlador" method="post">		
		<table>
			<tr>
				<td>Nro. CI</td>
				<td><input type="text" name="ci" id="ci" required onchange="return validarci(this.value)"/></td>
			</tr>
			<tr>
				<td>Nombre</td>
				<td><input type="text" name="nombre" required /></td>
			</tr>
			<tr>
				<td>Apellido</td>
				<td><input type="text" name="apellido" required /></td>
			</tr>
			<tr>
				<td>Fecha Nacimiento</td>
				<td><input type="text" name="fechaNac" required /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" required /></td>
			</tr>
			<tr>
				<td>Telefono</td>
				<td><input type="text" name="telefono" required id=""/></td>
			</tr>
			<tr>
				<td>Sexo</td>
				<td>
					<input type="radio" name="sexo" value="M" checked="checked">Masculino
					<input type="radio" name="sexo" value="F">Femenino
				</td>
			</tr>
			<tr>
				<td>Pais</td>
				<td>
					<select name="idPais"  onchange="return ver(this.value)">
						<c:forEach items="${lps}" var="ps">
							<option value="${ps.idPais}">
								${ps.nombrePais}
							</option>				
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" name="accion" value="Guardar" />
					<!-- <input type="submit" name="accion" value="inicio"/> -->
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript">
	function validarci(value){
		if(isNaN(value)){
			alert("EL VALOR PARA CEDULA DEBE SER NUMERICO!!!");
			document.getElementById("ci").value = "";
		}
	}
	function ver(value){
		alert(value);
	}
	
</script>
</body>
</html>