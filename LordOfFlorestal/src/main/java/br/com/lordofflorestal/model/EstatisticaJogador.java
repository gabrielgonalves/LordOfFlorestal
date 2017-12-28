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
public class EstatisticaJogador implements Serializable {

    private int numJogos;
    private int numJogosGanho;
    private int numJogosPerdido;
    private int numJogosGanhoLord;

    public EstatisticaJogador() {
    }

    public EstatisticaJogador(int numJogos, int numJogosGanho, int numJogosPerdido, int numJogosGanhoLord) {
        this.numJogos = numJogos;
        this.numJogosGanho = numJogosGanho;
        this.numJogosPerdido = numJogosPerdido;
        this.numJogosGanhoLord = numJogosGanhoLord;
    }

    public int getNumJogos() {
        return numJogos;
    }

    public void setNumJogos(int numJogos) {
        this.numJogos = numJogos;
    }

    public int getNumJogosGanho() {
        return numJogosGanho;
    }

    public void setNumJogosGanho(int numJogosGanho) {
        this.numJogosGanho = numJogosGanho;
    }

    public int getNumJogosPerdido() {
        return numJogosPerdido;
    }

    public void setNumJogosPerdido(int numJogosPerdido) {
        this.numJogosPerdido = numJogosPerdido;
    }

    public int getNumJogosGanhoLord() {
        return numJogosGanhoLord;
    }

    public void setNumJogosGanhoLord(int numJogosGanhoLord) {
        this.numJogosGanhoLord = numJogosGanhoLord;
    }

}
