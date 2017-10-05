/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.dao;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import java.util.List;

/**
 *
 * @author gabriel
 */
public interface CartaDAO {

    public void salvar(Carta carta);

    public void atualizar(Carta carta);

    public void excluir(Carta carta);

    public Carta buscarPorId(Integer idCarta);

    public Carta buscarPorNome(String nome);

    public List<Carta> buscarPorTipo(TipoCarta tipoCarta);

    public List<Carta> buscarPorSubTipo(SubtipoCarta subtipoCarta);

    public List<Carta> listar();
}
