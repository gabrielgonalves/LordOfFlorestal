/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.dao.JogadorDAO;
import br.com.lordofflorestal.model.EstatisticaJogador;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.util.DAOFactory;
import br.com.lordofflorestal.util.EmailUtil;
import br.com.lordofflorestal.util.MessageUtil;
import br.com.lordofflorestal.util.exception.RNException;
import br.com.lordofflorestal.util.exception.UtilException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class JogadorRN {

    private JogadorDAO jogadorDAO;

    public JogadorRN() {
        this.jogadorDAO = DAOFactory.criarJogadorDAO();
    }

    public void salvar(Jogador jogador) {
        if (buscarPorMatricula(jogador.getMatricula()) == null) {
            if (buscarPorLogin(jogador.getLogin()) == null) {
                this.jogadorDAO.salvar(jogador);

                try {
                    enviarEmail(jogador);
                } catch (RNException ex) {
                    Logger.getLogger(JogadorRN.class.getName()).log(Level.SEVERE, null, ex);
                }

                MessageUtil.info("Jogador " + jogador.getNome() + " salvo com sucesso!");
            } else {
                MessageUtil.erro("O login informado já esta em uso!");
            }
        } else {
            this.jogadorDAO.atualizar(jogador);
            MessageUtil.info("Jogador " + jogador.getNome() + " editado com sucesso!");
        }
    }

    public void enviarEmail(Jogador jogador) throws RNException {
        try {
            EmailUtil emailUtil = new EmailUtil();
            emailUtil.enviarEmail(null, jogador.getEmail(), "Cadastro - Lord Of Florestal",
                    "Olá " + jogador.getNome() + " você acaba de se cadastrar no nosso site.\n"
                    + "Usuário:" + jogador.getLogin() + "\n"
                    + "Senha:" + jogador.getSenha());
        } catch (UtilException ex) {
            throw new RNException(ex);
        }
    }

    public void excluir(Jogador jogador) {
        this.jogadorDAO.excluir(jogador);
        MessageUtil.info("Jogador " + jogador.getNome() + " excluido com sucesso!");
    }

    public Jogador buscarPorLogin(String login) {
        return this.jogadorDAO.buscarPorLogin(login);
    }

    public Jogador buscarPorMatricula(Integer matricula) {
        return this.jogadorDAO.buscarPorMatricula(matricula);
    }

    public EstatisticaJogador buscarEstatisticaJogador(Integer matricula) {
        return this.jogadorDAO.buscarEstatisticaJogador(matricula);
    }

    public List<Jogador> listar() {
        return this.jogadorDAO.listar();
    }
}
