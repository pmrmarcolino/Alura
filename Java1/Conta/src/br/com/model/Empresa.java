package br.com.model;

public class Empresa {
	private String nome;
	private String cnpj;
	private int livre = 0;
	Funcionario [] funcionarios;
	
	void adicionar(Funcionario f){
		this.funcionarios[this.livre] = f;
		this.livre++;
	}
	
	void mostraEmpregados(){
		for (int i = 0; i < this.livre; i++) {
			System.out.println("Funcionario na posição: " + i);
		}
	}
	
	void mostraTodasInformacoes(){
		System.out.println("Nome: "+ this.nome);
		System.out.println("Cnpj: "+ this.cnpj);
		for (int i = 0; i < this.livre; i++) {
			System.out.println("Funcionario "+i);
			funcionarios[i].mostra();
		}
	}
	
	   boolean contem(Funcionario f) {
           for (int i = 0; i < this.livre; i++) {
               if (f == this.funcionarios[i]) {
                   return true;
               }
           }
           return false;
       }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	   
}
