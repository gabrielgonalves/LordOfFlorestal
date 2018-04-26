/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.mysql;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.DeckJogador;
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
public class DeckJogadorDAOMysql {

    private Connection connection;

    public void salvar(DeckJogador deckJogador) {
        String sql = "INSERT INTO Deck (matricula_jogador, nome) VALUES (?, ?);";

        int idDeck = 0;

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, deckJogador.getJogador().getMatricula());
            statement.setString(2, deckJogador.getNome());

            statement.executeUpdate();

            ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()");
            if (rs.next()) {
                idDeck = rs.getInt("LAST_INSERT_ID()");
            }

            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a inserção. Erro: " + e.getMessage());
        }

        for (Carta carta : deckJogador.getCartas()) {
            adicionarCarta(idDeck, carta.getId());
        }
    }

    public void atualizar(DeckJogador deckJogador) {
        removerCartasDeck(deckJogador.getId());
        String sql = "UPDATE Deck SET nome = ? WHERE id_deck = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, deckJogador.getNome());
            statement.setInt(2, deckJogador.getId());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de atualização. Erro: " + ex.getMessage());
        }

        for (Carta carta : deckJogador.getCartas()) {
            adicionarCarta(deckJogador.getId(), carta.getId());
        }
    }

    public void excluir(DeckJogador deckJogador) {
        removerCartasDeck(deckJogador.getId());
        String sql = "DELETE FROM Deck WHERE id_deck = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, deckJogador.getId());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de exclusão. Erro: " + ex.getMessage());
        }
    }

    public List<DeckJogador> buscaPorJogador(int matricula) {
        List<DeckJogador> lista = new ArrayList();

        String sql = "SELECT * FROM Deck WHERE matricula_jogador = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, matricula);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                DeckJogador deckJogador = new DeckJogador();

                deckJogador.setId(rs.getInt("id_deck"));
                deckJogador.setNome(rs.getString("nome"));

                lista.add(deckJogador);
            }
            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }

        for (DeckJogador deck : lista) {
            deck.setCartas(buscaCartasPorDeck(deck.getId()));
        }

        return lista;
    }

    public DeckJogador buscaPorId(int id) {
        DeckJogador deckJogador = new DeckJogador();
        String sql = "SELECT * FROM Deck WHERE id_deck = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                deckJogador.setId(rs.getInt("id_deck"));
                deckJogador.setNome(rs.getString("nome"));

                break;
            }
            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }

        deckJogador.setCartas(buscaCartasPorDeck(deckJogador.getId()));

        return deckJogador;
    }

    public List<Carta> buscaCartasPorDeck(int id) {
        List<Carta> lista = new ArrayList();

        String sql = "SELECT * FROM Deck_has_Carta INNER JOIN Carta ON Carta.id_carta = Deck_has_Carta.id_carta WHERE id_deck = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

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

    public void adicionarCarta(int id_deck, int id_carta) {
        String sql = "INSERT INTO Deck_has_Carta (id_deck, id_carta) VALUES (?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id_deck);
            statement.setInt(2, id_carta);

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a inserção. Erro: " + e.getMessage());
        }
    }

    public void removerCartasDeck(int id) {
        String sql = "DELETE FROM Deck_has_Carta WHERE id_deck = ?;";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a inserção. Erro: " + e.getMessage());
        }
    }

}
