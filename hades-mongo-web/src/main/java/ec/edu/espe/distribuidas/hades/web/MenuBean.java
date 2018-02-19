/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.enums.TipoMenuEnum;
import ec.edu.espe.distribuidas.hades.model.Consumo;
import ec.edu.espe.distribuidas.hades.model.Menu;
import ec.edu.espe.distribuidas.hades.model.Reserva;
import ec.edu.espe.distribuidas.hades.service.ConsumoService;
import ec.edu.espe.distribuidas.hades.service.MenuService;
import ec.edu.espe.distribuidas.hades.web.util.FacesUtil;
import ec.edu.espe.distribuidas.hades.web.util.UtiLjsf;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Hades Cruise Corp.
 */
@Named
@ViewScoped
public class MenuBean extends BaseBean implements Serializable {

    private List<Menu> itemsMenu;
    private String reservaBusqueda;
    private List<Consumo> consumos;
    private List<Reserva> reservas;
    private Consumo consumo;
    private Menu itemMenu;
    private Menu itemMenuSel;
    private String menuBusqueda;
    private String imagen;
 
    @Inject
    private MenuService menuService;
    
    @Inject
    private ConsumoService consumoService;
    

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
    
    public void subirImagen(FileUploadEvent event){
       try{
        this.itemMenu.setImagen(event.getFile().getContents());
        imagen = UtiLjsf.guardaBlobEnFicheroTemporal(this.itemMenu.getImagen(), event.getFile().getFileName());
                }catch(Exception e){
                    e.printStackTrace();
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
    public void guardarConsumo(Integer codigoProducto){
//        this.carrito.setCodigo(carritos.size()+1);
//        this.carrito.setProducto(codigoProducto);
//        this.carritoService.crear(this.carrito);
//        super.reset();
//        this.carrito = new Carrito();
    }
    
    public void buscar() {

        Reserva reserva = new Reserva();
        reserva.setCodigo(this.reservaBusqueda);
     //   this.consumos = this.consumoService.buscarPorReserva(recuperaTipo(reserva));

    }
    
//    public Menu recuperaTipo(String tipo) {
//        Menu aux = new Menu();
//
//        for (int i = 0; i < itemsMenu.size(); i++) {
//            aux = tiposTours.get(i);
//            if (aux.getCodigo().equals(tipoTour.getCodigo())) {
//                break;
//            }
//        }
//        return aux;
//    }


    
    public TipoMenuEnum[] getTiposMenu(){
        return TipoMenuEnum.values();
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

    public String getReservaBusqueda() {
        return reservaBusqueda;
    }

    public void setReservaBusqueda(String reservaBusqueda) {
        this.reservaBusqueda = reservaBusqueda;
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public String getMenuBusqueda() {
        return menuBusqueda;
    }

    public void setMenuBusqueda(String menuBusqueda) {
        this.menuBusqueda = menuBusqueda;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    

}
