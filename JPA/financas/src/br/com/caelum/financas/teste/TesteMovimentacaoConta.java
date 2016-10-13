package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacaoConta {
	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();
		Movimentacao movimentacao = manager
				.find(Movimentacao.class, 2); // id 2 deve existir no banco

		System.out.println(movimentacao.getConta().getTitular());

	}
}
