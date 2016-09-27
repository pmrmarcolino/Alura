package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.caelum.jdbc.modelo.Produto;

public class TestaInsercaoDeProduto {

    public static void main(String[] args) throws SQLException {
        Produto produto = new Produto("Mesa azul", "Mesa com 4 pés");

        try (Connection con = new ConnectionPool().getConnection()) {
            new BancoDeProdutos(con).salvaProduto(produto);
        }

        System.out.println("A produto foi inserida com sucesso " + produto);
    }
}
