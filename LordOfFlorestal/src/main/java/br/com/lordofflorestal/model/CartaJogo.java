/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.util.Objects;

/**
 *
 * @author gabriel
 */
public class CartaJogo {

    private int valorAtaque;
    private int valorDefesa;
    private int turno;
    private boolean ativa;
    private boolean posicao; //true ataque false defesa
    private LocalCarta localCarta;
    private EstadoCarta estadoCarta;
    private Carta carta;

    public CartaJogo() {
        this.localCarta = LocalCarta.MONTE;
        this.estadoCarta = EstadoCarta.NEUTRO;
        this.turno = 0;
        this.posicao = true;
        this.ativa = true;
    }

    public CartaJogo(int valorAtaque, int valorDefesa, int turno, boolean ativa, LocalCarta localCarta, EstadoCarta estadoCarta, Carta carta) {
        this.valorAtaque = valorAtaque;
        this.valorDefesa = valorDefesa;
        this.turno = turno;
        this.ativa = ativa;
        this.localCarta = localCarta;
        this.estadoCarta = estadoCarta;
        this.carta = carta;
    }

    public CartaJogo(Carta carta) {
        this.carta = carta;
        this.valorAtaque = carta.getValorAtaque();
        this.valorDefesa = carta.getValorDefesa();
        this.localCarta = LocalCarta.MONTE;
        this.estadoCarta = EstadoCarta.NEUTRO;
        this.turno = 0;
        this.ativa = true;
        this.posicao = true;
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

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
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

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public boolean isPosicao() {
        return posicao;
    }

    public void setPosicao(boolean posicao) {
        this.posicao = posicao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.valorAtaque;
        hash = 67 * hash + this.valorDefesa;
        hash = 67 * hash + this.turno;
        hash = 67 * hash + (this.ativa ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.localCarta);
        hash = 67 * hash + Objects.hashCode(this.estadoCarta);
        hash = 67 * hash + Objects.hashCode(this.carta);
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
        if (this.valorAtaque != other.valorAtaque) {
            return false;
        }
        if (this.valorDefesa != other.valorDefesa) {
            return false;
        }
        if (this.turno != other.turno) {
            return false;
        }
        if (this.ativa != other.ativa) {
            return false;
        }
        if (this.localCarta != other.localCarta) {
            return false;
        }
        if (this.estadoCarta != other.estadoCarta) {
            return false;
        }
        if (!Objects.equals(this.carta, other.carta)) {
            return false;
        }
        return true;
    }

}
