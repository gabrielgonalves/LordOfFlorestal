/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author gabriel
 */
public class FileUploadUtil {

    public void upload(UploadedFile uploadedFile, String nome) {
        try {
            File file = new File(diretorioRaiz(), nome);

            OutputStream out = new FileOutputStream(file);
            out.write(uploadedFile.getContents());
            out.close();
        } catch (IOException e) {
        }

    }
    
    public void uploadCarta(UploadedFile uploadedFile, String nome) {
        try {
            File file = new File(diretorioRaizCarta(), nome);

            OutputStream out = new FileOutputStream(file);
            out.write(uploadedFile.getContents());
            out.close();
        } catch (IOException e) {
        }

    }
    
    public String diretorioRaiz(){
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        return servletContext.getRealPath("") + "resources/img";
    }
    
    public String diretorioRaizCarta(){
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        return servletContext.getRealPath("") + "resources/cartas";
    }
}
