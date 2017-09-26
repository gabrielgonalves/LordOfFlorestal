/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.hibernate;

import br.com.lordofflorestal.dao.DeckDAO;
import br.com.lordofflorestal.model.Deck;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author gabriel
 */
public class DeckDAOHibernate implements DeckDAO {

    Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void salvar(Deck deck) {
        this.session.save(deck);
    }

    @Override
    public void atualizar(Deck deck) {
        this.session.update(deck);
    }

    @Override
    public void excluir(Deck deck) {
        this.session.delete(deck);
    }

    @Override
    public List<Deck> buscarPorJogador(Integer idJogador) {
        String hql = "select d from Deck d where d.idJogador = :idJogador";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("idJogador", idJogador);
        return (List<Deck>) consulta.list();
    }

    @Override
    public List<Deck> buscarPorDuelo(Integer idDuelo) {
        String hql = "select d from Deck d where d.idDuelo = :idDuelo";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("idDuelo", idDuelo);
        return (List<Deck>) consulta.list();
    }

    @Override
    public Deck buscarPorDueloEJogador(Integer idDuelo, Integer idJogador) {
        String hql = "select d from Deck d where d.idDuelo = :idDuelo and d.idJogador = :idJogador";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("idDuelo", idDuelo);
        consulta.setInteger("idJogador", idJogador);
        return (Deck) consulta.uniqueResult();
    }

    @Override
    public List<Deck> listar() {
        return (List<Deck>) this.session.createCriteria(Deck.class).list();
    }

}
