/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.dao;

import br.com.lordofflorestal.model.EstatisticaJogador;
import br.com.lordofflorestal.model.Jogador;
import java.util.List;

/**
 *
 * @author gabriel
 */
public interface JogadorDAO {

    public void salvar(Jogador jogador);

    public void atualizar(Jogador jogador);

    public void excluir(Jogador jogador);

    public Jogador buscarPorLogin(String login);

    public Jogador buscarPorMatricula(Integer matricula);
    
    public EstatisticaJogador buscarEstatisticaJogador(Integer matricula);

    public List<Jogador> listar();
}
