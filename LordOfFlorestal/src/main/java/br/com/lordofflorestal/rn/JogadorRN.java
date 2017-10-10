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
import br.com.lordofflorestal.util.MessageUtil;
import java.util.List;

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
        System.out.println(jogador.getMatricula());
        if (buscarPorMatricula(jogador.getMatricula()) == null) {
            if (buscarPorLogin(jogador.getLogin()) == null) {
                this.jogadorDAO.salvar(jogador);
                MessageUtil.info("Jogador " + jogador.getNome() + " salvo com sucesso!");
            } else {
                MessageUtil.erro("O login informado já esta em uso!");
            }
        } else {
            this.jogadorDAO.atualizar(jogador);
            MessageUtil.info("Jogador " + jogador.getNome() + " editado com sucesso!");
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
