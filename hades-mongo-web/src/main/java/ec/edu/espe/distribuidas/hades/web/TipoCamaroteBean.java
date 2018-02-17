/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.model.TipoCamarote;
import ec.edu.espe.distribuidas.hades.service.TipoCamaroteService;
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
public class TipoCamaroteBean extends BaseBean implements Serializable{
    
    private List<TipoCamarote> tiposCamarote;
    
    private TipoCamarote tipoCamarote;
    
    private TipoCamarote tipoCamaroteSel;
    
    @Inject
    private TipoCamaroteService tipoCamaroteService;
    
    @PostConstruct
    public void init()
    {
        this.tiposCamarote = this.tipoCamaroteService.obtenerTodos();
        this.tipoCamarote = new TipoCamarote();
    }
    
    public List<TipoCamarote> getTiposCamarote(){
        return tiposCamarote;
    }
    
    @Override
    public void agregar() {
        this.tipoCamarote = new TipoCamarote();
        super.agregar();
    }
    
    @Override
    public void modificar() {
        super.modificar();
        this.tipoCamarote = new TipoCamarote();
        this.tipoCamarote.setCodigo(this.tipoCamaroteSel.getCodigo());
        this.tipoCamarote.setNombre(this.tipoCamaroteSel.getNombre());
    }
    
    public void eliminar() {
        try {
            this.tipoCamaroteService.eliminar(this.tipoCamaroteSel.getCodigo());
            this.tiposCamarote = this.tipoCamaroteService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro ");
            this.tipoCamaroteSel = null;  
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }
    
    @Override
    public void detalles() {
        super.detalles();
        this.tipoCamarote = this.tipoCamaroteSel;
    }
    
    public void cancelar() {
        super.reset();
        this.tipoCamarote = new TipoCamarote();
    }
    
    public void guardar() {
        try {
            if (this.enAgregar) {
                this.tipoCamaroteService.crear(this.tipoCamarote);
                FacesUtil.addMessageInfo("Se agreg\u00f3n el Tipo de Camarote: " + this.tipoCamarote.getNombre());
            } else {
                this.tipoCamaroteService.modificar(this.tipoCamarote);
                FacesUtil.addMessageInfo("Se modific\u00f3 el Tipo de Camarote con c\u00f3digo: " + this.tipoCamarote.getCodigo());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.tipoCamarote = new TipoCamarote();
        this.tiposCamarote = this.tipoCamaroteService.obtenerTodos();
    }
    
    public TipoCamarote getTipoCamarote() {
       return tipoCamarote; 
    }

    public void setTipoCamarote(TipoCamarote tipoCamarote) {
        this.tipoCamarote = tipoCamarote;
    }

    public TipoCamarote getTipoCamaroteSel() {
        return tipoCamaroteSel;
    }

    public void setTipoCamaroteSel(TipoCamarote tipoCamaroteSel) {
        this.tipoCamaroteSel = tipoCamaroteSel;
    }
}
