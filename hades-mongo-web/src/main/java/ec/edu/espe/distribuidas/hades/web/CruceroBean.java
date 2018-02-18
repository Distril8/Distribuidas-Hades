/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.enums.TipoCruceroEnum;
import ec.edu.espe.distribuidas.hades.model.Camarote;
import java.util.List;
import ec.edu.espe.distribuidas.hades.model.Crucero;
import ec.edu.espe.distribuidas.hades.service.CamaroteService;
import ec.edu.espe.distribuidas.hades.service.CruceroService;
import ec.edu.espe.distribuidas.hades.web.util.FacesUtil;
import java.io.Serializable;
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
public class CruceroBean extends BaseBean implements Serializable {

    private List<Crucero> cruceros;
    private Crucero crucero;
    private Crucero cruceroSel;
    private Camarote camarote;

    @Inject
    private CruceroService cruceroService;
    @Inject
    private CamaroteService camaroteService;

    @PostConstruct
    public void init() {
        this.cruceros = this.cruceroService.obtenerTodos();
        this.crucero = new Crucero();
    }

    public List<Crucero> getCruceros() {
        return cruceros;
    }

    @Override
    public void agregar() {
        this.crucero = new Crucero();
        super.agregar();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.crucero = new Crucero();
        this.crucero.setRegistro(this.cruceroSel.getRegistro());
        this.crucero.setNombre(this.cruceroSel.getNombre());
        this.crucero.setTipo(this.cruceroSel.getTipo());
        this.crucero.setCapacidad(this.cruceroSel.getCapacidad());
    }

    public void eliminar() {
        try {
            this.cruceroService.eliminar(this.cruceroSel.getCodigo());
            this.cruceros = this.cruceroService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro.");
            this.cruceroSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }

    @Override
    public void detalles() {
        super.detalles();
        this.crucero = this.cruceroSel;
    }

    public void cancelar() {
        super.reset();
        this.crucero = new Crucero();
    }

    public void asignarRegistro()
    {
        this.crucero.setCodigo(""+this.cruceroService.obtenerTodos().size());
        String regCrucero = "S" + this.cruceroService.obtenerTodos().size();
        this.crucero.setRegistro(regCrucero);
    }
    
    public void guardar() {
        try {
            if (this.enAgregar) {
                asignarRegistro();
                this.cruceroService.crear(this.crucero);
                crearCamarotes();
                FacesUtil.addMessageInfo("Se agreg\u00f3 el item al men\u00fa: " + this.crucero.getNombre());
            } else {
                this.cruceroService.modificar(this.crucero);
                FacesUtil.addMessageInfo("Se modific\u00f3 el item del men\u00fa con el nombre: " + this.crucero.getNombre());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.crucero = new Crucero();
        this.cruceros = this.cruceroService.obtenerTodos();
    }

    public void crearCamarotes() {
        Integer codCamarotes = 1000 + this.camaroteService.obtenerTodos().size();
        if (this.crucero.getTipo().getTexto().equals("ALFA")) {
            for (int i = 0; i < 10; i++) {
                this.camarote = new Camarote();
                this.camarote.setCodigo(codCamarotes + i);
                this.camarote.setNomCrucero(this.crucero.getNombre());
                if (i < 3) {
                    this.camarote.setNomTipo("Presidencial");
                    this.camarote.setCapacidad(4);
                    this.camarote.setUbicacion("Norte-Barco");
                } else if (i < 6) {
                    this.camarote.setNomTipo("Placer");
                    this.camarote.setCapacidad(5);
                    this.camarote.setUbicacion("Este-Barco");
                } else {
                    this.camarote.setNomTipo("Ecologico");
                    this.camarote.setCapacidad(6);
                    this.camarote.setUbicacion("Sur-Barco");
                }
                this.camarote.setNumero(i+1);
                this.camaroteService.crear(camarote);
            }
        } else {
                for (int i = 0; i < 15; i++) {
                this.camarote = new Camarote();
                this.camarote.setCodigo(codCamarotes + i);
                this.camarote.setNomCrucero(this.crucero.getNombre());
                if (i < 4) {
                    this.camarote.setNomTipo("Presidencial");
                    this.camarote.setCapacidad(4);
                    this.camarote.setUbicacion("Norte-Barco");
                } else if (i < 9) {
                    this.camarote.setNomTipo("Placer");
                    this.camarote.setCapacidad(5);
                    this.camarote.setUbicacion("Este-Barco");
                } else {
                    this.camarote.setNomTipo("Ecologico");
                    this.camarote.setCapacidad(6);
                    this.camarote.setUbicacion("Sur-Barco");
                }
                this.camarote.setNumero(i);
                this.camaroteService.crear(camarote);
            }
        }
    }

    public Camarote getCamarote() {
        return camarote;
    }

    public void setCamarote(Camarote camarote) {
        this.camarote = camarote;
    }

    public TipoCruceroEnum[] getTiposCrucero() {
        return TipoCruceroEnum.values();
    }

    public Crucero getCrucero() {
        return crucero;
    }

    public void setCrucero(Crucero crucero) {
        this.crucero = crucero;
    }

    public Crucero getCruceroSel() {
        return cruceroSel;
    }

    public void setCruceroSel(Crucero cruceroSel) {
        this.cruceroSel = cruceroSel;
    }
}
