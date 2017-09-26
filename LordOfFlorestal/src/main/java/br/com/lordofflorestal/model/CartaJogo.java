/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author gabriel
 */
public class CartaJogo extends Carta {

    private int id;
    private LocalCarta localCarta;
    private EstadoCarta estadoCarta;

    public CartaJogo() {
    }

    public CartaJogo(LocalCarta localCarta, EstadoCarta estadoCarta) {
        this.localCarta = localCarta;
        this.estadoCarta = estadoCarta;
    }

    public CartaJogo(LocalCarta localCarta, EstadoCarta estadoCarta, int id, String nome, String imagem, TipoCarta tipoCarta, SubtipoCarta subtipoCarta, String efeito, String descricao, int valorAtaque, int valorDefesa) {
        super(id, nome, imagem, tipoCarta, subtipoCarta, efeito, descricao, valorAtaque, valorDefesa);
        this.localCarta = localCarta;
        this.estadoCarta = estadoCarta;
    }

    public LocalCarta getLocalCarta() {
        return localCarta;
    }

    public void setLocalCarta(LocalCarta localCarta) {
        this.localCarta = localCarta;
    }

    public EstadoCarta getEstadoCarta() {
        return estadoCarta;
    }

    public void setEstadoCarta(EstadoCarta estadoCarta) {
        this.estadoCarta = estadoCarta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.localCarta);
        hash = 67 * hash + Objects.hashCode(this.estadoCarta);
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
        final CartaJogo other = (CartaJogo) obj;
        if (this.localCarta != other.localCarta) {
            return false;
        }
        if (this.estadoCarta != other.estadoCarta) {
            return false;
        }
        return true;
    }

}
