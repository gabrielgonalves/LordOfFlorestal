/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.DuelosJogador;
import br.com.lordofflorestal.model.EstatisticaJogador;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.model.Ranking;
import br.com.lordofflorestal.mysql.JogadorDAOMysql;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class JogadorRN {
    
    private JogadorDAOMysql jogadorDAOMysql;
    
    public JogadorRN() {
        this.jogadorDAOMysql = new JogadorDAOMysql();
    }
    
    public void salvar(Jogador jogador) {
        if (buscarPorMatricula(jogador.getMatricula()) == null) {
            if (buscarPorLogin(jogador.getLogin()) == null) {
                this.jogadorDAOMysql.salvar(jogador);
                
                MessageUtil.info("Jogador " + jogador.getNome() + " salvo com sucesso!");
            } else {
                MessageUtil.erro("O login informado já esta em uso!");
            }
        } else {
            this.jogadorDAOMysql.atualizar(jogador);
            MessageUtil.info("Jogador " + jogador.getNome() + " editado com sucesso!");
        }
    }
    
    public void atualizarPontos(Jogador jogador) {
        if (buscarPorMatricula(jogador.getMatricula()).getEstatisticaJogador().getNumJogos() != jogador.getEstatisticaJogador().getNumJogos()) {
            this.jogadorDAOMysql.atualizarPontuacao(jogador);
        }
    }
    
    public void excluir(Jogador jogador) {
        this.jogadorDAOMysql.excluir(jogador);
        MessageUtil.info("Jogador " + jogador.getNome() + " excluido com sucesso!");
    }
    
    public List<Ranking> ranking(){
        return this.jogadorDAOMysql.ranking();
    }
    
    public List<DuelosJogador> buscaDuelosJogador(int matricula){
        return this.jogadorDAOMysql.buscaDuelosJogador(matricula);
    }
    
    public Jogador buscarPorLogin(String login) {
        return this.jogadorDAOMysql.buscarPorLogin(login);
    }
    
    public Jogador buscarPorMatricula(Integer matricula) {
        return this.jogadorDAOMysql.buscarPorMatricula(matricula);
    }
    
    public EstatisticaJogador buscarEstatisticaJogador(Integer matricula) {
        return this.jogadorDAOMysql.buscarEstatisticaJogador(matricula);
    }
    
    public List<Jogador> listar() {
        return this.jogadorDAOMysql.listar();
    }
    
    public List<Jogador> listarJogadoresSemCarta(int id) {
        return this.jogadorDAOMysql.listarJogadoresSemCarta(id);
    }
    
    public List<Jogador> listarExceto(Jogador jogador) {
        return this.jogadorDAOMysql.listarExceto(jogador);
    }
    
    public String buscaImagemJogador(String login) {
        return this.jogadorDAOMysql.buscaImagemJogador(login);
    }
    
    public void inserirCartaJogador(Carta carta, Jogador jogador) {
        this.jogadorDAOMysql.inserirCartaJogador(carta, jogador);
    }
    
    public List<Carta> buscarCartasJogador(Jogador jogador) {
        return this.jogadorDAOMysql.buscarCartasJogador(jogador);
    }
    
    public void excluirTodasCartasJogador(Jogador jogador) {
        this.jogadorDAOMysql.excluirTodasCartasJogador(jogador);
    }
    
    public boolean jogadorEstaCadastrado(String login){
        return this.jogadorDAOMysql.jogadorEstaCadastrado(login);
    }
}
