function percorreArray(trsPacientes,comportamento){
	for (var i = 0 ; i <= trsPacientes.length-1 ; i++) {	
		var pacienteAtual = trsPacientes[i];

		comportamento(pacienteAtual);
	}
}