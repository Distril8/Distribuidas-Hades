/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.model.CheckOut;
import ec.edu.espe.distribuidas.hades.model.Cliente;
import ec.edu.espe.distribuidas.hades.model.Consumo;
import ec.edu.espe.distribuidas.hades.model.Reserva;
import ec.edu.espe.distribuidas.hades.model.Tour;
import ec.edu.espe.distribuidas.hades.model.TuristaReserva;
import ec.edu.espe.distribuidas.hades.service.CheckOutService;
import ec.edu.espe.distribuidas.hades.service.ClienteService;
import ec.edu.espe.distribuidas.hades.service.ReservaService;
import ec.edu.espe.distribuidas.hades.service.TourService;
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
public class checkOutBean extends BaseBean implements Serializable {

    private String identificacionCliente;
    
    private List<CheckOut> checkOuts;
    private CheckOut checkOut;
    private CheckOut checkOutSel;

    private List<Cliente> clientes;
    private Cliente cliente;

    private List<Tour> tours;
    private Tour tour;

    private List<Reserva> reservas;
    private Reserva reserva;
    
    private TuristaReserva turista;

    private List<Consumo> consumos;
    private Consumo consumo;

    private boolean enEncontrado;
    private boolean habilitaFormConsumos;
    private boolean habilitaFormEquipaje;

    @Inject
    private CheckOutService checkOutService;
    @Inject
    private ClienteService clienteService;
    @Inject
    private TourService tourService;
    @Inject
    private ReservaService reservaService;
//    @Inject
//    private ConsumoService consumoService;
   
    @PostConstruct
    public void init() {
        this.cliente = new Cliente();
        this.tour = new Tour();
        this.reserva = new Reserva();
//        this.consumo = new Consumo();
        this.habilitaFormConsumos = false;
        this.habilitaFormEquipaje = false;
    }

    @Override
    public void agregar() {
        this.checkOut = new CheckOut();
        super.agregar();

    }
    
    public void buscar(String identificacion) {
        this.cliente = this.clienteService.obtenerPorIdentificacion(identificacion);
        if (cliente != null) {
            enEncontrado = true;
            this.checkOuts = this.checkOutService.obtenerPorCliente(cliente);
        } else {
            FacesUtil.addMessageInfo("No se encontro cliente, verifique la identificacion");
        }
    }
    
     public void mostrarConsumos() {
        //this.actividades = this.actividadService.obtenerPorProgramaCliente(this.programaSel);
        this.habilitaFormConsumos = true;
    }
     
     public void mostrarEquipaje() {
        this.habilitaFormEquipaje = true;
    }
     
    public void facturar() {
        //para mandar parametros al programa de factura
    }

    public String getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

    public List<CheckOut> getCheckOuts() {
        return checkOuts;
    }

    public void setCheckOuts(List<CheckOut> checkOuts) {
        this.checkOuts = checkOuts;
    }

    public CheckOut getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(CheckOut checkOut) {
        this.checkOut = checkOut;
    }

    public CheckOut getCheckOutSel() {
        return checkOutSel;
    }

    public void setCheckOutSel(CheckOut checkOutSel) {
        this.checkOutSel = checkOutSel;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TuristaReserva getTurista() {
        return turista;
    }

    public void setTurista(TuristaReserva turista) {
        this.turista = turista;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public boolean isEnEncontrado() {
        return enEncontrado;
    }

    public void setEnEncontrado(boolean enEncontrado) {
        this.enEncontrado = enEncontrado;
    }

    public boolean isHabilitaFormConsumos() {
        return habilitaFormConsumos;
    }

    public void setHabilitaFormConsumos(boolean habilitaFormConsumos) {
        this.habilitaFormConsumos = habilitaFormConsumos;
    }

    public boolean isHabilitaFormEquipaje() {
        return habilitaFormEquipaje;
    }

    public void setHabilitaFormEquipaje(boolean habilitaFormEquipaje) {
        this.habilitaFormEquipaje = habilitaFormEquipaje;
    }

     
}
