/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.dao.CartaDAO;
import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import br.com.lordofflorestal.util.DAOFactory;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class CartaRN {

    private CartaDAO cartaDAO;

    public CartaRN() {
        this.cartaDAO = DAOFactory.criarCartaDAO();
    }

    public void salvar(Carta carta) {
        Integer id = carta.getId();
        if (id == null || id == 0) {
            this.cartaDAO.salvar(carta);
            MessageUtil.info("Carta " + carta.getNome() + " salva com sucesso!");
        } else {
            this.cartaDAO.atualizar(carta);
            MessageUtil.info("Carta " + carta.getNome() + " editada com sucesso!");
        }
    }

    public void excluir(Carta carta) {
        this.cartaDAO.excluir(carta);
        MessageUtil.info("Carta " + carta.getNome() + " excluida com sucesso!");
    }

    public Carta buscarPorId(Integer idCarta) {
        return this.cartaDAO.buscarPorId(idCarta);
    }

    public Carta buscarPorNome(String nome) {
        return this.cartaDAO.buscarPorNome(nome);
    }

    public List<Carta> buscarPorTipo(TipoCarta tipoCarta) {
        return this.cartaDAO.buscarPorTipo(tipoCarta);
    }

    public List<Carta> buscarPorSubTipo(SubtipoCarta subtipoCarta) {
        return this.cartaDAO.buscarPorSubTipo(subtipoCarta);
    }

    public List<Carta> listar() {
        return this.cartaDAO.listar();
    }
}
