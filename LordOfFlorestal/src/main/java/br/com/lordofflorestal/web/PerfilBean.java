/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.web;

import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.rn.JogadorRN;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class PerfilBean {

    private Jogador jogador;
    
    private UploadedFile img;

    public PerfilBean() {
        JogadorRN jogadorRN = new JogadorRN();
        String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        jogador = jogadorRN.buscarPorLogin(login);
    }
    
    public String salvar(){
        JogadorRN jogadorRN = new JogadorRN();
        jogador.setImagem(img.getContents());
        jogadorRN.salvar(jogador);
        
        return "/jogador/perfil.xhtml";
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }
}
