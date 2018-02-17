/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.model.Crucero;
import ec.edu.espe.distribuidas.hades.model.TipoTour;
import ec.edu.espe.distribuidas.hades.model.Tour;
import ec.edu.espe.distribuidas.hades.service.CruceroService;
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
public class TourBean extends BaseBean implements Serializable {

    private String filtro;
    private String tipoTourBusqueda;
    private boolean enBusquedaPorTipo;
    private Date fechaInicioBusqueda;
    private Date fechaFinBusqueda;
    private List<Tour> tours;
    private Tour tour;
    private Tour tourSel;
    private List<TipoTour> tiposTours;
    private List<Crucero> cruceros;

    @Inject
    private TourService tourService;
    @Inject
    private TipoTourService tipoTourService;
    @Inject
    private CruceroService cruceroService;

    @PostConstruct
    public void init() {
        this.filtro = "TIP";
        this.enBusquedaPorTipo = true;
        this.tours = this.tourService.obtenerTodos();
        this.tour = new Tour();
        this.tiposTours = this.tipoTourService.obtenerTodos();
        this.cruceros = this.cruceroService.obtenerTodos();
    }
    
    public void cambiarFiltro() {
        this.enBusquedaPorTipo = !this.enBusquedaPorTipo;
        System.out.println("Valor para enbusqueda: "+this.enBusquedaPorTipo);
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

    @Override
    public void agregar() {
        this.tour = new Tour();
        this.tiposTours = this.tipoTourService.obtenerTodos();
        this.cruceros = this.cruceroService.obtenerTodos();
        super.agregar();
    }

    public void cancelar() {
        super.reset();
        this.tour = new Tour();
        this.tiposTours = this.tipoTourService.obtenerTodos();
        this.cruceros = this.cruceroService.obtenerTodos();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.tour = new Tour();
        this.tour.setCodigo(this.tourSel.getCodigo());
        this.tour.setTipoTour(this.tourSel.getTipoTour());
        this.tour.setCrucero(this.tourSel.getCrucero());
        this.tour.setNombre(this.tourSel.getNombre());
        this.tour.setDuracion(this.tourSel.getDuracion());
        this.tour.setFechaInicio(this.tourSel.getFechaInicio());
        this.tour.setFechaInicio(this.tourSel.getFechaFin());
        this.tour.setPuertoEmbarque(this.tourSel.getPuertoEmbarque());
        this.tour.setPuertoDesembarque(this.tourSel.getPuertoDesembarque());
        this.tour.setPrecioBase(this.tourSel.getPrecioBase());
        this.tour.setPorcentajeMenu(this.tourSel.getPorcentajeMenu());
    }

    public void eliminar() {
        try {
            this.tourService.eliminar(this.tourSel.getCodigo().toString());
            this.tours = this.tourService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro");
            this.tourSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informaci\u00f3n relacionada.");
        }
    }

    public void guardar() {
        try {
            
            tour.setTipoTour(retornaTipoTour(this.tour));
            tour.setCrucero(retornaCrucero(this.tour));

            if (this.enAgregar) {
                this.tourService.crear(this.tour);
                FacesUtil.addMessageInfo("Se agrego el Tour: " + this.tour.getNombre());
            } else {
                this.tourService.modificar(this.tour);
                FacesUtil.addMessageInfo("Se modific\u00f3 la Actividad con c\u00f3digo: " + this.tour.getCodigo());
            }

        } catch (Exception e) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }

        super.reset();
        this.tour = new Tour();
        //this.actividadPK = new ActividadPK();
        this.tours = this.tourService.obtenerTodos();
        this.tiposTours = this.tipoTourService.obtenerTodos();
        this.cruceros = this.cruceroService.obtenerTodos();
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
    
    public TipoTour retornaTipoTour(Tour tour)
    {
        TipoTour aux = new TipoTour();
        
        for(int i= 0; i<tiposTours.size();i++)
        {
            aux= tiposTours.get(i);
            if(aux.getCodigo().equals(tour.getTipoTour().getCodigo()))
            {
                break;
            }
        }
        return aux;
    }
    
    public Crucero retornaCrucero(Tour tour)
    {
        Crucero aux = new Crucero();
        
        for(int i= 0; i<tiposTours.size();i++)
        {
            aux= cruceros.get(i);
            if(aux.getCodigo().equals(tour.getCrucero().getCodigo()))
            {
                break;
            }
        }
        return aux;
    }
    
    public TipoTour recuperaTipoTour(TipoTour tipoTour)
    {
        TipoTour aux = new TipoTour();
        
        for(int i= 0; i<tiposTours.size();i++)
        {
            aux= tiposTours.get(i);
            if(aux.getCodigo().equals(tipoTour.getCodigo()))
            {
                break;
            }
        }
        return aux;
    }
}
