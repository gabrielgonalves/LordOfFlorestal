/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.CartaJogo;
import br.com.lordofflorestal.model.Deck;
import br.com.lordofflorestal.model.EstadoCarta;
import br.com.lordofflorestal.model.LocalCarta;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import br.com.lordofflorestal.rn.CartaRN;

/**
 *
 * @author gabriel
 */
public class EfeitoCartaRN {

    //A carta mais forte do jogo
    public static void carta1() {

    }

    //Alan turing de verdade
    public static void carta3() {

    }

    //Albert einstein
    public static void carta4(Deck deckOponente) {
        int pontoDetermincacao = deckOponente.getPontosDeterminacao() - 2;
        deckOponente.setPontosDeterminacao(pontoDetermincacao);
    }

    //Isacc newton
    public static void carta19(Deck deckOponente) {
        CartaJogo forte = new CartaJogo();
        for (CartaJogo carta : deckOponente.getCartas()) {
            if (carta.getLocalCarta().equals(LocalCarta.MESA)) {
                if (carta.getValorAtaque() > forte.getValorAtaque()) {
                    forte = carta;
                } else if (carta.getValorAtaque() == forte.getValorAtaque()) {
                    if (carta.getValorDefesa() > forte.getValorDefesa()) {
                        forte = carta;
                    }
                }

            }
        }
        forte.setLocalCarta(LocalCarta.DESCARTE);
    }

//Margaret hamilton
    public static void carta23(CartaJogo cartaOponente) {
        if (cartaOponente.getCarta().getTipoCarta().equals(TipoCarta.ESPECIAL)) {
        }
    }

    //Absurdo matemático conveniente
    public static void carta46(Deck deckOponente) {
        for (CartaJogo carta : deckOponente.getCartas()) {
            if (carta.getValorDefesa() == 100) {
                carta.setLocalCarta(LocalCarta.DESCARTE);
            }
        }
    }

    //Ano novo
    public static void carta47(Deck deck, Deck deckOponente) {
        for (CartaJogo carta : deckOponente.getCartas()) {
            carta.setLocalCarta(LocalCarta.MESA);
        }
        for (CartaJogo carta : deck.getCartas()) {
            carta.setLocalCarta(LocalCarta.MESA);
        }
    }

    //Café do carlinhos
    public static void carta48(Deck deck) {
        for (CartaJogo carta : deck.getCartas()) {
            if (carta.getCarta().getSubtipoCarta().equals(SubtipoCarta.ALUNO)) {
                int valorAtaque = carta.getValorAtaque() + 1;
                int valorDefesa = carta.getValorDefesa() + 1;
                carta.setValorAtaque(valorAtaque);
                carta.setValorDefesa(valorDefesa);
            }
        }
    }

    //Confusão causada por variáveis do for
    public static void carta49(Deck deck, Deck deckOponente) {
        for (CartaJogo carta : deckOponente.getCartas()) {
            if (carta.getLocalCarta().equals(LocalCarta.MESA) && carta.getCarta().getTipoCarta().equals(TipoCarta.DESAFIO)) {
                int valorAtaque = carta.getValorAtaque();
                int valorDefesa = carta.getValorDefesa();
                carta.setValorAtaque(valorDefesa);
                carta.setValorDefesa(valorAtaque);
            }
        }
        for (CartaJogo carta : deck.getCartas()) {
            if (carta.getLocalCarta().equals(LocalCarta.MESA) && carta.getCarta().getTipoCarta().equals(TipoCarta.DESAFIO)) {
                int valorAtaque = carta.getValorAtaque();
                int valorDefesa = carta.getValorDefesa();
                carta.setValorAtaque(valorDefesa);
                carta.setValorDefesa(valorAtaque);
            }
        }
    }

    //Desculpa esfarrapada
    public static void carta50(CartaJogo cartaOponente) {
        int valorAtaque = cartaOponente.getValorAtaque() - 1;
        int valorDefesa = cartaOponente.getValorDefesa() - 1;
        cartaOponente.setValorAtaque(valorAtaque);
        cartaOponente.setValorDefesa(valorDefesa);
    }

    //Desmotivação induzida pela nota da prova
    public static void carta51() {

    }

