package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJpqlAvancada {

    public static void main(String[] args) {

        EntityManager manager = new JPAUtil().getEntityManager();

        Conta conta = new Conta();
        conta.setId(3);

        MovimentacaoDao dao = new MovimentacaoDao(manager);

        // Double resultado = dao.mediaDaContaPeloTipo(conta,
        // TipoMovimentacao.ENTRADA);

        // System.out.println("Total movimentado ..: R$ " + resultado);
    }
}
