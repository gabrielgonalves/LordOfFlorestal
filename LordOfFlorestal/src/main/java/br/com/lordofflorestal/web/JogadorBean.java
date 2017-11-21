/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.web;

import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.model.TipoJogador;
import br.com.lordofflorestal.rn.JogadorRN;
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
public class JogadorBean {

    private Jogador jogador = new Jogador();
    private String confirmarSenha;
    
    private UploadedFile img;
    
    private HttpServletRequest request;

    public String novo() {
        this.jogador = new Jogador();
        return "/adm/jogador/cadastrar.xhtml?faces-redirect=true";
    }

    public String salvar() {
        String senha = this.jogador.getSenha();
        if (!senha.equals(confirmarSenha)) {
            return null;
        }
        
        if(img != null){
           jogador.setImagem(img.getContents()); 
        }
        
        JogadorRN jogadorRN = new JogadorRN();
        jogadorRN.salvar(jogador);

        this.jogador = new Jogador();
        return "/adm/jogador/cadastrar.xhtml";
    }
    
    public String excluir(){
        System.out.println("Entrou no excluir com o jogador " + jogador.getNome());
        JogadorRN jogadorRN = new JogadorRN();
        jogadorRN.excluir(jogador);
        return null;
    }
    
    public void preRender() {
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int matricula;
        if (request.getParameter("matricula") != null) {
            matricula = Integer.parseInt(request.getParameter("matricula"));
            JogadorRN jogadorRN = new JogadorRN();
            jogador = jogadorRN.buscarPorMatricula(matricula);
        }
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        System.out.println("Alterou jogador para " + jogador.getNome());
        this.jogador = jogador;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }
    
    public TipoJogador[] getTipoJogador() {
        return TipoJogador.values();
    }
    
    public List<Jogador> getJogadores(){
        JogadorRN jogadorRN = new JogadorRN();
        return jogadorRN.listar();
    }

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }

}
