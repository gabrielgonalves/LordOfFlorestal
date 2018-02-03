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
import br.com.lordofflorestal.model.TipoCarta;
import br.com.lordofflorestal.model.TipoJogador;
import br.com.lordofflorestal.rn.DueloRN;
import br.com.lordofflorestal.rn.EfeitoCartaRN;
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

    private Calendar dataAtual;

    private CartaJogo cartaAtaca;
    private CartaJogo cartaAtacada;
    private boolean podeAtacar;

    private boolean podeFinalizar;
    private boolean podeDescer;
    private boolean podeComprar;

    private boolean especial;
    private boolean especialOponente;

    private String mensagem;

    private boolean som;

    private boolean possuiDefesa;

    public DueloBean() {
        cria();
    }

    public void preRender() {
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String uri;
        if (request.getParameter("duelo") != null) {
            uri = (request.getParameter("duelo"));
            if (!uri.equals(duelo.getUri())) {
                duelo.setSituacaoDuelo(SituacaoDuelo.CANCELADO);
                cria();
            }
        }
    }

    private void cria() {
        possuiDefesa = true;
        som = true;
        especial = false;
        especialOponente = false;
        podeAtacar = false;
        podeDescer = false;
        podeFinalizar = false;
        podeComprar = true;
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
        if (suaMao.isEmpty()) {
            seuMonte.get(0).setLocalCarta(LocalCarta.MAO);
            seuMonte.get(1).setLocalCarta(LocalCarta.MAO);
            seuMonte.get(2).setLocalCarta(LocalCarta.MAO);
            separaCartas();
        }
    }

    public String atacarDeterminacao() {
        duelo.setBatePapo(jogador.getLogin() + " atacou os pontos de determinação de " + oponente.getLogin() + " tirando " + cartaAtaca.getValorAtaque() + " pontos.\n\n" + duelo.getBatePapo());
        int pd = deckOponente.getPontosDeterminacao();
        deckOponente.setPontosDeterminacao(pd - cartaAtaca.getValorAtaque());
        cartaAtaca.setAtiva(false);
        cartaAtaca.setTurno(false);
        cartaAtaca.setValorAtaque(cartaAtaca.getValorAtaque() - pd);
        if (cartaAtaca.getValorAtaque() <= 0) {
            suaMesa.remove(cartaAtaca);
            cartaAtaca.setLocalCarta(LocalCarta.DESCARTE);
            seuDescarte.add(cartaAtaca);
        }
        podeAtacar = false;
        verificaPontosDeterminacao();
        if (duelo.getSituacaoDuelo().equals(SituacaoDuelo.FINALIZADO)) {
            new DueloRN().salvar(duelo, ganhador.getMatricula());
            atualizaEstatistica();
        }
        return null;
    }

    private void atualizaEstatistica() {
        if (ganhador.equals(oponente)) {
            oponente.getEstatisticaJogador().setNumJogos(oponente.getEstatisticaJogador().getNumJogos() + 1);
            if (jogador.getTipoJogador().equals(TipoJogador.LORD)) {
                oponente.getEstatisticaJogador().setNumJogosGanhoLord(oponente.getEstatisticaJogador().getNumJogosGanhoLord() + 1);
            } else {
                oponente.getEstatisticaJogador().setNumJogosGanho(oponente.getEstatisticaJogador().getNumJogosGanho()+ 1);
            }
            jogador.getEstatisticaJogador().setNumJogos(jogador.getEstatisticaJogador().getNumJogos() + 1);
            jogador.getEstatisticaJogador().setNumJogosPerdido(jogador.getEstatisticaJogador().getNumJogosPerdido() + 1);

            new JogadorRN().atualizarPontos(jogador);
            new JogadorRN().atualizarPontos(oponente);
        } else {
            jogador.getEstatisticaJogador().setNumJogos(jogador.getEstatisticaJogador().getNumJogos() + 1);
            if (oponente.getTipoJogador().equals(TipoJogador.LORD)) {
                jogador.getEstatisticaJogador().setNumJogosGanhoLord(jogador.getEstatisticaJogador().getNumJogosGanhoLord() + 1);
            } else {
                jogador.getEstatisticaJogador().setNumJogosGanho(jogador.getEstatisticaJogador().getNumJogosGanho()+ 1);
            }
            oponente.getEstatisticaJogador().setNumJogos(oponente.getEstatisticaJogador().getNumJogos() + 1);
            oponente.getEstatisticaJogador().setNumJogosPerdido(oponente.getEstatisticaJogador().getNumJogosPerdido() + 1);

            new JogadorRN().atualizarPontos(jogador);
            new JogadorRN().atualizarPontos(oponente);
        }
        duelo.setBatePapo(ganhador.getLogin() + " foi o ganhador deste duelo. \n\n" + duelo.getBatePapo());
    }

    private void colocaMesa() {
        suaMao.remove(cartaSelecionada);
        cartaSelecionada.setLocalCarta(LocalCarta.MESA);
        suaMesa.add(cartaSelecionada);
        duelo.setBatePapo(jogador.getLogin() + " desceu a carta especial " + cartaSelecionada.getCarta().getNome() + "\n\n" + duelo.getBatePapo());
    }

    private void colocaDescarte() {
        suaMao.remove(cartaSelecionada);
        cartaSelecionada.setLocalCarta(LocalCarta.DESCARTE);
        seuDescarte.add(cartaSelecionada);
        duelo.setBatePapo(jogador.getLogin() + " desceu a carta especial " + cartaSelecionada.getCarta().getNome() + "\n\n" + duelo.getBatePapo());
    }

    public String selecionar() {
        switch (cartaSelecionada.getCarta().getId()) {
            case 50:
                EfeitoCartaRN.carta50(cartaAtacada);
                atualizaMesaOponente();
                colocaDescarte();
                duelo.setBatePapo(jogador.getLogin() + " aplicou efeito da carta " + cartaSelecionada.getCarta().getNome() + " em " + cartaAtacada.getCarta().getNome() + "\n\n" + duelo.getBatePapo());
                break;
            case 52:
                EfeitoCartaRN.carta52(cartaAtaca);
                colocaDescarte();
                duelo.setBatePapo(jogador.getLogin() + " aplicou efeito da carta " + cartaSelecionada.getCarta().getNome() + " em " + cartaAtaca.getCarta().getNome() + "\n\n" + duelo.getBatePapo());
                break;
            case 53:
                EfeitoCartaRN.carta53(cartaSelecionada, cartaAtaca);
                colocaMesa();
                duelo.setBatePapo(jogador.getLogin() + " aplicou efeito da carta " + cartaSelecionada.getCarta().getNome() + " em " + cartaAtaca.getCarta().getNome() + "\n\n" + duelo.getBatePapo());
                break;
            case 54:
                EfeitoCartaRN.carta54(cartaAtaca);
                colocaDescarte();
                duelo.setBatePapo(jogador.getLogin() + " aplicou efeito da carta " + cartaSelecionada.getCarta().getNome() + " em " + cartaAtaca.getCarta().getNome() + "\n\n" + duelo.getBatePapo());
                break;
            case 60:
                EfeitoCartaRN.carta60(seuDeck, cartaAtacada);
                atualizaMesaOponente();
                colocaDescarte();
                duelo.setBatePapo(jogador.getLogin() + " aplicou efeito da carta " + cartaSelecionada.getCarta().getNome() + " em " + cartaAtacada.getCarta().getNome() + "\n\n" + duelo.getBatePapo());
                break;
            case 64:
                EfeitoCartaRN.carta64(cartaAtaca);
                colocaDescarte();
                duelo.setBatePapo(jogador.getLogin() + " aplicou efeito da carta " + cartaSelecionada.getCarta().getNome() + " em " + cartaAtaca.getCarta().getNome() + "\n\n" + duelo.getBatePapo());
                break;
            case 69:
                EfeitoCartaRN.carta69(cartaAtacada);
                colocaDescarte();
                duelo.setBatePapo(jogador.getLogin() + " aplicou efeito da carta " + cartaSelecionada.getCarta().getNome() + " em " + cartaAtacada.getCarta().getNome() + "\n\n" + duelo.getBatePapo());
                break;
        }
        especial = false;
        especialOponente = false;
        return null;
    }

    public String atacar() {
        cartaAtaca.setAtiva(false);
        cartaAtaca.setTurno(false);
        int va = cartaAtaca.getValorAtaque();
        int vd = cartaAtacada.getValorDefesa();
        duelo.setBatePapo(jogador.getLogin() + " atacou " + cartaAtacada.getCarta().getNome() + " com a carta " + cartaAtaca.getCarta().getNome() + "\n\n" + duelo.getBatePapo());
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
                EfeitoCartaRN.carta4(seuDeck);
            }
            //EFEITO CARTA 19
            if (cartaAtacada.getCarta().getId() == 19) {
                EfeitoCartaRN.carta19(seuDeck);
            }
        } else if (va == vd) { //AMBAS AS CARTAS SÃO DESTRUIDAS
            mesaOponente.remove(cartaAtacada);
            suaMesa.remove(cartaAtaca);
            cartaAtacada.setLocalCarta(LocalCarta.DESCARTE);
            cartaAtaca.setLocalCarta(LocalCarta.DESCARTE);
            //EFEITO CARTA 4
            if (cartaAtacada.getCarta().getId() == 4) {
                EfeitoCartaRN.carta4(seuDeck);
            }
            if (cartaAtaca.getCarta().getId() == 4) {
                EfeitoCartaRN.carta4(deckOponente);
            }
            //EFEITO CARTA 19
            if (cartaAtacada.getCarta().getId() == 19) {
                EfeitoCartaRN.carta19(seuDeck);
            }
            if (cartaAtaca.getCarta().getId() == 19) {
                EfeitoCartaRN.carta19(deckOponente);
            }
        } else { //SUA CARTA É DESTRUIDA
            suaMesa.remove(cartaAtaca);
            cartaAtaca.setLocalCarta(LocalCarta.DESCARTE);
            cartaAtacada.setValorDefesa(vd - va);
            //EFEITO CARTA 4
            if (cartaAtaca.getCarta().getId() == 4) {
                EfeitoCartaRN.carta4(deckOponente);
            }
            //EFEITO CARTA 19
            if (cartaAtaca.getCarta().getId() == 19) {
                EfeitoCartaRN.carta19(deckOponente);
            }
        }

        if (cartaAtaca.getValorAtaque() == 0 || cartaAtaca.getValorDefesa() == 0) {
            suaMesa.remove(cartaAtaca);
            cartaAtaca.setLocalCarta(LocalCarta.DESCARTE);
        }

        if (cartaAtacada.getValorAtaque() == 0 || cartaAtacada.getValorDefesa() == 0) {
            mesaOponente.remove(cartaAtacada);
            cartaAtacada.setLocalCarta(LocalCarta.DESCARTE);
        }

        podeAtacar = false;
        verificaPontosDeterminacao();
        if (duelo.getSituacaoDuelo().equals(SituacaoDuelo.FINALIZADO)) {
            new DueloRN().salvar(duelo, ganhador.getMatricula());
            atualizaEstatistica();
        }
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
        seuDeck.setCartas(novoMonte);
    }

    public String finalizarTurno() {
        duelo.setBatePapo(jogador.getLogin() + " finalizou o turno " + "\n\n" + duelo.getBatePapo());
        som = true;
        for (int i = 0; i < suaMao.size(); i++) {
            suaMao.get(i).setNova(false);
        }
        seuDeck.setPodeUsarEspecial(true);
        seuDeck.setPodeAtacar(true);
        podeFinalizar = false;
        podeDescer = false;
        podeComprar = true;
        podeAtacar = false;
        especial = false;
        especialOponente = false;
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

    public void atualizaMesaOponente() {
        boolean atk = false;
        mesaOponente = new ArrayList();
        for (CartaJogo carta : deckOponente.getCartas()) {
            switch (carta.getLocalCarta()) {
                case MESA:
                    if (carta.getEstadoCarta().equals(EstadoCarta.DEFESA)) {
                        atk = true;
                    }
                    mesaOponente.add(carta);
                    break;
            }
        }
        if (atk) {
            possuiDefesa = true;
        } else {
            possuiDefesa = false;
        }
    }

    public String ativaPodeAtacar() {
        podeAtacar = true;
        return null;
    }

    public void atualizarDados() {
        dataAtual = Calendar.getInstance();
        if (duelo.getDataCriacao().getTimeInMillis() <= dataAtual.getTimeInMillis() && duelo.getSituacaoDuelo().equals(SituacaoDuelo.CRIADO)) {
            duelo.setSituacaoDuelo(SituacaoDuelo.CANCELADO);
            new DueloRN().salvar(duelo, 0);
        }
        if (duelo.getCriadoPor().equals(jogador)) {
            seuDeck = duelo.getDeckJogador1();
            deckOponente = duelo.getDeckJogador2();
            oponente = duelo.getOponente();
        } else {
            seuDeck = duelo.getDeckJogador2();
            deckOponente = duelo.getDeckJogador1();
            oponente = duelo.getCriadoPor();
        }
        verificaPontosDeterminacao();
        separaCartas();
        atualizaMesaOponente();
    }

    public String comprar() {
        for (int i = 0; i < suaMesa.size(); i++) {
            if (suaMesa.get(i).getCarta().getId() == 1) {
                if (EfeitoCartaRN.carta1(suaMesa.get(i))) {
                    continue;
                }
            }
            suaMesa.get(i).setAtiva(true);
        }
        podeFinalizar = true;
        podeDescer = true;
        podeComprar = false;
        podeAtacar = false;
        if (!seuMonte.isEmpty()) {
            duelo.setBatePapo(jogador.getLogin() + " comprou\n\n" + duelo.getBatePapo());
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
            duelo.setBatePapo(jogador.getLogin() + " não tem mais cartas para comprar\n\n" + duelo.getBatePapo());
            seuDeck.setPontosDeterminacao(seuDeck.getPontosDeterminacao() - 1);
            MessageUtil.aviso("Você não possui mais cartas no monte");
        }
        return null;
    }

    public String descer() {
        if (!cartaSelecionada.getCarta().getTipoCarta().equals(TipoCarta.ESPECIAL)) {
            suaMao.remove(cartaSelecionada);
            cartaSelecionada.setEstadoCarta(EstadoCarta.DEFESA);
            cartaSelecionada.setLocalCarta(LocalCarta.MESA);
            suaMesa.add(cartaSelecionada);
            duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaSelecionada.getCarta().getNome() + "\n\n" + duelo.getBatePapo());
        } else {
            //EFEITOS CARTAS ESPECIAIS
            switch (cartaSelecionada.getCarta().getId()) {
                case 46:
                    EfeitoCartaRN.carta46(deckOponente);
                    colocaDescarte();
                    atualizaMesaOponente();
                    break;
                case 47:
                    MessageUtil.aviso("Seu oponente não poderá utilizar carta especial no próximo turno.");
                    colocaDescarte();
                    EfeitoCartaRN.carta47(seuDeck, deckOponente);
                    atualizaMesaOponente();
                    separaCartas();
                    break;
                case 48:
                    EfeitoCartaRN.carta48(seuDeck);
                    colocaDescarte();
                    break;
                case 49:
                    EfeitoCartaRN.carta49(seuDeck, deckOponente);
                    colocaDescarte();
                    break;
                case 50:
                    MessageUtil.aviso("Selecione uma carta da mesa do seu oponente");
                    especialOponente = true;
                    break;
                case 51:
                    EfeitoCartaRN.carta51(deckOponente);
                    colocaDescarte();
                    break;
                case 52:
                    MessageUtil.aviso("Selecione uma carta da sua mesa");
                    especial = true;
                    break;
                case 53:
                    MessageUtil.aviso("Selecione uma carta da sua mesa");
                    especial = true;
                    break;
                case 54:
                    MessageUtil.aviso("Selecione uma carta da sua mesa");
                    especial = true;
                    break;
                case 55:
                    break;
                case 56:
                    EfeitoCartaRN.carta56(seuDeck);
                    colocaDescarte();
                    break;
                case 60:
                    MessageUtil.aviso("Selecione uma carta da mesa do seu oponente");
                    especialOponente = true;
                    break;
                case 61:
                    EfeitoCartaRN.carta61(seuDeck, deckOponente);
                    colocaDescarte();
                    break;
                case 64:
                    MessageUtil.aviso("Selecione uma carta da sua mesa");
                    especial = true;
                    break;
                case 65:
                    MessageUtil.aviso("Seu oponente não poderá atacar no próximo turno");
                    EfeitoCartaRN.carta65(deckOponente);
                    colocaDescarte();
                    break;
                case 69:
                    MessageUtil.aviso("Selecione uma carta da mesa do seu oponente");
                    especialOponente = true;
                    break;
            }
        }
        return null;
    }

    public void alterarPosicaoCarta() {
        if (cartaSelecionada.getEstadoCarta().equals(EstadoCarta.ATAQUE)) {
            duelo.setBatePapo(jogador.getLogin() + " alterou a posição da carta " + cartaSelecionada.getCarta().getNome() + " para modo de defesa " + "\n\n" + duelo.getBatePapo());
            cartaSelecionada.setEstadoCarta(EstadoCarta.DEFESA);
            cartaSelecionada.setPosicao(false);
            cartaSelecionada.setAtiva(true);
            podeAtacar = false;
        } else {
            duelo.setBatePapo(jogador.getLogin() + " alterou a posição da carta " + cartaSelecionada.getCarta().getNome() + " para modo de ataque " + "\n\n" + duelo.getBatePapo());
            cartaSelecionada.setEstadoCarta(EstadoCarta.ATAQUE);
            cartaSelecionada.setPosicao(true);
            cartaSelecionada.setAtiva(true);
            podeAtacar = false;
        }
    }

    public String enviarMensagem() {
        if (!mensagem.isEmpty()) {
            duelo.setBatePapo(jogador.getLogin() + ": " + mensagem + "\n\n" + duelo.getBatePapo());
        }
        mensagem = "";
        return null;
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

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public boolean isEspecialOponente() {
        return especialOponente;
    }

    public void setEspecialOponente(boolean especialOponente) {
        this.especialOponente = especialOponente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isSom() {
        return som;
    }

    public void setSom(boolean som) {
        this.som = som;
    }

    public boolean isPossuiDefesa() {
        return possuiDefesa;
    }

    public void setPossuiDefesa(boolean possuiDefesa) {
        this.possuiDefesa = possuiDefesa;
    }

}
