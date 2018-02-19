/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.model.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Hendrix
 */
@Named
@SessionScoped
public class UsuarioSesionBean implements Serializable{
    
    private Usuario usuario;
    
    public String getNombre() {
        return this.usuario.getNombre();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
