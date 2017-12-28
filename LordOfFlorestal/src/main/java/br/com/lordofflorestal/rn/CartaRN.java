/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.SubtipoCarta;
import br.com.lordofflorestal.model.TipoCarta;
import br.com.lordofflorestal.mysql.CartaDAOMysql;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class CartaRN {

    private CartaDAOMysql cartaDAOMysql;

    public CartaRN() {
        this.cartaDAOMysql = new CartaDAOMysql();
    }

    public void salvar(Carta carta) {
        Integer id = carta.getId();
        if (id == 0) {
            this.cartaDAOMysql.salvar(carta);
            MessageUtil.info("Carta " + carta.getNome() + " salva com sucesso!");
        } else {
            this.cartaDAOMysql.atualizar(carta);
            MessageUtil.info("Carta " + carta.getNome() + " editada com sucesso!");
        }
    }

    public void excluir(Carta carta) {
        this.cartaDAOMysql.excluir(carta);
        MessageUtil.info("Carta " + carta.getNome() + " excluida com sucesso!");
    }

    public Carta buscarPorId(Integer idCarta) {
        return this.cartaDAOMysql.buscarPorId(idCarta);
    }

    public Carta buscarPorNome(String nome) {
        return this.cartaDAOMysql.buscarPorNome(nome);
    }

    public List<Carta> buscarPorTipo(TipoCarta tipoCarta) {
        return this.cartaDAOMysql.buscarPorTipo(tipoCarta);
    }

    public List<Carta> buscarPorSubTipo(SubtipoCarta subtipoCarta) {
        return this.cartaDAOMysql.buscarPorSubTipo(subtipoCarta);
    }

    public List<Carta> listar() {
        return this.cartaDAOMysql.listar();
    }
}
