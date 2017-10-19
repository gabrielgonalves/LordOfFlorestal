/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.web;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import br.com.lordofflorestal.rn.CartaRN;
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

    public CartaBean() {
    }

    public void preRender() {
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int id;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            CartaRN cartaRN = new CartaRN();
            carta = cartaRN.buscarPorId(id);
        }
    }

    public String novo() {
        this.carta = new Carta();
        return "/adm/carta/cadastrar.xhtml?faces-redirect=true";
    }

    public String salvar() {
        carta.setImagem(img.getContents());
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

}
