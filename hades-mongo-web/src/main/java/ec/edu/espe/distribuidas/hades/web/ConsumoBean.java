/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.model.Camarote;
import ec.edu.espe.distribuidas.hades.model.Consumo;
import ec.edu.espe.distribuidas.hades.model.Menu;
import ec.edu.espe.distribuidas.hades.model.Reserva;
import ec.edu.espe.distribuidas.hades.model.TipoTour;
import ec.edu.espe.distribuidas.hades.model.Tour;
import ec.edu.espe.distribuidas.hades.service.CamaroteService;
import ec.edu.espe.distribuidas.hades.service.ConsumoService;
import ec.edu.espe.distribuidas.hades.service.MenuService;
import ec.edu.espe.distribuidas.hades.service.ReservaService;
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
 * @author jubenavides
 */
@Named
@ViewScoped
public class ConsumoBean extends BaseBean implements Serializable {

    private boolean enMostrarTour;
    private String filtro;
    private String tipoTourBusqueda;
    private Integer numCamarote;
    private boolean enBusquedaPorTipo;
    private Date fechaInicioBusqueda;
    private Date fechaFinBusqueda;
    private Integer codMenu;

    private Reserva reserva;
    private Consumo consumo;
    private Camarote camarote;
    private Tour tourSel;
    private Menu menu;

    private List<Tour> tours;
    private List<TipoTour> tiposTours;
    private List<Menu> menus;

    @Inject
    private ConsumoService consumoService;
    @Inject
    private MenuService menuService;
    @Inject
    private ReservaService reservaService;
    @Inject
    private TourService tourService;
    @Inject
    private TipoTourService tipoTourService;
    @Inject
    private CamaroteService camaroteService;

    @PostConstruct
    public void init() {
        this.reserva = new Reserva();
        enMostrarTour = true;
        this.filtro = "TIP";
        this.enBusquedaPorTipo = true;
        this.tours = this.tourService.obtenerTodos();
        this.tiposTours = this.tipoTourService.obtenerTodos();
        this.menus = this.menuService.obtenerTodos();
        this.menu = new Menu();
        this.consumo = new Consumo();
    }

    public void cambiarFiltro() {
        this.enBusquedaPorTipo = !this.enBusquedaPorTipo;
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

    public void seleccionarTour() {
        enMostrarTour = false;
    }

    public void seleccionarCamarote() {
        this.camarote = this.camaroteService.obtenerPorNombreYNumero(tourSel.getCrucero().getNombre(), numCamarote);
        this.reserva = this.reservaService.obtenerPorTourYCamarote(tourSel, camarote);
        if (reserva != null) {
            enAgregar = true;
        } else {
            FacesUtil.addMessageError(null, "No se existe reserva para el numero de camarote seleccionado");
            enMostrarTour = true;
            reset();
            this.tourSel = new Tour();
        }
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

    public void guardar() {
        try {
            this.consumo.setMenu(this.menuService.obtenerPorCodigo(codMenu));
            this.consumo.setReserva(this.reserva);
            this.consumoService.crear(this.consumo);
            FacesUtil.addMessageInfo("Se agreg\u00f3 el consumo");
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.consumo = new Consumo();
    }

public void cancelar(){
        super.reset();
        this.consumo = new Consumo();
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
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

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public List<TipoTour> getTiposTours() {
        return tiposTours;
    }

    public void setTiposTours(List<TipoTour> tiposTours) {
        this.tiposTours = tiposTours;
    }

    public boolean isEnMostrarTour() {
        return enMostrarTour;
    }

    public void setEnMostrarTour(boolean enMostrarTour) {
        this.enMostrarTour = enMostrarTour;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Tour getTourSel() {
        return tourSel;
    }

    public void setTourSel(Tour tourSel) {
        this.tourSel = tourSel;
    }

    public Camarote getCamarote() {
        return camarote;
    }

    public void setCamarote(Camarote camarote) {
        this.camarote = camarote;
    }

    public Integer getNumCamarote() {
        return numCamarote;
    }

    public void setNumCamarote(Integer numCamarote) {
        this.numCamarote = numCamarote;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public Integer getCodMenu() {
        return codMenu;
    }

    public void setCodMenu(Integer codMenu) {
        this.codMenu = codMenu;
    }

}
