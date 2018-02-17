/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.model.Missao;
import br.com.lordofflorestal.mysql.MissaoDAOMysql;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class MissaoRN {

    private MissaoDAOMysql missaoDAOMysql;

    public MissaoRN() {
        missaoDAOMysql = new MissaoDAOMysql();
    }

    public void salvar(Missao missao) {
       if(missao.getId() == 0){
           this.missaoDAOMysql.salvar(missao);
           MessageUtil.info("Missão salva com sucesso");
       } else {
           this.missaoDAOMysql.atualizar(missao);
           MessageUtil.info("Missão atualizada com sucesso");
       }
    }

    public void excluir(Missao missao) {
        this.missaoDAOMysql.excluir(missao);
        MessageUtil.info("Missão " + missao.getId() + " excluida com sucesso!");
    }
    
    public Missao buscaPorId(int id){
        return this.missaoDAOMysql.buscaPorId(id);
    }

    public List<Missao> listar() {
        return this.missaoDAOMysql.listar();
    }
    
    public List<Missao> listarAtivas() {
        return this.missaoDAOMysql.listarAtivas();
    }
}
