/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.bean;

import br.com.lordofflorestal.util.MessageUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gabriel
 */
@ManagedBean
public class LoginBean {

    private HttpServletRequest request;

    public void preRender() {
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String retorno = request.getParameter("login_error");
        if (retorno != null) {
            MessageUtil.erro("Usuário ou senha errados. Por favor tente outra vez.");
        }

    }

}
