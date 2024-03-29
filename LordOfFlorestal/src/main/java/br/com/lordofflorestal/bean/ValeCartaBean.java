/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.ValeCarta;
import br.com.lordofflorestal.rn.CartaRN;
import br.com.lordofflorestal.rn.ValeCartaRN;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class ValeCartaBean {

    private int qt;
    private Carta carta;
    private ValeCarta valeCarta = new ValeCarta();
    private List<ValeCarta> vales = new ArrayList();

    public String gerar() {
        for (int i = 0; i < qt; i++) {
            valeCarta.setCarta(carta);
            valeCarta.setValido(true);

            ValeCartaRN valeCartaRN = new ValeCartaRN();
            valeCartaRN.salvar(valeCarta);

            vales.add(valeCarta);
            valeCarta = new ValeCarta();
        }
        
        this.valeCarta = new ValeCarta();
        return "/adm/valecarta/cadastrar.xhtml";
    }

    public String novo() {
        this.valeCarta = new ValeCarta();
        return "/adm/valecarta/cadastrar.xhtml?faces-redirect=true";
    }
    
    public String excluir() {
        new ValeCartaRN().excluir(valeCarta);
        return null;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public ValeCarta getValeCarta() {
        return valeCarta;
    }

    public void setValeCarta(ValeCarta valeCarta) {
        this.valeCarta = valeCarta;
    }

    public List<ValeCarta> getVales() {
        return vales;
    }

    public void setVales(List<ValeCarta> vales) {
        this.vales = vales;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public List<Carta> getCartas() {
        CartaRN cartaRN = new CartaRN();
        return cartaRN.listar();
    }
    
    public List<ValeCarta> getValeCartas(){
        ValeCartaRN valeCartaRN = new ValeCartaRN();
        return valeCartaRN.listar();
    }
}
