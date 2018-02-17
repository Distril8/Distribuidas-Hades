/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.model.Reserva;
import ec.edu.espe.distribuidas.hades.model.TuristaReserva;
import ec.edu.espe.distribuidas.hades.service.ReservaService;
import ec.edu.espe.distribuidas.hades.service.TuristaReservaService;
import ec.edu.espe.distribuidas.hades.web.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jubenavides
 */
@Named
@ViewScoped
public class TuristaBean extends BaseBean implements Serializable {
    
    private String auxBusqueda;
    private List<TuristaReserva> turistas;
    private TuristaReserva turista;
    private TuristaReserva turistaSel;
    private Reserva reserva;
    
    @Inject
    private TuristaReservaService turistaReservaService;
    
    @Inject
    private ReservaService reservaService;
    

    @PostConstruct
    public void init() {
        this.turista = new TuristaReserva();
        this.reserva = new Reserva();
    }

    public void buscar() {
        this.reserva = this.reservaService.obtenerPorIdentificacion(auxBusqueda);
        if (reserva != null) {
            this.turistas = this.turistaReservaService.obtenerPorReserva(auxBusqueda);
            if(turistas.isEmpty())
                FacesUtil.addMessageInfo("No existen turistas registrados en la reserva");
        } else {
            FacesUtil.addMessageError(null, "No se encontró reserva, verifique el codigo");
        }
    }

    @Override
    public void agregar() {
        this.turista = new TuristaReserva();
        super.agregar();
        
    }

    @Override
    public void modificar() {
        super.modificar();
        this.turista = new TuristaReserva();
        this.turista.setCodReserva(this.turistaSel.getCodReserva());
        this.turista.setTipoIdentificacion(this.turistaSel.getTipoIdentificacion());
        this.turista.setIdentificacion(this.turistaSel.getIdentificacion());
        this.turista.setNombre(this.turistaSel.getNombre());
        this.turista.setFechaNacimiento(this.turistaSel.getFechaNacimiento());
        this.turista.setPesoMaleta(this.turistaSel.getPesoMaleta());
    }

    @Override
    public void detalles() {
        super.detalles();
        this.turista = this.turistaSel;
    }

    public void cancelar() {
        super.reset();
        this.turista = new TuristaReserva();
    }

    public void guardar() {
        try {
            this.turistaReservaService.crear(this.turista);
            FacesUtil.addMessageInfo("Se agrego el turista: " + this.turista.getNombre());
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.turista = new TuristaReserva();
        this.turistas = this.turistaReservaService.obtenerTodos();
    }
    
    public void eliminar(){
        try{
            this.turistaReservaService.eliminar(this.turistaSel.getId());
            FacesUtil.addMessageInfo("Se elimino el turista: " + this.turistaSel.getNombre());
        } catch (Exception ex) {
             FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
    }

    public String getAuxBusqueda() {
        return auxBusqueda;
    }

    public void setAuxBusqueda(String auxBusqueda) {
        this.auxBusqueda = auxBusqueda;
    }
    
    public List<TuristaReserva> getTuristas() {
        return turistas;
    }

    public TuristaReserva getTurista() {
        return turista;
    }

    public void setTurista(TuristaReserva turista) {
        this.turista = turista;
    }

    public TuristaReserva getTuristaSel() {
        return turistaSel;
    }

    public void setTuristaSel(TuristaReserva turistaSel) {
        this.turistaSel = turistaSel;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
   
}