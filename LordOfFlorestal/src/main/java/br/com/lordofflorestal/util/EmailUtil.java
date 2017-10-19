/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.util;

import br.com.lordofflorestal.util.exception.UtilException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author gabriel
 */
public class EmailUtil {

    public void enviarEmail(String de, String para, String assunto, String mensagem) throws UtilException {
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:comp/env");
            Session session = (Session) envContext.lookup("mail/Session");
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setMailSession(session);
            if (de != null) {
                email.setFrom(de);
            } else {
                Properties p = session.getProperties();
                email.setFrom(p.getProperty("mail.smtp.user"));
            }
            email.addTo(para);
            email.setSubject(assunto);
            email.setHtmlMsg(mensagem);
            email.setSentDate(new Date());
            email.send();
        } catch (EmailException | NamingException e) {
            throw new UtilException(e);
        }
    }
}
