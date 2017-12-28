/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author gabriel
 */
public class ValeCarta implements Serializable {

    private String codigo;
    private boolean valido;
    private Carta carta;

    public ValeCarta() {
        UUID uuid = UUID.randomUUID();
        this.codigo = uuid.toString().substring(0, 10);
    }

    public ValeCarta(String codigo, boolean valido, Carta carta) {
        this.codigo = codigo;
        this.valido = valido;
        this.carta = carta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.codigo);
        hash = 83 * hash + (this.valido ? 1 : 0);
        hash = 83 * hash + Objects.hashCode(this.carta);
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
