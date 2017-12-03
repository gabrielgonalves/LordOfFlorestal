/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.mysql;

import br.com.lordofflorestal.dao.ValeCartaDAO;
import br.com.lordofflorestal.model.ValeCarta;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author gabriel
 */
public class ValeCartaDAOMysql implements ValeCartaDAO {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void salvar(ValeCarta valeCarta) {
        this.session.save(valeCarta);
    }

    @Override
    public void atualizar(ValeCarta valeCarta) {
        this.session.update(valeCarta);
    }

    @Override
    public void excluir(ValeCarta valeCarta) {
        this.session.delete(valeCarta);
    }

    @Override
    public ValeCarta buscarPorCodigo(String codigo) {
        String hql = "select v from ValeCarta v where v.codigo = :codigo";
        Query consulta = this.session.createQuery(hql);
        consulta.setString("codigo", codigo);
        return (ValeCarta) consulta.uniqueResult();
    }

    @Override
    public List<ValeCarta> buscarPorStatus(Boolean status) {
        String hql = "select v from ValeCarta v where v.status = :status";
        Query consulta = this.session.createQuery(hql);
        consulta.setBoolean("codigo", status);
        return (List<ValeCarta>) consulta.list();
    }

    @Override
    public List<ValeCarta> buscarPorCarta(Integer idCarta) {
        String hql = "select v from ValeCarta v where v.carta = :idCarta";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("idCarta", idCarta);
        return (List<ValeCarta>) consulta.list();
    }

    @Override
    public List<ValeCarta> listar() {
        return (List<ValeCarta>) this.session.createCriteria(ValeCarta.class).list();
    }

}
