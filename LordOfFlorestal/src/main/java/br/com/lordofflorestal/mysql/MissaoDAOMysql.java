/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.mysql;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.Missao;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import br.com.lordofflorestal.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class MissaoDAOMysql {

    private Connection connection;

    public void salvar(Missao missao) {
        String sql = "INSERT INTO Missao (descricao, id_carta, ativa) VALUES (?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, missao.getDescricao());
            statement.setInt(2, missao.getCarta().getId());
            statement.setBoolean(3, missao.isAtiva());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a inserção. Erro: " + e.getMessage());
        }
    }

    public void atualizar(Missao missao) {
        String sql = "UPDATE Missao SET descricao = ?, id_carta = ?, ativa = ? WHERE id_missao = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, missao.getDescricao());
            statement.setInt(2, missao.getCarta().getId());
            statement.setBoolean(3, missao.isAtiva());
            statement.setInt(4, missao.getId());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de atualização. Erro: " + ex.getMessage());
        }
    }

    public void excluir(Missao missao) {
        String sql = "DELETE FROM Missao WHERE id_missao = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, missao.getId());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de exclusão. Erro: " + ex.getMessage());
        }
    }

    public Missao buscaPorId(int id){
        String sql = "SELECT * FROM Missao INNER JOIN Carta ON Carta.id_carta = Missao.id_carta WHERE id_missao = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Missao missao = new Missao();

                missao.setId(rs.getInt("id_missao"));
                missao.setDescricao(rs.getString("descricao"));
                missao.setAtiva(rs.getBoolean("ativa"));
                
                Carta carta = new Carta();

                carta.setId(rs.getInt("id_carta"));
                carta.setNome(rs.getString("nome"));
                carta.setImagem(rs.getString("imagem"));
                carta.setEfeito(rs.getString("efeito"));
                carta.setDescricao(rs.getString("descricao"));
                carta.setValorAtaque(rs.getInt("valor_ataque"));
                carta.setValorDefesa(rs.getInt("valor_defesa"));
                carta.setTipoCarta(TipoCarta.values()[rs.getInt("id_tipo_carta") - 1]);
                if (rs.getInt("id_subtipo_carta") != 0) {
                    carta.setSubtipoCarta(SubtipoCarta.values()[rs.getInt("id_subtipo_carta") - 1]);
                }
                
                missao.setCarta(carta);
                
                statement.close();

                connection.close();

                return missao;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }
        return null;
    }
    
    public List<Missao> listar() {
        List<Missao> lista = new ArrayList();

        String sql = "SELECT * FROM Missao INNER JOIN Carta ON Carta.id_carta = Missao.id_carta";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Missao missao = new Missao();

                missao.setId(rs.getInt("id_missao"));
                missao.setDescricao(rs.getString("descricao"));
                missao.setAtiva(rs.getBoolean("ativa"));
                
                Carta carta = new Carta();

                carta.setId(rs.getInt("id_carta"));
                carta.setNome(rs.getString("nome"));
                carta.setImagem(rs.getString("imagem"));
                carta.setEfeito(rs.getString("efeito"));
                carta.setDescricao(rs.getString("descricao"));
                carta.setValorAtaque(rs.getInt("valor_ataque"));
                carta.setValorDefesa(rs.getInt("valor_defesa"));
                carta.setTipoCarta(TipoCarta.values()[rs.getInt("id_tipo_carta") - 1]);
                if (rs.getInt("id_subtipo_carta") != 0) {
                    carta.setSubtipoCarta(SubtipoCarta.values()[rs.getInt("id_subtipo_carta") - 1]);
                }
                
                missao.setCarta(carta);

                lista.add(missao);
            }

            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }

        return lista;
    }
    
    public List<Missao> listarAtivas() {
        List<Missao> lista = new ArrayList();

        String sql = "SELECT * FROM Missao INNER JOIN Carta ON Carta.id_carta = Missao.id_carta WHERE ativa = true";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Missao missao = new Missao();

                missao.setId(rs.getInt("id_missao"));
                missao.setDescricao(rs.getString("descricao"));
                
                Carta carta = new Carta();

                carta.setId(rs.getInt("id_carta"));
                carta.setNome(rs.getString("nome"));
                carta.setImagem(rs.getString("imagem"));
                carta.setEfeito(rs.getString("efeito"));
                carta.setDescricao(rs.getString("descricao"));
                carta.setValorAtaque(rs.getInt("valor_ataque"));
                carta.setValorDefesa(rs.getInt("valor_defesa"));
                carta.setTipoCarta(TipoCarta.values()[rs.getInt("id_tipo_carta") - 1]);
                if (rs.getInt("id_subtipo_carta") != 0) {
                    carta.setSubtipoCarta(SubtipoCarta.values()[rs.getInt("id_subtipo_carta") - 1]);
                }
                
                missao.setCarta(carta);

                lista.add(missao);
            }

            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }

        return lista;
    }

}
