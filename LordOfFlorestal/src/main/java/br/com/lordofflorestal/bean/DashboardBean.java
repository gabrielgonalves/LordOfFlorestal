package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.model.SituacaoDuelo;
import br.com.lordofflorestal.control.DueloSingleton;
import br.com.lordofflorestal.rn.JogadorRN;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class DashboardBean {

    private Jogador jogador;
    private List<Duelo> duelos;
    private Calendar dataAtual;
    private Duelo dueloSelecionado;

    public DashboardBean() {
        this.jogador = new JogadorRN().buscarPorLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        duelos = DueloSingleton.getInstance().buscaDuelosMinCarta(jogador.getCartas().size());
    }

    public void atualizarDados() {
        dataAtual = Calendar.getInstance();
        duelos = DueloSingleton.getInstance().buscaDuelosMinCarta(jogador.getCartas().size());
    }

    public void removerDuelo() {
        try {
            for (int i = 0; i <= duelos.size(); i++) {
                if (duelos.get(i).getDataCriacao().getTimeInMillis() <= dataAtual.getTimeInMillis()) {
                    if (duelos.get(i).getSituacaoDuelo().equals(SituacaoDuelo.CRIADO)) {
                        duelos.get(i).setSituacaoDuelo(SituacaoDuelo.CANCELADO);
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {

        }
    }

    public String jogar() {
        dueloSelecionado.setOponente(jogador);
        dueloSelecionado.getDeckJogador2().setJogador(jogador);
        dueloSelecionado.setSituacaoDuelo(SituacaoDuelo.AGUARDANDO);
        return "juntar-se.xhtml?duelo=" + dueloSelecionado.getUri() + "&faces-redirect=true";
    }

    public String textoOponente(Jogador oponente) {
        return "Jogador 2: " + oponente.getLogin();
    }

    public boolean verificaSituacaoDuelo(Duelo duelo) {
        return !duelo.getSituacaoDuelo().equals(SituacaoDuelo.CRIADO);
    }

    public Calendar getDataAtual() {
        return dataAtual;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public List<Duelo> getDuelos() {
        return duelos;
    }

    public Duelo getDueloSelecionado() {
        return dueloSelecionado;
    }

    public void setDueloSelecionado(Duelo dueloSelecionado) {
        this.dueloSelecionado = dueloSelecionado;
    }

}
