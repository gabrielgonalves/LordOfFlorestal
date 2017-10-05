/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.util;

import br.com.lordofflorestal.dao.CartaDAO;
import br.com.lordofflorestal.dao.CartaJogoDAO;
import br.com.lordofflorestal.dao.DeckDAO;
import br.com.lordofflorestal.dao.DueloDAO;
import br.com.lordofflorestal.dao.JogadorDAO;
import br.com.lordofflorestal.dao.ValeCartaDAO;
import br.com.lordofflorestal.hibernate.CartaDAOHibernate;
import br.com.lordofflorestal.hibernate.CartaJogoDAOHibernate;
import br.com.lordofflorestal.hibernate.DeckDAOHibernate;
import br.com.lordofflorestal.hibernate.DueloDAOHibernate;
import br.com.lordofflorestal.hibernate.JogadorDAOHibernate;
import br.com.lordofflorestal.hibernate.ValeCartaDAOHibernate;

/**
 *
 * @author gabriel
 */
public class DAOFactory {

    public static CartaDAO criarCartaDAO() {
        CartaDAOHibernate cartaDAO = new CartaDAOHibernate();
        cartaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return cartaDAO;
    }
    
    public static CartaJogoDAO criarCartaJogoDAO() {
        CartaJogoDAOHibernate cartaJogoDAO = new CartaJogoDAOHibernate();
        cartaJogoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return cartaJogoDAO;
    }
    
    public static DeckDAO criarDeckDAO() {
        DeckDAOHibernate deckDAOHibernate = new DeckDAOHibernate();
        deckDAOHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return deckDAOHibernate;
    }
    
    public static DueloDAO criarDueloDAO() {
        DueloDAOHibernate dueloDAOHibernate = new DueloDAOHibernate();
        dueloDAOHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return dueloDAOHibernate;
    }
    
    public static JogadorDAO criarJogadorDAO() {
        JogadorDAOHibernate jogadorDAO = new JogadorDAOHibernate();
        jogadorDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return jogadorDAO;
    }
    
    public static ValeCartaDAO criarValeCartaDAO() {
        ValeCartaDAOHibernate valeCartaDAOHibernate = new ValeCartaDAOHibernate();
        valeCartaDAOHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return valeCartaDAOHibernate;
    }
}
