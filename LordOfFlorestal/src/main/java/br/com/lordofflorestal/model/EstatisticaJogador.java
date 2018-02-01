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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.numJogos;
        hash = 47 * hash + this.numJogosGanho;
        hash = 47 * hash + this.numJogosPerdido;
        hash = 47 * hash + this.numJogosGanhoLord;
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
        if (this.numJogosGanhoLord != other.numJogosGanhoLord) {
            return false;
        }
        return true;
    }

}
