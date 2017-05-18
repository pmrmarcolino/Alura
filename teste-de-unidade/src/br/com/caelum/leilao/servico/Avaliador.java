package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private double media = 0;
	private List<Lance> maiores;
	private List<Lance> menores;    

    public void avalia(Leilao leilao) {
        double total = 0;
        for(Lance lance : leilao.getLances()) {
            if(lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            if(lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
            total += lance.getValor();
        }
        media = total / leilao.getLances().size();
    
        //Ordem crescente
        maiores = new ArrayList<Lance>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {
        	public int compare(Lance o1, Lance o2){
        		if(o1.getValor() < o2.getValor()) return 1;
        		if(o1.getValor() > o2.getValor()) return -1;
        		return 0;
        	}
		});
        // 0, para inicio da lista, tendo como resultado somente os 3 primeiros elementos
        	maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
        	
        	//Ordem decrescente
            menores = new ArrayList<Lance>(leilao.getLances());
            Collections.sort(menores, new Comparator<Lance>() {
            	public int compare(Lance o1, Lance o2){
            		if(o1.getValor() < o2.getValor()) return 1;
            		if(o1.getValor() > o2.getValor()) return -1;
            		return 0;
            	}
    		});
        }
    
    public List<Lance> getListaDecrescente() { return menores; }
    public List<Lance> getTresMaiores() { return maiores; }
    public double getMaiorLance() { return maiorDeTodos; }
    public double getMenorLance() { return menorDeTodos; }
    public double getMedia() { return media; }
}