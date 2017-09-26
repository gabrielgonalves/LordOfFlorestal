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

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class JogadorBean {

    private Jogador jogador = new Jogador();
    private String confirmarSenha;

    public String novo() {
        this.jogador = new Jogador();
        return "/administrador/cadastrarJogador.xhtml";
    }

    public String salvar() {
        String senha = this.jogador.getSenha();
        if (!senha.equals(confirmarSenha)) {
            return null;
        }

        JogadorRN jogadorRN = new JogadorRN();
        jogadorRN.salvar(jogador);

        return novo();
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
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

}
