
/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.model.Camarote;
import ec.edu.espe.distribuidas.hades.model.Cliente;
import ec.edu.espe.distribuidas.hades.model.Crucero;
import ec.edu.espe.distribuidas.hades.model.Reserva;
import ec.edu.espe.distribuidas.hades.model.TipoAlimentacion;
import ec.edu.espe.distribuidas.hades.model.TipoTour;
import ec.edu.espe.distribuidas.hades.model.Tour;
import ec.edu.espe.distribuidas.hades.service.CamaroteService;
import ec.edu.espe.distribuidas.hades.service.ClienteService;
import ec.edu.espe.distribuidas.hades.service.CruceroService;
import ec.edu.espe.distribuidas.hades.service.ReservaService;
import ec.edu.espe.distribuidas.hades.service.TipoAlimentacionService;
import ec.edu.espe.distribuidas.hades.service.TipoTourService;
import ec.edu.espe.distribuidas.hades.service.TourService;
import ec.edu.espe.distribuidas.hades.web.util.FacesUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Hades Cruise Corp.
 */
@Named
@ViewScoped
public class ReservaBean extends BaseBean implements Serializable {

    private String identificacionCliente;
    private String filtro;
    private String tipoTourBusqueda;
    private String codTipoAlimentacion;
    
    private String auxBusqueda;
    
    private boolean enBusquedaPorTipo;
    private boolean enTourElegido;
    private boolean enEncontrado;
    
    private Date fechaInicioBusqueda;
    private Date fechaFinBusqueda;
    
    private Reserva reserva;
    private Tour tour;
    private Tour tourSel;
    private Cliente cliente;
    private Camarote camarote;
    private TipoAlimentacion alimentacionSel;
    private Reserva reservaSel;
    private Camarote camarotesSel;

    private List<Tour> tours;
    private List<TipoTour> tiposTours;
    private List<Crucero> cruceros;
    private List<Camarote> camarotes;
    private List<Reserva> reservas;
    private List<TipoAlimentacion> alimentaciones;
    
    @Inject
    private ReservaService reservaService;
    @Inject
    private TourService tourService;
    @Inject
    private TipoTourService tipoTourService;
    @Inject
    private CruceroService cruceroService;
    @Inject
    private ClienteService clienteService;
    @Inject
    private CamaroteService camaroteService;
    @Inject
    private TipoAlimentacionService tipoAlimentacionService;

    @PostConstruct
    public void init() {
        this.reserva = new Reserva();
        this.filtro = "TIP";
        this.enBusquedaPorTipo = true;
        this.enTourElegido = false;
        this.enEncontrado = false;
        this.tour = new Tour();
        this.tiposTours = this.tipoTourService.obtenerTodos();
        this.alimentaciones = this.tipoAlimentacionService.obtenerTodos();
        this.reservas = this.reservaService.obtenerTodos();
    }

    public void cambiarFiltro() {
        this.enBusquedaPorTipo = !this.enBusquedaPorTipo;
        System.out.println("Valor para enbusqueda: " + this.enBusquedaPorTipo);
    }
    
    public void buscarR(){
        this.reserva = this.reservaService.obtenerPorIdentificacion(auxBusqueda);
    }

    public void buscar() {
        if (this.enBusquedaPorTipo) {
            TipoTour tipoTour = new TipoTour();
            tipoTour.setCodigo(this.tipoTourBusqueda);
            this.tours = this.tourService.buscarPorTipo(recuperaTipoTour(tipoTour));
        } else {
            this.tours = this.tourService.buscarPorFechas(this.fechaInicioBusqueda, this.fechaFinBusqueda);
        }
    }

    public void elegirTour() {
        this.reserva.setTour(this.tourSel);
        this.enTourElegido = true;
    }
    
    public void buscarCliente(String identificacion) {
        this.cliente = this.clienteService.obtenerPorIdentificacion(identificacion);
        if (cliente != null) {
            enEncontrado = true;
            this.camarotes= this.camaroteService.obtenerPorCrucero(this.tourSel.getCrucero());
        } else {
            FacesUtil.addMessageInfo("No se encontro cliente, verifique la identificacion");
        }
    }

    public void cancelar() {
        super.reset();
        this.tour = new Tour();
        this.tourSel = new Tour();
        this.reserva = new Reserva();
    }

    public String generarCodigo(){
        Integer codigo = this.reservaService.obtenerTodos().size()+1;
        String res="RS0";
        StringBuilder codigofinal = new StringBuilder();
        if (codigo==0) {
            codigo = 1;
        } 
        codigofinal.append(res);
        codigofinal.append(codigo.toString());
        return codigofinal.toString();
    }
    
