/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.dao;

import br.com.lordofflorestal.model.Deck;
import java.util.List;

/**
 *
 * @author gabriel
 */
public interface DeckDAO {

    public void salvar(Deck deck);

    public void atualizar(Deck deck);

    public void excluir(Deck deck);

    public List<Deck> buscarPorJogador(Integer idJogador);

    public List<Deck> buscarPorDuelo(Integer idDuelo);

    public Deck buscarPorDueloEJogador(Integer idDuelo, Integer idJogador);

    public List<Deck> listar();
}
