/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.web;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.rn.JogadorRN;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class DashboardBean {

    private Jogador jogador;
    private Carta carta;
    private ArrayList<String> tarefas;
    private ArrayList<String> ranking;

    private PieChartModel grafico;
    
    HttpServletRequest request;

    @PostConstruct
    public void init() {
        createPieModels();
    }

    private void createPieModels() {
        createPieModel1();
    }

    private void createPieModel1() {
        grafico = new PieChartModel();

        grafico.set("Ganhou", jogador.getEstatisticaJogador().getNumJogosGanho());
        grafico.set("Perdeu", jogador.getEstatisticaJogador().getNumJogosPerdido());
        grafico.set("Lord", jogador.getEstatisticaJogador().getNumJogosGanhoLord());

        grafico.setTitle("Número de jogos: "+jogador.getEstatisticaJogador().getNumJogos());
        grafico.setLegendPosition("w");
    }

    public DashboardBean() {
        JogadorRN jogadorRN = new JogadorRN();
        String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        jogador = jogadorRN.buscarPorLogin(login);
        
        tarefas = new ArrayList();
        ranking = new ArrayList();
        for (int i = 0; i < 3; i++) {
            tarefas.add("Tarefa " + i);
        }
        for (int i = 0; i < 10; i++) {
            ranking.add("Pessoa " + i);
        }
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public ArrayList<String> getTarefas() {
        return tarefas;
    }

    public ArrayList<String> getRanking() {
        return ranking;
    }

    public PieChartModel getGrafico() {
        return grafico;
    }
}
