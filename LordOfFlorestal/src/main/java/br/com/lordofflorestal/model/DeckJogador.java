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
public class DeckJogador implements Serializable {

    private int id;
    private String nome;
    private Jogador jogador;
    private List<Carta> cartas;

    public DeckJogador() {
        cartas = new ArrayList();
    }
    
    public DeckJogador(DeckJogador deckJogador){
        this.id = deckJogador.getId();
        this.nome = deckJogador.getNome();
        this.jogador = deckJogador.getJogador();
        this.cartas = deckJogador.getCartas();
    }

    public DeckJogador(int id, String nome, Jogador jogador, List<Carta> cartas) {
        this.id = id;
        this.nome = nome;
        this.jogador = jogador;
        this.cartas = cartas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.nome);
        hash = 61 * hash + Objects.hashCode(this.jogador);
        hash = 61 * hash + Objects.hashCode(this.cartas);
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
        final DeckJogador other = (DeckJogador) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
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
