package br.com.model;

public class Funcionario {
    private String nome;
    private String departamento;
    private double salario;
    Data dataEntrada;
    private String rg;
    private static int proxFuncionario = 0;
    private int identificador;
    
    Funcionario(String nome){
    	this.nome = nome;
    	this.identificador = ++proxFuncionario;
    }
    
    Funcionario(){
    	
    }
    

    void recebeAumento (double aumento) {
        setSalario(getSalario()+ aumento); 
    }

    double calculaGanhoAnual() {
        return getSalario() * 12;
    }

	public void mostra() {
		System.out.println("Nome: "+ this.nome);
		System.out.println("Departamento: "+ this.departamento);
		System.out.println("Salario: "+ this.salario);
		System.out.println("Data de entrada: "+ this.dataEntrada.getDia() +"/"+ this.dataEntrada.getMes()+"/"+ this.dataEntrada.getAno());
		System.out.println("RG: "+ this.rg);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Data getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Data dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public int getIdentificador() {
		return identificador;
	}
	

}
