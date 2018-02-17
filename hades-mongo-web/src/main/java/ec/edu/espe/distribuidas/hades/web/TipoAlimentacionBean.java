/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.model.TipoAlimentacion;
import ec.edu.espe.distribuidas.hades.service.TipoAlimentacionService;
import ec.edu.espe.distribuidas.hades.web.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Hendrix
 */
@Named
@ViewScoped
public class TipoAlimentacionBean extends BaseBean implements Serializable {

    private List<TipoAlimentacion> tiposAlimentacion;

    private TipoAlimentacion tipoAlimentacion;

    private TipoAlimentacion tipoAlimentacionSel;

    @Inject
    private TipoAlimentacionService tipoAlimentacionService;

    @PostConstruct
    public void init() {
        this.tiposAlimentacion = this.tipoAlimentacionService.obtenerTodos();
        this.tipoAlimentacion = new TipoAlimentacion();
    }

    public List<TipoAlimentacion> getTiposAlimentacion() {
        return tiposAlimentacion;
    }

    @Override
    public void agregar() {
        this.tipoAlimentacion = new TipoAlimentacion();
        super.agregar();
        
    }

    @Override
    public void modificar() {
        super.modificar();
        this.tipoAlimentacion = new TipoAlimentacion();
        this.tipoAlimentacion.setCodigo(this.tipoAlimentacionSel.getCodigo());
        this.tipoAlimentacion.setDescripcion(this.tipoAlimentacionSel.getDescripcion());
    }
    
    public void eliminar() {
        try {
            this.tipoAlimentacionService.eliminar(this.tipoAlimentacionSel.getCodigo());
            this.tiposAlimentacion = this.tipoAlimentacionService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro ");
            this.tipoAlimentacionSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }

    @Override
    public void detalles() {
        super.detalles();
        this.tipoAlimentacion = this.tipoAlimentacionSel;
    }

    public void cancelar() {
        super.reset();
        this.tipoAlimentacion = new TipoAlimentacion();
    }

    public void guardar() {
        try {
            if (this.enAgregar) {
                this.tipoAlimentacionService.crear(this.tipoAlimentacion);
                FacesUtil.addMessageInfo("Se agregó el Tipo de Alimentaci\u00f3n: " + this.tipoAlimentacion.getDescripcion());
            } else {
                this.tipoAlimentacionService.modificar(this.tipoAlimentacion);
                FacesUtil.addMessageInfo("Se modific\u00f3 el Tipo de Alimentaci\u00f3n con c\u00f3digo: " + this.tipoAlimentacion.getCodigo());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.tipoAlimentacion = new TipoAlimentacion();
        this.tiposAlimentacion = this.tipoAlimentacionService.obtenerTodos();
    }

    public TipoAlimentacion getTipoAlimentacion() {
        return tipoAlimentacion;
    }

    public void setTipoAlimentacion(TipoAlimentacion tipoAlimentacion) {
        this.tipoAlimentacion = tipoAlimentacion;
    }

    public TipoAlimentacion getTipoAlimentacionSel() {
        return tipoAlimentacionSel;
    }

    public void setTipoAlimentacionSel(TipoAlimentacion tipoAlimentacionSel) {
        this.tipoAlimentacionSel = tipoAlimentacionSel;
    }

}
