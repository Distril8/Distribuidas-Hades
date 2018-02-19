/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.model;

import java.math.BigDecimal;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author hendrix
 */
@Entity(noClassnameStored = true, value = "precioCamarote")
public class PrecioCamarote {
    
    @Indexed(options = @IndexOptions(name = "precioCamarote_codigoUIdx", unique = true))
    private Integer codigo;
    
    @Reference
    private TipoCamarote tipoCamarote;
    @Reference
    private Tour tour;
    
    
    private BigDecimal porcentajeAdicional;
    private BigDecimal porecentajePersona;

    public TipoCamarote getTipoCamarote() {
        return tipoCamarote;
    }

    public void setTipoCamarote(TipoCamarote tipoCamarote) {
        this.tipoCamarote = tipoCamarote;
    }

    public BigDecimal getPorcentajeAdicional() {
        return porcentajeAdicional;
    }

    public void setPorcentajeAdicional(BigDecimal porcentajeAdicional) {
        this.porcentajeAdicional = porcentajeAdicional;
    }

    public BigDecimal getPorecentajePersona() {
        return porecentajePersona;
    }

    public void setPorecentajePersona(BigDecimal porecentajePersona) {
        this.porecentajePersona = porecentajePersona;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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
        if (!(object instanceof PrecioCamarote)) {
            return false;
        }
        PrecioCamarote other = (PrecioCamarote) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "ec.edu.espe.distribuidas.hades.model.PrecioCamarote[ codPrecioCamarote=" + codigo + " ]";
    }
    
    
}
