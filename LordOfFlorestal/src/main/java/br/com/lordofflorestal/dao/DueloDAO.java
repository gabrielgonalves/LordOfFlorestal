/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.dao;

import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.model.SituacaoDuelo;
import java.util.List;

/**
 *
 * @author gabriel
 */
public interface DueloDAO {

    public void salvar(Duelo duelo);

    public void atualizar(Duelo duelo);

    public void excluir(Duelo duelo);

    public Duelo buscarPorId(Integer id);
    
    public List<Duelo> buscarPorSituacao(SituacaoDuelo situacaoDuelo);

    public List<Duelo> listar();
}
