/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.model;

import ec.edu.espe.distribuidas.hades.enums.OrdenTipoValorEnum;
import ec.edu.espe.distribuidas.hades.enums.TipoValorEnum;
import ec.edu.espe.distribuidas.nosql.mongo.BaseEntity;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

/**
 *
 * @author Hades Cruise Corp.
 */
@Entity(noClassnameStored = true, value = "tipo_valor")
public class TipoValor extends BaseEntity {
    
    @Indexed(options = @IndexOptions(name = "tipoValor_codigoUIdx", unique = true))
    private String codigo;
    private String nombre;
    private String descripcion;
    private TipoValorEnum tipoCobro;
    private OrdenTipoValorEnum orden;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoValorEnum getTipoCobro() {
        return tipoCobro;
    }

    public void setTipoCobro(TipoValorEnum tipoCobro) {
        this.tipoCobro = tipoCobro;
    }

    public OrdenTipoValorEnum getOrden() {
        return orden;
    }

    public void setOrden(OrdenTipoValorEnum orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        //Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoValor)) {
            return false;
        }
        TipoValor other = (TipoValor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.distribuidas.hades.model.TipoValor[ codTipoValor=" + codigo + " ]";
    }
}
