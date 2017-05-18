package br.com.caelum.leilao.teste;

import static org.junit.Assert.*;

import java.util.List;

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
		
		assertEquals(valorMedio , leiloeiro.getMedia(),0.0001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance(){
		Usuario joao = new Usuario("João"); 
		
		Leilao leilao = new Leilao("PS3 novo");
		
		leilao.propoe(new Lance(joao, 1000.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(1000.0 , leiloeiro.getMaiorLance(),0.00001);
		assertEquals(1000.0 , leiloeiro.getMaiorLance(),0.00001);
	}
	
	@Test
	public void deveEntenderMaiorEMenorValor(){
		Usuario joao = new Usuario("João"); 
		
		Leilao leilao = new Leilao("PS3 novo");
		
		leilao.propoe(new Lance(joao, 120.0));
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(joao, 500.00));
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(joao, 1000.0));
		leilao.propoe(new Lance(joao, 10.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(10, leiloeiro.getMenorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresTestes(){
		//parte 1: Cenário
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("PS3 novo");
		
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, maiores.size());
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);

	}
	
	@Test
	public void deveEncontrarOrdemDecrescente(){
		//parte 1: Cenário
		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("PS3 novo");
		
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(joao, 400.0));

		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> menores = leiloeiro.getListaDecrescente();
		
		assertEquals(4, menores.size());
		assertEquals(400.0, menores.get(0).getValor(), 0.00001);
		assertEquals(300.0, menores.get(1).getValor(), 0.00001);
		assertEquals(200.0, menores.get(2).getValor(), 0.00001);
		assertEquals(100.0, menores.get(3).getValor(), 0.00001);

	}
	
    @Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new Leilao("PS 3 Novo");

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }
	
}
