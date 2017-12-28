/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author gabriel
 */
public class Carta implements Serializable {

    protected int id;
    protected String nome;
    protected String imagem;
    protected String efeito;
    protected String descricao;
    protected int valorAtaque;
    protected int valorDefesa;
    protected TipoCarta tipoCarta;
    protected SubtipoCarta subtipoCarta;

    public Carta() {
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getValorAtaque() {
        return valorAtaque;
    }

    public void setValorAtaque(int valorAtaque) {
        this.valorAtaque = valorAtaque;
    }

    public int getValorDefesa() {
        return valorDefesa;
    }

    public void setValorDefesa(int valorDefesa) {
        this.valorDefesa = valorDefesa;
    }

    public TipoCarta getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(TipoCarta tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    public SubtipoCarta getSubtipoCarta() {
        return subtipoCarta;
    }

    public void setSubtipoCarta(SubtipoCarta subtipoCarta) {
        this.subtipoCarta = subtipoCarta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.imagem);
        hash = 79 * hash + Objects.hashCode(this.efeito);
        hash = 79 * hash + Objects.hashCode(this.descricao);
        hash = 79 * hash + this.valorAtaque;
        hash = 79 * hash + this.valorDefesa;
        hash = 79 * hash + Objects.hashCode(this.tipoCarta);
        hash = 79 * hash + Objects.hashCode(this.subtipoCarta);
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
        final Carta other = (Carta) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.valorAtaque != other.valorAtaque) {
            return false;
        }
        if (this.valorDefesa != other.valorDefesa) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.imagem, other.imagem)) {
            return false;
        }
        if (!Objects.equals(this.efeito, other.efeito)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (this.tipoCarta != other.tipoCarta) {
            return false;
        }
        if (this.subtipoCarta != other.subtipoCarta) {
            return false;
        }
        return true;
    }

}
