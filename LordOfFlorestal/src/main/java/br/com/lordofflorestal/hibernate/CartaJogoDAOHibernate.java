/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.hibernate;

import br.com.lordofflorestal.dao.CartaJogoDAO;
import br.com.lordofflorestal.model.CartaJogo;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author gabriel
 */
public class CartaJogoDAOHibernate implements CartaJogoDAO {

    Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void salvar(CartaJogo cartaJogo) {
        this.session.save(cartaJogo);
    }

    @Override
    public void atualizar(CartaJogo cartaJogo) {
        this.session.update(cartaJogo);
    }

    @Override
    public void excluir(CartaJogo cartaJogo) {
        this.session.delete(cartaJogo);
    }

    @Override
    public CartaJogo buscarPorId(Integer idCarta) {
        String hql = "select c from Carta c where c.id = :idCarta";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("idCarta", idCarta);
        return (CartaJogo) consulta.uniqueResult();
    }

    @Override
    public List<CartaJogo> buscarPorTipo(TipoCarta tipoCarta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CartaJogo> buscarPorSubTipo(SubtipoCarta subtipoCarta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CartaJogo buscarPorJogadorEDuelo(Integer idJogador, Integer idDuelo) {
        String hql = "select c from Carta c where c.idJogador = :idJogador and c.idDuelo = :idDuelo";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("idJogador", idJogador);
        consulta.setInteger("idDuelo", idDuelo);
        return (CartaJogo) consulta.uniqueResult();
    }

    @Override
    public List<CartaJogo> listar() {
        return (List<CartaJogo>) this.session.createCriteria(CartaJogo.class).list();
    }

}
