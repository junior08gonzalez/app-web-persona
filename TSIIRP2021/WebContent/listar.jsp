<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de Personas</title>
<link rel="stylesheet" href="CSS/estilos.css"/>
<link rel="stylesheet" href="CSS/estilos2.css"/>
<script type="text/javascript">
	
	
	function verificarPermiso(){
		if('${u}'){
			var usr = {usuario:'${u.usuario}',permiso:'${u.permiso}'}
			if(usr.permiso === "1"){
				return true;
			}
			else{
				alert("Usuario: "+usr.usuario +", "+"no posee el permiso necesario!!!");
				return false;
			}	
		}else{
			alert("Necesita iniciar sesion!!!");
			return false;
		}
		
		
		
	}
	
	function confirmar(){
		if(verificarPermiso()){
			var opc = confirm("Esta accion eliminara permanentemente los datos. Esta seguro de continuar?");
			if(opc){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	
</script>
</head>
<body>
	<%@include file="navbar" %>
	<h3>Listado De Personas</h3>
	<form action="Controlador" method="post">
		<table id="lista_persona">
			<tr>
				<th>Nro. CI:</th>
				<th>Nombre:</th>
				<th>Apellido:</th>
				<th>Fecha Nacimiento:</th>
				<th>Email:</th>
				<th>Telefono:</th>
				<th>Sexo:</th>
				<th>Pais:</th>
				<th>Acciones</th>
			</tr>
			<c:forEach items="${lista}" var="p">
				<tr>
					<td>${p.ci}</td>
					<td>${p.nombre}</td>
					<td>${p.apellido}</td>
					<td>${p.fechaNac}</td>
					<td>${p.email}</td>
					<td>${p.telefono}</td>
					<td>${p.sexo}</td>
					<td>${p.pais.nombrePais}</td>
					<td>
					<a href="Controlador?accion=editar&ci=${p.ci}" onclick="return verificarPermiso()">Actualizar</a>
					<a href="Controlador?accion=eliminar&ci=${p.ci}" onclick="return confirmar()">Eliminar</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		<br>
		<!-- <input type="submit" name="accion" value="inicio"/> -->
	</form>
</body>
</html>