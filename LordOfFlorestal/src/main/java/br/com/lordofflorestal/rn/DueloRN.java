/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

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
import br.com.lordofflorestal.mysql.DueloDAOMysql;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author gabriel
 */
public class DueloRN {

    private DueloDAOMysql dueloDAOMysql;

    private List<CartaJogo> descarte;
    private List<CartaJogo> mao;
    private List<CartaJogo> mesa;
    private List<CartaJogo> mesaOponente;
    private List<CartaJogo> monte;

    private Duelo duelo;

    private Jogador jogador;
    private Jogador oponente;
    private Jogador ganhador;

    private Deck deck;
    private Deck deckOponente;

    private boolean podeAtacar; //Se clicou em alguma carta para atacar
    private boolean podeAtacarDeterminacao; //Se pode atacar determinação
    private boolean podeComprar; //Se pode comprar
    private boolean podeDescer; //Se pode descer
    private boolean podeFinalizar; //Se pode finalizar
    private boolean som; //Se som deve ou não tocar
    private boolean especialMesa; //Se a carta especial afeta sua mesa
    private boolean especialMesaOponete; //Se a carta especial afeta mesa do oponente

    public DueloRN(String uri, String login) {
        this.dueloDAOMysql = new DueloDAOMysql();
        this.descarte = new ArrayList();
        this.mao = new ArrayList();
        this.mesa = new ArrayList();
        this.mesaOponente = new ArrayList();
        this.monte = new ArrayList();
        this.duelo = DueloSingleton.getInstance().buscaPorURI(uri);
        inicializaDuelo(login);
    }

    public void alterarEstadoCarta(CartaJogo carta) {
        if (carta.getEstadoCarta().equals(EstadoCarta.ATAQUE)) {
            duelo.setBatePapo(jogador.getLogin() + " alterou a posição da carta " + carta.getCarta().getNome() + " para modo de defesa " + "\n\n" + duelo.getBatePapo());
            carta.setEstadoCarta(EstadoCarta.DEFESA);
        } else {
            duelo.setBatePapo(jogador.getLogin() + " alterou a posição da carta " + carta.getCarta().getNome() + " para modo de ataque " + "\n\n" + duelo.getBatePapo());
            carta.setEstadoCarta(EstadoCarta.ATAQUE);
        }
    }

