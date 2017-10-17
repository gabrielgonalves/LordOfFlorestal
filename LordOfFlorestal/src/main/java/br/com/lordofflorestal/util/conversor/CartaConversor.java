/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.util.conversor;

import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.rn.CartaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author gabriel
 */
@FacesConverter(forClass = Carta.class, value = "cartaconverter")
public class CartaConversor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        CartaRN cartaRN = new CartaRN();
        System.out.println(value + "obj");
        if (value != null && !"".equals(value)) {
            return cartaRN.buscarPorId(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !"".equals(value)) {
            return String.valueOf(((Carta) value).getId());
        }
        return null;
    }

}
