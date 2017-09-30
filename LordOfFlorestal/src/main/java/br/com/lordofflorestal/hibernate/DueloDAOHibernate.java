/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.hibernate;

import br.com.lordofflorestal.dao.DueloDAO;
import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.model.SituacaoDuelo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author gabriel
 */
public class DueloDAOHibernate implements DueloDAO {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void salvar(Duelo duelo) {
        this.session.save(duelo);
    }

    @Override
    public void atualizar(Duelo duelo) {
        this.session.update(duelo);
    }

    @Override
    public void excluir(Duelo duelo) {
        this.session.delete(duelo);
    }

    @Override
    public Duelo buscarPorId(Integer id) {
        String hql = "select d from Duelo d where d.id = :idDuelo";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("idDuelo", id);
        return (Duelo) consulta.uniqueResult();
    }

    @Override
    public List<Duelo> buscarPorSituacao(SituacaoDuelo situacaoDuelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Duelo> listar() {
        return (List<Duelo>) this.session.createCriteria(Duelo.class).list();
    }

}
