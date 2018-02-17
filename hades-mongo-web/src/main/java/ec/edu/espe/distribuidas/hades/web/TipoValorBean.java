/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;


import ec.edu.espe.distribuidas.hades.model.TipoValor;
import ec.edu.espe.distribuidas.hades.service.TipoValorService;
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
public class TipoValorBean extends BaseBean implements Serializable{
    
    private List<TipoValor> tiposValor;
    private TipoValor tipoValor;
    private TipoValor tipoValorSel;
    
    @Inject
    private TipoValorService tipoValorService;
    
    @PostConstruct
    public void init()
    {
        this.tiposValor = this.tipoValorService.obtenerTodos();
        this.tipoValor = new TipoValor();
    }
    
    @Override
    public void agregar()
    {
        this.tipoValor = new TipoValor();
        super.agregar();
    }
    
    public void cancelar() {
        super.reset();
        this.tipoValor = new TipoValor();
    }
    
    @Override
    public void modificar() {
        super.modificar();
        this.tipoValor = new TipoValor();
        this.tipoValor.setCodigo(this.tipoValorSel.getCodigo());
        this.tipoValor.setNombre(this.tipoValorSel.getNombre());
        this.tipoValor.setDescripcion(this.tipoValorSel.getDescripcion());
        this.tipoValor.setTipoCobro(this.tipoValorSel.getTipoCobro());
        this.tipoValor.setOrden(this.tipoValorSel.getOrden());
    }
    
    public void eliminar() {
        try {
            this.tipoValorService.eliminar(this.tipoValorSel.getCodigo());
            this.tiposValor = this.tipoValorService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro ");
            this.tipoValorSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informaci\u00f3n relacionada.");
        }
    }
    
    public void guardar() {
        try {
            if (this.enAgregar) {
                this.tipoValorService.crear(this.tipoValor);
                FacesUtil.addMessageInfo("Se agregó el Tipo de Valor: " + this.tipoValor.getNombre());
            } else {
                this.tipoValorService.modificar(this.tipoValor);
                FacesUtil.addMessageInfo("Se modific\u00f3 el Tipo de Valor con c\u00f3digo: " + this.tipoValor.getCodigo());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.tipoValor = new TipoValor();
        this.tiposValor = this.tipoValorService.obtenerTodos();
    }

    public List<TipoValor> getTiposValor() {
        return tiposValor;
    }

    public TipoValor getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(TipoValor tipoValor) {
        this.tipoValor = tipoValor;
    }

    public TipoValor getTipoValorSel() {
        return tipoValorSel;
    }

    public void setTipoValorSel(TipoValor tipoValorSel) {
        this.tipoValorSel = tipoValorSel;
    }
}
