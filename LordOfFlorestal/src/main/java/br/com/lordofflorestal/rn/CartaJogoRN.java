/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.dao.CartaJogoDAO;
import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.CartaJogo;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import br.com.lordofflorestal.util.DAOFactory;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class CartaJogoRN {

    private CartaJogoDAO cartaJogoDAO;

    public CartaJogoRN() {
        this.cartaJogoDAO = DAOFactory.criarCartaJogoDAO();
    }

    public void salvar(CartaJogo cartaJogo) {
        Integer id = cartaJogo.getId();
        if (id == null || id == 0) {
            this.cartaJogoDAO.salvar(cartaJogo);
        } else {
            this.cartaJogoDAO.atualizar(cartaJogo);
        }
    }

    public void excluir(CartaJogo carta) {
        this.cartaJogoDAO.excluir(carta);
    }

    public CartaJogo buscarPorId(Integer idCarta) {
        return this.cartaJogoDAO.buscarPorId(idCarta);
    }

    public List<CartaJogo> buscarPorTipo(TipoCarta tipoCarta) {
        return this.cartaJogoDAO.buscarPorTipo(tipoCarta);
    }

    public List<CartaJogo> buscarPorSubTipo(SubtipoCarta subtipoCarta) {
        return this.cartaJogoDAO.buscarPorSubTipo(subtipoCarta);
    }

    public CartaJogo buscarPorJogadorEDuelo(Integer idJogador, Integer idDuelo) {
        return this.cartaJogoDAO.buscarPorJogadorEDuelo(idJogador, idDuelo);
    }

    public List<CartaJogo> listar() {
        return this.cartaJogoDAO.listar();
    }
}
