/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.mysql;

import br.com.lordofflorestal.model.Carta;
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
public class CartaDAOMysql {

    private Connection connection;

    public void salvar(Carta carta) {
        String sql = "INSERT INTO Carta (nome, imagem, efeito, descricao, valor_ataque, valor_defesa, id_tipo_carta, id_subtipo_carta) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, carta.getNome());
            statement.setString(2, carta.getImagem());
            statement.setString(3, carta.getEfeito());
            statement.setString(4, carta.getDescricao());
            statement.setInt(5, carta.getValorAtaque());
            statement.setInt(6, carta.getValorDefesa());
            statement.setInt(7, carta.getTipoCarta().ordinal() + 1);
            if (carta.getSubtipoCarta() != null) {
                statement.setInt(8, carta.getSubtipoCarta().ordinal() + 1);
            } else {
                statement.setNull(8, 0);
            }

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a inserção. Erro: " + e.getMessage());
        }
    }

    public void atualizar(Carta carta) {
        String sql = "UPDATE Carta SET nome = ?, imagem = ?, efeito = ?, descricao = ?, valor_ataque = ?, valor_defesa = ?, id_tipo_carta = ?, id_subtipo_carta = ? WHERE id_carta = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, carta.getNome());
            statement.setString(2, carta.getImagem());
            statement.setString(3, carta.getEfeito());
            statement.setString(4, carta.getDescricao());
            statement.setInt(5, carta.getValorAtaque());
            statement.setInt(6, carta.getValorDefesa());
            statement.setInt(7, carta.getTipoCarta().ordinal() + 1);
            if (carta.getSubtipoCarta() != null) {
                statement.setInt(8, carta.getSubtipoCarta().ordinal() + 1);
            }
            statement.setInt(9, carta.getId());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de atualização. Erro: " + ex.getMessage());
        }
    }

    public void excluir(Carta carta) {
        String sql = "DELETE FROM Carta WHERE id_carta = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, carta.getId());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de exclusão. Erro: " + ex.getMessage());
        }
    }

    public Carta buscarPorId(Integer idCarta) {
        String sql = "SELECT * FROM Carta WHERE id_carta = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idCarta);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
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

                statement.close();

                connection.close();

                return carta;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }

        return null;
    }

    public Carta buscarPorNome(String nome) {
        return null;
    }

    public List<Carta> buscarPorTipo(TipoCarta tipoCarta) {
        return null;
    }

    public List<Carta> buscarPorSubTipo(SubtipoCarta subtipoCarta) {
        return null;
    }

    public List<Carta> listar() {
        List<Carta> lista = new ArrayList();

        String sql = "SELECT * FROM Carta";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
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

                lista.add(carta);
            }

            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }

        return lista;
    }
}
