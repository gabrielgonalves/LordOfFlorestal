/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.dao.DueloDAO;
import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.model.SituacaoDuelo;
import br.com.lordofflorestal.util.DAOFactory;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class DueloRN {

    DueloDAO dueloDAO;

    public DueloRN() {
        dueloDAO = DAOFactory.criarDueloDAO();
    }

    public void salvar(Duelo duelo){
        Integer id = duelo.getId();
        if(id == null || id == 0){
            this.dueloDAO.salvar(duelo);
        } else {
            this.dueloDAO.atualizar(duelo);
        }
    }

    public void excluir(Duelo duelo){
        this.dueloDAO.excluir(duelo);
    }

    public Duelo buscarPorId(Integer id){
        return this.dueloDAO.buscarPorId(id);
    }

    public List<Duelo> buscarPorSituacao(SituacaoDuelo situacaoDuelo){
        return this.dueloDAO.buscarPorSituacao(situacaoDuelo);
    }

    public List<Duelo> listar(){
        return  this.dueloDAO.listar();
    }

}