    public boolean atacar(List<CartaJogo> cartaAtaca, CartaJogo cartaAtacada) {
        int va = 0;
        String nomeCartas = "";
        for (int i = 0; i < cartaAtaca.size(); i++) {
            nomeCartas += cartaAtaca.get(i).getCarta().getNome() + ", ";
            cartaAtaca.get(i).setAtiva(false);
            if (cartaAtaca.get(i).getEspecial() != 70) {
                va += cartaAtaca.get(i).getValorAtaque();
            }
        }
        int vd = cartaAtacada.getValorDefesa();
        if (va > vd) {
            mesaOponente.remove(cartaAtacada);
            colocaCartaDescarte(cartaAtacada);
            if (cartaAtacada.getEstadoCarta().equals(EstadoCarta.DEFESA)) {
                duelo.setBatePapo(jogador.getLogin() + " atacou a carta " + cartaAtacada.getCarta().getNome() + " do jogador " + oponente.getLogin() + " com a(s) carta(s) " + nomeCartas + " destruindo-a\n\n" + duelo.getBatePapo());
            }
            if (cartaAtacada.getEstadoCarta().equals(EstadoCarta.ATAQUE) && cartaAtacada.getCarta().getId() != 3) {
                duelo.setBatePapo("A carta " + cartaAtacada.getCarta().getNome() + " do jogador " + oponente.getLogin() + " estava em modo de ataque, por isso, ele perdeu " + (va - vd) + " pontos de determinação\n\n" + duelo.getBatePapo());
                diminuiPontosDeterminacao(deckOponente, va - vd);
            }
            //EFEITO CARTA 4
            if (cartaAtacada.getCarta().getId() == 4) {
                duelo.setBatePapo("A carta " + cartaAtacada.getCarta().getNome() + " do jogador " + oponente.getLogin() + " foi destruida. Com isso o jogador " + jogador.getLogin() + " perdeu 2 pontos de determinação\n\n" + duelo.getBatePapo());
                EfeitoCartaRN.carta4(deck);
            }
            //EFEITO CARTA 19
            if (cartaAtacada.getCarta().getId() == 19) {
                CartaJogo retorno = EfeitoCartaRN.carta19(deck);
                duelo.setBatePapo("A carta " + cartaAtacada.getCarta().getNome() + " do jogador " + oponente.getLogin() + " foi destruida. Com isso a carta " + retorno.getCarta().getNome() + " do jogador" + jogador.getLogin() + " foi destruída\n\n" + duelo.getBatePapo());
            }
        } else if (va == vd) {
            for (int i = 0; i < cartaAtaca.size(); i++) {
                mesa.remove(cartaAtaca.get(i));
                colocaCartaDescarte(cartaAtaca.get(i));
            }
            mesaOponente.remove(cartaAtacada);
            colocaCartaDescarte(cartaAtacada);
            if (cartaAtacada.getEstadoCarta().equals(EstadoCarta.DEFESA)) {
                duelo.setBatePapo(jogador.getLogin() + " atacou a carta " + cartaAtacada.getCarta().getNome() + " do jogador " + oponente.getLogin() + " com a(s) carta(s) " + nomeCartas + " e todas foram destruídas\n\n" + duelo.getBatePapo());
            }
            //EFEITO CARTA 4
            if (cartaAtacada.getCarta().getId() == 4) {
                duelo.setBatePapo("A carta " + cartaAtacada.getCarta().getNome() + " do jogador " + oponente.getLogin() + " foi destruida. Com isso o jogador " + jogador.getLogin() + " perdeu 2 pontos de determinação\n\n" + duelo.getBatePapo());
                EfeitoCartaRN.carta4(deck);
            }
            for (int i = 0; i < cartaAtaca.size(); i++) {
                if (cartaAtaca.get(i).getCarta().getId() == 4) {
                    duelo.setBatePapo("A carta " + cartaAtaca.get(i).getCarta().getNome() + " do jogador " + jogador.getLogin() + " foi destruida. Com isso o jogador " + oponente.getLogin() + " perdeu 2 pontos de determinação\n\n" + duelo.getBatePapo());
                    EfeitoCartaRN.carta4(deckOponente);
                }
            }
            //EFEITO CARTA 19
            if (cartaAtacada.getCarta().getId() == 19) {
                CartaJogo retorno = EfeitoCartaRN.carta19(deck);
                duelo.setBatePapo("A carta " + cartaAtacada.getCarta().getNome() + " do jogador " + oponente.getLogin() + " foi destruida. Com isso a carta " + retorno.getCarta().getNome() + " do jogador" + jogador.getLogin() + " foi destruída\n\n" + duelo.getBatePapo());
            }
            for (int i = 0; i < cartaAtaca.size(); i++) {
                if (cartaAtaca.get(i).getCarta().getId() == 4) {
                    if (cartaAtaca.get(i).getCarta().getId() == 19) {
                        CartaJogo retorno = EfeitoCartaRN.carta19(deckOponente);
                        duelo.setBatePapo("A carta " + cartaAtaca.get(i).getCarta().getNome() + " do jogador " + jogador.getLogin() + " foi destruida. Com isso a carta " + retorno.getCarta().getNome() + " do jogador" + oponente.getLogin() + " foi destruída\n\n" + duelo.getBatePapo());
                    }
                }
            }
        } else {
            if (cartaAtacada.getEstadoCarta().equals(EstadoCarta.DEFESA)) {
                duelo.setBatePapo(jogador.getLogin() + " atacou a carta " + cartaAtacada.getCarta().getNome() + " do jogador " + oponente.getLogin() + " com a(s) carta(s) " + nomeCartas + " e todas as suas cartas foram destruidas\n\n" + duelo.getBatePapo());
            }
            for (int i = 0; i < cartaAtaca.size(); i++) {
                mesa.remove(cartaAtaca.get(i));
                colocaCartaDescarte(cartaAtaca.get(i));

                //EFEITO CARTA 4
                if (cartaAtaca.get(i).getCarta().getId() == 4) {
                    duelo.setBatePapo("A carta " + cartaAtaca.get(i).getCarta().getNome() + " do jogador " + jogador.getLogin() + " foi destruida. Com isso o jogador " + oponente.getLogin() + " perdeu 2 pontos de determinação\n\n" + duelo.getBatePapo());
                    EfeitoCartaRN.carta4(deckOponente);
                }
                //EFEITO CARTA 19
                if (cartaAtaca.get(i).getCarta().getId() == 19) {
                    CartaJogo retorno = EfeitoCartaRN.carta19(deckOponente);
                    duelo.setBatePapo("A carta " + cartaAtaca.get(i).getCarta().getNome() + " do jogador " + jogador.getLogin() + " foi destruida. Com isso a carta " + retorno.getCarta().getNome() + " do jogador" + oponente.getLogin() + " foi destruída\n\n" + duelo.getBatePapo());
                }
            }
        }

        podeAtacar = false;

        if (duelo.getSituacaoDuelo().equals(SituacaoDuelo.FINALIZADO)) {
            salvar(duelo, ganhador.getMatricula());
            atualizaEstatistica();
        }
        
        return verificaPontosDeterminacao();
    }

