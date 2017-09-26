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
            cartaJogo.setNome("Teste " + i);
            cartaJogo.setImagem("/cartas/"+(i+1)+".jpg");
            mao.add(cartaJogo);
        }
    }
    
    public void descer(){
        mesa.add(carta);
        for(int i=0;i<mao.size(); i++){
            if(carta.getNome().equals(mao.get(i).getNome())){
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
