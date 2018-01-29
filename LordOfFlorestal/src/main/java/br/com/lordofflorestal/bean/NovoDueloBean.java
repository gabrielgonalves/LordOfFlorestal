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
import br.com.lordofflorestal.rn.JogadorRN;
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

    public NovoDueloBean() {
        qtCartas = 10;
        cartasSelecionadas = new ArrayList();
        suasCartas = new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()).getCartas();
    }

    public String adicionarCarta() {
        cartasSelecionadas.add(cartaSelecionada);
        suasCartas.remove(cartaSelecionada);
        return null;
    }

    public String removerCarta() {
        cartasSelecionadas.remove(cartaSelecionada);
        suasCartas.add(cartaSelecionada);
        if (qtCartas > 10) {
            qtCartas = cartasSelecionadas.size();
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

    public void onSlideEnd(SlideEndEvent event) {
        qtCartas = event.getValue();
    }

}
