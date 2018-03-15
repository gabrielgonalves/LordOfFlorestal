/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.util;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

/**
 *
 * @author gabriel
 */
public class ConexaoJDBC {

    private String dataBase;
    private Connection Conexao;
    private DataSource ds;

    private ConexaoJDBC(String db) {
        this.dataBase = db;
    }

    public ConexaoJDBC() {
        this.dataBase = "LordOfFlorestal";
    }

    public void abreConexao() {
        try {
            Context initCtx = new InitialContext();
            ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/" + this.dataBase);
            this.Conexao = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fechaConexao() {
        try {
            this.Conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPreparedStatement(String query) {
        try {
            return this.Conexao.prepareStatement(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    public ResultSet select(String query) {
        try {
            return this.Conexao.createStatement().executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(String query) {
        try {
            this.Conexao.createStatement().executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
