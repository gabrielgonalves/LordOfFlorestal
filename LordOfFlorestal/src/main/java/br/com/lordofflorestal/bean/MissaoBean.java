/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.model.Missao;
import br.com.lordofflorestal.rn.CartaRN;
import br.com.lordofflorestal.rn.JogadorRN;
import br.com.lordofflorestal.rn.MissaoRN;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.ArrayList;
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

    private Jogador jogador;
    private List<Jogador> jogadoresSelecionados;
    private List<Jogador> jogadores;

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

    public String selecionar() {
        jogadoresSelecionados.add(jogador);
        jogadores.remove(jogador);
        return null;
    }

    public String remover() {
        jogadoresSelecionados.remove(jogador);
        jogadores.add(jogador);
        return null;
    }

    public String enviar() {
        JogadorRN jogadorRN = new JogadorRN();
        for (Jogador j : jogadoresSelecionados) {
            jogadorRN.inserirCartaJogador(missao.getCarta(), j);
        }
        MessageUtil.info("Cartas envidas com sucesso.");
        return null;
    }

    public MissaoBean() {
        missao = new Missao();
        jogador = new Jogador();
        jogadoresSelecionados = new ArrayList();
        JogadorRN jogadorRN = new JogadorRN();
        jogadores = jogadorRN.listar();
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

    public List<Missao> getMissoes() {
        MissaoRN missaoRN = new MissaoRN();
        return missaoRN.listar();
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public List<Jogador> getJogadoresSelecionados() {
        return jogadoresSelecionados;
    }

    public void setJogadoresSelecionados(List<Jogador> jogadoresSelecionados) {
        this.jogadoresSelecionados = jogadoresSelecionados;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

}
