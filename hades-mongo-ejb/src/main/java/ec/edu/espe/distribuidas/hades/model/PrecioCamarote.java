/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.model;

import java.math.BigDecimal;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author hendrix
 */
@Embedded
public class PrecioCamarote {
    
    @Reference
    private TipoCamarote tipoCamarote;
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
    
    
}
