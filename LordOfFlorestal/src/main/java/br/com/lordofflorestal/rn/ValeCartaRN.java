/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.dao.ValeCartaDAO;
import br.com.lordofflorestal.model.ValeCarta;
import br.com.lordofflorestal.util.DAOFactory;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class ValeCartaRN {

    private ValeCartaDAO valeCartaDAO;

    public ValeCartaRN() {
        valeCartaDAO = DAOFactory.criarValeCartaDAO();
    }

    public void salvar(ValeCarta valeCarta) {
        if (valeCarta.isValido()) {
            this.valeCartaDAO.salvar(valeCarta);
            MessageUtil.info("Vale carta " + valeCarta.getCodigo() + " salvo com sucesso!");
        } else {
            this.valeCartaDAO.atualizar(valeCarta);
            //MessageUtil.info("Vale carta " + valeCarta.getCodigo() + " editado com sucesso!");
        }
    }

    public void excluir(ValeCarta valeCarta) {
        this.valeCartaDAO.excluir(valeCarta);
        MessageUtil.info("Vale carta " + valeCarta.getCodigo() + " excluido com sucesso!");
    }

    public ValeCarta buscarPorCodigo(String codigo) {
        return this.valeCartaDAO.buscarPorCodigo(codigo);
    }

    public List<ValeCarta> buscarPorStatus(Boolean status) {
        return this.valeCartaDAO.buscarPorStatus(status);
    }

    public List<ValeCarta> buscarPorCarta(Integer idCarta) {
        return this.valeCartaDAO.buscarPorCarta(idCarta);
    }

    public List<ValeCarta> listar() {
        return this.valeCartaDAO.listar();
    }
}
