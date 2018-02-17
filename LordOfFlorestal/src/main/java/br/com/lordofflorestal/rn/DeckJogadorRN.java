/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.rn;

import br.com.lordofflorestal.model.DeckJogador;
import br.com.lordofflorestal.model.Missao;
import br.com.lordofflorestal.mysql.DeckJogadorDAOMysql;
import br.com.lordofflorestal.mysql.MissaoDAOMysql;
import br.com.lordofflorestal.util.MessageUtil;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class DeckJogadorRN {

    private DeckJogadorDAOMysql deckJogadorDAOMysql;

    public DeckJogadorRN() {
        deckJogadorDAOMysql = new DeckJogadorDAOMysql();
    }

    public void salvar(DeckJogador deckJogador) {
       if(deckJogador.getId() == 0){
           this.deckJogadorDAOMysql.salvar(deckJogador);
           MessageUtil.info("Deck " + deckJogador.getNome() + " salvo com sucesso");
       } else {
           this.deckJogadorDAOMysql.atualizar(deckJogador);
           MessageUtil.info("Deck "+ deckJogador.getNome() +" atualizado com sucesso");
       }
    }

    public void excluir(DeckJogador deckJogador) {
        this.deckJogadorDAOMysql.excluir(deckJogador);
        MessageUtil.info("Deck " + deckJogador.getNome() + " excluido com sucesso!");
    }
    
    public List<DeckJogador> buscaPorJogador(int matricula){
        return this.deckJogadorDAOMysql.buscaPorJogador(matricula);
    }
    
    public DeckJogador buscaPorId(int id){
        return this.deckJogadorDAOMysql.buscaPorId(id);
    }
}
