/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.model.Camarote;
import ec.edu.espe.distribuidas.hades.model.Crucero;
import ec.edu.espe.distribuidas.hades.model.TipoCamarote;
import ec.edu.espe.distribuidas.hades.service.CamaroteService;
import ec.edu.espe.distribuidas.hades.service.CruceroService;
import ec.edu.espe.distribuidas.hades.service.TipoCamaroteService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
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
public class CamaroteAdminBean extends BaseBean implements Serializable{
    
    private String tipoCamaroteBusqueda;
    private List<TipoCamarote> tiposCamarote;
    private String cruceroBusqueda;
    private List<Crucero> cruceros;
    
    private List<Camarote> camarotes;
    private Camarote camarote;
    private Camarote camaroteSel;
    private static final Logger LOG = Logger.getLogger(CamaroteAdminBean.class.getName());
    
    
    @Inject
    private TipoCamaroteService tipoCamaroteService;
    @Inject
    private CruceroService cruceroService;
    @Inject
    private CamaroteService camaroteService;
    
    @PostConstruct
    public void init() {
        this.cruceros = this.cruceroService.obtenerTodos();
        this.tiposCamarote = this.tipoCamaroteService.obtenerTodos();
        this.camarotes = this.camaroteService.obtenerTodos();
    }
    
    public void buscar(){
        this.camarotes = this.camaroteService.obtenerPorCruceroTipo(this.cruceroService.obtenerPorCodigo(this.cruceroBusqueda),this.tipoCamaroteService.obtenerPorCodigo(this.tipoCamaroteBusqueda));
    }

    public List<TipoCamarote> getTiposCamarote() {
        return tiposCamarote;
    }

    public List<Crucero> getCruceros() {
        return cruceros;
    }

    public String getTipoCamaroteBusqueda() {
        return tipoCamaroteBusqueda;
    }

    public void setTipoCamaroteBusqueda(String tipoCamaroteBusqueda) {
        this.tipoCamaroteBusqueda = tipoCamaroteBusqueda;
    }

    public String getCruceroBusqueda() {
        return cruceroBusqueda;
    }

    public void setCruceroBusqueda(String cruceroBusqueda) {
        this.cruceroBusqueda = cruceroBusqueda;
    }

    public List<Camarote> getCamarotes() {
        return camarotes;
    }

    public void setCamarotes(List<Camarote> camarotes) {
        this.camarotes = camarotes;
    }

    public Camarote getCamarote() {
        return camarote;
    }

    public void setCamarote(Camarote camarote) {
        this.camarote = camarote;
    }

    public Camarote getCamaroteSel() {
        return camaroteSel;
    }

    public void setCamaroteSel(Camarote camaroteSel) {
        this.camaroteSel = camaroteSel;
    }
}
