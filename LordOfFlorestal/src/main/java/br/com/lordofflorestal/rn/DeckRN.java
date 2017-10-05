/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.dao.DeckDAO;
import br.com.lordofflorestal.model.Deck;
import br.com.lordofflorestal.util.DAOFactory;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class DeckRN {
    
    private DeckDAO deckDAO;
    
    public DeckRN(){
        deckDAO = DAOFactory.criarDeckDAO();
    }
    
    public void salvar(Deck deck){
        if(deck.getJogador() == null){
            this.deckDAO.salvar(deck);
        } else {
            this.deckDAO.atualizar(deck);
        }
    }

    public void excluir(Deck deck){
        this.deckDAO.excluir(deck);
    }

    public List<Deck> buscarPorJogador(Integer matriculaJogador){
        return this.deckDAO.buscarPorJogador(matriculaJogador);
    }

    public List<Deck> buscarPorDuelo(Integer idDuelo){
        return this.deckDAO.buscarPorDuelo(idDuelo);
    }

    public Deck buscarPorDueloEJogador(Integer idDuelo, Integer matriculaJogador){
        return this.deckDAO.buscarPorDueloEJogador(idDuelo, matriculaJogador);
    }

    public List<Deck> listar(){
        return this.deckDAO.listar();
    }
    
}
