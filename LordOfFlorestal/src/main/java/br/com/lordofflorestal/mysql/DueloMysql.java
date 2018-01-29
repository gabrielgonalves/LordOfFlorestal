/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.mysql;

import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gabriel
 */
public class DueloMysql {

    private Connection connection;

    public void salvar(Duelo duelo, int vencedor) {
        String sql = "INSERT INTO Duelo (uri, criador, oponente, data_criacao, id_situacao_duelo, vencedor) VALUES (?, ?, ?, ?, ?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, duelo.getUri());
            statement.setInt(2, duelo.getCriadoPor().getMatricula());
            if (duelo.getOponente() != null) {
                statement.setInt(3, duelo.getOponente().getMatricula());
            } else {
                statement.setInt(3, 0);
            }
            statement.setDate(4, (Date) duelo.getDataCriacao().getTime());
            statement.setInt(5, duelo.getSituacaoDuelo().ordinal() + 1);
            statement.setInt(6, vencedor);

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a inserção. Erro: " + e.getMessage());
        }
    }
}
