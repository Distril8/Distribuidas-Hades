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
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author user
 */
@Entity(noClassnameStored = true, value = "turista")
public class TuristaReserva extends BaseEntity {
    
    @Indexed(options = @IndexOptions(name = "turista_codigoUIdx", unique = true))
    private Integer codigo;
    private String tipoIdentificacion;
    private String identificacion;
    private String nombre;
    private Date fechaNacimiento;
    private BigDecimal pesoMaleta;
    private Integer codReserva;
    private BigDecimal valorMaleta;

    @Reference
    private Reserva Reserva;
   

    public TuristaReserva() {
    }

    
    
    public TuristaReserva(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(Integer codReserva) {
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
    
    public Reserva getReserva() {
        return Reserva;
    }

    public void setReserva(Reserva Reserva) {
        this.Reserva = Reserva;
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
        if (!(object instanceof TuristaReserva)) {
            return false;
        }
        TuristaReserva other = (TuristaReserva) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TuristaReserva{" + "codigo=" + codigo + ", tipoIdentificacion=" + tipoIdentificacion + ", identificacion=" + identificacion + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", pesoMaleta=" + pesoMaleta + ", codReserva=" + codReserva + ", valorMaleta=" + valorMaleta + ", Reserva=" + Reserva + '}';
    }
       
}
