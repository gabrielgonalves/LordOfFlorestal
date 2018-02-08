/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.CartaJogo;
import br.com.lordofflorestal.model.EstadoCarta;
import br.com.lordofflorestal.model.LocalCarta;
import br.com.lordofflorestal.model.SituacaoDuelo;
import br.com.lordofflorestal.model.TipoCarta;
import br.com.lordofflorestal.rn.DueloRN;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.ArrayList;
import java.util.List;
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

    private DueloRN dueloRN;

    private String login;
    private String uri;
    private String mensagem;

    private HttpServletRequest request;

    private CartaJogo cartaDescer;
    private CartaJogo cartaAtaca;
    private CartaJogo cartaAtacada;
    private CartaJogo cartaSelecionada;
    private CartaJogo cartaSelecionadaMesa;
    private CartaJogo cartaSelecionadaMesaOponente;

    private List<CartaJogo> cartasAtacam;
    private List<CartaJogo> listaCartaEfeito;
    private List<CartaJogo> ordemMonte;

    private boolean caracoroa;
    private boolean caracoroaResultado;
    private boolean podeJogarCaraCoroa;

    public DueloBean() {
        podeJogarCaraCoroa = true;
        this.request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.uri = request.getParameter("duelo");
        this.login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        this.dueloRN = new DueloRN(uri, login);
        this.cartasAtacam = new ArrayList();
        this.listaCartaEfeito = new ArrayList();
        this.ordemMonte = new ArrayList();
    }

    public void preRender() {
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String uri;
        if (request.getParameter("duelo") != null) {
            uri = (request.getParameter("duelo"));
            if (!uri.equals(dueloRN.getDuelo().getUri())) {
                dueloRN.getDuelo().setSituacaoDuelo(SituacaoDuelo.CANCELADO);
                this.dueloRN = new DueloRN(uri, login);
            }
        }
    }

    public String alterarPosicaoCarta() {
        if (cartasAtacam.isEmpty()) {
            dueloRN.setPodeAtacar(false);
        }
        dueloRN.alterarEstadoCarta(cartaSelecionada);
        int atk = 0;
        if (cartaSelecionada.getEstadoCarta().equals(EstadoCarta.DEFESA)) {
            cartasAtacam.remove(cartaSelecionada);
            for (int i = 0; i < cartasAtacam.size(); i++) {
                atk += cartasAtacam.get(i).getValorAtaque();
            }
            if (cartasAtacam.size() > 1) {
                MessageUtil.aviso("Você selecionou mais de uma carta para atacar o seu oponente. Seu novo valor de ataque é " + atk);
            }
        }
        return null;
    }

    public String atacarDeterminacao() {
        if (dueloRN.atacarDeterminacao(cartasAtacam)) {
            return "jogo.xhtml?duelo=" + uri + "faces-redirect=true";
        }
        cartasAtacam = new ArrayList();
        return null;
    }

    public String atacar() {
        if (dueloRN.atacar(cartasAtacam, cartaAtacada)) {
            return "jogo.xhtml?duelo=" + uri + "faces-redirect=true";
        }
        cartasAtacam = new ArrayList();
        return null;
    }

    private void atualizaEstatistica() {
        dueloRN.atualizaEstatistica();
    }

    public void atualizarDados() {
        dueloRN.atualizarDados(login);
    }

    public void atualizaMesaOponente() {
        dueloRN.separaCartasOponente();
    }

    private void cria() {
        dueloRN.inicializaDuelo(login);
    }

    public String comprar() {
        dueloRN.comprar();
        return null;
    }

    public String descer() {
        dueloRN.descer(cartaDescer);
        return null;
    }

    public String enviarMensagem() {
        dueloRN.enviarMensagem(mensagem);
        return null;
    }

    public String finalizar() {
        dueloRN.finalizar();
        return null;
    }

    public String selecionaAtacar() {
        cartasAtacam.add(cartaAtaca);
        int atk = 0;
        for (int i = 0; i < cartasAtacam.size(); i++) {
            atk += cartasAtacam.get(i).getValorAtaque();
        }
        if (cartasAtacam.size() > 1) {
            MessageUtil.aviso("Você selecionou mais de uma carta para atacar o seu oponente. Seu novo valor de ataque é " + atk);
        }
        dueloRN.setPodeAtacar(true);
        return null;
    }

    public String selecionar() {
        dueloRN.selecionar(cartaDescer, cartaSelecionadaMesa, cartaSelecionadaMesaOponente, ordemMonte);
        return null;
    }

    private void verificaPontosDeterminacao() {
        dueloRN.verificaPontosDeterminacao();
    }

    public String jogarCaraOuCoroa() {
        caracoroaResultado = dueloRN.caraCoroa(cartaDescer, caracoroa);
        podeJogarCaraCoroa = false;
        return null;
    }

    public String atualizaListaCarta59() {
        List<CartaJogo> lista = new ArrayList();
        for (CartaJogo carta : dueloRN.getDeckOponente().getCartas()) {
            if (carta.getLocalCarta().equals(LocalCarta.MAO)) {
                lista.add(carta);
            }
        }
        for (CartaJogo carta : dueloRN.getDeckOponente().getCartas()) {
            if (carta.getLocalCarta().equals(LocalCarta.MESA)) {
                lista.add(carta);
            }
        }

        listaCartaEfeito = lista;
        return null;
    }

    public String adicionar62() {
        ordemMonte.add(cartaSelecionada);
        listaCartaEfeito.remove(cartaSelecionada);
        return null;
    }

    public String remover62() {
        ordemMonte.remove(cartaSelecionada);
        listaCartaEfeito.add(cartaSelecionada);
        return null;
    }

    public String atualizaListaCarta62() {
        ordemMonte = new ArrayList();
        List<CartaJogo> lista = new ArrayList();
        for (CartaJogo carta : dueloRN.getMonte()) {
            if (lista.size() < 5) {
                lista.add(carta);
            }
        }

        listaCartaEfeito = lista;
        return null;
    }

    public String atualizaListaCarta67() {
        List<CartaJogo> lista = new ArrayList();
        for (CartaJogo carta : dueloRN.getDescarte()) {
            if (carta.getCarta().getTipoCarta().equals(TipoCarta.ESPECIAL)) {
                lista.add(carta);
            }
        }

        listaCartaEfeito = lista;
        return null;
    }

    public String atualizaListaCarta68() {
        List<CartaJogo> lista = new ArrayList();
        for (CartaJogo carta : dueloRN.getDescarte()) {
            if (carta.getCarta().getTipoCarta().equals(TipoCarta.DESAFIO)) {
                lista.add(carta);
            }
        }

        listaCartaEfeito = lista;
        return null;
    }

    //GETTERS E SETTERS
    public DueloRN getDueloRN() {
        return dueloRN;
    }

    public void setDueloRN(DueloRN dueloRN) {
        this.dueloRN = dueloRN;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public CartaJogo getCartaDescer() {
        return cartaDescer;
    }

    public void setCartaDescer(CartaJogo cartaDescer) {
        this.cartaDescer = cartaDescer;
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

    public List<CartaJogo> getCartasAtacam() {
        return cartasAtacam;
    }

    public void setCartasAtacam(List<CartaJogo> cartasAtacam) {
        this.cartasAtacam = cartasAtacam;
    }

    public CartaJogo getCartaSelecionada() {
        return cartaSelecionada;
    }

    public void setCartaSelecionada(CartaJogo cartaSelecionada) {
        this.cartaSelecionada = cartaSelecionada;
    }

    public CartaJogo getCartaSelecionadaMesa() {
        return cartaSelecionadaMesa;
    }

    public void setCartaSelecionadaMesa(CartaJogo cartaSelecionadaMesa) {
        this.cartaSelecionadaMesa = cartaSelecionadaMesa;
    }

    public CartaJogo getCartaSelecionadaMesaOponente() {
        return cartaSelecionadaMesaOponente;
    }

    public void setCartaSelecionadaMesaOponente(CartaJogo cartaSelecionadaMesaOponente) {
        this.cartaSelecionadaMesaOponente = cartaSelecionadaMesaOponente;
    }

    public List<CartaJogo> getListaCartaEfeito() {
        return listaCartaEfeito;
    }

    public void setListaCartaEfeito(List<CartaJogo> listaCartaEfeito) {
        this.listaCartaEfeito = listaCartaEfeito;
    }

    public List<CartaJogo> getOrdemMonte() {
        return ordemMonte;
    }

    public void setOrdemMonte(List<CartaJogo> ordemMonte) {
        this.ordemMonte = ordemMonte;
    }

    public boolean isCaracoroa() {
        return caracoroa;
    }

    public void setCaracoroa(boolean caracoroa) {
        this.caracoroa = caracoroa;
    }

    public boolean isPodeJogarCaraCoroa() {
        return podeJogarCaraCoroa;
    }

    public void setPodeJogarCaraCoroa(boolean podeJogarCaraCoroa) {
        this.podeJogarCaraCoroa = podeJogarCaraCoroa;
    }

    public boolean isCaracoroaResultado() {
        return caracoroaResultado;
    }

    public void setCaracoroaResultado(boolean caracoroaResultado) {
        this.caracoroaResultado = caracoroaResultado;
    }

}
