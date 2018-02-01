/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.mysql.DueloDAOMysql;

/**
 *
 * @author gabriel
 */
public class DueloRN {

    private DueloDAOMysql dueloDAOMysql;

    public DueloRN() {
        this.dueloDAOMysql = new DueloDAOMysql();
    }

    public void salvar(Duelo duelo, int vencedor) {
        if (!dueloDAOMysql.jaInserido(duelo)) {
            this.dueloDAOMysql.salvar(duelo, vencedor);
        }
    }

}
