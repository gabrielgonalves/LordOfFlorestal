/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.model.ValeCarta;
import br.com.lordofflorestal.mysql.ValeCartaDAOMysql;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class ValeCartaRN {

    private ValeCartaDAOMysql valeCartaDAOMysql;

    public ValeCartaRN() {
        valeCartaDAOMysql = new ValeCartaDAOMysql();
    }

    public void salvar(ValeCarta valeCarta) {
        if (valeCarta.isValido()) {
            if (valeCarta.getCarta() == null) {
                MessageUtil.erro("É obrigatório selecionar uma carta");
            } else {
                this.valeCartaDAOMysql.salvar(valeCarta);
                MessageUtil.info("Vale carta " + valeCarta.getCodigo() + " salvo com sucesso!");
            }
        } else {
            this.valeCartaDAOMysql.atualizar(valeCarta);
            //MessageUtil.info("Vale carta " + valeCarta.getCodigo() + " editado com sucesso!");
        }
    }

    public void excluir(ValeCarta valeCarta) {
        this.valeCartaDAOMysql.excluir(valeCarta);
        MessageUtil.info("Vale carta " + valeCarta.getCodigo() + " excluido com sucesso!");
    }

    public ValeCarta buscarPorCodigo(String codigo) {
        return this.valeCartaDAOMysql.buscarPorCodigo(codigo);
    }

    public List<ValeCarta> buscarPorStatus(Boolean status) {
        return this.valeCartaDAOMysql.buscarPorStatus(status);
    }

    public List<ValeCarta> buscarPorCarta(Integer idCarta) {
        return this.valeCartaDAOMysql.buscarPorCarta(idCarta);
    }

    public List<ValeCarta> listar() {
        return this.valeCartaDAOMysql.listar();
    }
}
