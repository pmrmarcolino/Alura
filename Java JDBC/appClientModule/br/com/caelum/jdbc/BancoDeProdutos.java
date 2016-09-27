package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.modelo.Produto;

public class BancoDeProdutos {

    private final Connection con;

    public BancoDeProdutos(Connection con) {
        this.con = con;
    }

    public void salvaProduto(Produto produto) throws SQLException {
        String sql = "insert into Produto (nome, descricao) values (?,?)";

        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    produto.setId(id);
                }
            }

        }
    }
}
