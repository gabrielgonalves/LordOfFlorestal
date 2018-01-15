/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

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
    private List<String> imagens;

    public IndexBean() {
        imagens = new ArrayList();
        imagens.add("tela.png");
        imagens.add("tela2.png");
        imagens.add("tela3.png");
        imagens.add("tela4.png");
        imagens.add("tela5.png");
        imagens.add("tela6.png");
        imagens.add("tela7.png");
    }

    public List<String> getImagens() {
        return imagens;
    }
}
