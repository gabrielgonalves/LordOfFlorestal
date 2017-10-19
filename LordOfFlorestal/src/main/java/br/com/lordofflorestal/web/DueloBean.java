/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.web;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.CartaJogo;
import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.model.SituacaoDuelo;
import br.com.lordofflorestal.rn.JogadorRN;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DualListModel;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class DueloBean {

    private Duelo duelo;

    private Jogador jogador;
    private Jogador oponente;

    private DualListModel<Carta> cartas;
    private List<Carta> cartasSelecionadas;
    private List<Carta> cartasDisponiveis;

    public DueloBean() {
        duelo = new Duelo();
        JogadorRN jogadorRN = new JogadorRN();
        jogador = jogadorRN.buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
    }

    @PostConstruct
    public void init() {
        cartasDisponiveis = new ArrayList();
        cartasDisponiveis.addAll(jogador.getCartas());
        cartasSelecionadas = new ArrayList();
        cartas = new DualListModel(cartasDisponiveis, cartasSelecionadas);
    }

    public String jogar() {
        duelo.getDeckJogador1().setJogador(jogador);
        
        List<CartaJogo> cartasJogo = new ArrayList();
        for(Carta c : cartasSelecionadas){
            CartaJogo cj = new CartaJogo();
            cj.setCarta(c);
            cartasJogo.add(cj);
        }
        
        duelo.getDeckJogador1().setCartas(cartasJogo);
        duelo.setSituacaoDuelo(SituacaoDuelo.AGUARDANDO);
        duelo.getDeckJogador1().setPontosDeterminacao(20);
        
        return "/jogo/jogo.xhtml?faces-redirect=true";
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

    public List<Jogador> getOponentes() {
        JogadorRN jogadorRN = new JogadorRN();
        return jogadorRN.listarExceto(jogador);
    }

    public DualListModel<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(DualListModel<Carta> cartas) {
        this.cartas = cartas;
    }

    public List<Carta> getCartasSelecionadas() {
        if (cartas != null) {
            cartasSelecionadas = cartas.getTarget();
        }
        return cartasSelecionadas;
    }

    public void setCartasSelecionadas(List<Carta> cartasSelecionadas) {
        this.cartasSelecionadas = cartasSelecionadas;
    }

    public List<Carta> getCartasDisponiveis() {
        return cartasDisponiveis;
    }

    public void setCartasDisponiveis(List<Carta> cartasDisponiveis) {
        this.cartasDisponiveis = cartasDisponiveis;
    }

}
