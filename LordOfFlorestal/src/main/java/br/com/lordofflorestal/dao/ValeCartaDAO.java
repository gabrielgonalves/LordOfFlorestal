/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.dao;

import br.com.lordofflorestal.model.ValeCarta;
import java.util.List;

/**
 *
 * @author gabriel
 */
public interface ValeCartaDAO {

    public void salvar(ValeCarta valeCarta);

    public void atualizar(ValeCarta valeCarta);

    public void excluir(ValeCarta valeCarta);

    public ValeCarta buscarPorCodigo(String codigo);
    
    public List<ValeCarta> buscarPorStatus(Boolean status);
    
    public List<ValeCarta> buscarPorCarta(Integer idCarta);
    
    public List<ValeCarta> listar();
}
