/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

/**
 *
 * @author gabriel
 */
@Entity
public class Jogador implements Serializable {

    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoJogador tipoJogador;
    @Id
    private int matricula;
    private String email;
    private String login;
    private String senha;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagem;
    @ManyToMany
    private List<Carta> cartas;
    @Embedded
    private EstatisticaJogador estatisticaJogador;

    public Jogador() {
        cartas = new ArrayList();
        estatisticaJogador = new EstatisticaJogador();
    }

    public Jogador(String nome, TipoJogador tipoJogador, int matricula, String email, String login, String senha, byte[] imagem, List<Carta> cartas, EstatisticaJogador estatisticaJogador) {
        this.nome = nome;
        this.tipoJogador = tipoJogador;
        this.matricula = matricula;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.imagem = imagem;
        this.cartas = cartas;
        this.estatisticaJogador = estatisticaJogador;
    }

   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoJogador getTipoJogador() {
        return tipoJogador;
    }

    public void setTipoJogador(TipoJogador tipoJogador) {
        this.tipoJogador = tipoJogador;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public EstatisticaJogador getEstatisticaJogador() {
        return estatisticaJogador;
    }

    public void setEstatisticaJogador(EstatisticaJogador estatisticaJogador) {
        this.estatisticaJogador = estatisticaJogador;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.tipoJogador);
        hash = 23 * hash + this.matricula;
        hash = 23 * hash + Objects.hashCode(this.email);
        hash = 23 * hash + Objects.hashCode(this.login);
        hash = 23 * hash + Objects.hashCode(this.senha);
        hash = 23 * hash + Objects.hashCode(this.imagem);
        hash = 23 * hash + Objects.hashCode(this.cartas);
        hash = 23 * hash + Objects.hashCode(this.estatisticaJogador);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jogador other = (Jogador) obj;
        if (this.matricula != other.matricula) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.imagem, other.imagem)) {
            return false;
        }
        if (this.tipoJogador != other.tipoJogador) {
            return false;
        }
        if (!Objects.equals(this.cartas, other.cartas)) {
            return false;
        }
        if (!Objects.equals(this.estatisticaJogador, other.estatisticaJogador)) {
            return false;
        }
        return true;
    }

}
