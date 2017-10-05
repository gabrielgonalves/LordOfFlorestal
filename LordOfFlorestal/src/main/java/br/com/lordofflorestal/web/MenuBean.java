/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author gabriel
 */
@ManagedBean
@RequestScoped
public class MenuBean {

    public String getItemCssClass(String viewId) {
        FacesContext context = FacesContext.getCurrentInstance();
        String currentViewId = context.getViewRoot().getViewId();


        return currentViewId.equals(viewId) ? "is-selected" : null;
    }
}
