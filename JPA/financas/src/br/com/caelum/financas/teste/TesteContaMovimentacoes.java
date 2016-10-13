package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteContaMovimentacoes {
	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		// Conta conta = manager.find(Conta.class, 2); // id deve existir

		Query query = manager
				.createQuery("select c from Conta c join fetch c.movimentacoes where c.id = :pId");

		query.setParameter("pId", 2);

		Conta conta = (Conta) query.getSingleResult();

		manager.close(); // fechando EntityManager

		System.out.println(conta.getMovimentacoes().size());

	}
}
