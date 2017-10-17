/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.util.conversor;

import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.rn.JogadorRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author gabriel
 */
@FacesConverter(forClass = Jogador.class)
public class JogadorConversor implements Converter{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        JogadorRN jogadorRN = new JogadorRN();
        return jogadorRN.buscarPorLogin(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && !"".equals(value)){
            return String.valueOf(((Jogador) value).getLogin());
        }
        return null;
    }
}
