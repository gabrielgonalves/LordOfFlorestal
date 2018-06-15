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
    private int numJogosGanhouLord;
    private int numJogosPerdeuLord;
    private int numMissoes;

    public EstatisticaJogador() {
    }

    public EstatisticaJogador(int numJogos, int numJogosGanho, int numJogosPerdido, int numJogosGanhouLord, int numJogosPerdeuLord) {
        this.numJogos = numJogos;
        this.numJogosGanho = numJogosGanho;
        this.numJogosPerdido = numJogosPerdido;
        this.numJogosGanhouLord = numJogosGanhouLord;
        this.numJogosPerdeuLord = numJogosPerdeuLord;
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

    public int getNumJogosGanhouLord() {
        return numJogosGanhouLord;
    }

    public void setNumJogosGanhouLord(int numJogosGanhouLord) {
        this.numJogosGanhouLord = numJogosGanhouLord;
    }
    
    public int getNumJogosPerdeuLord() {
        return numJogosPerdeuLord;
    }

    public void setNumJogosPerdeuLord(int numJogosPerdeuLord) {
        this.numJogosPerdeuLord = numJogosPerdeuLord;
    }

    public int getNumMissoes() {
        return numMissoes;
    }

    public void setNumMissoes(int numMissoes) {
        this.numMissoes = numMissoes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.numJogos;
        hash = 71 * hash + this.numJogosGanho;
        hash = 71 * hash + this.numJogosPerdido;
        hash = 71 * hash + this.numJogosGanhouLord;
        hash = 71 * hash + this.numJogosPerdeuLord;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstatisticaJogador other = (EstatisticaJogador) obj;
        if (this.numJogos != other.numJogos) {
            return false;
        }
        if (this.numJogosGanho != other.numJogosGanho) {
            return false;
        }
        if (this.numJogosPerdido != other.numJogosPerdido) {
            return false;
        }
        if (this.numJogosGanhouLord != other.numJogosGanhouLord) {
            return false;
        }
        if (this.numJogosPerdeuLord != other.numJogosPerdeuLord) {
            return false;
        }
        return true;
    }

}
