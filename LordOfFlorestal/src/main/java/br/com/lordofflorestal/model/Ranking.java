/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.io.Serializable;

/**
 *
 * @author gabriel
 */
public class Ranking implements Serializable {

    private String login;
    private int media;
    private int jogosGanho;
    private int jogosGanhoLord;
    private int jogosPerdido;
    private int jogosPerdidoLord;
    private int xp;

    public Ranking() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public int getJogosGanho() {
        return jogosGanho;
    }

    public void setJogosGanho(int jogosGanho) {
        this.jogosGanho = jogosGanho;
    }

    public int getJogosGanhoLord() {
        return jogosGanhoLord;
    }

    public void setJogosGanhoLord(int jogosGanhoLord) {
        this.jogosGanhoLord = jogosGanhoLord;
    }

    public int getJogosPerdido() {
        return jogosPerdido;
    }

    public void setJogosPerdido(int jogosPerdido) {
        this.jogosPerdido = jogosPerdido;
    }

    public int getJogosPerdidoLord() {
        return jogosPerdidoLord;
    }

    public void setJogosPerdidoLord(int jogosPerdidoLord) {
        this.jogosPerdidoLord = jogosPerdidoLord;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
