package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class MovimentacaoDao {

    private EntityManager manager;

    public MovimentacaoDao(EntityManager manager) {
        this.manager = manager;

    }

    public Double mediaDaContaPeloTipo(Conta conta, TipoMovimentacao tipo) {
        TypedQuery<Double> query = manager.createQuery(
                "select avg(m.valor) from Movimentacao m where m.conta=:pConta " + "and m.tipoMovimentacao=:pTipo",
                Double.class);

        query.setParameter("pConta", conta);
        query.setParameter("pTipo", tipo);

        // getSingleResult() retorna um Object
        Double resultado = query.getSingleResult();

        return resultado;
    }

    public Long totalDeMovimentacoes(Conta conta) {
        TypedQuery<Long> query = manager.createNamedQuery("totalDeMovimentacoes", Long.class);

        query.setParameter("pConta", conta);

        // getSingleResult() retorna um Object

        return query.getSingleResult();
    }

}
