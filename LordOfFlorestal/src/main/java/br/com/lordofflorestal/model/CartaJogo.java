/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name = "CartaJogo")
public class CartaJogo {

    @Id
    @GeneratedValue
    @Column(name = "id_carta_jogo")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_carta", nullable = false)
    private Carta carta;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "local_carta", nullable = false)
    private LocalCarta localCarta;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado_carta", nullable = false)
    private EstadoCarta estadoCarta;
    @ManyToOne
    @JoinColumn(name = "id_deck", nullable = false)
    private Deck deck;
    @Column(name = "valor_ataque")
    private int valorAtaque;
    @Column(name = "valor_defesa")
    private int valorDefesa;

    public CartaJogo() {
    }

    public CartaJogo(int id, Carta carta, LocalCarta localCarta, EstadoCarta estadoCarta, Deck deck, int valorAtaque, int valorDefesa) {
        this.id = id;
        this.carta = carta;
        this.localCarta = localCarta;
        this.estadoCarta = estadoCarta;
        this.deck = deck;
        this.valorAtaque = valorAtaque;
        this.valorDefesa = valorDefesa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
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

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
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
        int hash = 5;
        hash = 31 * hash + this.id;
        hash = 31 * hash + Objects.hashCode(this.carta);
        hash = 31 * hash + Objects.hashCode(this.localCarta);
        hash = 31 * hash + Objects.hashCode(this.estadoCarta);
        hash = 31 * hash + Objects.hashCode(this.deck);
        hash = 31 * hash + this.valorAtaque;
        hash = 31 * hash + this.valorDefesa;
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
        if (this.id != other.id) {
            return false;
        }
        if (this.valorAtaque != other.valorAtaque) {
            return false;
        }
        if (this.valorDefesa != other.valorDefesa) {
            return false;
        }
        if (!Objects.equals(this.carta, other.carta)) {
            return false;
        }
        if (this.localCarta != other.localCarta) {
            return false;
        }
        if (this.estadoCarta != other.estadoCarta) {
            return false;
        }
        if (!Objects.equals(this.deck, other.deck)) {
            return false;
        }
        return true;
    }

}
