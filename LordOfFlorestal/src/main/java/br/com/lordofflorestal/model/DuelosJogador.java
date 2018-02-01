/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author gabriel
 */
public class DuelosJogador implements Serializable {

    private int matriculaJogador;
    private int matriculaOponente;
    private int matriculaVencedor;
    private Date dataCriacao;
    private SituacaoDuelo situacaoDuelo;
    private String jogador;
    private String oponente;
    private String imgJogador;
    private String imgOponente;

    public DuelosJogador() {
    }

    public int getMatriculaJogador() {
        return matriculaJogador;
    }

    public void setMatriculaJogador(int matriculaJogador) {
        this.matriculaJogador = matriculaJogador;
    }

    public int getMatriculaOponente() {
        return matriculaOponente;
    }

    public void setMatriculaOponente(int matriculaOponente) {
        this.matriculaOponente = matriculaOponente;
    }

    public int getMatriculaVencedor() {
        return matriculaVencedor;
    }

    public void setMatriculaVencedor(int matriculaVencedor) {
        this.matriculaVencedor = matriculaVencedor;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public SituacaoDuelo getSituacaoDuelo() {
        return situacaoDuelo;
    }

    public void setSituacaoDuelo(SituacaoDuelo situacaoDuelo) {
        this.situacaoDuelo = situacaoDuelo;
    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public String getOponente() {
        return oponente;
    }

    public void setOponente(String oponente) {
        this.oponente = oponente;
    }

    public String getImgJogador() {
        return imgJogador;
    }

    public void setImgJogador(String imgJogador) {
        this.imgJogador = imgJogador;
    }

    public String getImgOponente() {
        return imgOponente;
    }

    public void setImgOponente(String imgOponente) {
        this.imgOponente = imgOponente;
    }    

}
