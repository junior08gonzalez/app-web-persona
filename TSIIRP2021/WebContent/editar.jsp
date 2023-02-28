<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro de Personas</title>
<link rel="stylesheet" href="CSS/estilos.css"/>
</head>
<body>
	<%@include file="navbar" %>
	<c:if test="${readonly!=null}">
	<h3>Persona Encontrada</h3>
	</c:if>
	<c:if test="${readonly==null}">
	<h3>Editar Persona</h3>
	</c:if>
	<hr>
	<form action="Controlador" method="post">		
		<c:if test="${readonly==null}">
		

		<table>
			<tr>
				<td>Nro. CI</td>
				<td><input type="text" name="ci" value="${p.ci}"/></td>
			</tr>
			<tr>
				<td>Nombre</td>
				<td><input type="text" name="nombre" value="${p.nombre}"/></td>
			</tr>
			<tr>
				<td>Apellido</td>
				<td><input type="text" name="apellido" value="${p.apellido}"/></td>
			</tr>
			<tr>
				<td>Fecha Nacimiento</td>
				<td><input type="text" name="fechaNac" value="${p.fechaNac}"/></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" value="${p.email}"/></td>
			</tr>
			<tr>
				<td>Telefono</td>
				<td><input type="text" name="telefono" value="${p.telefono}"/></td>
			</tr>
			<tr>
				<td>Sexo</td>
				<td>
					<input type="radio" name="sexo" value="M" ${p.sexo=="M"?"checked":""}>Masculino
					<input type="radio" name="sexo" value="F" ${p.sexo=="F"?"checked":""}>Femenino
				</td>
			</tr>
			<tr>
				<td>Pais</td>
				<td>
					<!--  <input type="text" name="idPais" value="${p.pais.idPais}"/>-->
					<select id="idPais" name="idPais">
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
					<input type="submit" name="accion" value="Editar"/>
					<!--  <input type="submit" name="accion" value="inicio"/>-->
				</td>
			</tr>
		</table>
		</c:if>
		<c:if test="${readonly!=null}">
				<table>
			<tr>
				<td>Nro. CI</td>
				<td><input type="text" name="ci" value="${p.ci}" readonly/></td>
			</tr>
			<tr>
				<td>Nombre</td>
				<td><input type="text" name="nombre" value="${p.nombre}" readonly/></td>
			</tr>
			<tr>
				<td>Apellido</td>
				<td><input type="text" name="apellido" value="${p.apellido}" readonly/></td>
			</tr>
			<tr>
				<td>Fecha Nacimiento</td>
				<td><input type="text" name="fechaNac" value="${p.fechaNac}" readonly/></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" value="${p.email}" readonly/></td>
			</tr>
			<tr>
				<td>Telefono</td>
				<td><input type="text" name="telefono" value="${p.telefono}" readonly/></td>
			</tr>
			<tr>
				<td>Sexo</td>
				<td>
					<input type="radio" name="sexo" value="M" ${p.sexo=="M"?"checked":""} readonly>Masculino
					<input type="radio" name="sexo" value="F" ${p.sexo=="F"?"checked":""} readonly>Femenino
				</td>
			</tr>
			<tr>
				<td>Pais</td>
				<td>
					<!--  <input type="text" name="idPais" value="${p.pais.idPais}"/>-->
					<select id="idPais" name="idPais">
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
			</tr>
		</table>
		</c:if>
	</form>
<script type="text/javascript">
	var pais = document.querySelector("#idPais");
	pais.value ="${p.pais.idPais}";
</script>
</body>
</html>