/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;
import ec.edu.espe.distribuidas.hades.model.TipoTour;
import ec.edu.espe.distribuidas.hades.service.TipoTourService;
import ec.edu.espe.distribuidas.hades.web.util.FacesUtil;
import java.io.Serializable;
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
public class TipoTourBean extends BaseBean implements Serializable{
    
    private List<TipoTour> tiposTour;
    
    private TipoTour tipoTour;
    
    private TipoTour tipoTourSel;
    
    @Inject
    private TipoTourService tipoTourService;
    
    @PostConstruct
    public void init()
    {
       this.tipoTour = new TipoTour();
        this.tiposTour = this.tipoTourService.obtenerTodos();
    }
    
    public List<TipoTour> getTiposTour(){
        return tiposTour;
    }
    
    @Override
    public void agregar() {
        this.tipoTour = new TipoTour();
        super.agregar();
    }
    
    @Override
    public void modificar() {
        super.modificar();
        this.tipoTour = new TipoTour();
        this.tipoTour.setCodigo(this.tipoTourSel.getCodigo());
        this.tipoTour.setNombre(this.tipoTourSel.getNombre());
        this.tipoTour.setDescripcion(this.tipoTourSel.getDescripcion());
        this.tipoTour.setDuracion(this.tipoTourSel.getDuracion());
    }
    
    public void eliminar() {
        try {
            this.tipoTourService.eliminar(this.tipoTourSel.getCodigo());
            this.tiposTour = this.tipoTourService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro ");
            this.tipoTourSel = null;  
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }
    
    @Override
    public void detalles() {
        super.detalles();
        this.tipoTour = this.tipoTourSel;
    }
    
    public void cancelar() {
        super.reset();
        this.tipoTour = new TipoTour();
    }
    
    public void guardar() {
        try {
            if (this.enAgregar) {
                this.tipoTourService.crear(this.tipoTour);
                FacesUtil.addMessageInfo("Se agreg\u00f3n el Tipo de Tour: " + this.tipoTour.getNombre());
            } else {
                this.tipoTourService.modificar(this.tipoTour);
                FacesUtil.addMessageInfo("Se modific\u00f3 el Tipo de Tour con c\u00f3digo: " + this.tipoTour.getCodigo());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.tipoTour = new TipoTour();
        this.tiposTour = this.tipoTourService.obtenerTodos();
    }
    
    public TipoTour getTipoTour() {
       return tipoTour; 
    }

    public void setTipoTour(TipoTour tipoTour) {
        this.tipoTour = tipoTour;
    }

    public TipoTour getTipoTourSel() {
        return tipoTourSel;
    }

    public void setTipoTourSel(TipoTour tipoTourSel) {
        this.tipoTourSel = tipoTourSel;
    }
}
