package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

    public static void main(String[] args) throws SQLException {

        try (Connection connection = new ConnectionPool().getConnection()) {
            connection.setAutoCommit(false);
            String sql = "insert into Produto(nome, descricao) values(?,?)";

            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                adiciona("Notebook'i5 2013", "Notebook", sql, statement);
                adiciona("TV LCD", "32 polegadas", sql, statement);
                adiciona("Blueray", "FULL HDMI", sql, statement);
                connection.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                connection.rollback();
                System.out.println("Rollcack efetuado");
            }

        }
    }

    private static void adiciona(String nome, String descricao, String sql, PreparedStatement statement)
            throws SQLException {

        if (nome.equals("Blueray")) {
            throw new IllegalArgumentException("Problema ocorrido");
        }

        statement.setString(1, nome);
        statement.setString(2, descricao);
        System.out.println(sql);
        boolean resultado = statement.execute();
        System.out.println("O resultado foi " + resultado);

        ResultSet resultset = statement.getGeneratedKeys();
        while (resultset.next()) {
            String id = resultset.getString("id");
            System.out.println(id + " gerado");
        }
        resultset.close();
    }
}
