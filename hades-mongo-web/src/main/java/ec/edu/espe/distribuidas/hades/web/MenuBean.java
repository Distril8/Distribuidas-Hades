/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.enums.MenuEnum;
import ec.edu.espe.distribuidas.hades.model.Menu;
import ec.edu.espe.distribuidas.hades.service.MenuService;
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
public class MenuBean extends BaseBean implements Serializable {

    private List<Menu> itemsMenu;
    private Menu itemMenu;
    private Menu itemMenuSel;

    @Inject
    private MenuService menuService;

    @PostConstruct
    public void init() {
        this.itemsMenu = this.menuService.obtenerTodos();
        
        this.itemMenu = new Menu();
    }

    public List<Menu> getItemsMenu() {
        return itemsMenu;
    }

    @Override
    public void agregar() {
        this.itemMenu = new Menu();
        super.agregar();
        
    }

    @Override
    public void modificar() {
        super.modificar();
        this.itemMenu = new Menu();
        this.itemMenu.setCodigo(this.itemMenuSel.getCodigo());
        this.itemMenu.setNombre(this.itemMenuSel.getNombre());
        this.itemMenu.setDescripcion(this.itemMenuSel.getDescripcion());
        this.itemMenu.setPrecio(this.itemMenuSel.getPrecio());
        this.itemMenu.setTipo(this.itemMenuSel.getTipo());
    }
    
    public void eliminar() {
        try {
            this.menuService.eliminar(this.itemMenuSel.getCodigo());
            this.itemsMenu = this.menuService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro ");
            this.itemMenuSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }

    @Override
    public void detalles() {
        super.detalles();
        this.itemMenu = this.itemMenuSel;
    }

    public void cancelar() {
        super.reset();
        this.itemMenu = new Menu();
    }

    public void guardar() {
        try {
            if (this.enAgregar) {
                this.menuService.crear(this.itemMenu);
                FacesUtil.addMessageInfo("Se agreg\u00f3 el item al men\u00fa: " + this.itemMenu.getNombre());
            } else {
                this.menuService.modificar(this.itemMenu);
                FacesUtil.addMessageInfo("Se modific\u00f3 el item del men\u00fa con el nombre: " + this.itemMenu.getNombre());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.itemMenu = new Menu();
        this.itemsMenu = this.menuService.obtenerTodos();
    }
    
    public MenuEnum[] getTiposMenu(){
        return MenuEnum.values();
    }

    public Menu getItemMenu() {
        return itemMenu;
    }

    public void setItemMenu(Menu menu) {
        this.itemMenu = menu;
    }

    public Menu getItemMenuSel() {
        return itemMenuSel;
    }

    public void setItemMenuSel(Menu menuSel) {
        this.itemMenuSel = menuSel;
    }
}
