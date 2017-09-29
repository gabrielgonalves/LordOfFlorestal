/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name = "Duelo")
public class Duelo implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_duelo")
    private int id; 
    @Transient
    private Deck deckJogador1;
    @Transient
    private Deck deckJogador2;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "situacao_duelo")
    private SituacaoDuelo situacaoDuelo;
    @OneToMany(mappedBy = "duelo", fetch = FetchType.LAZY)
    private List<Turno> turnos;

    public Duelo() {
        deckJogador1 = new Deck();
        deckJogador2 = new Deck();
    }

    public Duelo(int id, Deck deckJogador1, Deck deckJogador2, SituacaoDuelo situacaoDuelo, List<Turno> turnos) {
        this.id = id;
        this.deckJogador1 = deckJogador1;
        this.deckJogador2 = deckJogador2;
        this.situacaoDuelo = situacaoDuelo;
        this.turnos = turnos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Deck getDeckJogador1() {
        return deckJogador1;
    }

    public void setDeckJogador1(Deck deckJogador1) {
        this.deckJogador1 = deckJogador1;
    }

    public Deck getDeckJogador2() {
        return deckJogador2;
    }

    public void setDeckJogador2(Deck deckJogador2) {
        this.deckJogador2 = deckJogador2;
    }

    public SituacaoDuelo getSituacaoDuelo() {
        return situacaoDuelo;
    }

    public void setSituacaoDuelo(SituacaoDuelo situacaoDuelo) {
        this.situacaoDuelo = situacaoDuelo;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.deckJogador1);
        hash = 71 * hash + Objects.hashCode(this.deckJogador2);
        hash = 71 * hash + Objects.hashCode(this.situacaoDuelo);
        hash = 71 * hash + Objects.hashCode(this.turnos);
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
        final Duelo other = (Duelo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.deckJogador1, other.deckJogador1)) {
            return false;
        }
        if (!Objects.equals(this.deckJogador2, other.deckJogador2)) {
            return false;
        }
        if (this.situacaoDuelo != other.situacaoDuelo) {
            return false;
        }
        if (!Objects.equals(this.turnos, other.turnos)) {
            return false;
        }
        return true;
    }


}
