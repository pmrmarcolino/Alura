package br.com.caelum.leilao.teste;
//
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matcher.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TesteDoAvaliador {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	// Faz com que essa parte do codigo seja executada antes de cada @Test
	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();

		this.joao = new Usuario("João");
		this.jose = new Usuario("Jose");
		this.maria = new Usuario("Maria");
	}

	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {

		Leilao leilao = new CriadorDeLeilao().para("Ps3").controi();
		leiloeiro.avalia(leilao);

	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		// parte 1: Cenário

		Leilao leilao = new CriadorDeLeilao().para("PS3").lance(joao, 300.0).lance(jose, 400.0).lance(maria, 250.0)
				.controi();

		leiloeiro.avalia(leilao);

		// parte 3: Avaliação

		double maiorEsperado = 400;
		double menorEsperado = 250;
		double valorMedio = (300.0 + 400.0 + 250.0) / 3;

		// esse valor é um delta para margem de erro de um double
		// Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(),
		// 0.0001);

		// Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance());

		assertEquals(valorMedio, leiloeiro.getMedia(), 0.0001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {

		Leilao leilao = new CriadorDeLeilao().para("PS3").lance(joao, 1000.0).controi();

		leiloeiro.avalia(leilao);

		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
	}

	@Test
	public void MaiorEMenorValor() {

		Leilao leilao = new CriadorDeLeilao().para("PS3").lance(joao, 10.0).lance(joao, 100.0).lance(joao, 500.0)
				.lance(joao, 250.0).lance(joao, 1000.0).lance(joao, 120.0).controi();

		leiloeiro.avalia(leilao);
		
		assertEquals(10, leiloeiro.getMenorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
	}


	@Test
	public void deveEncontrarOsTresMaioresTestes() {

		Leilao leilao = new CriadorDeLeilao().para("PS3").lance(joao, 100.0).lance(maria, 200.0).lance(joao, 300.0)
				.lance(maria, 400.0).controi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);

	}

	@Test
	public void deveEncontrarOrdemDecrescente() {

		Leilao leilao = new CriadorDeLeilao().para("PS3")
				.lance(joao, 100.0)
				.lance(joao, 200.0)
				.lance(joao, 300.0)
				.lance(joao, 400.0)
				.controi();

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

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getListaDecrescente();

		assertEquals(0, maiores.size());
	}

}
