/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.web.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author DAVID
 */
public class UtiLjsf {
    public static String guardaBlobEnFicheroTemporal(byte[] bytes, String nombreArchivo){
        String ubicacionImagen = null;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("") +
                File.separatorChar + "menu" + File.separatorChar
                 + "tmp" + File.separatorChar + nombreArchivo;
        
        File f = null;
        InputStream in = null;
        try{
            f = new File(path);
            in = new ByteArrayInputStream(bytes);
            FileOutputStream out = new FileOutputStream(f.getAbsolutePath());
            
            int c=0;
            while((c= in.read())>=0){
                out.write(c);
            }
            out.flush();
            out.close();
            ubicacionImagen="../../menu/tmp" + nombreArchivo;
        }catch(Exception e){
            System.out.println("No se pudo cargar la imagen");
        }
        return ubicacionImagen;
    }
}
