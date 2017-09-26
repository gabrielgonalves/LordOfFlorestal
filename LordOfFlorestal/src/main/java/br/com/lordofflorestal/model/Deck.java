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
public class Deck implements Serializable {

    private int id;
    private Jogador jogador;
    private int pontosDeterminacao;
    private List<CartaJogo> cartas;

    public Deck() {
        cartas = new ArrayList();
    }

    public Deck(int id, Jogador jogador, int pontosDeterminacao, List<CartaJogo> cartas) {
        this.id = id;
        this.jogador = jogador;
        this.pontosDeterminacao = pontosDeterminacao;
        this.cartas = cartas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public int getPontosDeterminacao() {
        return pontosDeterminacao;
    }

    public void setPontosDeterminacao(int pontosDeterminacao) {
        this.pontosDeterminacao = pontosDeterminacao;
    }

    public List<CartaJogo> getCartas() {
        return cartas;
    }

    public void setCartas(List<CartaJogo> cartas) {
        this.cartas = cartas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.jogador);
        hash = 53 * hash + this.pontosDeterminacao;
        hash = 53 * hash + Objects.hashCode(this.cartas);
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
        final Deck other = (Deck) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.pontosDeterminacao != other.pontosDeterminacao) {
            return false;
        }
        if (!Objects.equals(this.jogador, other.jogador)) {
            return false;
        }
        if (!Objects.equals(this.cartas, other.cartas)) {
            return false;
        }
        return true;
    }

}
