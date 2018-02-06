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
    private boolean turno;
    private boolean ativa;
    private boolean posicao; //true ataque false defesa
    private LocalCarta localCarta;
    private EstadoCarta estadoCarta;
    private Carta carta;
    private boolean nova;
    private boolean podeVencerDuelo;
    private int aumentouAtaque;

    public CartaJogo() {
        this.localCarta = LocalCarta.MONTE;
        this.estadoCarta = EstadoCarta.NEUTRO;
        this.turno = false;
        this.posicao = false;
        this.ativa = false;
        this.nova = false;
        this.podeVencerDuelo = true;
        this.aumentouAtaque = 0;
    }

    public CartaJogo(int valorAtaque, int valorDefesa, boolean turno, boolean ativa, LocalCarta localCarta, EstadoCarta estadoCarta, Carta carta) {
        this.valorAtaque = valorAtaque;
        this.valorDefesa = valorDefesa;
        this.turno = turno;
        this.ativa = ativa;
        this.localCarta = localCarta;
        this.estadoCarta = estadoCarta;
        this.carta = carta;
        this.podeVencerDuelo = true;
    }

    public CartaJogo(Carta carta) {
        this.carta = carta;
        this.valorAtaque = carta.getValorAtaque();
        this.valorDefesa = carta.getValorDefesa();
        this.localCarta = LocalCarta.MONTE;
        this.estadoCarta = EstadoCarta.NEUTRO;
        this.turno = false;
        this.ativa = false;
        this.posicao = false;
        this.nova = false;
        this.podeVencerDuelo = true;
        this.aumentouAtaque = 0;
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

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
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

    public boolean isNova() {
        return nova;
    }

    public void setNova(boolean nova) {
        this.nova = nova;
    }

    public boolean isPodeVencerDuelo() {
        return podeVencerDuelo;
    }

    public void setPodeVencerDuelo(boolean podeVencerDuelo) {
        this.podeVencerDuelo = podeVencerDuelo;
    }

    public int getAumentouAtaque() {
        return aumentouAtaque;
    }

    public void setAumentouAtaque(int aumentouAtaque) {
        this.aumentouAtaque = aumentouAtaque;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.valorAtaque;
        hash = 19 * hash + this.valorDefesa;
        hash = 19 * hash + (this.turno ? 1 : 0);
        hash = 19 * hash + (this.ativa ? 1 : 0);
        hash = 19 * hash + (this.posicao ? 1 : 0);
        hash = 19 * hash + Objects.hashCode(this.localCarta);
        hash = 19 * hash + Objects.hashCode(this.estadoCarta);
        hash = 19 * hash + Objects.hashCode(this.carta);
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
        if (this.posicao != other.posicao) {
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
