<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Criar Telefone</title>

</head>
<body>
	<h1 class="title">Adicionar Telefone</h1>
	
	<form action="telefones" method="post">
	<input type="hidden" name="opcao" value="salvar">
	<input type="hidden" name="usuario_id" value="${ param.id }">
		<table class="tablecadastro">
			<tr class="textocriar">
				<td class="tdcentro">DDD:</td>
				<td><input class="inputcentro" type="text" name="ddd"></td>
			</tr>
			
			<tr class="textocriar">
				<td class="tdcentro">Numero:</td>
				<td><input class="inputcentro" type="text" name="numero"></td>
			</tr>
			
			<tr class="textocriar">
				<td class="tdcentro">Tipo:</td>
				<td><input class="inputcentro" type="text" name="tipo"></td>
			</tr>	
		</table>	
		<input type="submit" value="salvar" class="salvarcentro">
	</form>
	</div>
</body>
</html>