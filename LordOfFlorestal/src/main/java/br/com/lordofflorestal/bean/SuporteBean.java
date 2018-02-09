/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.model.Suporte;
import br.com.lordofflorestal.rn.SuporteRN;
import br.com.lordofflorestal.util.MessageUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class SuporteBean implements Serializable {

    private Suporte suporte;

    public SuporteBean() {
        suporte = new Suporte();
    }

    public String enviar() {
        new SuporteRN().enviar(suporte);
        MessageUtil.info("Mensagem enviada com sucesso!");
        return null;
    }

    public Suporte getSuporte() {
        return suporte;
    }

    public void setSuporte(Suporte suporte) {
        this.suporte = suporte;
    }

}