    public boolean atacarDeterminacao(List<CartaJogo> cartaAtaca) {
        String nomeCartas = "";
        int pontosDeterminacao = deckOponente.getPontosDeterminacao();
        int ataque = 0;
        for (int i = 0; i < cartaAtaca.size(); i++) {
            nomeCartas += cartaAtaca.get(i).getCarta().getNome() + ", ";
            if (cartaAtaca.get(i).getEspecial() != 70) {
                ataque += cartaAtaca.get(i).getValorAtaque();
            }
            cartaAtaca.get(i).setAtiva(false);
        }
        duelo.setBatePapo(jogador.getLogin() + " atacou os pontos de determinação de " + oponente.getLogin() + " com a(s) carta(s) " + nomeCartas + " tirando " + ataque + " ponto(s)\n\n" + duelo.getBatePapo());
        diminuiPontosDeterminacao(deckOponente, ataque);

        podeAtacar = false;

        if (duelo.getSituacaoDuelo().equals(SituacaoDuelo.FINALIZADO)) {
            salvar(duelo, ganhador.getMatricula());
            atualizaEstatistica();
        }
        
        return verificaPontosDeterminacao();
    }

    public void atualizaEstatistica() {
        if (ganhador.equals(oponente)) {
            oponente.getEstatisticaJogador().setNumJogos(oponente.getEstatisticaJogador().getNumJogos() + 1);
            if (jogador.getTipoJogador().equals(TipoJogador.LORD)) {
                oponente.getEstatisticaJogador().setNumJogosGanhoLord(oponente.getEstatisticaJogador().getNumJogosGanhoLord() + 1);
            } else {
                oponente.getEstatisticaJogador().setNumJogosGanho(oponente.getEstatisticaJogador().getNumJogosGanho() + 1);
            }
            if (oponente.getTipoJogador().equals(TipoJogador.LORD)) {
                jogador.getEstatisticaJogador().setNumJogosPerdeuLord(jogador.getEstatisticaJogador().getNumJogosPerdeuLord() + 1);
            } else {
                jogador.getEstatisticaJogador().setNumJogosPerdido(jogador.getEstatisticaJogador().getNumJogosPerdido() + 1);
            }
            jogador.getEstatisticaJogador().setNumJogos(jogador.getEstatisticaJogador().getNumJogos() + 1);

            new JogadorRN().atualizarPontos(jogador);
            new JogadorRN().atualizarPontos(oponente);
        } else {
            jogador.getEstatisticaJogador().setNumJogos(jogador.getEstatisticaJogador().getNumJogos() + 1);
            if (oponente.getTipoJogador().equals(TipoJogador.LORD)) {
                jogador.getEstatisticaJogador().setNumJogosGanhoLord(jogador.getEstatisticaJogador().getNumJogosGanhoLord() + 1);
            } else {
                jogador.getEstatisticaJogador().setNumJogosGanho(jogador.getEstatisticaJogador().getNumJogosGanho() + 1);
            }
            if (jogador.getTipoJogador().equals(TipoJogador.LORD)) {
                oponente.getEstatisticaJogador().setNumJogosPerdeuLord(oponente.getEstatisticaJogador().getNumJogosPerdeuLord() + 1);
            } else {
                oponente.getEstatisticaJogador().setNumJogosPerdido(oponente.getEstatisticaJogador().getNumJogosPerdido() + 1);
            }
            oponente.getEstatisticaJogador().setNumJogos(oponente.getEstatisticaJogador().getNumJogos() + 1);

            new JogadorRN().atualizarPontos(jogador);
            new JogadorRN().atualizarPontos(oponente);
        }
        duelo.setBatePapo(ganhador.getLogin() + " foi o ganhador deste duelo. \n\n" + duelo.getBatePapo());
    }

    public void atualizarDados(String login) {
        if (duelo != null) {
            if (duelo.getCriadoPor().getLogin().equals(login)) {
                deck = duelo.getDeckJogador1();
                jogador = duelo.getCriadoPor();
                deckOponente = duelo.getDeckJogador2();
                if (duelo.getOponente() != null) {
                    oponente = duelo.getOponente();
                }
            } else if (duelo.getOponente() != null) {
                if (duelo.getOponente().getLogin().equals(login)) {
                    deck = duelo.getDeckJogador2();
                    deckOponente = duelo.getDeckJogador1();
                    jogador = duelo.getOponente();
                    oponente = duelo.getCriadoPor();
                }
            }
            verificaPontosDeterminacao();
        }
        separaCartas();
    }

