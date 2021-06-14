<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Usuário</title>
</head>
<body>
	<h1>Editar Usuário</h1>
	<form action="usuario" method="post">
	<c:set var="usuario" value="${usuario}"> </c:set>
	<input type="hidden" name="opcao" value="editar">
	<input type="hidden" name="id" value="${usuario.id}">
		<table border="1">
			<tr>
				<td>Nome:</td>
				<td> <input type="text" name="nome" size="50" value="${usuario.nome}"> </td>
			</tr>	
			<tr>
				<td>E-mail:</td>
				<td> <input type="text" name="email" size="50" value="${usuario.email}"> </td>
			</tr>	
			<tr>
				<td>Senha:</td>
				<td> <input type="text" name="senha" size="50" value="${usuario.senha}"> </td>
			</tr>	
		</table>
		<h1></h1>
		<input type="submit" value="Salvar">
	</form>
</body>
</html>