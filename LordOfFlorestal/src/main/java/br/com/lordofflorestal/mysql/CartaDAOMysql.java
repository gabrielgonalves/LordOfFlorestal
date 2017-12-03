/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.mysql;

import br.com.lordofflorestal.dao.CartaDAO;
import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author gabriel
 */
public class CartaDAOMysql implements CartaDAO {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void salvar(Carta carta) {
        this.session.save(carta);
    }

    @Override
    public void atualizar(Carta carta) {
        this.session.update(carta);
    }

    @Override
    public void excluir(Carta carta) {
        this.session.delete(carta);
    }

    @Override
    public Carta buscarPorId(Integer idCarta) {
        String hql = "select c from Carta c where c.id = :idCarta";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("idCarta", idCarta);
        return (Carta) consulta.uniqueResult();
    }

    @Override
    public Carta buscarPorNome(String nome) {
        String hql = "select c from Carta c where c.nome = :nome";
        Query consulta = this.session.createQuery(hql);
        consulta.setString("nome", nome);
        return (Carta) consulta.uniqueResult();
    }

    @Override
    public List<Carta> buscarPorTipo(TipoCarta tipoCarta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Carta> buscarPorSubTipo(SubtipoCarta subtipoCarta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Carta> listar() {
        return this.session.createCriteria(Carta.class).list();
    }
}