    //Erro de cálculo conveniente
    public static void carta52(CartaJogo carta) {
        int valorDefesa = carta.getValorDefesa() + 3;
        carta.setValorDefesa(valorDefesa);
    }

    //Erro de física conveniente
    public static void carta53() {

    }

    //Erro lógico conveniente
    public static void carta54(CartaJogo carta) {
        int valorAtaque = carta.getValorAtaque() + 3;
        carta.setValorAtaque(valorAtaque);
    }

    //Estudo de última hora
    public static void carta55() {

    }

    //Feriado quase no fim do semestre
    public static void carta56(Deck deck) {
        int pontosDeterminacao = deck.getPontosDeterminacao() + 2;
        deck.setPontosDeterminacao(pontosDeterminacao);
    }

    //Inspiração espontanêa
    public static void carta57() {

    }

    //Interpretação popular dos objetivos do curso
    public static void carta58() {

    }

    //Lorde of florestal
    public static void carta59(CartaJogo cartaOponente) {
        cartaOponente.setLocalCarta(LocalCarta.DESCARTE);
    }

    //Madrugada de estudos
    public static void carta60(Deck deck, CartaJogo cartaOponente) {
        int pontosDeterminacao = deck.getPontosDeterminacao() - 2;
        deck.setPontosDeterminacao(pontosDeterminacao);
        cartaOponente.setLocalCarta(LocalCarta.DESCARTE);
    }

    //Nota muito abaixo da média
    public static void carta61(Deck deck, Deck deckOponente) {
        for (CartaJogo carta : deckOponente.getCartas()) {
            if(carta.getCarta().getSubtipoCarta().equals(SubtipoCarta.ALUNO)){
                carta.setLocalCarta(LocalCarta.DESCARTE);
            }
        }
        for (CartaJogo carta : deck.getCartas()) {
            if(carta.getCarta().getSubtipoCarta().equals(SubtipoCarta.ALUNO)){
                carta.setLocalCarta(LocalCarta.DESCARTE);
            }
        }
    }

    //Organização utópica esperada pelos professores
    public static void carta62(Deck deck) {
//        List<CartaJogo> monte = new ArrayList();
//        for(CartaJogo carta : deck.getCartas()){
//            if(carta.getLocalCarta().equals(LocalCarta.MONTE)){
//                monte.add(carta);
//            }
//        }
    }

    //Pânico induzido pelo prazo de entrega
    public static void carta63() {

    }

    //Ponto extra inesperado
    public static void carta64(CartaJogo carta) {
        int valorDefesa = carta.getValorDefesa() +1;
        carta.setValorDefesa(valorDefesa);
    }

    //Procrastinação fora de controle
    public static void carta65() {

    }

    //Professor substituto
    public static void carta66(CartaJogo cartaOponente) {
        CartaRN cartaRN = new CartaRN();
        Carta carta = cartaRN.buscarPorId(66);
        CartaJogo cartaJogo = new CartaJogo();
        cartaJogo.setCarta(carta);
        cartaJogo.setValorAtaque(carta.getValorAtaque());
        cartaJogo.setValorDefesa(carta.getValorDefesa());
        cartaJogo.setLocalCarta(LocalCarta.MESA);
        cartaJogo.setEstadoCarta(cartaOponente.getEstadoCarta());
        cartaJogo.setId(cartaOponente.getId());
        cartaJogo.setDeck(cartaOponente.getDeck());
    }

    //Prova de consulta
    public static void carta67(CartaJogo carta) {
        carta.setLocalCarta(LocalCarta.MAO);
    }

    //Prova em dupla
    public static void carta68(CartaJogo carta) {
        carta.setLocalCarta(LocalCarta.MAO);
    }

    //Prova inesperada
    public static void carta69(CartaJogo cartaOponente) {
        if(cartaOponente.getEstadoCarta().equals(EstadoCarta.ATAQUE)){
            cartaOponente.setEstadoCarta(EstadoCarta.DEFESA);
        } else {
            cartaOponente.setEstadoCarta(EstadoCarta.ATAQUE);
        }
    }

    //Reprovação em cálculo 1
    public static void carta70() {

    }
}
