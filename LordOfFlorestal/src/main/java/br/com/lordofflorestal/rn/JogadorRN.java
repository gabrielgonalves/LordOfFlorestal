/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.model.Badge;
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

    public List<Ranking> ranking() {
        return this.jogadorDAOMysql.ranking();
    }
    
    public List<Ranking> rankingGeral() {
        return this.jogadorDAOMysql.rankingGeral();
    }

    public List<DuelosJogador> buscaDuelosJogador(int matricula) {
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

    public boolean jogadorEstaCadastrado(String login) {
        return this.jogadorDAOMysql.jogadorEstaCadastrado(login);
    }
    
    public List<Badge> buscarBadgesJogador(int matricula) {
        return this.jogadorDAOMysql.buscarBadgesJogador(matricula);
    }
    
    public void inserirBadgeJogador(Badge badge, Jogador jogador) {
        this.jogadorDAOMysql.inserirBadgeJogador(badge, jogador);
    }

    public void alteraXpJogador(Jogador jogador, int xp) {
        String nivelAtual = jogador.getNivel();
        this.jogadorDAOMysql.alteraXpJogador(jogador, xp);
        jogador.setXp(jogador.getXp() + xp);
        String novoNivel = jogador.getNivel();
        Carta carta = new Carta();
        Badge badge = new Badge();
        try {
            if (!nivelAtual.equals(novoNivel)) {
                switch (novoNivel) {
                    case "Novato(a)":
                        carta.setId(3);
                        badge.setId(2);
                        inserirBadgeJogador(badge, jogador);
                        inserirCartaJogador(carta, jogador);
                        MessageUtil.info("Parabéns, você atingiu o nível Novato(a)! Confira sua nova carta em seu perfil.");
                        break;
                    case "Calouro(a)":
                        carta.setId(4);
                        badge.setId(3);
                        inserirBadgeJogador(badge, jogador);
                        inserirCartaJogador(carta, jogador);
                        MessageUtil.info("Parabéns, você atingiu o nível Calouro(a)! Confira sua nova carta em seu perfil.");
                        break;
                    case "Veterano(a)":
                        carta.setId(19);
                        badge.setId(4);
                        inserirBadgeJogador(badge, jogador);
                        inserirCartaJogador(carta, jogador);
                        MessageUtil.info("Parabéns, você atingiu o nível Veterano(a)! Confira sua nova carta em seu perfil.");
                        break;
                    case "Formando(a)":
                        carta.setId(23);
                        badge.setId(5);
                        inserirBadgeJogador(badge, jogador);
                        inserirCartaJogador(carta, jogador);
                        MessageUtil.info("Parabéns, você atingiu o nível Formando(a)! Confira sua nova carta em seu perfil.");
                        break;
                    case "Graduado(a)":
                        carta.setId(1);
                        badge.setId(6);
                        inserirBadgeJogador(badge, jogador);
                        inserirCartaJogador(carta, jogador);
                        MessageUtil.info("Parabéns, você atingiu o nível Graduado(a)! Confira sua nova carta em seu perfil.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
    
    public void adicionaUmaMissao(Jogador jogador){
        if(jogador.getEstatisticaJogador().getNumMissoes() + 1 == 10){
            Badge badge = new Badge();
            badge.setId(12);
            this.jogadorDAOMysql.inserirBadgeJogador(badge, jogador);
        }
        this.jogadorDAOMysql.adicionaUmaMissao(jogador);
    }
}
