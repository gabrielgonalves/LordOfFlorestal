/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author gabriel
 */
@Entity
public class Carta implements Serializable {

    @Id
    @GeneratedValue
    protected int id;
    protected String nome;
    protected String imagem;
    protected TipoCarta tipoCarta;
    protected SubtipoCarta subtipoCarta;
    protected String efeito;
    protected String descricao;
    protected int valorAtaque;
    protected int valorDefesa;

    public Carta() {
    }

    public Carta(int id, String nome, String imagem, TipoCarta tipoCarta, SubtipoCarta subtipoCarta, String efeito, String descricao, int valorAtaque, int valorDefesa) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
        this.tipoCarta = tipoCarta;
        this.subtipoCarta = subtipoCarta;
        this.efeito = efeito;
        this.descricao = descricao;
        this.valorAtaque = valorAtaque;
        this.valorDefesa = valorDefesa;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.imagem);
        hash = 23 * hash + Objects.hashCode(this.tipoCarta);
        hash = 23 * hash + Objects.hashCode(this.subtipoCarta);
        hash = 23 * hash + Objects.hashCode(this.efeito);
        hash = 23 * hash + Objects.hashCode(this.descricao);
        hash = 23 * hash + this.valorAtaque;
        hash = 23 * hash + this.valorDefesa;
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
