/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.model;

import ec.edu.espe.distribuidas.nosql.mongo.BaseEntity;
import java.math.BigDecimal;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;
/**
 *
 * @author Hp
 */
@Entity(noClassnameStored = true, value = "checkOut")
public class CheckOut extends BaseEntity {
    
    private BigDecimal totalConsumos;
    private BigDecimal totalEquipaje;
    private String estado;
    
    @Reference
    private Reserva Reserva;
    @Reference
    private Tour tour;
    @Reference
    private Cliente cliente;

    public CheckOut() {
    }

    public BigDecimal getTotalConsumos() {
        return totalConsumos;
    }

    public void setTotalConsumos(BigDecimal totalConsumos) {
        this.totalConsumos = totalConsumos;
    }

    public BigDecimal getTotalEquipaje() {
        return totalEquipaje;
    }

    public void setTotalEquipaje(BigDecimal totalEquipaje) {
        this.totalEquipaje = totalEquipaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Reserva getReserva() {
        return Reserva;
    }

    public void setReserva(Reserva Reserva) {
        this.Reserva = Reserva;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        if (!(object instanceof CheckOut)) {
            return false;
        }
        CheckOut other = (CheckOut) object;
        if ((super.id == null && other.id != null) || (super.id != null && !super.id.equals(super.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CheckOut{" + "totalConsumos=" + totalConsumos + ", totalEquipaje=" + totalEquipaje + ", estado=" + estado + ", Reserva=" + Reserva + ", tour=" + tour + ", cliente=" + cliente + '}';
    }
    
}