    public boolean caraCoroa(CartaJogo carta, boolean caracoroa) {
        mao.remove(carta);
        colocaCartaDescarte(carta);

        boolean caracoroaResultado;

        int num = new Random().nextInt();
        if (num % 2 == 0) {
            caracoroaResultado = true; //cara
        } else {
            caracoroaResultado = false; //coroa
        }
        if (caracoroa = caracoroaResultado) {
            EfeitoCartaRN.carta55(deck, deckOponente, true);
            FacesContext.getCurrentInstance().addMessage("caracoroamsg", new FacesMessage("Você ganhou", ""));
            duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + carta.getCarta().getNome() + " e ganhou no Cara ou Coroa. Com isso, as cartas da mesa do jogador " + oponente.getLogin() + " foram descartas\n\n" + duelo.getBatePapo());
        } else {
            EfeitoCartaRN.carta55(deck, deckOponente, false);
            FacesContext.getCurrentInstance().addMessage("caracoroamsg", new FacesMessage("Você perdeu", ""));
            duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + carta.getCarta().getNome() + " e perdeu no Cara ou Coroa. Com isso, suas cartas da mesa foram descartas\n\n" + duelo.getBatePapo());
        }
        separaCartas();
        separaCartasOponente();

        return caracoroaResultado;
    }

    public void comprar() {
        podeAtacar = false;
        podeComprar = false;
        podeDescer = true;
        podeFinalizar = true;

        CartaJogo carta;
        //Ativa todas as cartas que foram decidas no turno anterior
        for (int i = 0; i < mesa.size(); i++) {
            mesa.get(i).setAtiva(true);
        }
        int qtMao = 3 - mao.size();
        int qtMonte = monte.size();
        //Verifica quantas cartas devem ser compradas
        if (qtMao > 1) {
            //Verifica se possui quantidade de cartas suficiente no monte
            if (qtMonte >= qtMao) {
                duelo.setBatePapo(jogador.getLogin() + " comprou\n\n" + duelo.getBatePapo());
                while (qtMao != 0) {
                    carta = monte.get(0);
                    monte.remove(carta);
                    colocaCartaMao(carta);
                    qtMao--;
                }
            } else {
                duelo.setBatePapo(jogador.getLogin() + " comprou e perdeu 1 Ponto de Determinação\n\n" + duelo.getBatePapo());
                while (qtMonte != 0) {
                    carta = monte.get(0);
                    monte.remove(carta);
                    colocaCartaMao(carta);
                    qtMonte--;
                }
                diminuiPontosDeterminacao(deck, 1);
            }
        } else {
            //Verifica se possui carta no monte
            if (qtMonte != 0) {
                if (mao.size() != 6) {
                    duelo.setBatePapo(jogador.getLogin() + " comprou\n\n" + duelo.getBatePapo());
                    carta = monte.get(0);
                    monte.remove(carta);
                    colocaCartaMao(carta);
                    if (mao.size() == 6) {
                        MessageUtil.aviso("Se você não descer nenhuma carta, a próxima comprada irá direto para o descarte");
                    }
                } else {
                    duelo.setBatePapo(jogador.getLogin() + " comprou e a carta foi descartada\n\n" + duelo.getBatePapo());
                    carta = monte.get(0);
                    monte.remove(carta);
                    colocaCartaDescarte(carta);
                }
            } else {
                duelo.setBatePapo(jogador.getLogin() + " não tem mais cartas no monte para comprar, por isso, perdeu 1 Ponto de Determinação\n\n" + duelo.getBatePapo());
                diminuiPontosDeterminacao(deck, 1);
            }
        }
    }

    public void colocaCartaDescarte(CartaJogo carta) {
        carta.setLocalCarta(LocalCarta.DESCARTE);
        descarte.add(carta);
    }

    public void colocaCartaMao(CartaJogo carta) {
        carta.setLocalCarta(LocalCarta.MAO);
        mao.add(carta);
    }

    public void colocaCartaMesa(CartaJogo carta) {
        carta.setLocalCarta(LocalCarta.MESA);
        carta.setEstadoCarta(EstadoCarta.DEFESA);
        mesa.add(carta);
    }

    public void descer(CartaJogo carta) {
        //Verifica se a carta é do tipo especial
        if (!carta.getCarta().getTipoCarta().equals(TipoCarta.ESPECIAL)) {
            mao.remove(carta);
            colocaCartaMesa(carta);
            duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + carta.getCarta().getNome() + "\n\n" + duelo.getBatePapo());
        } else {
            //EFEITOS CARTAS ESPECIAIS
            switch (carta.getCarta().getId()) {
                case 46:
                    CartaJogo retorno = EfeitoCartaRN.carta46(deckOponente);
                    if (retorno != null) {
                        duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + carta.getCarta().getNome() + " e destruiu a carta " + retorno.getCarta().getNome() + " do jogador " + oponente.getLogin() + "\n\n" + duelo.getBatePapo());
                    } else {
                        duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + carta.getCarta().getNome() + " porém, não destruiu nenhuma carta do oponente\n\n" + duelo.getBatePapo());
                    }
                    colocaCartaDescarte(carta);
                    break;
                case 47:
                    colocaCartaDescarte(carta);
                    EfeitoCartaRN.carta47(deck, deckOponente);
                    duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + carta.getCarta().getNome() + " e todas as cartas da mão foram jogadas na mesa\n\n" + duelo.getBatePapo());
                    break;
                case 48:
                    EfeitoCartaRN.carta48(deck);
                    duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + carta.getCarta().getNome() + " e todas as cartas do tipo Aluno receberam +1/+1\n\n" + duelo.getBatePapo());
                    colocaCartaDescarte(carta);
                    break;
                case 49:
                    EfeitoCartaRN.carta49(deck, deckOponente);
                    duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + carta.getCarta().getNome() + " e todas as cartas do tipo Desafio tiveram seus valores de ataque e defesa invertidos\n\n" + duelo.getBatePapo());
                    colocaCartaDescarte(carta);
                    break;
                case 50:
                    MessageUtil.aviso("Selecione uma carta da mesa do seu oponente");
                    especialMesaOponete = true;
                    break;
                case 51:
                    MessageUtil.aviso("Seu oponente não poderá utilizar carta especial no próximo turno.");
                    EfeitoCartaRN.carta51(deckOponente);
                    duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + carta.getCarta().getNome() + " com isso, o jogador " + oponente.getLogin() + " não poderá usar nenhuma carta do tipo Especial no próximo turno\n\n" + duelo.getBatePapo());
                    colocaCartaDescarte(carta);
                    break;
                case 52:
                    MessageUtil.aviso("Selecione uma carta da sua mesa");
                    especialMesa = true;
                    break;
                case 53:
                    MessageUtil.aviso("Selecione uma carta da sua mesa");
                    especialMesa = true;
                    break;
                case 54:
                    MessageUtil.aviso("Selecione uma carta da sua mesa");
                    especialMesa = true;
                    break;
                case 55:
                    break;
                case 56:
                    EfeitoCartaRN.carta56(deck);
                    duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + carta.getCarta().getNome() + " e ganhou 2 pontos de determinação\n\n" + duelo.getBatePapo());
                    colocaCartaDescarte(carta);
                    break;
                case 57:
                    MessageUtil.aviso("Selecione uma carta da mesa do seu oponente");
                    especialMesa = true;
                    break;
                case 60:
                    MessageUtil.aviso("Selecione uma carta da mesa do seu oponente");
                    especialMesaOponete = true;
                    break;
                case 61:
                    EfeitoCartaRN.carta61(deck, deckOponente);
                    duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + carta.getCarta().getNome() + " e todas as cartas do tipo Aluno foram descartadas\n\n" + duelo.getBatePapo());
                    colocaCartaDescarte(carta);
                    break;
                case 62:
                    break;
                case 63:
                    MessageUtil.aviso("Selecione uma carta da mesa do seu oponente");
                    especialMesa = true;
                    break;
                case 64:
                    MessageUtil.aviso("Selecione uma carta da sua mesa");
                    especialMesa = true;
                    break;
                case 65:
                    MessageUtil.aviso("Seu oponente não poderá atacar no próximo turno");
                    EfeitoCartaRN.carta65(deckOponente);
                    duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + carta.getCarta().getNome() + " com isso, o jogador " + oponente.getLogin() + " não poderá realizar nenhum tipo de ataque no próximo turno\n\n" + duelo.getBatePapo());
                    colocaCartaDescarte(carta);
                    break;
                case 66:
                    MessageUtil.aviso("Selecione uma carta da mesa do seu oponente");
                    especialMesaOponete = true;
                    break;
                case 69:
                    MessageUtil.aviso("Selecione uma carta da mesa do seu oponente");
                    especialMesaOponete = true;
                    break;
                case 70:
                    MessageUtil.aviso("Selecione uma carta da mesa do seu oponente");
                    especialMesaOponete = true;
                    break;
            }
            separaCartas();
        }
    }

    public void diminuiPontosDeterminacao(Deck deck, int pontos) {
        int pontosDeterminacao = deck.getPontosDeterminacao() - pontos;
        deck.setPontosDeterminacao(pontosDeterminacao);
    }

    public void embaralhar() {
        List<CartaJogo> embaralhado = new ArrayList();
        for (int i = monte.size() - 1; i >= 0; i--) {
            int num = new Random().nextInt(monte.size());
            embaralhado.add(monte.get(num));
            monte.remove(num);
        }
        monte = embaralhado;
        deck.setCartas(embaralhado);
    }

    public void enviarMensagem(String mensagem) {
        if (!mensagem.isEmpty()) {
            duelo.setBatePapo(jogador.getLogin() + ": " + mensagem + "\n\n" + duelo.getBatePapo());
        }
        mensagem = "";
    }

    public void finalizar() {
        for (int i = 0; i < mesa.size(); i++) {
            if (mesa.get(i).getCarta().getId() == 1) {
                EfeitoCartaRN.carta1(mesa.get(i));
            }
            if (mesa.get(i).getEspecial() == 63) {
                mesa.get(i).setValorAtaque(mesa.get(i).getValorAtaque() - 2);
                mesa.get(i).setEspecial(0);
            }
            if (mesa.get(i).getEspecial() == 57) {
                mesa.get(i).setValorAtaque(mesa.get(i).getValorAtaque() - 1);
                mesa.get(i).setEspecial(0);
            }
        }

        duelo.setBatePapo(jogador.getLogin() + " finalizou o turno " + "\n\n" + duelo.getBatePapo());
        som = true;

        //Altera todas as cartas compradas
        for (int i = 0; i < mao.size(); i++) {
            mao.get(i).setNova(false);
        }

        deck.setPodeUsarEspecial(true);
        deck.setPodeAtacar(true);

        podeFinalizar = false;
        podeDescer = false;
        podeComprar = true;
        podeAtacar = false;

        duelo.setVezDe(oponente.getLogin());
    }

    public void inicializaDuelo(String login) {
        CartaJogo carta;
        this.som = true;
        this.podeAtacar = false;
        this.podeAtacarDeterminacao = false;
        this.podeComprar = true;
        this.podeDescer = false;
        this.podeFinalizar = false;

        atualizarDados(login);

        separaCartas();
        embaralhar();

        if (mao.isEmpty()) {
            monte.get(0).setLocalCarta(LocalCarta.MAO);
            monte.get(1).setLocalCarta(LocalCarta.MAO);
            monte.get(2).setLocalCarta(LocalCarta.MAO);
            separaCartas();
        }
    }

    public void separaCartas() {
        this.descarte = new ArrayList();
        this.mao = new ArrayList();
        this.mesa = new ArrayList();
        this.mesaOponente = new ArrayList();
        this.monte = new ArrayList();

        //Separa suas cartas
        for (CartaJogo carta : deck.getCartas()) {
            switch (carta.getLocalCarta()) {
                case DESCARTE:
                    descarte.add(carta);
                    break;
                case MAO:
                    mao.add(carta);
                    break;
                case MESA:
                    mesa.add(carta);
                    break;
                case MONTE:
                    monte.add(carta);
                    break;
            }
        }

        separaCartasOponente();
    }

    public void separaCartasOponente() {
        boolean possuiCartaDefesa = false;
        //Separa cartas oponente
        for (CartaJogo carta : deckOponente.getCartas()) {
            switch (carta.getLocalCarta()) {
                case MESA:
                    if (carta.getEstadoCarta().equals(EstadoCarta.DEFESA)) {
                        possuiCartaDefesa = true;
                    }
                    mesaOponente.add(carta);
                    break;
            }
        }
        if (possuiCartaDefesa) {
            podeAtacarDeterminacao = false;
        } else {
            podeAtacarDeterminacao = true;
        }
    }

    public void salvar(Duelo duelo, int vencedor) {
        if (!dueloDAOMysql.jaInserido(duelo)) {
            this.dueloDAOMysql.salvar(duelo, vencedor);
        }
    }

    public void selecionar(CartaJogo cartaEspecial, CartaJogo cartaSelecionadaMesa, CartaJogo cartaSelecionadaMesaOponente, List<CartaJogo> ordemMonte) {
        switch (cartaEspecial.getCarta().getId()) {
            case 50:
                EfeitoCartaRN.carta50(cartaSelecionadaMesaOponente);
                verificaPontosCarta(cartaSelecionadaMesaOponente);
                colocaCartaDescarte(cartaEspecial);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou a carta " + cartaSelecionadaMesaOponente.getCarta().getNome() + " do jogador " + oponente.getLogin() + " para receber -1/-1\n\n" + duelo.getBatePapo());
                break;
            case 52:
                EfeitoCartaRN.carta52(cartaSelecionadaMesa);
                colocaCartaDescarte(cartaEspecial);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou a carta " + cartaSelecionadaMesa.getCarta().getNome() + " para receber 0/+3\n\n" + duelo.getBatePapo());
                break;
            case 53:
                EfeitoCartaRN.carta53(cartaEspecial, cartaSelecionadaMesa);
                colocaCartaMesa(cartaEspecial);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou a carta " + cartaSelecionadaMesa.getCarta().getNome() + " para ser duplicada\n\n" + duelo.getBatePapo());
                break;
            case 54:
                EfeitoCartaRN.carta54(cartaSelecionadaMesa);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou a carta " + cartaSelecionadaMesa.getCarta().getNome() + " para receber +3/0\n\n" + duelo.getBatePapo());
                colocaCartaDescarte(cartaEspecial);
                break;
            case 57:
                EfeitoCartaRN.carta57(cartaSelecionadaMesa);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou a carta " + cartaSelecionadaMesa.getCarta().getNome() + " para receber +1/0\n\n" + duelo.getBatePapo());
                colocaCartaDescarte(cartaEspecial);
                break;
            case 59:
                EfeitoCartaRN.carta59(cartaSelecionadaMesaOponente);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou a carta " + cartaSelecionadaMesaOponente.getCarta().getNome() + " do jogador " + oponente.getLogin() + " para ser descartada\n\n" + duelo.getBatePapo());
                colocaCartaDescarte(cartaEspecial);
                break;
            case 60:
                EfeitoCartaRN.carta60(deck, cartaSelecionadaMesaOponente);
                duelo.setBatePapo(jogador.getLogin() + " perdeu 2 pontos de determinação por descer a carta " + cartaEspecial.getCarta().getNome() + " e selecionou a carta " + cartaSelecionadaMesaOponente.getCarta().getNome() + " do jogador " + oponente.getLogin() + " para ser descartada\n\n" + duelo.getBatePapo());
                colocaCartaDescarte(cartaEspecial);
                break;
            case 62:
                EfeitoCartaRN.carta62(deck, ordemMonte);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e reorganizou as 5 primeiras cartas do seu monte\n\n" + duelo.getBatePapo());
                colocaCartaDescarte(cartaEspecial);
                break;
            case 63:
                EfeitoCartaRN.carta63(cartaSelecionadaMesa);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou a carta " + cartaSelecionadaMesa.getCarta().getNome() + " para receber +2/0\n\n" + duelo.getBatePapo());
                colocaCartaDescarte(cartaEspecial);
                break;
            case 64:
                EfeitoCartaRN.carta64(cartaSelecionadaMesa);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou a carta " + cartaSelecionadaMesa.getCarta().getNome() + " para receber 0/+1\n\n" + duelo.getBatePapo());
                colocaCartaDescarte(cartaEspecial);
                break;
            case 66:
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou a carta " + cartaSelecionadaMesaOponente.getCarta().getNome() + " do jogador " + oponente.getLogin() + " que foi substituida pela carta Professor Substituto\n\n" + duelo.getBatePapo());
                EfeitoCartaRN.carta66(cartaSelecionadaMesaOponente);
                colocaCartaDescarte(cartaEspecial);
                break;
            case 67:
                EfeitoCartaRN.carta67(cartaSelecionadaMesa);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou uma carta do tipo Especial do seu descarte para voltar para sua mão\n\n" + duelo.getBatePapo());
                colocaCartaDescarte(cartaEspecial);
                break;
            case 68:
                EfeitoCartaRN.carta68(cartaSelecionadaMesa);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou uma carta do tipo Desafio do seu descarte para voltar para sua mão\n\n" + duelo.getBatePapo());
                colocaCartaDescarte(cartaEspecial);
                break;
            case 69:
                EfeitoCartaRN.carta69(cartaSelecionadaMesaOponente);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou a carta " + cartaSelecionadaMesaOponente.getCarta().getNome() + " do jogador " + oponente.getLogin() + " para alterar seu modo (de ataque ou de defesa)\n\n" + duelo.getBatePapo());
                colocaCartaDescarte(cartaEspecial);
                break;
            case 70:
                EfeitoCartaRN.carta70(cartaSelecionadaMesaOponente);
                duelo.setBatePapo(jogador.getLogin() + " desceu a carta " + cartaEspecial.getCarta().getNome() + " e selecionou a carta " + cartaSelecionadaMesaOponente.getCarta().getNome() + " do jogador " + oponente.getLogin() + " para não poder mais vencer um duelo\n\n" + duelo.getBatePapo());
                colocaCartaDescarte(cartaEspecial);
                break;
        }
        separaCartas();
        especialMesa = false;
        especialMesaOponete = false;
    }

    public void verificaHoraDuelo() {
        Calendar dataAtual = Calendar.getInstance();
        if (duelo.getDataCriacao().getTimeInMillis() <= dataAtual.getTimeInMillis() && duelo.getSituacaoDuelo().equals(SituacaoDuelo.CRIADO)) {
            duelo.setSituacaoDuelo(SituacaoDuelo.CANCELADO);
            salvar(duelo, 0);
        }
    }

    public void verificaPontosCarta(CartaJogo carta) {
        if (carta.getValorAtaque() <= 0 || carta.getValorDefesa() <= 0) {
            colocaCartaDescarte(carta);
            separaCartas();
        }
    }

    public boolean verificaPontosDeterminacao() {
        if (deck.getPontosDeterminacao() <= 0) {
            duelo.setSituacaoDuelo(SituacaoDuelo.FINALIZADO);
            ganhador = oponente;
            return true;
        } else if (deckOponente.getPontosDeterminacao() <= 0) {
            duelo.setSituacaoDuelo(SituacaoDuelo.FINALIZADO);
            ganhador = jogador;
            return true;
        }
        return false;
    }

    //GETTERS E SETTERS
    public DueloDAOMysql getDueloDAOMysql() {
        return dueloDAOMysql;
    }

    public void setDueloDAOMysql(DueloDAOMysql dueloDAOMysql) {
        this.dueloDAOMysql = dueloDAOMysql;
    }

    public List<CartaJogo> getDescarte() {
        return descarte;
    }

    public void setDescarte(List<CartaJogo> descarte) {
        this.descarte = descarte;
    }

    public List<CartaJogo> getMao() {
        return mao;
    }

    public void setMao(List<CartaJogo> mao) {
        this.mao = mao;
    }

    public List<CartaJogo> getMesa() {
        return mesa;
    }

    public void setMesa(List<CartaJogo> mesa) {
        this.mesa = mesa;
    }

    public List<CartaJogo> getMesaOponente() {
        return mesaOponente;
    }

    public void setMesaOponente(List<CartaJogo> mesaOponente) {
        this.mesaOponente = mesaOponente;
    }

    public List<CartaJogo> getMonte() {
        return monte;
    }

    public void setMonte(List<CartaJogo> monte) {
        this.monte = monte;
    }

    public Duelo getDuelo() {
        return duelo;
    }

    public void setDuelo(Duelo duelo) {
        this.duelo = duelo;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogador getOponente() {
        return oponente;
    }

    public void setOponente(Jogador oponente) {
        this.oponente = oponente;
    }

    public Jogador getGanhador() {
        return ganhador;
    }

    public void setGanhador(Jogador ganhador) {
        this.ganhador = ganhador;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeckOponente() {
        return deckOponente;
    }

    public void setDeckOponente(Deck deckOponente) {
        this.deckOponente = deckOponente;
    }

    public boolean isPodeAtacar() {
        return podeAtacar;
    }

    public void setPodeAtacar(boolean podeAtacar) {
        this.podeAtacar = podeAtacar;
    }

    public boolean isPodeAtacarDeterminacao() {
        return podeAtacarDeterminacao;
    }

    public void setPodeAtacarDeterminacao(boolean podeAtacarDeterminacao) {
        this.podeAtacarDeterminacao = podeAtacarDeterminacao;
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

    public boolean isPodeFinalizar() {
        return podeFinalizar;
    }

    public void setPodeFinalizar(boolean podeFinalizar) {
        this.podeFinalizar = podeFinalizar;
    }

    public boolean isSom() {
        return som;
    }

    public void setSom(boolean som) {
        this.som = som;
    }

    public boolean isEspecialMesa() {
        return especialMesa;
    }

    public void setEspecialMesa(boolean especialMesa) {
        this.especialMesa = especialMesa;
    }

    public boolean isEspecialMesaOponete() {
        return especialMesaOponete;
    }

    public void setEspecialMesaOponete(boolean especialMesaOponete) {
        this.especialMesaOponete = especialMesaOponete;
    }

}
