/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.control.DueloSingleton;
import br.com.lordofflorestal.model.CartaJogo;
import br.com.lordofflorestal.model.Deck;
import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.model.LocalCarta;
import br.com.lordofflorestal.model.SituacaoDuelo;
import br.com.lordofflorestal.rn.DueloRN;
import br.com.lordofflorestal.rn.JogadorRN;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class DueloBean {

    private Duelo duelo;

    private HttpServletRequest request;

    private CartaJogo cartaSelecionada;

    private Jogador jogador;
    private Jogador oponente;
    private Jogador ganhador;

    private Deck seuDeck;
    private Deck deckOponente;

    private List<CartaJogo> suaMao;
    private List<CartaJogo> seuMonte;
    private List<CartaJogo> suaMesa;
    private List<CartaJogo> seuDescarte;
    private List<CartaJogo> mesaOponente;

    private String corFundo;

    private Calendar dataAtual;

    private CartaJogo cartaAtaca;
    private CartaJogo cartaAtacada;
    private boolean podeAtacar;

    private boolean podeFinalizar;
    private boolean podeComprar;

    public DueloBean() {
        podeAtacar = true;
        podeFinalizar = false;
        DueloRN dueloRN = new DueloRN();
        this.jogador = new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String uri = request.getParameter("duelo");
        this.duelo = DueloSingleton.getInstance().buscaPorURI(uri);
        if (duelo.getCriadoPor().equals(jogador)) {
            seuDeck = duelo.getDeckJogador1();
            deckOponente = duelo.getDeckJogador2();
            oponente = duelo.getOponente();
        } else if (duelo.getOponente().equals(jogador)) {
            seuDeck = duelo.getDeckJogador2();
            deckOponente = duelo.getDeckJogador1();
            oponente = duelo.getCriadoPor();
        }
        separaCartas();
        embaralhar();
    }

    public String atacar() {
        int va = cartaAtaca.getValorAtaque();
        int vd = cartaAtacada.getValorDefesa();
        if (va >= vd) {
            deckOponente.setPontosDeterminacao(deckOponente.getPontosDeterminacao() - (va - vd));
            cartaAtacada.setLocalCarta(LocalCarta.DESCARTE);
            cartaAtaca.setValorAtaque(cartaAtaca.getValorAtaque() - vd);
        } else {
            cartaAtacada.setValorDefesa(cartaAtacada.getValorDefesa() - va);
            cartaAtaca.setLocalCarta(LocalCarta.DESCARTE);
        }
        podeAtacar = true;
        return null;
    }

    private void separaCartas() {
        suaMesa = new ArrayList();
        suaMao = new ArrayList();
        seuDescarte = new ArrayList();
        seuMonte = new ArrayList();
        mesaOponente = new ArrayList();
        for (CartaJogo carta : seuDeck.getCartas()) {
            switch (carta.getLocalCarta()) {
                case DESCARTE:
                    seuDescarte.add(carta);
                    break;
                case MAO:
                    suaMao.add(carta);
                    break;
                case MESA:
                    suaMesa.add(carta);
                    break;
                case MONTE:
                    seuMonte.add(carta);
                    break;
            }
        }
        for (CartaJogo carta : deckOponente.getCartas()) {
            switch (carta.getLocalCarta()) {
                case MESA:
                    mesaOponente.add(carta);
                    break;
            }
        }
    }

    private void embaralhar() {
        List<CartaJogo> novoMonte = new ArrayList();
        for (int i = seuMonte.size()-1; i >= 0 ; i--) {
            int num = new Random().nextInt(seuMonte.size());
            novoMonte.add(seuMonte.get(num));
            seuMonte.remove(i);
        }
        seuMonte = novoMonte;
    }

    public String finalizarTurno() {
        podeFinalizar = false;
        duelo.setVezDe(oponente.getLogin());
        return null;
    }

    private void verificaPontosDeterminacao() {
        if (seuDeck.getPontosDeterminacao() <= 0) {
            duelo.setSituacaoDuelo(SituacaoDuelo.FINALIZADO);
            ganhador = oponente;
        } else if (deckOponente.getPontosDeterminacao() <= 0) {
            duelo.setSituacaoDuelo(SituacaoDuelo.FINALIZADO);
            ganhador = jogador;
        }
    }

    public void atualizarDados() {
        dataAtual = Calendar.getInstance();
        if (duelo.getDataCriacao().getTimeInMillis() <= dataAtual.getTimeInMillis() && duelo.getSituacaoDuelo().equals(SituacaoDuelo.CRIADO)) {
            duelo.setSituacaoDuelo(SituacaoDuelo.CANCELADO);
        }
        if (duelo.getCriadoPor().equals(jogador)) {
            seuDeck = duelo.getDeckJogador1();
            deckOponente = duelo.getDeckJogador2();
            oponente = duelo.getOponente();
            verificaPontosDeterminacao();
        } else {
            seuDeck = duelo.getDeckJogador2();
            deckOponente = duelo.getDeckJogador1();
            oponente = duelo.getCriadoPor();
            verificaPontosDeterminacao();
        }
        mesaOponente = new ArrayList();
        for (CartaJogo carta : deckOponente.getCartas()) {
            switch (carta.getLocalCarta()) {
                case MESA:
                    mesaOponente.add(carta);
                    break;
            }
        }
    }

    public String comprar() {
        if (!seuMonte.isEmpty()) {
            duelo.setVezDe(jogador.getLogin() + "a");
            podeFinalizar = true;
            cartaSelecionada = seuMonte.get(0);
            cartaSelecionada.setLocalCarta(LocalCarta.MAO);
            suaMao.add(cartaSelecionada);
            seuMonte.remove(0);
        } else {
            MessageUtil.aviso("Você não possui mais cartas no monte");
            duelo.setVezDe(jogador.getLogin() + "a");
        }
        return null;
    }

    public String descer() {
        suaMao.remove(cartaSelecionada);
        cartaSelecionada.setLocalCarta(LocalCarta.MESA);
        suaMesa.add(cartaSelecionada);
        return null;
    }

    public void preto() {
        corFundo = "background-color: #363636";
    }

    public void vermelho() {
        corFundo = "background-color: #E85E4A";
    }

    public void bege() {
        corFundo = "background-color: #FFF9DA";
    }

    public void verde() {
        corFundo = "background-color: #79C2AA";
    }

    public void azul() {
        corFundo = "background-color: #608096";
    }

    public String getCorFundo() {
        return corFundo;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Jogador getOponente() {
        return oponente;
    }

    public Duelo getDuelo() {
        return duelo;
    }

    public void setDuelo(Duelo duelo) {
        this.duelo = duelo;
    }

    public CartaJogo getCartaSelecionada() {
        return cartaSelecionada;
    }

    public void setCartaSelecionada(CartaJogo cartaSelecionada) {
        this.cartaSelecionada = cartaSelecionada;
    }

    public List<CartaJogo> getSuaMao() {
        return suaMao;
    }

    public void setSuaMao(List<CartaJogo> suaMao) {
        this.suaMao = suaMao;
    }

    public List<CartaJogo> getSeuMonte() {
        return seuMonte;
    }

    public void setSeuMonte(List<CartaJogo> seuMonte) {
        this.seuMonte = seuMonte;
    }

    public List<CartaJogo> getSuaMesa() {
        return suaMesa;
    }

    public void setSuaMesa(List<CartaJogo> suaMesa) {
        this.suaMesa = suaMesa;
    }

    public List<CartaJogo> getSeuDescarte() {
        return seuDescarte;
    }

    public void setSeuDescarte(List<CartaJogo> seuDescarte) {
        this.seuDescarte = seuDescarte;
    }

    public List<CartaJogo> getMesaOponente() {
        return mesaOponente;
    }

    public void setMesaOponente(List<CartaJogo> mesaOponente) {
        this.mesaOponente = mesaOponente;
    }

    public Deck getSeuDeck() {
        return seuDeck;
    }

    public void setSeuDeck(Deck seuDeck) {
        this.seuDeck = seuDeck;
    }

    public Deck getDeckOponente() {
        return deckOponente;
    }

    public void setDeckOponente(Deck deckOponente) {
        this.deckOponente = deckOponente;
    }

    public Calendar getDataAtual() {
        return dataAtual;
    }

    public CartaJogo getCartaAtaca() {
        return cartaAtaca;
    }

    public void setCartaAtaca(CartaJogo cartaAtaca) {
        podeAtacar = false;
        this.cartaAtaca = cartaAtaca;
    }

    public CartaJogo getCartaAtacada() {
        return cartaAtacada;
    }

    public void setCartaAtacada(CartaJogo cartaAtacada) {
        this.cartaAtacada = cartaAtacada;
    }

    public boolean isPodeAtacar() {
        return podeAtacar;
    }

    public void setPodeAtacar(boolean podeAtacar) {
        this.podeAtacar = podeAtacar;
    }

    public Jogador getGanhador() {
        return ganhador;
    }

    public void setGanhador(Jogador ganhador) {
        this.ganhador = ganhador;
    }

    public boolean isPodeFinalizar() {
        return podeFinalizar;
    }

    public void setPodeFinalizar(boolean podeFinalizar) {
        this.podeFinalizar = podeFinalizar;
    }

    public boolean isPodeComprar() {
        return podeComprar;
    }

    public void setPodeComprar(boolean podeComprar) {
        this.podeComprar = podeComprar;
    }

}
