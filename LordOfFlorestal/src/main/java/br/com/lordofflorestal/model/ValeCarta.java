/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name = "ValeCarta")
public class ValeCarta implements Serializable {

    @Id
    @NaturalId
    private String codigo;
    @ManyToOne
    @JoinColumn(name = "id_carta")
    private Carta carta;
    private boolean valido;

    public ValeCarta() {
    }

    public ValeCarta(String codigo, Carta carta, boolean valido) {
        this.codigo = codigo;
        this.carta = carta;
        this.valido = valido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.codigo);
        hash = 59 * hash + Objects.hashCode(this.carta);
        hash = 59 * hash + (this.valido ? 1 : 0);
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
        final ValeCarta other = (ValeCarta) obj;
        if (this.valido != other.valido) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.carta, other.carta)) {
            return false;
        }
        return true;
    }

}
