/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author gabriel
 */
public class MessageUtil {

    private static FacesMessage message;

    public static void info(String mensagem) {
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void aviso(String mensagem) {
        message = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void erro(String mensagem) {
        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
