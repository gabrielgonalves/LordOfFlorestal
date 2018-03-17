/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.CartaJogo;
import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.model.SituacaoDuelo;
import br.com.lordofflorestal.control.DueloSingleton;
import br.com.lordofflorestal.model.DeckJogador;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.model.TipoJogador;
import br.com.lordofflorestal.rn.DeckJogadorRN;
import br.com.lordofflorestal.rn.JogadorRN;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.ArrayList;
import java.util.List;
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
public class JuntarDueloBean {

    private HttpServletRequest request;

    private Duelo duelo;

    private List<Carta> cartasSelecionadas;
    private List<Carta> suasCartas;

    private Carta cartaSelecionada;

    private int qtCartas;

    private List<DeckJogador> decks;
    private DeckJogador deckSelecionado;

    public JuntarDueloBean() {
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String uri = request.getParameter("duelo");
        duelo = DueloSingleton.getInstance().buscaPorURI(uri);
        if (!duelo.isCronometro()) {
            new Thread(duelo.getTempoThread()).start();
            duelo.setCronometro(true);
        }
        qtCartas = duelo.getDeckJogador1().getCartas().size();
        cartasSelecionadas = new ArrayList();
        suasCartas = new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()).getCartas();
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

    private boolean jaSalvou = false;

    public boolean isJaSalvou() {
        return jaSalvou;
    }

    public void cronometro() {
        if (duelo.getTempoThread().getTempo() == 0) {
            duelo.setSituacaoDuelo(SituacaoDuelo.CANCELADO);
            if (duelo.getCriadoPor().getTipoJogador().equals(TipoJogador.ALUNO)) {
                duelo.getOponente().getEstatisticaJogador().setNumJogosPerdido(duelo.getOponente().getEstatisticaJogador().getNumJogosPerdido() + 1);
                duelo.getOponente().getEstatisticaJogador().setNumJogos(duelo.getOponente().getEstatisticaJogador().getNumJogos() + 1);
            } else {
                duelo.getOponente().getEstatisticaJogador().setNumJogosPerdeuLord(duelo.getOponente().getEstatisticaJogador().getNumJogosPerdeuLord() + 1);
                duelo.getOponente().getEstatisticaJogador().setNumJogos(duelo.getOponente().getEstatisticaJogador().getNumJogos() + 1);
            }
            if (duelo.getOponente().getTipoJogador().equals(TipoJogador.ALUNO)) {
                duelo.getCriadoPor().getEstatisticaJogador().setNumJogosGanho(duelo.getCriadoPor().getEstatisticaJogador().getNumJogosGanho() + 1);
                duelo.getCriadoPor().getEstatisticaJogador().setNumJogos(duelo.getCriadoPor().getEstatisticaJogador().getNumJogos() + 1);
            } else {
                duelo.getCriadoPor().getEstatisticaJogador().setNumJogosGanhouLord(duelo.getCriadoPor().getEstatisticaJogador().getNumJogosGanhouLord() + 1);
                duelo.getCriadoPor().getEstatisticaJogador().setNumJogos(duelo.getCriadoPor().getEstatisticaJogador().getNumJogos() + 1);
            }
            if (!jaSalvou) {
                new JogadorRN().salvar(duelo.getOponente());
                new JogadorRN().salvar(duelo.getCriadoPor());
                jaSalvou = true;
            }
        }
    }

    public String entrarDuelo() {
        List<CartaJogo> cartas = new ArrayList();
        for (Carta c : cartasSelecionadas) {
            cartas.add(new CartaJogo(c));
        }
        duelo.getDeckJogador2().setCartas(cartas);
        duelo.setSituacaoDuelo(SituacaoDuelo.EM_ANDAMENTO);
        duelo.setBatePapo(duelo.getOponente().getLogin() + " juntou-se ao duelo.\n\n" + duelo.getBatePapo());
        return "jogo.xhtml?duelo=" + duelo.getUri() + "&faces-redirect=true";
    }

    public Duelo getDuelo() {
        return duelo;
    }

    public int getQtCartas() {
        return qtCartas;
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

    public DeckJogador getDeckSelecionado() {
        return deckSelecionado;
    }

    public void setDeckSelecionado(DeckJogador deckSelecionado) {
        this.deckSelecionado = deckSelecionado;
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

}
