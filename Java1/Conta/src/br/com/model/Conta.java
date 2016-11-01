package br.com.model;

public class Conta {
	private int numero;
	private String dono;
	private double saldo;
	private double limite;
	private double salario;
	
	void saca(double quantidade){
		double novoSaldo = getSaldo() - quantidade;
		setSaldo(novoSaldo);
	}
	
	void deposita(double quantidade){
		setSaldo(getSaldo()+quantidade);; 
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDono() {
		return dono;
	}

	public void setDono(String dono) {
		this.dono = dono;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
}
