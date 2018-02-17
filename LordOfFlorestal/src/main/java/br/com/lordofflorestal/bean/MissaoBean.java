/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.Missao;
import br.com.lordofflorestal.rn.CartaRN;
import br.com.lordofflorestal.rn.MissaoRN;
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
public class MissaoBean {

    private Missao missao = new Missao();

    private HttpServletRequest request;

    public String novo() {
        this.missao = new Missao();
        return "/adm/missao/cadastrar.xhtml?faces-redirect=true";
    }

    public String salvar() {
        new MissaoRN().salvar(missao);
        return "/adm/missao/cadastrar.xhtml";
    }

    public String excluir() {
        new MissaoRN().excluir(missao);
        return null;
    }

    public void preRender() {
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int id;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            MissaoRN missaoRN = new MissaoRN();
            this.missao = missaoRN.buscaPorId(id);
        }
    }

    public Missao getMissao() {
        return missao;
    }

    public void setMissao(Missao missao) {
        this.missao = missao;
    }

    public List<Carta> getCartas() {
        CartaRN cartaRN = new CartaRN();
        return cartaRN.listar();
    }
    
    public List<Missao> getMissoes(){
        MissaoRN missaoRN = new MissaoRN();
        return missaoRN.listar();
    }

}
