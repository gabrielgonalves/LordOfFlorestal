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
import java.util.List;

/**
 *
 * @author gabriel
 */
public class EfeitoCartaRN {

    //A carta mais forte do jogo
    public static boolean carta1(CartaJogo carta) {
        if (carta.isTurno()) {
            carta.setTurno(false);
        }
        if (!carta.isPosicao() && carta.isAtiva()) {
            carta.setPosicao(true);
            carta.setEstadoCarta(EstadoCarta.ATAQUE);
            carta.setTurno(true);
            return true;
        }
        return false;
    }

    //Alan turing de verdade
    public static void carta3() {

    }

    //Albert einstein
    public static void carta4(Deck seuDeck) {
        int pontoDetermincacao = seuDeck.getPontosDeterminacao() - 2;
        seuDeck.setPontosDeterminacao(pontoDetermincacao);
    }

    //Isacc newton
    public static CartaJogo carta19(Deck seuDeck) {
        CartaJogo forte = new CartaJogo();
        for (CartaJogo carta : seuDeck.getCartas()) {
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
        return forte;
    }

//Margaret hamilton
    public static boolean carta23(CartaJogo cartaOponente) {
        if (cartaOponente.getCarta().getTipoCarta().equals(TipoCarta.ESPECIAL)) {
            return false;
        }
        return true;
    }

    //Absurdo matemático conveniente
    public static CartaJogo carta46(Deck deckOponente) {
        for (CartaJogo carta : deckOponente.getCartas()) {
            if (carta.getLocalCarta().equals(LocalCarta.MESA) && carta.getCarta().getId() == 1) {
                if (carta.getValorDefesa() == 100) {
                    carta.setLocalCarta(LocalCarta.DESCARTE);
                    return carta;
                }
            }
        }
        return null;
    }

    //Ano novo
    public static void carta47(Deck deck, Deck deckOponente) {
        for (CartaJogo carta : deckOponente.getCartas()) {
            if (carta.getLocalCarta().equals(LocalCarta.MAO)) {
                if (carta.getCarta().getTipoCarta().equals(TipoCarta.DESAFIO)) {
                    carta.setLocalCarta(LocalCarta.MESA);
                } else {
                    carta.setLocalCarta(LocalCarta.DESCARTE);
                }
            }
        }
        for (CartaJogo carta : deck.getCartas()) {
            if (carta.getLocalCarta().equals(LocalCarta.MAO)) {
                if (carta.getCarta().getTipoCarta().equals(TipoCarta.DESAFIO)) {
                    carta.setLocalCarta(LocalCarta.MESA);
                } else {
                    carta.setLocalCarta(LocalCarta.DESCARTE);
                }
            }
        }
    }

    //Café do carlinhos
    public static void carta48(Deck deck) {
        for (CartaJogo carta : deck.getCartas()) {
            if (carta.getLocalCarta().equals(LocalCarta.MESA) && SubtipoCarta.ALUNO.equals(carta.getCarta().getSubtipoCarta())) {
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
        if(cartaOponente.getValorDefesa() == 100){
            valorDefesa = 100;
        }
        cartaOponente.setValorAtaque(valorAtaque);
        cartaOponente.setValorDefesa(valorDefesa);
        if (cartaOponente.getValorAtaque() <= 0 || cartaOponente.getValorDefesa() <= 0) {
            cartaOponente.setLocalCarta(LocalCarta.DESCARTE);
        }
    }

    //Desmotivação induzida pela nota da prova
    public static void carta51(Deck deckOponente) {
        deckOponente.setPodeUsarEspecial(false);
    }

    //Erro de cálculo conveniente
    public static void carta52(CartaJogo carta) {
        int valorDefesa = carta.getValorDefesa() + 3;
        carta.setValorDefesa(valorDefesa);
    }

    //Erro de física conveniente
    public static void carta53(CartaJogo carta, CartaJogo cartaSelecionada) {
        carta.setValorAtaque(cartaSelecionada.getValorAtaque());
        carta.setValorDefesa(cartaSelecionada.getValorDefesa());
    }

    //Erro lógico conveniente
    public static void carta54(CartaJogo carta) {
        int valorAtaque = carta.getValorAtaque() + 3;
        carta.setValorAtaque(valorAtaque);
    }

    //Estudo de última hora
    public static void carta55(Deck seuDeck, Deck deckOponente, boolean resultado) {
        if(resultado){
            for(CartaJogo carta : deckOponente.getCartas()){
                if(carta.getLocalCarta().equals(LocalCarta.MESA)){
                    carta.setLocalCarta(LocalCarta.DESCARTE);
                }
            }
        } else {
            for(CartaJogo carta : seuDeck.getCartas()){
                if(carta.getLocalCarta().equals(LocalCarta.MESA)){
                    carta.setLocalCarta(LocalCarta.DESCARTE);
                }
            }
            seuDeck.setPontosDeterminacao(seuDeck.getPontosDeterminacao() - 2);
        }
    }

    //Feriado quase no fim do semestre
    public static void carta56(Deck deck) {
        int pontosDeterminacao = deck.getPontosDeterminacao() + 2;
        deck.setPontosDeterminacao(pontosDeterminacao);
    }

    //Inspiração espontanêa
    public static void carta57(CartaJogo carta) {
        carta.setValorAtaque(carta.getValorAtaque() + 1);
        carta.setAumentouAtaque(1);
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
            if (LocalCarta.MESA.equals(carta.getLocalCarta()) && SubtipoCarta.ALUNO.equals(carta.getCarta().getSubtipoCarta())) {
                carta.setLocalCarta(LocalCarta.DESCARTE);
            }
        }
        for (CartaJogo carta : deck.getCartas()) {
            if (LocalCarta.MESA.equals(carta.getLocalCarta()) && SubtipoCarta.ALUNO.equals(carta.getCarta().getSubtipoCarta())) {
                carta.setLocalCarta(LocalCarta.DESCARTE);
            }
        }
    }

    //Organização utópica esperada pelos professores
    public static void carta62(Deck deck, List<CartaJogo> lista) {
        for(CartaJogo carta : lista){
            deck.getCartas().remove(carta);
        }
        for(CartaJogo carta : deck.getCartas()){
            lista.add(carta);
        }
        deck.setCartas(lista);
    }

    //Pânico induzido pelo prazo de entrega
    public static void carta63(CartaJogo carta) {
        carta.setValorAtaque(carta.getValorAtaque() + 2);
        carta.setAumentouAtaque(2);
    }

    //Ponto extra inesperado
    public static void carta64(CartaJogo carta) {
        int valorDefesa = carta.getValorDefesa() + 1;
        carta.setValorDefesa(valorDefesa);
    }

    //Procrastinação fora de controle
    public static void carta65(Deck deckOponente) {
        deckOponente.setPodeAtacar(false);
    }

    //Professor substituto
    public static void carta66(CartaJogo cartaOponente) {
        CartaRN cartaRN = new CartaRN();
        Carta carta = cartaRN.buscarPorId(66);
        cartaOponente.setCarta(carta);
        cartaOponente.setValorAtaque(carta.getValorAtaque());
        cartaOponente.setValorDefesa(carta.getValorDefesa());
        cartaOponente.setLocalCarta(LocalCarta.MESA);
    }

    //Prova de consulta
    public static void carta67(CartaJogo carta) {
        carta.setLocalCarta(LocalCarta.MAO);
        carta.setNova(true);
    }

    //Prova em dupla
    public static void carta68(CartaJogo carta) {
        carta.setLocalCarta(LocalCarta.MAO);
        carta.setNova(true);
    }

    //Prova inesperada
    public static void carta69(CartaJogo cartaOponente) {
        if (EstadoCarta.ATAQUE.equals(cartaOponente.getEstadoCarta())) {
            cartaOponente.setEstadoCarta(EstadoCarta.DEFESA);
            cartaOponente.setPosicao(false);
        } else {
            cartaOponente.setEstadoCarta(EstadoCarta.ATAQUE);
            cartaOponente.setPosicao(true);
        }
    }

    //Reprovação em cálculo 1
    public static void carta70(CartaJogo carta) {
        carta.setPodeVencerDuelo(false);
    }
}
