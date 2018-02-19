/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import br.com.lordofflorestal.rn.CartaRN;
import br.com.lordofflorestal.rn.JogadorRN;
import br.com.lordofflorestal.util.FileUploadUtil;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class CartaBean {

    private Carta carta = new Carta();
    private UploadedFile img;

    private HttpServletRequest request;

    private Jogador jogador;
    private List<Jogador> jogadoresSelecionados;
    private List<Jogador> jogadores;

    public CartaBean() {
        jogador = new Jogador();
        jogadores = new ArrayList();
        jogadoresSelecionados = new ArrayList();
    }

    public void preRender() {
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int id;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            CartaRN cartaRN = new CartaRN();
            carta = cartaRN.buscarPorId(id);
            jogadores = new JogadorRN().listarJogadoresSemCarta(id);
        }
    }

    public String novo() {
        this.carta = new Carta();
        return "/adm/carta/cadastrar.xhtml?faces-redirect=true";
    }

    public String salvar() {
        if (img != null) {
            if (img.getSize() != 0) {
                String extensao[] = img.getContentType().split("/");
                carta.setImagem(carta.getNome() + "." + extensao[extensao.length - 1]);
                new FileUploadUtil().uploadCarta(img, carta.getNome() + "." + extensao[extensao.length - 1]);
            }
        }

        CartaRN cartaRN = new CartaRN();
        cartaRN.salvar(carta);

        this.carta = new Carta();
        return "/adm/carta/cadastrar.xhtml";
    }

    public String excluir() {
        CartaRN cartaRN = new CartaRN();
        cartaRN.excluir(carta);
        return null;
    }

    public String selecionar() {
        jogadoresSelecionados.add(jogador);
        jogadores.remove(jogador);
        return null;
    }

    public String remover() {
        jogadoresSelecionados.remove(jogador);
        jogadores.add(jogador);
        return null;
    }

    public String enviar() {
        JogadorRN jogadorRN = new JogadorRN();
        for (Jogador j : jogadoresSelecionados) {
            jogadorRN.inserirCartaJogador(carta, j);
        }
        MessageUtil.info("Cartas envidas com sucesso.");
        return null;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public TipoCarta[] getTipoCarta() {
        return TipoCarta.values();
    }

    public SubtipoCarta[] getSubtipoCarta() {
        return SubtipoCarta.values();
    }

    public List<Carta> getCartas() {
        CartaRN cartaRN = new CartaRN();
        return cartaRN.listar();
    }

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public List<Jogador> getJogadoresSelecionados() {
        return jogadoresSelecionados;
    }

    public void setJogadoresSelecionados(List<Jogador> jogadoresSelecionados) {
        this.jogadoresSelecionados = jogadoresSelecionados;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

}
