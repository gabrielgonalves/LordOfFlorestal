/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.dao;

import br.com.lordofflorestal.model.CartaJogo;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import java.util.List;

/**
 *
 * @author gabriel
 */
public interface CartaJogoDAO {

    public void salvar(CartaJogo cartaJogo);

    public void atualizar(CartaJogo cartaJogo);

    public void excluir(CartaJogo cartaJogo);

    public CartaJogo buscarPorId(Integer idCarta);

    public List<CartaJogo> buscarPorTipo(TipoCarta tipoCarta);

    public List<CartaJogo> buscarPorSubTipo(SubtipoCarta subtipoCarta);

    public CartaJogo buscarPorJogadorEDuelo(Integer idJogador, Integer idDuelo);

    public List<CartaJogo> listar();
}
