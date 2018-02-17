/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.control;

/**
 *
 * @author gabriel
 */
public class BatepapoSingleton {

    private static BatepapoSingleton batepapoSingleton;
    private String mensagem = "";

    public BatepapoSingleton() {
        mensagem = "";
    }

    public static BatepapoSingleton getInstance() {
        if (batepapoSingleton == null) {
            batepapoSingleton = new BatepapoSingleton();
        }
        return batepapoSingleton;
    }
    
    public void limpar(){
        mensagem = "";
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
