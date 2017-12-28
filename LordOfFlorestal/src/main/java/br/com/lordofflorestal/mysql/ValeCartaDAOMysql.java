/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.mysql;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import br.com.lordofflorestal.model.ValeCarta;
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
public class ValeCartaDAOMysql {

    private Connection connection;

    public void salvar(ValeCarta valeCarta) {
        String sql = "INSERT INTO ValeCarta (codigo, valido, id_carta) VALUES (?, ?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, valeCarta.getCodigo());
            statement.setBoolean(2, valeCarta.isValido());
            statement.setInt(3, valeCarta.getCarta().getId());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a inserção. Erro: " + e.getMessage());
        }
    }

    public void atualizar(ValeCarta valeCarta) {
        String sql = "UPDATE ValeCarta SET valido = ? WHERE codigo = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setBoolean(1, valeCarta.isValido());
            statement.setString(2, valeCarta.getCodigo());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de atualização. Erro: " + ex.getMessage());
        }
    }

    public void excluir(ValeCarta valeCarta) {
        String sql = "DELETE FROM ValeCarta WHERE codigo = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, valeCarta.getCodigo());

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar operações de exclusão. Erro: " + ex.getMessage());
        }
    }

    public ValeCarta buscarPorCodigo(String codigo) {
        List<ValeCarta> lista = new ArrayList();

        String sql = "SELECT * FROM ValeCarta WHERE codigo = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, codigo);
            
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ValeCarta valeCarta = new ValeCarta();

                valeCarta.setCodigo(rs.getString("codigo"));
                valeCarta.setValido(rs.getBoolean("valido"));
                
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
                
                valeCarta.setCarta(carta);

                return valeCarta;
            }

            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }
        
        return null;
    }

    public List<ValeCarta> buscarPorStatus(Boolean status) {
        List<ValeCarta> lista = new ArrayList();

        String sql = "SELECT * FROM ValeCarta WHERE valido = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setBoolean(1, status);
            
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ValeCarta valeCarta = new ValeCarta();

                valeCarta.setCodigo(rs.getString("codigo"));
                valeCarta.setValido(rs.getBoolean("valido"));
                
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
                
                valeCarta.setCarta(carta);

                lista.add(valeCarta);
            }

            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }
        
        return lista;
    }

    public List<ValeCarta> buscarPorCarta(Integer idCarta) {
        List<ValeCarta> lista = new ArrayList();

        String sql = "SELECT * FROM ValeCarta WHERE id_carta = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, idCarta);
            
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ValeCarta valeCarta = new ValeCarta();

                valeCarta.setCodigo(rs.getString("codigo"));
                valeCarta.setValido(rs.getBoolean("valido"));
                
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
                
                valeCarta.setCarta(carta);

                lista.add(valeCarta);
            }

            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }
        
        return lista;
    }

    public List<ValeCarta> listar() {
        List<ValeCarta> lista = new ArrayList();

        String sql = "SELECT * FROM ValeCarta NATURAL JOIN Carta";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ValeCarta valeCarta = new ValeCarta();

                valeCarta.setCodigo(rs.getString("codigo"));
                valeCarta.setValido(rs.getBoolean("valido"));
                
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
                
                valeCarta.setCarta(carta);

                lista.add(valeCarta);
            }

            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a consulta. Erro: " + e.getMessage());
        }

        return lista;
    }

}
