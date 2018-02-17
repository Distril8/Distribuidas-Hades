/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.model;

import ec.edu.espe.distribuidas.nosql.mongo.BaseEntity;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

/**
 *
 * @author Hades Cruise Corp.
 */
@Entity(noClassnameStored = true, value = "tipo_alimentacion")
public class TipoAlimentacion extends BaseEntity {

    @Indexed(options = @IndexOptions(name = "tipoAlimentacion_codigoUIdx", unique = true))
    private String codigo;
    private String descripcion;

    public TipoAlimentacion() {
    }

    public TipoAlimentacion(String codTipoAlimentacion) {
        this.codigo = codTipoAlimentacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof TipoAlimentacion)) {
            return false;
        }
        TipoAlimentacion other = (TipoAlimentacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.distribuidas.hades.model.TipoAlimentacion[ codTipoAlimentacion=" + codigo + " ]";
    }
}
