/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.web;

import br.com.lordofflorestal.model.CartaJogo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped 
public class JogoBean implements Serializable {

    private List<CartaJogo> mao = new ArrayList();
    private List<CartaJogo> mesa = new ArrayList();
    
    private CartaJogo carta;

    public JogoBean() {
        for (int i = 0; i < 18; i++) {
            CartaJogo cartaJogo = new CartaJogo();
            cartaJogo.setId(i+1);
            cartaJogo.setNome("Carta" + i+1);
            mao.add(cartaJogo);
        }
    }
    
    public void descer(DragDropEvent ddEvent){
        CartaJogo c = ((CartaJogo) ddEvent.getData());
        mesa.add(c);
        for(int i=0;i<mao.size(); i++){
            if(c.getId() == mao.get(i).getId()){
                mao.remove(i);
            }
        }
    }

    public CartaJogo getCarta() {
        return carta;
    }

    public void setCarta(CartaJogo carta) {
        this.carta = carta;
    }

    public List<CartaJogo> getMao() {
        return mao;
    }

    public List<CartaJogo> getMesa() {
        return mesa;
    }
}
