/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.web;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author gabriel
 */
@ManagedBean
@RequestScoped
public class DashboardBean {

    private ArrayList<String> cartas;
    private ArrayList<String> tarefas;
    private ArrayList<String> ranking;

    private PieChartModel pieModel1;

    @PostConstruct
    public void init() {
        createPieModels();
    }

    private void createPieModels() {
        createPieModel1();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        pieModel1.set("Ganhou", 540);
        pieModel1.set("Perdeu", 325);
        pieModel1.set("Desistiu", 112);

        pieModel1.setTitle("Jogos");
        pieModel1.setLegendPosition("w");
    }

    public DashboardBean() {
        cartas = new ArrayList();
        tarefas = new ArrayList();
        ranking = new ArrayList();
        for (int i = 1; i < 19; i++) {
            cartas.add("/cartas/" + i + ".jpg");
        }
        for (int i = 0; i < 3; i++) {
            tarefas.add("Tarefa " + i);
        }
        for (int i = 0; i < 10; i++) {
            ranking.add("Pessoa " + i);
        }
    }

    public ArrayList<String> getCartas() {
        return cartas;
    }

    public ArrayList<String> getTarefas() {
        return tarefas;
    }

    public ArrayList<String> getRanking() {
        return ranking;
    }

    public PieChartModel getGrafico() {
        return pieModel1;
    }
}
