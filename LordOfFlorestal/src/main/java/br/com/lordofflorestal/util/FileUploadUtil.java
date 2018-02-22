/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.util;

import br.com.lordofflorestal.model.Duelo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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

    public void uploadLog(Duelo duelo) {
        try {
            FileWriter arq = new FileWriter(diretorioRaizLog() + "/" + duelo.getUri() + ".txt");
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.printf("Duelo: %s\n", duelo.getUri());
            gravarArq.printf("Situação: %s\n", duelo.getSituacaoDuelo());
            gravarArq.printf("Jogador: %s\n", duelo.getCriadoPor().getLogin());
            if (duelo.getOponente() != null) {
                gravarArq.printf("Oponente: %s\n", duelo.getOponente().getLogin());
            }
            gravarArq.printf("Data de Criação: %s\n", duelo.getDataCriacao().getTime());
            gravarArq.printf("Histórico de ações: \n\n");
            gravarArq.printf("%s", duelo.getBatePapo());
            
            arq.close();
        } catch (IOException e) {
        }

    }

    public String diretorioRaiz() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        return servletContext.getRealPath("") + "resources/img";
    }

    public String diretorioRaizCarta() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        return servletContext.getRealPath("") + "resources/cartas";
    }

    public String diretorioRaizLog() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        return servletContext.getRealPath("") + "resources/logs";
    }
}
