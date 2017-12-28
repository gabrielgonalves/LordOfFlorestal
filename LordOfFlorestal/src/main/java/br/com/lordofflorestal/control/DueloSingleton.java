/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.control;

import br.com.lordofflorestal.model.Duelo;
import br.com.lordofflorestal.model.SituacaoDuelo;
import br.com.lordofflorestal.util.comparator.DueloComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class DueloSingleton {
    
    private List<Duelo> duelos;
    private static DueloSingleton dueloSingleton;
    
    private DueloSingleton() {
        duelos = new ArrayList();
    }
    
    public static DueloSingleton getInstance(){
        if(dueloSingleton == null){
            dueloSingleton = new DueloSingleton();
        }
        return dueloSingleton;
    }
    
    public void adicionar(Duelo duelo){
        duelos.add(duelo);
        Collections.sort(duelos, new DueloComparator());
    }
    
    public void remover(Duelo duelo){
        duelos.remove(duelo);
    }
    
    public Duelo buscaPorURI(String uri){
        for(Duelo duelo : duelos){
            if(duelo.getUri().equals(uri)){
                return duelo;
            }
        }
        return null;
    }
    
    public List<Duelo> buscaDuelosMinCarta(int min){
        List<Duelo> lista = new ArrayList();
        for(Duelo duelo : duelos){
            if(duelo.getDeckJogador1().getCartas().size() <= min && duelo.getSituacaoDuelo().equals(SituacaoDuelo.CRIADO)){
                lista.add(duelo);
            }
        }
        return lista;
    }

    public List<Duelo> getDuelos() {
        return duelos;
    }
    
}
