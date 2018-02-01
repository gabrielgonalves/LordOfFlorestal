/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.DuelosJogador;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.rn.JogadorRN;
import br.com.lordofflorestal.util.FileUploadUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class PerfilBean implements Serializable {

    private Jogador jogador;

    private UploadedFile img;

    private PieChartModel grafico;

    private List<DuelosJogador> duelosJogadores;

    public PerfilBean() {
        duelosJogadores = new ArrayList();
        JogadorRN jogadorRN = new JogadorRN();
        String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if (login != null) {
            jogador = jogadorRN.buscarPorLogin(login);
            duelosJogadores = jogadorRN.buscaDuelosJogador(jogador.getMatricula());
        }
    }

    @PostConstruct
    public void init() {
        criarGrafico();
    }

    public String salvar() {
        JogadorRN jogadorRN = new JogadorRN();
        if (img != null) {
            if (img.getSize() != 0) {
                String extensao[] = img.getContentType().split("/");
                jogador.setImagem(jogador.getLogin() + "." + extensao[extensao.length - 1]);
                new FileUploadUtil().upload(img, jogador.getLogin() + "." + extensao[extensao.length - 1]);
            }
        }
        jogadorRN.salvar(jogador);

        return "/jogador/perfil.xhtml";
    }

    private void criarGrafico() {
        grafico = new PieChartModel();

        grafico.set("Ganhou", jogador.getEstatisticaJogador().getNumJogosGanho());
        grafico.set("Perdeu", jogador.getEstatisticaJogador().getNumJogosPerdido());
        grafico.set("Lord", jogador.getEstatisticaJogador().getNumJogosGanhoLord());

        grafico.setTitle("Total de jogos: " + jogador.getEstatisticaJogador().getNumJogos());
        grafico.setLegendPosition("w");
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }

    public PieChartModel getGrafico() {
        return grafico;
    }

    public void setGrafico(PieChartModel grafico) {
        this.grafico = grafico;
    }

    public List<DuelosJogador> getDuelosJogadores() {
        return duelosJogadores;
    }

    public void setDuelosJogadores(List<DuelosJogador> duelosJogadores) {
        this.duelosJogadores = duelosJogadores;
    }
}
