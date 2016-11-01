package br.com.model;

public class Programa {
	public static void main(String[] args) {
		Conta minhaConta;
		minhaConta = new Conta();
		
		Funcionario f1 = new Funcionario();
		f1.dataEntrada = new Data();
		
		Empresa empresa = new Empresa();
		empresa.funcionarios = new Funcionario[10];
		
		
		minhaConta.setDono( "Duke");
		minhaConta.setSaldo(1000.0);
		
		System.out.println("Saldo atual: " + minhaConta.getSaldo());
		
		minhaConta.saca(200);
		System.out.println("Saldo atual: " + minhaConta.getSaldo());
		
		minhaConta.deposita(300);
		System.out.println("Saldo atual: " + minhaConta.getSaldo());
		
		f1.setNome( "Joao");
		f1.setDepartamento ( "RH");
		f1.setSalario(1000.0);
		f1.dataEntrada.preencheData(12, 12, 2016);
		f1.mostra();
	
		empresa.adicionar(f1);
		empresa.mostraEmpregados();
		empresa.mostraTodasInformacoes();
	}
}
