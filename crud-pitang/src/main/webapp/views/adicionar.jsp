<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adicionar usuário</title>
</head>
<body>
	<h1>Adicionar usuário</h1>
	
	<form action="usuario" method="post">
	<input type="hidden" name="opcao" value="adicionar">
		<table border="1">
			<tr>
				<td>Nome:</td>
				<td> <input type="text" name="nome" size="50"> </td>
			</tr>	
			<tr>
				<td>E-mail:</td>
				<td> <input type="text" name="email" size="50"> </td>
			</tr>	
			<tr>
				<td>Senha:</td>
				<td> <input type="text" name="senha" size="50"> </td>
			</tr>	
		</table>
		<h1></h1>
		<input type="submit" value="Adicionar">
	</form>
</body>
</html>