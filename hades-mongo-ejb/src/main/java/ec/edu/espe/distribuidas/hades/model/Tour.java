/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.model;

import ec.edu.espe.distribuidas.nosql.mongo.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author hendrix
 */
@Entity(noClassnameStored = true, value = "tour")
public class Tour extends BaseEntity {

    @Indexed(options = @IndexOptions(name = "tour_codigoUIdx", unique = true))
    private Integer codigo;
    private String nombre;
    private Integer duracion;
    private Date fechaInicio;
    private Date fechaFin;
    private String puertoEmbarque;
    private String puertoDesembarque;
    private BigDecimal precioBase;
    private Integer porcentajeMenu;
    @Reference
    private TipoTour tipoTour;
    @Reference
    private Crucero crucero;
    @Embedded
    private List<PrecioCamarote> precios;

    public Tour()
    {
        this.tipoTour = new TipoTour();
        this.crucero = new Crucero();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getPuertoEmbarque() {
        return puertoEmbarque;
    }

    public void setPuertoEmbarque(String puertoEmbarque) {
        this.puertoEmbarque = puertoEmbarque;
    }

    public String getPuertoDesembarque() {
        return puertoDesembarque;
    }

    public void setPuertoDesembarque(String puertoDesembarque) {
        this.puertoDesembarque = puertoDesembarque;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public Integer getPorcentajeMenu() {
        return porcentajeMenu;
    }

    public void setPorcentajeMenu(Integer porcentajeMenu) {
        this.porcentajeMenu = porcentajeMenu;
    }

    public TipoTour getTipoTour() {
        return tipoTour;
    }

    public void setTipoTour(TipoTour tipoTour) {
        this.tipoTour = tipoTour;
    }

    public Crucero getCrucero() {
        return crucero;
    }

    public void setCrucero(Crucero crucero) {
        this.crucero = crucero;
    }

    public List<PrecioCamarote> getPrecios() {
        return precios;
    }

    public void setPrecios(List<PrecioCamarote> precios) {
        this.precios = precios;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Tour{" + "codigo=" + codigo + ", nombre=" + nombre + ", duracion=" + duracion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", puertoEmbarque=" + puertoEmbarque + ", puertoDesembarque=" + puertoDesembarque + ", precioBase=" + precioBase + ", porcentajeMenu=" + porcentajeMenu + ", tipoTour=" + tipoTour + ", crucero=" + crucero + ", precios=" + precios + '}';
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
        if (!(object instanceof Crucero)) {
            return false;
        }
        Tour other = (Tour) object;
        if ((super.id == null && other.id != null) || (super.id != null && !super.id.equals(super.id))) {
            return false;
        }
        return true;
    }

}
