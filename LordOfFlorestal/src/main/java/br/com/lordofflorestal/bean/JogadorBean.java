/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.model.TipoJogador;
import br.com.lordofflorestal.rn.JogadorRN;
import br.com.lordofflorestal.util.FileUploadUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class JogadorBean {

    private Jogador jogador = new Jogador();

    private UploadedFile img;

    private HttpServletRequest request;

    public void upload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
    }

    public String novo() {
        this.jogador = new Jogador();
        return "/adm/jogador/cadastrar.xhtml?faces-redirect=true";
    }

    public String salvar() {
        String senha = this.jogador.getSenha();

        if (img != null) {
            if (img.getSize() != 0) {
                String extensao[] = img.getContentType().split("/");
                jogador.setImagem(jogador.getLogin() + "." + extensao[extensao.length - 1]);
                new FileUploadUtil().upload(img, jogador.getLogin() + "." + extensao[extensao.length - 1]);
            }
        }
        
        if(jogador.getImagem() == null){
            jogador.setImagem("user.png");
        }
        
        JogadorRN jogadorRN = new JogadorRN();
        jogadorRN.salvar(jogador);

        this.jogador = new Jogador();
        return "/adm/jogador/cadastrar.xhtml";
    }

    public String excluir() {
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
        this.jogador = jogador;
    }

    public TipoJogador[] getTipoJogador() {
        return TipoJogador.values();
    }

    public List<Jogador> getJogadores() {
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
