/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.mysql;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.EstatisticaJogador;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.model.DuelosJogador;
import br.com.lordofflorestal.model.Ranking;
import br.com.lordofflorestal.model.SituacaoDuelo;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import br.com.lordofflorestal.model.TipoJogador;
import br.com.lordofflorestal.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class JogadorDAOMysql {

    private Connection connection;

    public void salvar(Jogador jogador) {
        String sql = "INSERT INTO Jogador (nome, email, imagem, matricula, login, senha, id_tipo_jogador) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, jogador.getNome());
            statement.setString(2, jogador.getEmail());
            statement.setString(3, jogador.getImagem());
            statement.setInt(4, jogador.getMatricula());
            statement.setString(5, jogador.getLogin());
            statement.setString(6, jogador.getSenha());
            statement.setInt(7, jogador.getTipoJogador().ordinal() + 1);

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de inserção. Erro: " + ex.getMessage());
        }
    }

    public void atualizar(Jogador jogador) {
        String sql = "UPDATE Jogador SET nome = ?, email = ?, imagem = ?, login = ?, senha = ?, id_tipo_jogador = ? WHERE matricula = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, jogador.getNome());
            statement.setString(2, jogador.getEmail());
            statement.setString(3, jogador.getImagem());
            statement.setString(4, jogador.getLogin());
            statement.setString(5, jogador.getSenha());
            statement.setInt(6, jogador.getTipoJogador().ordinal() + 1);
            statement.setInt(7, jogador.getMatricula());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de atualização. Erro: " + ex.getMessage());
        }
    }

    public void atualizarPontuacao(Jogador jogador) {
        String sql = "UPDATE Jogador SET num_jogos = ?, num_jogos_ganho = ?, num_jogos_perdido = ?, num_jogos_ganho_lord = ? WHERE matricula = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, jogador.getEstatisticaJogador().getNumJogos());
            statement.setInt(2, jogador.getEstatisticaJogador().getNumJogosGanho());
            statement.setInt(3, jogador.getEstatisticaJogador().getNumJogosPerdido());
            statement.setInt(4, jogador.getEstatisticaJogador().getNumJogosGanhoLord());
            statement.setInt(5, jogador.getMatricula());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de atualização. Erro: " + ex.getMessage());
        }
    }

    public void excluir(Jogador jogador) {
        String sql = "DELETE FROM Jogador WHERE matricula = ?";

        try {
            for (Carta carta : jogador.getCartas()) {
                excluirCartaJogador(carta, jogador);
            }

            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, jogador.getMatricula());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de exclusão. Erro: " + ex.getMessage());
        }
    }

    public Jogador buscarPorLogin(String login) {
        String sql = "SELECT * FROM Jogador WHERE login = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, login);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Jogador jogador = new Jogador();

                jogador.setNome(rs.getString("nome"));
                jogador.setEmail(rs.getString("email"));
                jogador.setImagem(rs.getString("imagem"));
                jogador.setMatricula(rs.getInt("matricula"));
                jogador.setLogin(rs.getString("login"));
                jogador.setSenha(rs.getString("senha"));
                jogador.setTipoJogador(TipoJogador.values()[rs.getInt("id_tipo_jogador") - 1]);

                EstatisticaJogador ej = new EstatisticaJogador();
                ej.setNumJogos(rs.getInt("num_jogos"));
                ej.setNumJogosGanho(rs.getInt("num_jogos_ganho"));
                ej.setNumJogosPerdido(rs.getInt("num_jogos_perdido"));
                ej.setNumJogosGanhoLord(rs.getInt("num_jogos_ganho_lord"));

                jogador.setEstatisticaJogador(ej);

                statement.close();

                connection.close();

                jogador.setCartas(buscarCartasJogador(jogador));

                return jogador;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }
        return null;
    }

    public String buscaImagemJogadorPorMatricula(int matricula) {
        String sql = "SELECT imagem FROM Jogador WHERE matricula = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, matricula);

            ResultSet rs = statement.executeQuery();

            String imagem = "";

            while (rs.next()) {
                imagem = rs.getString("imagem");
            }

            statement.close();

            connection.close();

            return imagem;

        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }
        return "";
    }
    
    public List<Ranking> ranking(){
        List<Ranking> ranking = new ArrayList();

        String sql = "SELECT login, (num_jogos_ganho + num_jogos_ganho_lord )/(num_jogos) * 100 as media, num_jogos_ganho, num_jogos_ganho_lord, num_jogos_perdido FROM LordOfFlorestal.Jogador order by media desc, num_jogos_ganho_lord desc limit 10;";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ranking r = new Ranking();
                r.setLogin(rs.getString("login"));
                r.setMedia(rs.getInt("media"));
                r.setJogosGanho(rs.getInt("num_jogos_ganho"));
                r.setJogosGanhoLord(rs.getInt("num_jogos_ganho_lord"));
                r.setJogosPerdido(rs.getInt("num_jogos_perdido"));

                ranking.add(r);
            }

            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }

        return ranking;
    }

    public String buscaLoginJogadorPorMatricula(int matricula) {
        String sql = "SELECT login FROM Jogador WHERE matricula = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, matricula);

            ResultSet rs = statement.executeQuery();

            String login = "";

            while (rs.next()) {
                login = rs.getString("login");
            }

            statement.close();

            connection.close();
            
            return login;
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }
        return "";
    }

    public List<DuelosJogador> buscaDuelosJogador(int matricula) {
        List<DuelosJogador> duelos = new ArrayList();

        String sql = "SELECT * FROM Duelo WHERE criador = ? OR oponente = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, matricula);
            statement.setInt(2, matricula);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                DuelosJogador dj = new DuelosJogador();
                dj.setMatriculaJogador(rs.getInt("criador"));
                dj.setMatriculaOponente(rs.getInt("oponente"));
                dj.setMatriculaVencedor(rs.getInt("vencedor"));
                String data = rs.getString("data_criacao");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    dj.setDataCriacao(sdf.parse(data));
                } catch (ParseException ex) {
                    Logger.getLogger(JogadorDAOMysql.class.getName()).log(Level.SEVERE, null, ex);
                }
                dj.setSituacaoDuelo(SituacaoDuelo.values()[rs.getInt("id_situacao_duelo") - 1]);

                duelos.add(dj);
            }

            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }

        for (DuelosJogador dj : duelos) {
            dj.setImgJogador(buscaImagemJogadorPorMatricula(dj.getMatriculaJogador()));
            dj.setImgOponente(buscaImagemJogadorPorMatricula(dj.getMatriculaOponente()));
            dj.setJogador(buscaLoginJogadorPorMatricula(dj.getMatriculaJogador()));
            dj.setOponente(buscaLoginJogadorPorMatricula(dj.getMatriculaOponente()));
        }

        return duelos;
    }

    public Jogador buscarPorMatricula(Integer matricula) {
        String sql = "SELECT * FROM Jogador WHERE matricula = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, matricula);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Jogador jogador = new Jogador();

                jogador.setNome(rs.getString("nome"));
                jogador.setEmail(rs.getString("email"));
                jogador.setImagem(rs.getString("imagem"));
                jogador.setMatricula(rs.getInt("matricula"));
                jogador.setLogin(rs.getString("login"));
                jogador.setSenha(rs.getString("senha"));
                jogador.setTipoJogador(TipoJogador.values()[rs.getInt("id_tipo_jogador") - 1]);

                EstatisticaJogador ej = new EstatisticaJogador();
                ej.setNumJogos(rs.getInt("num_jogos"));
                ej.setNumJogosGanho(rs.getInt("num_jogos_ganho"));
                ej.setNumJogosPerdido(rs.getInt("num_jogos_perdido"));
                ej.setNumJogosGanhoLord(rs.getInt("num_jogos_ganho_lord"));

                jogador.setEstatisticaJogador(ej);

                statement.close();

                connection.close();

                jogador.setCartas(buscarCartasJogador(jogador));

                return jogador;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }
        return null;
    }

    public EstatisticaJogador buscarEstatisticaJogador(Integer matricula) {
        String sql = "SELECT * FROM Jogador WHERE matricula = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, matricula);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                EstatisticaJogador ej = new EstatisticaJogador();
                ej.setNumJogos(rs.getInt("num_jogos"));
                ej.setNumJogosGanho(rs.getInt("num_jogos_ganho"));
                ej.setNumJogosPerdido(rs.getInt("num_jogos_perdido"));
                ej.setNumJogosGanhoLord(rs.getInt("num_jogos_ganho_lord"));

                statement.close();

                connection.close();

                return ej;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }
        return null;
    }

    public List<Jogador> listar() {
        List<Jogador> lista = new ArrayList();

        String sql = "SELECT * FROM Jogador";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Jogador jogador = new Jogador();

                jogador.setNome(rs.getString("nome"));
                jogador.setEmail(rs.getString("email"));
                jogador.setImagem(rs.getString("imagem"));
                jogador.setMatricula(rs.getInt("matricula"));
                jogador.setLogin(rs.getString("login"));
                jogador.setSenha(rs.getString("senha"));
                jogador.setTipoJogador(TipoJogador.values()[rs.getInt("id_tipo_jogador") - 1]);

                EstatisticaJogador ej = new EstatisticaJogador();
                ej.setNumJogos(rs.getInt("num_jogos"));
                ej.setNumJogosGanho(rs.getInt("num_jogos_ganho"));
                ej.setNumJogosPerdido(rs.getInt("num_jogos_perdido"));
                ej.setNumJogosGanhoLord(rs.getInt("num_jogos_ganho_lord"));

                jogador.setEstatisticaJogador(ej);

                lista.add(jogador);
            }

            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }

        for (Jogador jogador : lista) {
            jogador.setCartas(buscarCartasJogador(jogador));
        }

        return lista;
    }

    public List<Jogador> listarExceto(Jogador jogador) {
        List<Jogador> lista = new ArrayList();

        String sql = "SELECT * FROM Jogador WHERE matricula != ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, jogador.getMatricula());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Jogador j = new Jogador();

                j.setNome(rs.getString("nome"));
                j.setEmail(rs.getString("email"));
                j.setImagem(rs.getString("imagem"));
                j.setMatricula(rs.getInt("matricula"));
                j.setLogin(rs.getString("login"));
                j.setSenha(rs.getString("senha"));
                j.setTipoJogador(TipoJogador.values()[rs.getInt("id_tipo_jogador") - 1]);

                EstatisticaJogador ej = new EstatisticaJogador();
                ej.setNumJogos(rs.getInt("num_jogos"));
                ej.setNumJogosGanho(rs.getInt("num_jogos_ganho"));
                ej.setNumJogosPerdido(rs.getInt("num_jogos_perdido"));
                ej.setNumJogosGanhoLord(rs.getInt("num_jogos_ganho_lord"));

                j.setEstatisticaJogador(ej);

                lista.add(j);
            }

            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }

        for (Jogador j : lista) {
            j.setCartas(buscarCartasJogador(j));
        }

        return lista;
    }

    public void inserirCartaJogador(Carta carta, Jogador jogador) {
        String sql = "INSERT INTO Jogador_has_Carta (matricula_jogador, id_carta) VALUES (?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, jogador.getMatricula());
            statement.setInt(2, carta.getId());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de inserção. Erro: " + ex.getMessage());
        }
    }

    public void excluirCartaJogador(Carta carta, Jogador jogador) {
        String sql = "DELETE FROM Jogador_has_Carta WHERE matricula_jogador = ? AND id_carta = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, jogador.getMatricula());
            statement.setInt(2, carta.getId());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de exclusão. Erro: " + ex.getMessage());
        }
    }

    public List<Carta> buscarCartasJogador(Jogador jogador) {
        List<Carta> lista = new ArrayList();

        String sql = "SELECT * FROM Jogador_has_Carta NATURAL JOIN Carta WHERE matricula_jogador = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, jogador.getMatricula());

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

    public String buscaImagemJogador(String login) {
        String sql = "SELECT imagem FROM Jogador WHERE login = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, login);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                return rs.getString("imagem");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }
        return "user.png";
    }

}
