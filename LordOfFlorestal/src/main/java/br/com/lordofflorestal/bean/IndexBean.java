/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.Ranking;
import br.com.lordofflorestal.rn.JogadorRN;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ApplicationScoped
public class IndexBean {

    private List<Ranking> ranking;

    public IndexBean() {
    }

    public List<Ranking> getRanking() {
        return ranking;
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
}
