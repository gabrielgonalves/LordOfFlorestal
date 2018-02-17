package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.control.BatepapoSingleton;
import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.model.SituacaoDuelo;
import br.com.lordofflorestal.control.DueloSingleton;
import br.com.lordofflorestal.model.Missao;
import br.com.lordofflorestal.model.Ranking;
import br.com.lordofflorestal.rn.JogadorRN;
import br.com.lordofflorestal.rn.MissaoRN;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
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
    private String mensagem;
    private List<Ranking> ranking;

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

    public String enviarMensagem() {
        Calendar data = Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        if (!mensagem.trim().isEmpty()) {
            BatepapoSingleton.getInstance().setMensagem(sdf.format(data.getTime()) + " " + jogador.getLogin() + ": " + mensagem + "\n" + BatepapoSingleton.getInstance().getMensagem());
        }
        mensagem = "";
        return null;
    }

    public String limparChat() {
        BatepapoSingleton.getInstance().limpar();
        return null;
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getBatePapo() {
        return BatepapoSingleton.getInstance().getMensagem();
    }

    public List<Ranking> getRanking5() {
        ranking = new JogadorRN().ranking();
        List<Ranking> lista = new ArrayList();
        for (int i = 0; i < 5; i++) {
            ranking.get(i).setLogin(i + 1 + ".   " + ranking.get(i).getLogin());
            lista.add(ranking.get(i));
        }
        return lista;
    }

    public List<Ranking> getRanking10() {
        ranking = new JogadorRN().ranking();
        List<Ranking> lista = new ArrayList();
        for (int i = 5; i < 10; i++) {
            ranking.get(i).setLogin(i + 1 + ".   " + ranking.get(i).getLogin());
            lista.add(ranking.get(i));
        }
        return lista;
    }

    public List<Missao> getMissoes() {
        return new MissaoRN().listarAtivas();
    }

}
