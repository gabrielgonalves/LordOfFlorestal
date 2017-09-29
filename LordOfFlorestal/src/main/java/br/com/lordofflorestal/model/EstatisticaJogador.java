/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author gabriel
 */
@Embeddable
public class EstatisticaJogador implements Serializable {

    @Column(name = "num_jogos")
    private int numJogos;
    @Column(name = "num_jogos_ganho")
    private int numJogosGanho;
    @Column(name = "num_jogos_perdido")
    private int numJogosPerdido;
    @Column(name = "num_jogos_ganho_lord")
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
