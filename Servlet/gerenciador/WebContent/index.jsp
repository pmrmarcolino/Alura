<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
Bem vindo ao nosso gerenciador de empresas!<br/>

<c:if test = "${not empty usuarioLogado}">
	Logado como ${usuarioLogado.email}<br/>
</c:if>

<form action="fazTudo" method="post">
    <input type = "hidden" name="tarefa" value="NovaEmpresa">
    Nome: <input type="text" name="nome" /><br />
    <input type="submit" value="Enviar" />
</form>
<form action = "login" method = "POST">
	Email: <input type = "email" name = "email"/>
	Senha: <input type = "password" name = "senha" />
	<input type="submit" value = "Enviar" />
</form>
<form action="fazTudo" method="POST">
	<input type = "hidden" name = "tarefa" value="Logout">
	<input type="submit" value = "Deslogar">
</form>
	
</body>
</html>