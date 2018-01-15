/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gabriel
 */
public class Turno implements Serializable {

    private int id;
    private List<ItemTurno> itensTurno;
    private Jogador jogador;

    public Turno() {
        itensTurno = new ArrayList();
    }

    public Turno(int id, List<ItemTurno> itensTurno, Jogador jogador) {
        this.id = id;
        this.itensTurno = itensTurno;
        this.jogador = jogador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemTurno> getItensTurno() {
        return itensTurno;
    }

    public void setItensTurno(List<ItemTurno> itensTurno) {
        this.itensTurno = itensTurno;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.itensTurno);
        hash = 61 * hash + Objects.hashCode(this.jogador);
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
        final Turno other = (Turno) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.itensTurno, other.itensTurno)) {
            return false;
        }
        if (!Objects.equals(this.jogador, other.jogador)) {
            return false;
        }
        return true;
    }

}
