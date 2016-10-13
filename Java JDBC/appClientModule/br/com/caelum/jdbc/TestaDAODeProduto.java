package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.dao.ProdutosDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaDAODeProduto {

    public static void main(String[] args) throws SQLException {
        Produto produto = new Produto("Mesa vermelha", "Mesa com 4 pés");

        try (Connection con = new ConnectionPool().getConnection()) {

            ProdutosDAO dao = new ProdutosDAO(con);
            dao.salva(produto);
            List<Produto> produtos = dao.lista();

            for (Produto p : produtos) {
                System.out.println("Existe o produto: " + p);
            }
        }

        System.out.println("A produto foi inserida com sucesso " + produto);
    }
}
