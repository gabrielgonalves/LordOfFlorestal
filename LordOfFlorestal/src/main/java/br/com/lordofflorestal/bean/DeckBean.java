/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.DeckJogador;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.rn.DeckJogadorRN;
import br.com.lordofflorestal.rn.JogadorRN;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SlideEndEvent;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class DeckBean {

    private DeckJogador deckJogador;
    private int qtCartas;

    private List<Carta> cartasSelecionadas;
    private List<Carta> suasCartas;

    private Carta cartaSelecionada;

    private HttpServletRequest request;

    public DeckBean() {
        deckJogador = new DeckJogador();
        qtCartas = 10;
        cartasSelecionadas = new ArrayList();
        suasCartas = new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()).getCartas();
    }

    public void preRender() {
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        List<DeckJogador> decks = new DeckJogadorRN().buscaPorJogador(new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()).getMatricula());
        int id;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            for (DeckJogador deck : decks) {
                if (deck.getId() == id) {
                    DeckJogadorRN deckJogadorRN = new DeckJogadorRN();
                    deckJogador = deckJogadorRN.buscaPorId(id);
                    cartasSelecionadas = deckJogador.getCartas();
                    qtCartas = cartasSelecionadas.size();
                    for(Carta carta : cartasSelecionadas){
                        suasCartas.remove(carta);
                    }
                    break;
                }
            }
        }
    }

    public String novo() {
        deckJogador = new DeckJogador();
        return "novo-deck.xhtml?faces-redirect=true";
    }

    public String adicionarCarta() {
        cartasSelecionadas.add(cartaSelecionada);
        suasCartas.remove(cartaSelecionada);
        return null;
    }

    public String removerCarta() {
        cartasSelecionadas.remove(cartaSelecionada);
        suasCartas.add(cartaSelecionada);
        return null;
    }

    public String salvar() {
        Jogador jogador = new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        deckJogador.setJogador(jogador);
        deckJogador.setCartas(cartasSelecionadas);
        new DeckJogadorRN().salvar(deckJogador);
        return "lista-deck.xhtml";
    }

    public String excluir() {
        new DeckJogadorRN().excluir(deckJogador);
        return null;
    }

    public DeckJogador getDeckJogador() {
        return deckJogador;
    }

    public void setDeckJogador(DeckJogador deckJogador) {
        this.deckJogador = deckJogador;
    }

    public int getQtCartas() {
        return qtCartas;
    }

    public void setQtCartas(int qtCartas) {
        this.qtCartas = qtCartas;
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

    public void onSlideEnd(SlideEndEvent event) {
        qtCartas = event.getValue();
    }

    public List<DeckJogador> getDecks() {
        Jogador jogador = new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        return new DeckJogadorRN().buscaPorJogador(jogador.getMatricula());
    }
}
