/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.web;

import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.model.ValeCarta;
import br.com.lordofflorestal.rn.JogadorRN;
import br.com.lordofflorestal.rn.ValeCartaRN;
import br.com.lordofflorestal.util.MessageUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class ResgateBean {

    private String codigo;
    private int idCarta;
    private Jogador jogador;

    public String validar() {
        JogadorRN jogadorRN = new JogadorRN();
        String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        jogador = jogadorRN.buscarPorLogin(login);

        ValeCartaRN valeCartaRN = new ValeCartaRN();
        ValeCarta valeCarta = valeCartaRN.buscarPorCodigo(codigo);

        if (valeCarta != null && valeCarta.isValido()) {
            if (!jogador.getCartas().contains(valeCarta.getCarta())) {
                valeCarta.setValido(false);
                valeCartaRN.salvar(valeCarta);
                jogador.getCartas().add(valeCarta.getCarta());
                jogadorRN.salvar(jogador);
                MessageUtil.info("Cupom " + valeCarta.getCodigo() + " resgatado com sucesso!");
                return null;
            }
        }
        MessageUtil.erro("O cupom " + valeCarta.getCodigo() + " é inválido ou já foi utilizado!");
        return null;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }
}
