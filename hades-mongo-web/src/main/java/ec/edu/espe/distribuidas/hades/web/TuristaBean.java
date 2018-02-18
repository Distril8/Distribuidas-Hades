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
import java.math.BigDecimal;
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
    private Integer valorMaximo;
    private BigDecimal excedenteKilos;
    private Integer excedenteValor;
    private String mensaje;
    
    @Inject
    private TuristaReservaService turistaReservaService;
    
    @Inject
    private ReservaService reservaService;
    

    @PostConstruct
    public void init() {
        this.turista = new TuristaReserva();
        this.reserva = new Reserva();
        this.excedenteKilos = new BigDecimal(0);
        this.excedenteValor = 0;
        this.mensaje = "";
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
    
    public void valoresValidacion(BigDecimal kilos, Integer valorMaximo){
        BigDecimal kilosExtras = BigDecimal.valueOf(valorMaximo - 10);
        this.excedenteKilos = kilos.subtract(kilosExtras);
        this.excedenteKilos = this.excedenteKilos.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.excedenteValor = this.excedenteKilos.intValueExact() * 10;
    }


    @Override
    public void agregar() {
        this.turista = new TuristaReserva();
        super.agregar();
        String tipo = this.reservaService.obtenerPorIdentificacion(this.auxBusqueda).getTour().getTipoTour().getNombre();
        if(tipo.equals("Aventura Extrema")){
            this.valorMaximo = 42;
        }else{
            this.valorMaximo = 35;
        }
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
        System.out.println("valor mx - 10: "+this.valorMaximo);
        this.valoresValidacion(this.turista.getPesoMaleta(),this.valorMaximo);
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

    public BigDecimal getExcedenteKilos() {
        return excedenteKilos;
    }

    public void setExcedenteKilos(BigDecimal excedenteKilos) {
        this.excedenteKilos = excedenteKilos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getExcedenteValor() {
        return excedenteValor;
    }

    public void setExcedenteValor(Integer excedenteValor) {
        this.excedenteValor = excedenteValor;
    }

    public String getAuxBusqueda() {
        return auxBusqueda;
    }

    public void setAuxBusqueda(String auxBusqueda) {
        this.auxBusqueda = auxBusqueda;
    }

    public Integer getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(Integer valorMaximo) {
        this.valorMaximo = valorMaximo;
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