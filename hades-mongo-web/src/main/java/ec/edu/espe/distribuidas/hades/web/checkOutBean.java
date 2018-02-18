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
import ec.edu.espe.distribuidas.hades.service.ConsumoService;
import ec.edu.espe.distribuidas.hades.service.ReservaService;
import ec.edu.espe.distribuidas.hades.service.TourService;
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
public class checkOutBean extends BaseBean implements Serializable {

    private String auxBusqueda;
    private boolean enBusquedaPorReserva;
    private String filtro;
    private List<CheckOut> checkOuts;
    private CheckOut checkOut;
    private CheckOut checkOutSel;

    private List<Cliente> clientes;
    private Cliente cliente;

    private List<Tour> tours;
    private Tour tour;

    private List<Reserva> reservas;
    private Reserva reserva;
    private Reserva reservaSel;

    private TuristaReserva turista;
    private List<TuristaReserva> turistas;
    
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
    @Inject
    private ConsumoService consumoService;
    @Inject
    private TuristaReservaService turistaService;

    @PostConstruct
    public void init() {
        this.filtro = "RES";
        this.enBusquedaPorReserva = true;
        this.cliente = new Cliente();
        this.tour = new Tour();
        this.reserva = new Reserva();
        this.reservas= this.reservaService.obtenerTodos();
//        this.consumo = new Consumo();
        this.enEncontrado = false;
        this.habilitaFormConsumos = false;
        this.habilitaFormEquipaje = false;
    }

    public void cambiarFiltro() {
        this.enBusquedaPorReserva = !this.enBusquedaPorReserva;
    }

    @Override
    public void agregar() {
        this.checkOut = new CheckOut();
        super.agregar();

    }

    public void buscar() {
        this.reserva = new Reserva();
        this.reservas.clear();
        if (enBusquedaPorReserva) {
            this.reserva = this.reservaService.obtenerPorIdentificacion(auxBusqueda);
            if (reserva != null) {
                enEncontrado = true;
                this.reservas = this.reservaService.obtenerPorCliente(cliente);
                this.reservas.add(this.reserva);
            } else {
                FacesUtil.addMessageInfo("No se encontro reserva, verifar el codigo de reserva");
            }
        } else {
            this.cliente = this.clienteService.obtenerPorIdentificacion(auxBusqueda);
            if (cliente != null) {
                enEncontrado = true;
                this.reservas = this.reservaService.obtenerPorCliente(cliente);
            } else {
                FacesUtil.addMessageInfo("No se encontro cliente, verifique la identificacion");
            }
        }
    }

    public void mostrarConsumos() {
        this.consumos = this.consumoService.buscarPorReserva(reservaSel);
        System.out.println(consumos.get(0));
        this.habilitaFormConsumos = true;
    }

    public void mostrarEquipaje() {
        this.turistas = this.turistaService.obtenerPorReserva(reserva.getCodigo());
        this.habilitaFormEquipaje = true;
    }

    public void facturar() {
        //para mandar parametros al programa de factura
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Reserva getReservaSel() {
        return reservaSel;
    }

    public void setReservaSel(Reserva reservaSel) {
        this.reservaSel = reservaSel;
    }

    public String getAuxBusqueda() {
        return auxBusqueda;
    }

    public void setAuxBusqueda(String auxBusqueda) {
        this.auxBusqueda = auxBusqueda;
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

    public boolean isEnBusquedaPorReserva() {
        return enBusquedaPorReserva;
    }

    public void setEnBusquedaPorReserva(boolean enBusquedaPorReserva) {
        this.enBusquedaPorReserva = enBusquedaPorReserva;
    }

    public boolean isEnEncontrado() {
        return enEncontrado;
    }

    public void setEnEncontrado(boolean enEncontrado) {
        this.enEncontrado = enEncontrado;
    }

    public List<TuristaReserva> getTuristas() {
        return turistas;
    }

    public void setTuristas(List<TuristaReserva> turistas) {
        this.turistas = turistas;
    }

}
