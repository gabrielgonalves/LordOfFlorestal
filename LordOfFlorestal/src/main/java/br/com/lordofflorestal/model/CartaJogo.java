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
    private LocalCarta localCarta;
    private EstadoCarta estadoCarta;
    private boolean ativa; //Se a carta pode mudar estado de ataque e defesa
    private boolean nova; //Se carta é nova na mão do jogador
    private int especial; //Id da carta especial que afetou esta carta jogo
    private Carta carta;

    public CartaJogo() {
        this.localCarta = LocalCarta.MONTE;
        this.estadoCarta = EstadoCarta.NEUTRO;
        this.ativa = false;
        this.nova = true;
        this.especial = 0;
    }

    public CartaJogo(int valorAtaque, int valorDefesa, LocalCarta localCarta, EstadoCarta estadoCarta, Carta carta) {
        this.valorAtaque = valorAtaque;
        this.valorDefesa = valorDefesa;
        this.localCarta = localCarta;
        this.estadoCarta = estadoCarta;
        this.carta = carta;
        this.ativa = false;
        this.nova = true;
    }

    public CartaJogo(Carta carta) {
        this.carta = carta;
        this.valorAtaque = carta.getValorAtaque();
        this.valorDefesa = carta.getValorDefesa();
        this.localCarta = LocalCarta.MONTE;
        this.estadoCarta = EstadoCarta.NEUTRO;
        this.ativa = false;
        this.nova = true;
        this.especial = 0;
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

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public boolean isNova() {
        return nova;
    }

    public void setNova(boolean nova) {
        this.nova = nova;
    }

    public int getEspecial() {
        return especial;
    }

    public void setEspecial(int especial) {
        this.especial = especial;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.carta);
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
        if (!Objects.equals(this.carta, other.carta)) {
            return false;
        }
        return true;
    }

}
