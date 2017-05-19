package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.management.RuntimeErrorException;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double media = 0;
	private List<Lance> maiores;
	private List<Lance> menores;

	public void avalia(Leilao leilao) {
		
		if(leilao.getLances().size() == 0){
			throw new RuntimeException("Não é possivel avaliar um leilão sem lances");
		}
		
		menorMaior(leilao);
		crescente(leilao);
		decrescente(leilao);
	}

	private void menorMaior(Leilao leilao) {

		double total = 0;

		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorDeTodos)
				maiorDeTodos = lance.getValor();
			if (lance.getValor() < menorDeTodos)
				menorDeTodos = lance.getValor();
			total += lance.getValor();
		}
		media(leilao, total);
	}

	private void media(Leilao leilao, double total) {
		media = total / leilao.getLances().size();
	}

	private void crescente(Leilao leilao) {
		
		maiores = new ArrayList<Lance>(leilao.getLances());
		
		Collections.sort(maiores, new Comparator<Lance>() {
			public int compare(Lance o1, Lance o2) {
				if (o1.getValor() < o2.getValor())
					return 1;
				if (o1.getValor() > o2.getValor())
					return -1;
				return 0;
			}
		});
	}

	private void decrescente(Leilao leilao) {
		menores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(menores, new Comparator<Lance>() {
			public int compare(Lance o1, Lance o2) {
				if (o1.getValor() > o2.getValor())
					return 1;
				if (o1.getValor() < o2.getValor())
					return -1;
				return 0;
			}
		});
	}

	public List<Lance> getListaDecrescente() {
		return menores;
	}

	// 0, para inicio da lista, tendo como resultado somente os 3 primeiros
	// elementos
	public List<Lance> getTresMaiores() {
		if(maiores.size() == 0) return maiores;
		return maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}

	public double getMaiorLance() {
		return maiorDeTodos;
	}

	public double getMenorLance() {
		return menorDeTodos;
	}

	public double getMedia() {
		return media;
	}
}