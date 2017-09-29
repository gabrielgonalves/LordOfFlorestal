/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name = "ItemTurno")
public class ItemTurno implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_item_turno")
    private int id;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "acao_turno")
    private AcaoTurno acaoTurno;
    @ManyToOne
    @JoinColumn(name = "id_carta_jogo")
    private CartaJogo cartaJogo;
    @ManyToOne
    @JoinColumn(name = "id_turno")
    private Turno turno;
    
    public ItemTurno() {
    }

    public ItemTurno(int id, AcaoTurno acaoTurno, CartaJogo cartaJogo) {
        this.id = id;
        this.acaoTurno = acaoTurno;
        this.cartaJogo = cartaJogo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AcaoTurno getAcaoTurno() {
        return acaoTurno;
    }

    public void setAcaoTurno(AcaoTurno acaoTurno) {
        this.acaoTurno = acaoTurno;
    }

    public CartaJogo getCartaJogo() {
        return cartaJogo;
    }

    public void setCartaJogo(CartaJogo cartaJogo) {
        this.cartaJogo = cartaJogo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.acaoTurno);
        hash = 67 * hash + Objects.hashCode(this.cartaJogo);
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
        final ItemTurno other = (ItemTurno) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.acaoTurno != other.acaoTurno) {
            return false;
        }
        if (!Objects.equals(this.cartaJogo, other.cartaJogo)) {
            return false;
        }
        return true;
    }
}
