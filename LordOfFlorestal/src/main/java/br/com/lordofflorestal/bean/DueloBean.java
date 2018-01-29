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
import br.com.lordofflorestal.model.EstadoCarta;
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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gabriel
 */
@ManagedBean
@SessionScoped
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
    private boolean podeDescer;
    private boolean podeComprar;

    public DueloBean() {
        podeAtacar = false;
        podeDescer = false;
        podeFinalizar = false;
        podeComprar = true;
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
        seuMonte.get(0).setLocalCarta(LocalCarta.MAO);
        seuMonte.get(1).setLocalCarta(LocalCarta.MAO);
        seuMonte.get(2).setLocalCarta(LocalCarta.MAO);
        separaCartas();
    }

    public String atacar() {
        cartaAtaca.setAtiva(false);
        cartaAtaca.setTurno(false);
        int va = cartaAtaca.getValorAtaque();
        int vd = cartaAtacada.getValorDefesa();
        if (va > vd) { //CARTA OPONENTE É DESTUIDA
            mesaOponente.remove(cartaAtacada);
            cartaAtacada.setLocalCarta(LocalCarta.DESCARTE);
            cartaAtaca.setValorAtaque(cartaAtaca.getValorAtaque() - vd);
            //SEGUNDA CLÁUSULA DO IF EFEITO CARTA 3
            if (cartaAtacada.getEstadoCarta().equals(EstadoCarta.ATAQUE) && cartaAtacada.getCarta().getId() != 3) {
                deckOponente.setPontosDeterminacao(deckOponente.getPontosDeterminacao() - (va - vd));
            }
            //EFEITO CARTA 4
            if (cartaAtacada.getCarta().getId() == 4) {
                deckOponente.setPontosDeterminacao(deckOponente.getPontosDeterminacao() - 2);
            }
            //FIM EFEITO CARTA 4
            //EFEITO CARTA 19
            if (cartaAtacada.getCarta().getId() == 19) {
                List<CartaJogo> cj = new ArrayList();
                cj.addAll(suaMao);
                cj.addAll(suaMesa);
                CartaJogo maior = new CartaJogo();
                for(CartaJogo c : cj){
                    if(c.getValorAtaque() > maior.getValorAtaque()){
                        maior = c;
                    }
                }
                suaMao.remove(maior);
                suaMesa.remove(maior);
            }
            //FIM EFEITO CARTA 19
        } else if (va == vd) { //AMBAS AS CARTAS SÃO DESTRUIDAS
            mesaOponente.remove(cartaAtacada);
            suaMesa.remove(cartaAtaca);
            cartaAtacada.setLocalCarta(LocalCarta.DESCARTE);
            cartaAtaca.setLocalCarta(LocalCarta.DESCARTE);
            //EFEITO CARTA 4
            if (cartaAtacada.getCarta().getId() == 4) {
                deckOponente.setPontosDeterminacao(deckOponente.getPontosDeterminacao() - 2);
            }
            //FIM EFEITO CARTA 4
            //EFEITO CARTA 19
            if (cartaAtacada.getCarta().getId() == 19) {
                List<CartaJogo> cj = new ArrayList();
                cj.addAll(suaMao);
                cj.addAll(suaMesa);
                CartaJogo maior = new CartaJogo();
                for(CartaJogo c : cj){
                    if(c.getValorAtaque() > maior.getValorAtaque()){
                        maior = c;
                    }
                }
                suaMao.remove(maior);
                suaMesa.remove(maior);
            }
            //FIM EFEITO CARTA 19
        } else { //SUA CARTA É DESTRUIDA
            suaMesa.remove(cartaAtaca);
            cartaAtaca.setLocalCarta(LocalCarta.DESCARTE);
            cartaAtacada.setValorDefesa(vd - va);
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
        for (int i = seuMonte.size() - 1; i >= 0; i--) {
            int num = new Random().nextInt(seuMonte.size());
            novoMonte.add(seuMonte.get(num));
            seuMonte.remove(num);
        }
        seuMonte = novoMonte;
    }

    public String finalizarTurno() {
        for (int i = 0; i < suaMao.size(); i++) {
            suaMao.get(i).setNova(false);
        }
        podeFinalizar = false;
        podeDescer = false;
        podeComprar = true;
        podeAtacar = false;
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
        separaCartas();
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
        for (int i = 0; i < suaMesa.size(); i++) {
            //EFEITO CARTA 1
            if (suaMesa.get(i).getCarta().getId() == 1) {
                if (suaMesa.get(i).isTurno()) {
                    suaMesa.get(i).setTurno(false);
                }
                if (!suaMesa.get(i).isPosicao() && suaMesa.get(i).isAtiva()) {
                    suaMesa.get(i).setPosicao(true);
                    suaMesa.get(i).setEstadoCarta(EstadoCarta.ATAQUE);
                    suaMesa.get(i).setTurno(true);
                    continue;
                }
            }
            //FIM EFEITO CARTA 1
            suaMesa.get(i).setAtiva(true);
        }
        podeFinalizar = true;
        podeDescer = true;
        podeComprar = false;
        podeAtacar = false;
        if (!seuMonte.isEmpty()) {
            if (suaMao.size() < 6) {
                int qt = 3 - suaMao.size();
                if (qt > 0) {
                    if (seuMonte.size() >= qt) {
                        while (qt != 0) {
                            seuMonte.get(0).setLocalCarta(LocalCarta.MAO);
                            seuMonte.get(0).setNova(true);
                            suaMao.add(seuMonte.get(0));
                            seuMonte.remove(0);
                            qt--;
                        }
                    } else {
                        int pd = qt - seuMonte.size();
                        while (seuMonte.size() != 0) {
                            seuMonte.get(0).setLocalCarta(LocalCarta.MAO);
                            seuMonte.get(0).setNova(true);
                            suaMao.add(seuMonte.get(0));
                            seuMonte.remove(0);
                        }
                        seuDeck.setPontosDeterminacao(seuDeck.getPontosDeterminacao() - pd);
                    }
                } else {
                    seuMonte.get(0).setLocalCarta(LocalCarta.MAO);
                    seuMonte.get(0).setNova(true);
                    suaMao.add(seuMonte.get(0));
                    seuMonte.remove(0);
                }
                if (suaMao.size() == 6) {
                    MessageUtil.aviso("Se você não descer uma carta, a próxima que for comprada será descartada.");
                }
            } else {
                seuMonte.get(0).setLocalCarta(LocalCarta.DESCARTE);
                seuDescarte.add(seuMonte.get(0));
                seuMonte.remove(0);
                MessageUtil.aviso("Você não pode ter mais que 6 cartas na mão, logo, sua carta foi direto para o descarte");
            }
        } else {
            seuDeck.setPontosDeterminacao(seuDeck.getPontosDeterminacao() - 1);
            MessageUtil.aviso("Você não possui mais cartas no monte");
        }
//        if (!seuMonte.isEmpty()) {
//            if (suaMao.size() < 3) {
//                int qt = 3 - suaMao.size();
//                if (seuMonte.size() >= qt) {
//                    podeFinalizar = true;
//                    while (qt != 0) {
//                        cartaSelecionada = seuMonte.get(0);
//                        cartaSelecionada.setLocalCarta(LocalCarta.MAO);
//                        suaMao.add(cartaSelecionada);
//                        seuMonte.remove(0);
//                        qt--;
//                    }
//                } else {
//                    podeFinalizar = true;
//                    cartaSelecionada = seuMonte.get(0);
//                    cartaSelecionada.setLocalCarta(LocalCarta.MAO);
//                    suaMao.add(cartaSelecionada);
//                    seuMonte.remove(0);
//                }
//            } else {
//                podeFinalizar = true;
//                cartaSelecionada = seuMonte.get(0);
//                cartaSelecionada.setLocalCarta(LocalCarta.MAO);
//                suaMao.add(cartaSelecionada);
//                seuMonte.remove(0);
//            }
//        } else {
//            podeFinalizar = true;
//            seuDeck.setPontosDeterminacao(seuDeck.getPontosDeterminacao() - 1);
//            MessageUtil.aviso("Você não possui mais cartas no monte");
//        }
        return null;
    }

    public String descer() {
        suaMao.remove(cartaSelecionada);
        cartaSelecionada.setEstadoCarta(EstadoCarta.DEFESA);
        cartaSelecionada.setLocalCarta(LocalCarta.MESA);
        suaMesa.add(cartaSelecionada);
        return null;
    }

    public void alterarPosicaoCarta() {
        if (cartaSelecionada.getEstadoCarta().equals(EstadoCarta.ATAQUE)) {
            cartaSelecionada.setEstadoCarta(EstadoCarta.DEFESA);
            cartaSelecionada.setPosicao(false);
            cartaSelecionada.setAtiva(true);
            podeAtacar = false;
        } else {
            cartaSelecionada.setEstadoCarta(EstadoCarta.ATAQUE);
            cartaSelecionada.setPosicao(true);
            cartaSelecionada.setAtiva(true);
            podeAtacar = false;
        }
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
        podeAtacar = true;
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

    public boolean isPodeDescer() {
        return podeDescer;
    }

    public void setPodeDescer(boolean podeDescer) {
        this.podeDescer = podeDescer;
    }

}
