/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.model.Reserva;
import ec.edu.espe.distribuidas.hades.model.Tour;
import ec.edu.espe.distribuidas.hades.service.ReservaService;
import java.io.Serializable;
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
public class ConsumoBean extends BaseBean implements Serializable{
    
    private Reserva reserva;
    private Tour tour;
    
    @Inject
    private ReservaService reservaService;
    
    @PostConstruct
    public void init() {
        this.reserva = new Reserva();
    }
    
    public void seleccionarTour(String codTour)
    {
    
    }
}