    public void guardar() {
        try {
            this.reserva.setCodigo(generarCodigo());
            this.reserva.setTipoAlimentacion(this.tipoAlimentacionService.obtenerPorCodigo(this.codTipoAlimentacion));
            this.reserva.setCliente(this.cliente);
            System.out.println(this.reserva);
            this.reservaService.crear(this.reserva);
            FacesUtil.addMessageInfo("Se agrego la reserva del cliente: " + this.cliente.getNombre());
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }

        super.reset();
        this.tour = new Tour();
        this.tourSel= new Tour();
        this.cliente = new Cliente();
        this.alimentacionSel = new TipoAlimentacion();
        this.reserva = new Reserva();
        this.tours = this.tourService.obtenerTodos();
        this.tiposTours = this.tipoTourService.obtenerTodos();
        this.cruceros = this.cruceroService.obtenerTodos();
        this.init();
    }

    public TipoAlimentacion getAlimentacionSel() {
        return alimentacionSel;
    }

    public void setAlimentacionSel(TipoAlimentacion alimentacionSel) {
        this.alimentacionSel = alimentacionSel;
    }

    public List<TipoAlimentacion> getAlimentaciones() {
        return alimentaciones;
    }

    public void setAlimentaciones(List<TipoAlimentacion> alimentaciones) {
        this.alimentaciones = alimentaciones;
    }

    public String getCodTipoAlimentacion() {
        return codTipoAlimentacion;
    }

    public void setCodTipoAlimentacion(String codTipoAlimentacion) {
        this.codTipoAlimentacion = codTipoAlimentacion;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public String getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

    public Camarote getCamarote() {
        return camarote;
    }

    public void setCamarote(Camarote camarote) {
        this.camarote = camarote;
    }

    public Camarote getCamarotesSel() {
        return camarotesSel;
    }

    public void setCamarotesSel(Camarote camarotesSel) {
        this.camarotesSel = camarotesSel;
    }

    public List<Camarote> getCamarotes() {
        return camarotes;
    }

    public void setCamarotes(List<Camarote> camarotes) {
        this.camarotes = camarotes;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Tour getTourSel() {
        return tourSel;
    }

    public void setTourSel(Tour tourSel) {
        this.tourSel = tourSel;
    }

    public boolean isEnTourElegido() {
        return enTourElegido;
    }

    public void setEnTourElegido(boolean enTourElegido) {
        this.enTourElegido = enTourElegido;
    }

    public CruceroService getCruceroService() {
        return cruceroService;
    }

    public void setCruceroService(CruceroService cruceroService) {
        this.cruceroService = cruceroService;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public List<TipoTour> getTiposTours() {
        return tiposTours;
    }

    public List<Crucero> getCruceros() {
        return cruceros;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public boolean isEnEncontrado() {
        return enEncontrado;
    }

    public void setEnEncontrado(boolean enEncontrado) {
        this.enEncontrado = enEncontrado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    

    public String getTipoTourBusqueda() {
        return tipoTourBusqueda;
    }

    public void setTipoTourBusqueda(String tipoTourBusqueda) {
        this.tipoTourBusqueda = tipoTourBusqueda;
    }

    public boolean isEnBusquedaPorTipo() {
        return enBusquedaPorTipo;
    }

    public void setEnBusquedaPorTipo(boolean enBusquedaPorTipo) {
        this.enBusquedaPorTipo = enBusquedaPorTipo;
    }

    public Date getFechaInicioBusqueda() {
        return fechaInicioBusqueda;
    }

    public void setFechaInicioBusqueda(Date fechaInicioBusqueda) {
        this.fechaInicioBusqueda = fechaInicioBusqueda;
    }

    public Date getFechaFinBusqueda() {
        return fechaFinBusqueda;
    }

    public void setFechaFinBusqueda(Date fechaFinBusqueda) {
        this.fechaFinBusqueda = fechaFinBusqueda;
    }

    public TipoTour retornaTipoTour(Tour tour) {
        TipoTour aux = new TipoTour();

        for (int i = 0; i < tiposTours.size(); i++) {
            aux = tiposTours.get(i);
            if (aux.getCodigo().equals(tour.getTipoTour().getCodigo())) {
                break;
            }
        }
        return aux;
    }

    public Crucero retornaCrucero(Tour tour) {
        Crucero aux = new Crucero();

        for (int i = 0; i < tiposTours.size(); i++) {
            aux = cruceros.get(i);
            if (aux.getCodigo().equals(tour.getCrucero().getCodigo())) {
                break;
            }
        }
        return aux;
    }

    public TipoTour recuperaTipoTour(TipoTour tipoTour) {
        TipoTour aux = new TipoTour();

        for (int i = 0; i < tiposTours.size(); i++) {
            aux = tiposTours.get(i);
            if (aux.getCodigo().equals(tipoTour.getCodigo())) {
                break;
            }
        }
        return aux;
    }

    public String getAuxBusqueda() {
        return auxBusqueda;
    }

    public void setAuxBusqueda(String auxBusqueda) {
        this.auxBusqueda = auxBusqueda;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Reserva getReservaSel() {
        return reservaSel;
    }

    public void setReservaSel(Reserva reservaSel) {
        this.reservaSel = reservaSel;
    }
    
    
}
