/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.hibernate;

import br.com.lordofflorestal.dao.JogadorDAO;
import br.com.lordofflorestal.model.EstatisticaJogador;
import br.com.lordofflorestal.model.Jogador;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author gabriel
 */
public class JogadorDAOHibernate implements JogadorDAO {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void salvar(Jogador jogador) {
        this.session.save(jogador);
    }

    @Override
    public void atualizar(Jogador jogador) {
        this.session.merge(jogador);
    }

    @Override
    public void excluir(Jogador jogador) {
        this.session.delete(jogador);
    }

    @Override
    public Jogador buscarPorLogin(String login) {
        String hql = "select j from Jogador j where j.login = :login";
        Query consulta = this.session.createQuery(hql);
        consulta.setString("login", login);
        return (Jogador) consulta.uniqueResult();
    }

    @Override
    public Jogador buscarPorMatricula(Integer matricula) {
        String hql = "select j from Jogador j where j.matricula = :matricula";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("matricula", matricula);
        return (Jogador) consulta.uniqueResult();
    }
    
    @Override
    public EstatisticaJogador buscarEstatisticaJogador(Integer matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jogador> listar() {
        return (List<Jogador>) this.session.createCriteria(Jogador.class).list();
    }

}
