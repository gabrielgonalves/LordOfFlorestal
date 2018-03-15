/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.CartaJogo;
import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.control.DueloSingleton;
import br.com.lordofflorestal.model.DeckJogador;
import br.com.lordofflorestal.rn.DeckJogadorRN;
import br.com.lordofflorestal.rn.JogadorRN;
import br.com.lordofflorestal.util.MessageUtil;
import br.com.lordofflorestal.util.TempoThread;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SlideEndEvent;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class NovoDueloBean {

    private List<Carta> cartasSelecionadas;
    private List<Carta> suasCartas;

    private Carta cartaSelecionada;

    private int tempo;
    private int qtCartas;

    private List<DeckJogador> decks;
    private DeckJogador deckSelecionado;

    private Jogador oponente;

    private TempoThread tempoThread;

    public TempoThread getTempoThread() {
        return tempoThread;
    }

    public NovoDueloBean() {
        tempoThread = new TempoThread();
        new Thread(tempoThread).start();
        qtCartas = 10;
        cartasSelecionadas = new ArrayList();
        suasCartas = new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()).getCartas();
        Jogador jogador = new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        decks = new DeckJogadorRN().buscaPorJogador(jogador.getMatricula());
    }

    public String adicionarCarta() {
        cartasSelecionadas.add(cartaSelecionada);
        suasCartas.remove(cartaSelecionada);
        deckSelecionado = null;
        return null;
    }

    public String removerCarta() {
        cartasSelecionadas.remove(cartaSelecionada);
        suasCartas.add(cartaSelecionada);
        deckSelecionado = null;
        return null;
    }

    public String selecionarDeck() {
        cartasSelecionadas = deckSelecionado.getCartas();
        for (Carta carta : cartasSelecionadas) {
            suasCartas.remove(carta);
        }
        if (cartasSelecionadas.size() < qtCartas) {
            MessageUtil.info("Deck " + deckSelecionado.getNome() + " selecionado com sucesso. Você ainda precisa selecionar mais " + (qtCartas - cartasSelecionadas.size()) + " carta(s).");
        } else {
            MessageUtil.info("Deck " + deckSelecionado.getNome() + " selecionado com sucesso.");
        }
        return null;
    }

    public String criarDuelo() {
        Jogador jogador = new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());

        Duelo duelo = new Duelo();
        duelo.setCriadoPor(jogador);
        duelo.getDataCriacao().add(Calendar.MINUTE, tempo);
        duelo.getDeckJogador1().setJogador(jogador);
        List<CartaJogo> cartas = new ArrayList();
        for (Carta c : cartasSelecionadas) {
            cartas.add(new CartaJogo(c));
        }
        duelo.getDeckJogador1().setCartas(cartas);
        duelo.setVezDe(duelo.getCriadoPor().getLogin());

        duelo.setOponente(oponente);

        duelo.setBatePapo(jogador.getLogin() + " criou o duelo");

        DueloSingleton.getInstance().adicionar(duelo);

        return "jogo.xhtml?duelo=" + duelo.getUri() + "&faces-redirect=true";
    }

    public List<Carta> getCartasSelecionadas() {
        return cartasSelecionadas;
    }

    public void setCartasSelecionadas(List<Carta> cartasSelecionadas) {
        this.cartasSelecionadas = cartasSelecionadas;
    }

    public List<Carta> getSuasCartas() {
        return suasCartas;
    }

    public void setSuasCartas(List<Carta> suasCartas) {
        this.suasCartas = suasCartas;
    }

    public Carta getCartaSelecionada() {
        return cartaSelecionada;
    }

    public void setCartaSelecionada(Carta cartaSelecionada) {
        this.cartaSelecionada = cartaSelecionada;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getQtCartas() {
        return qtCartas;
    }

    public void setQtCartas(int qtCartas) {
        this.qtCartas = qtCartas;
    }

    public DeckJogador getDeckSelecionado() {
        return deckSelecionado;
    }

    public void setDeckSelecionado(DeckJogador deckSelecionado) {
        this.deckSelecionado = deckSelecionado;
    }

    public Jogador getOponente() {
        return oponente;
    }

    public void setOponente(Jogador oponente) {
        this.oponente = oponente;
    }

    public void onSlideEnd(SlideEndEvent event) {
        qtCartas = event.getValue();
        if (qtCartas < cartasSelecionadas.size()) {
            MessageUtil.aviso("Você precisa remover " + (cartasSelecionadas.size() - qtCartas) + " carta(s) para criar um novo duelo com essa quantidade de cartas.");
        }
        if (cartasSelecionadas.size() != 0 && qtCartas > cartasSelecionadas.size()) {
            MessageUtil.aviso("Você precisa selecionar " + (qtCartas - cartasSelecionadas.size()) + " carta(s) para criar um novo duelo com essa quantidade de cartas.");
        }
    }

    public List<DeckJogador> getDecks() {
        Jogador jogador = new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        decks = new DeckJogadorRN().buscaPorJogador(jogador.getMatricula());
        List<DeckJogador> lista = new ArrayList();
        for (DeckJogador deck : decks) {
            if (deck.getCartas().size() <= qtCartas) {
                lista.add(deck);
            }
        }
        return lista;
    }

    public List<Jogador> getJogadores() {
        Jogador jogador = new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        return new JogadorRN().listarExceto(jogador);
    }

}
