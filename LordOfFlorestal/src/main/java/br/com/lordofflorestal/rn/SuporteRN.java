/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.model.Suporte;
import br.com.lordofflorestal.mysql.SuporteMysql;

/**
 *
 * @author gabriel
 */
public class SuporteRN {
    
    private SuporteMysql suporteMysql;
    
    public SuporteRN() {
        this.suporteMysql = new SuporteMysql();
    }
    
    public void enviar(Suporte suporte){
        this.suporteMysql.salvar(suporte);
    }
    
}
