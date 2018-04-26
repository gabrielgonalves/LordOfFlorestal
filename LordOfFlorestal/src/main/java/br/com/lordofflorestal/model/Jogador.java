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

/**
 *
 * @author gabriel
 */
public class Jogador implements Serializable {

    private String nome;
    private int matricula;
    private String email;
    private String login;
    private String senha;
    private String imagem;
    private TipoJogador tipoJogador;
    private int anoAdmissao;
    private EstatisticaJogador estatisticaJogador;
    private List<Carta> cartas;
    private int xp;

    public Jogador() {
        cartas = new ArrayList();
        estatisticaJogador = new EstatisticaJogador();
        xp = 0;
    }

    public Jogador(String nome, int matricula, String email, String login, String senha, String imagem, TipoJogador tipoJogador, int anoAdmissao, EstatisticaJogador estatisticaJogador, List<Carta> cartas) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.imagem = imagem;
        this.tipoJogador = tipoJogador;
        this.anoAdmissao = anoAdmissao;
        this.estatisticaJogador = estatisticaJogador;
        this.cartas = cartas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public TipoJogador getTipoJogador() {
        return tipoJogador;
    }

    public void setTipoJogador(TipoJogador tipoJogador) {
        this.tipoJogador = tipoJogador;
    }

    public int getAnoAdmissao() {
        return anoAdmissao;
    }

    public void setAnoAdmissao(int anoAdmissao) {
        this.anoAdmissao = anoAdmissao;
    }

    public EstatisticaJogador getEstatisticaJogador() {
        return estatisticaJogador;
    }

    public void setEstatisticaJogador(EstatisticaJogador estatisticaJogador) {
        this.estatisticaJogador = estatisticaJogador;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public String getNivel() {
        if (xp > 100000) {
            return "Graduado(a)";
        } else if (xp >= 50000) {
            return "Formando(a)";
        } else if (xp >= 1000) {
            return "Veterano(a)";
        } else if (xp >= 500) {
            return "Calouro(a)";
        } else if (xp >= 100) {
            return "Novato(a)";
        }
        return "";
    }

    public int getXpProximoNivel() {
        if (xp < 100) {
            return 100 - xp;
        } else if (xp < 500) {
            return 500 - xp;
        } else if (xp < 1000) {
            return 1000 - xp;
        } else if (xp < 50000) {
            return 50000 - xp;
        } else if (xp < 100000) {
            return 100000 - xp;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + this.matricula;
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.login);
        hash = 79 * hash + Objects.hashCode(this.senha);
        hash = 79 * hash + Objects.hashCode(this.imagem);
        hash = 79 * hash + Objects.hashCode(this.tipoJogador);
        hash = 79 * hash + this.anoAdmissao;
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
        if (this.anoAdmissao != other.anoAdmissao) {
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
        return true;
    }

}
