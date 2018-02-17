/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.control.DueloSingleton;
import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.model.SituacaoDuelo;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class DueloSingletonBean {

    public List<Duelo> getDuelos() {
        return DueloSingleton.getInstance().getDuelos();
    }
    
    public SituacaoDuelo[] getSituacao(){
        return SituacaoDuelo.values();
    }
    
    public String excluirFinalizado(){
        DueloSingleton.getInstance().remover(SituacaoDuelo.FINALIZADO);
        return null;
    }
    
    public String excluirCancelado(){
        DueloSingleton.getInstance().remover(SituacaoDuelo.CANCELADO);
        return null;
    }
}
