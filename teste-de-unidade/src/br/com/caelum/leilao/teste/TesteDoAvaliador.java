package br.com.caelum.leilao.teste;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TesteDoAvaliador {
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		//parte 1: Cenário
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("PS3 novo");
		
		//parte 2: Ação
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(maria, 250.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//parte 3: Avaliação
		
		double maiorEsperado = 400;
		double menorEsperado = 250;
		double valorMedio = (300.0+400.0+250.0)/3;
		
		//esse valor é um delta para margem de erro de um double
		//Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		
		//Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance());
		
		Assert.assertEquals(valorMedio , leiloeiro.getMedia(),0.0001);
	}
}
