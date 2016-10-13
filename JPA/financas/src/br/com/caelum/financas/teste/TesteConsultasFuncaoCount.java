package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultasFuncaoCount {

    public static void main(String[] args) {
        EntityManager manager = new JPAUtil().getEntityManager();
        Conta conta = manager.find(Conta.class, 2);

        TypedQuery<Long> query = manager.createQuery("select count(m) from Movimentacao m where m.conta= :pConta",
                Long.class);

        query.setParameter("pConta", conta);
        Long quantidade = query.getSingleResult();

        System.out.println(quantidade);
    }

}
