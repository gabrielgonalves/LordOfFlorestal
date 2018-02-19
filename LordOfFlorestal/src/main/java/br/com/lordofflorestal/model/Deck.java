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

    private int pontosDeterminacao;
    private List<CartaJogo> cartas;
    private Jogador jogador;
    private boolean podeUsarEspecial;  //Efeito da carta Desmotivação Induzida Pela Nota da Prova
    private boolean podeAtacar; //Efeito da carta Procrastinação Fora de Controle
    private boolean anulaEspecial;

    public Deck() {
        cartas = new ArrayList();
        pontosDeterminacao = 20;
        podeUsarEspecial = true;
        podeAtacar = true;
        anulaEspecial = false;
    }

    public Deck(int pontosDeterminacao, List<CartaJogo> cartas, Jogador jogador) {
        this.pontosDeterminacao = pontosDeterminacao;
        this.cartas = cartas;
        this.jogador = jogador;
        podeUsarEspecial = true;
        podeAtacar = true;
        anulaEspecial = false;
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

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public boolean isPodeUsarEspecial() {
        return podeUsarEspecial;
    }

    public void setPodeUsarEspecial(boolean podeUsarEspecial) {
        this.podeUsarEspecial = podeUsarEspecial;
    }

    public boolean isPodeAtacar() {
        return podeAtacar;
    }

    public void setPodeAtacar(boolean podeAtacar) {
        this.podeAtacar = podeAtacar;
    }

    public boolean isAnulaEspecial() {
        return anulaEspecial;
    }

    public void setAnulaEspecial(boolean anulaEspecial) {
        this.anulaEspecial = anulaEspecial;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.pontosDeterminacao;
        hash = 97 * hash + Objects.hashCode(this.cartas);
        hash = 97 * hash + Objects.hashCode(this.jogador);
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
        if (this.pontosDeterminacao != other.pontosDeterminacao) {
            return false;
        }
        if (!Objects.equals(this.cartas, other.cartas)) {
            return false;
        }
        if (!Objects.equals(this.jogador, other.jogador)) {
            return false;
        }
        return true;
    }
}
