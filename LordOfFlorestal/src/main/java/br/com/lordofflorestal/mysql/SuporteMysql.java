/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.mysql;

import br.com.lordofflorestal.model.Suporte;
import br.com.lordofflorestal.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gabriel
 */
public class SuporteMysql {
    
    private Connection connection;
    
    public void salvar(Suporte suporte) {
        String sql = "INSERT INTO Suporte (jogador, assunto, mensagem) VALUES (?, ?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, suporte.getJogador());
            statement.setString(2, suporte.getAssunto());
            statement.setString(3, suporte.getMensagem());
            
            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de inserção. Erro: " + ex.getMessage());
        }
    }
    
}
