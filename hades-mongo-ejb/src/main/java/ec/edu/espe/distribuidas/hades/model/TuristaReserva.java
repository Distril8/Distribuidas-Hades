/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.model;

import ec.edu.espe.distribuidas.nosql.mongo.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import org.mongodb.morphia.annotations.Entity;

/**
 *
 * @author user
 */
@Entity(noClassnameStored = true, value = "turista")
public class TuristaReserva extends BaseEntity {

    private String tipoIdentificacion;
    private String identificacion;
    private String nombre;
    private Date fechaNacimiento;
    private BigDecimal pesoMaleta;
    private String codReserva;
    private BigDecimal valorMaleta;

    public TuristaReserva() {
    }

    public String getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(String codReserva) {
        this.codReserva = codReserva;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public BigDecimal getPesoMaleta() {
        return pesoMaleta;
    }

    public void setPesoMaleta(BigDecimal pesoMaleta) {
        this.pesoMaleta = pesoMaleta;
    }

    public BigDecimal getValorMaleta() {
        return valorMaleta;
    }

    public void setValorMaleta(BigDecimal valorMaleta) {
        this.valorMaleta = valorMaleta;
    }

    @Override
    public String toString() {
        return "TuristaReserva{" + "tipoIdentificacion=" + tipoIdentificacion + ", identificacion=" + identificacion + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", pesoMaleta=" + pesoMaleta + ", codReserva=" + codReserva + ", valorMaleta=" + valorMaleta + '}';
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (super.id != null ? super.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TuristaReserva)) {
            return false;
        }
        TuristaReserva other = (TuristaReserva) object;
        if ((super.id == null && other.id != null) || (super.id != null && !super.id.equals(super.id))) {
            return false;
        }
        return true;
    }
}
