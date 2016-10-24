//tabela.contextContent = tabela.textContent + pacienteNovo;
//var tabela = document.getElementsByTagName("table")[0];					
//document.querySelectorAll("table");// Array

var botao = document. querySelector("#adicionar-paciente");
botao.addEventListener("click",function(event){

	var campoNome = document.querySelector("#campo-nome");	
	var campoPeso = document.querySelector("#campo-peso");	
	var campoAltura = document.querySelector("#campo-altura");	

	event.preventDefault(); // impede comportamento padrão

	var pacienteNovo = "<tr class='paciente'>"+
							"<td class='info-nome'>"+campoNome.value+"</td>"+
							"<td class='info-peso' >"+campoPeso.value+"</td>"+
							"<td class='info-altura'>"+campoAltura.value+"</td>"+
							"<td class='info-imc'></td>"+
						"</tr>";

	var tabela = document.querySelector("table"); // Primeiro elemento
	tabela.innerHTML += pacienteNovo;

	campoNome.value = "";
	campoPeso.value = "";
	campoAltura.value = "";
});
