/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.io.Serializable;

/**
 *
 * @author gabriel
 */
public class Suporte implements Serializable {

    private int id;
    private String jogador;
    private String assunto;
    private String mensagem;

    public Suporte() {
    }

    public Suporte(int id, String jogador, String assunto, String mensagem) {
        this.id = id;
        this.jogador = jogador;
        this.assunto = assunto;
        this.mensagem = mensagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
