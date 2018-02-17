/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.model;

import ec.edu.espe.distribuidas.nosql.mongo.BaseEntity;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author hendrix
 */
@Entity(noClassnameStored = true, value = "camarote")
public class Camarote extends BaseEntity{
    
    @Indexed(options = @IndexOptions(name = "camarote_codigoUIdx", unique = true))
    private Integer codigo;
    @Reference
    private Crucero crucero;
    @Reference
    private TipoCamarote tipo;
    private Integer numero;
    private Integer capacidad;
    private String ubicacion;
    private String nomCrucero;
    private String nomTipo;

    public Camarote(Integer codigo) {
        this.codigo = codigo;
    }

    public Camarote() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNomCrucero() {
        return nomCrucero;
    }

    public void setNomCrucero(String nomCrucero) {
        this.nomCrucero = nomCrucero;
    }

    public String getNomTipo() {
        return nomTipo;
    }

    public void setNomTipo(String nomTipo) {
        this.nomTipo = nomTipo;
    }

   
    
    
    public Crucero getCrucero() {
        return crucero;
    }

    public void setCrucero(Crucero crucero) {
        this.crucero = crucero;
    }

    public TipoCamarote getTipo() {
        return tipo;
    }

    public void setTipo(TipoCamarote tipo) {
        this.tipo = tipo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crucero)) {
            return false;
        }
        Camarote other = (Camarote) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.distribuidas.hades.model.Camarote[ codigo=" + codigo + " ]";
    }

    
}
