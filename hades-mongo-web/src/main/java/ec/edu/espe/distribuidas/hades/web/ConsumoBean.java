/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.web;

import ec.edu.espe.distribuidas.hades.enums.TipoMenuEnum;
import ec.edu.espe.distribuidas.hades.model.Cliente;
import ec.edu.espe.distribuidas.hades.model.Consumo;
import ec.edu.espe.distribuidas.hades.model.Menu;
import ec.edu.espe.distribuidas.hades.model.Reserva;
import ec.edu.espe.distribuidas.hades.service.ClienteService;
import ec.edu.espe.distribuidas.hades.service.ConsumoService;
import ec.edu.espe.distribuidas.hades.service.MenuService;
import ec.edu.espe.distribuidas.hades.service.ReservaService;
import ec.edu.espe.distribuidas.hades.web.util.FacesUtil;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class ConsumoBean extends BaseBean implements Serializable {

    private String filtro;
    private String reservaBusqueda;
    private boolean enBusquedaPorTipo;
    private boolean enReservaElegido;
    private Menu menu;
    private Menu menuSel;
    private boolean enBusquedaPorReserva;
    private Cliente cliente;
     private boolean enEncontrado;

    private List<Consumo> consumos;
    private List<Menu> menus;
    private Consumo consumo;
    private Consumo consumoSel;
    private Reserva reservaSel;
    private Reserva reserva;
    private List<Reserva> reservas;
    private String auxBusqueda;
    private String tipoBusqueda;

    @Inject
    private ConsumoService consumoService;
    @Inject
    private ReservaService reservaService;
     @Inject
    private MenuService menuService;
     
     @Inject
    private ClienteService clienteService;

    @PostConstruct
    public void init() {
        this.filtro = "RES";
        this.enBusquedaPorTipo = true;
        this.enBusquedaPorReserva = true;
        
        this.consumos = this.consumoService.obtenerTodos();
        this.consumo = new Consumo();
        this.reservas = this.reservaService.obtenerTodos();
        this.menus = this.menuService.obtenerTodos();
        this.enReservaElegido = false;
        this.enEncontrado = false;

    }

//    public void cambiarFiltro() {
//        this.enBusquedaPorTipo = !this.enBusquedaPorTipo;
//        System.out.println("Valor para enbusqueda: " + this.enBusquedaPorTipo);
//    }
    
    public void cambiarFiltro() {
        this.enBusquedaPorReserva = !this.enBusquedaPorReserva;
    }

    public void buscar() {
        this.reserva = new Reserva();
        this.reservas.clear();
        if (enBusquedaPorReserva) {
            this.reserva = this.reservaService.obtenerPorIdentificacion(auxBusqueda);
            if (reserva != null) {
                enEncontrado = true;
                this.reservas = this.reservaService.obtenerPorCliente(cliente);
                this.reservas.add(this.reserva);
            } else {
                FacesUtil.addMessageInfo("No se encontro reserva, verifar el codigo de reserva");
            }
        } else {
            this.cliente = this.clienteService.obtenerPorIdentificacion(auxBusqueda);
            if (cliente != null) {
                enEncontrado = true;
                this.reservas = this.reservaService.obtenerPorCliente(cliente);
            } else {
                FacesUtil.addMessageInfo("No se encontro cliente, verifique la identificacion");
            }
        }
    }

    @Override
    public void agregar() {
        this.consumo = new Consumo();
        this.reservas = this.reservaService.obtenerTodos();
        super.agregar();
    }

    public void cancelar() {
        super.reset();
        this.consumo = new Consumo();
        this.reservas = this.reservaService.obtenerTodos();

    }
    
    public void buscarMenu(){
        this.menus = this.menuService.obtenerPorTipo(tipoBusqueda);
        
    }
    
    public void buscarR(){
        this.reserva = this.reservaService.obtenerPorIdentificacion(auxBusqueda);
    }

    @Override
    public void modificar() {
        super.modificar();
        this.consumo = new Consumo();
        this.consumo.setCodigo(this.consumoSel.getCodigo());
        this.consumo.setReserva(this.consumoSel.getReserva());
        this.consumo.setMenu(this.consumoSel.getMenu());
        this.consumo.setCantidad(this.consumoSel.getCantidad());
        this.consumo.setFecha(this.consumoSel.getFecha());
        this.consumo.setValor(this.consumoSel.getValor());
        this.consumo.setReferencia(this.consumoSel.getReferencia());
    }

    public void eliminar() {
        try {
            this.consumoService.eliminar(this.consumoSel.getCodigo().toString());
            this.consumos = this.consumoService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro");
            this.consumoSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informaci\u00f3n relacionada.");
        }
    }

    public void guardarConsumo(Menu codigoProducto, String codReserva) {
        System.out.println("" + codReserva);
        this.consumo.setCodigo(consumos.size() + 1);
        this.consumo.setMenu(codigoProducto);
        System.out.println("" + codReserva);
        this.consumoService.crear(this.consumo);
        super.reset();
        this.consumo = new Consumo();
    }

    public void guardar() {
        try {

            
            this.consumo.setCodigo(consumos.size() + 1);
            
            this.consumo.setFecha(fecha());
            this.consumoService.crear(this.consumo);
            FacesUtil.addMessageInfo("Se agrego el Consumo de valor: " + this.consumo.getValor());

        } catch (Exception e) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }

        super.reset();
        this.consumo = new Consumo();
        this.menu = new Menu();
        //this.reserva = new Reserva();
       // this.menus = this.menuService.obtenerTodos();
       // this.reservas = this.reservaService.obtenerTodos();
        

    }

    public void elegirReserva() {
        this.consumo.setReserva(this.reservaSel);
        this.enReservaElegido = true;
        System.out.println(this.consumo.getReserva());
    }
    
    public void elegirMenu(Menu menuSel) {
        //if(this.consumo.getCantidad()==0){
        this.consumo.setMenu(this.menuSel);
        
        //
        this.consumo.setValor(valor(this.consumo.getCantidad(),this.menuSel.getPrecio()));
        //}
       
        System.out.println(""+this.consumo.getMenu());
    }
    
    public BigDecimal valor(Integer cantidad, BigDecimal precio)
    {
        BigDecimal valor = BigDecimal.ZERO;
        BigDecimal costo = BigDecimal.ZERO;
        costo = precio.multiply(new BigDecimal(cantidad));
        valor =  valor.add(costo);
        return costo;   
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
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

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public Consumo getConsumoSel() {
        return consumoSel;
    }

    public void setConsumoSel(Consumo consumoSel) {
        this.consumoSel = consumoSel;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public boolean isEnBusquedaPorTipo() {
        return enBusquedaPorTipo;
    }

    public void setEnBusquedaPorTipo(boolean enBusquedaPorTipo) {
        this.enBusquedaPorTipo = enBusquedaPorTipo;
    }

    public Reserva retornaReserva(Consumo consumo) {
        Reserva aux = new Reserva();

        for (int i = 0; i < reservas.size(); i++) {
            aux = reservas.get(i);
            if (aux.getCodigo().equals(consumo.getReserva().getCodigo())) {
                break;
            }
        }
        return aux;
    }

    public Reserva recuperaReserva(Reserva reserva) {
        Reserva aux = new Reserva();

        for (int i = 0; i < reservas.size(); i++) {
            aux = reservas.get(i);
            if (aux.getCodigo().equals(reserva.getCodigo())) {
                break;
            }
        }
        return aux;
    }

    public boolean isEnReservaElegido() {
        return enReservaElegido;
    }

    public void setEnReservaElegido(boolean enReservaElegido) {
        this.enReservaElegido = enReservaElegido;
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

    public Reserva getReservaSel() {
        return reservaSel;
    }

    public void setReservaSel(Reserva reservaSel) {
        this.reservaSel = reservaSel;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public String getAuxBusqueda() {
        return auxBusqueda;
    }

    public void setAuxBusqueda(String auxBusqueda) {
        this.auxBusqueda = auxBusqueda;
    }

    public Menu getMenuSel() {
        return menuSel;
    }

    public void setMenuSel(Menu menuSel) {
        this.menuSel = menuSel;
    }
    
    public Date fecha(){
        Date fecha = new Date();
        return fecha;
    }

    public boolean isEnBusquedaPorReserva() {
        return enBusquedaPorReserva;
    }

    public void setEnBusquedaPorReserva(boolean enBusquedaPorReserva) {
        this.enBusquedaPorReserva = enBusquedaPorReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isEnEncontrado() {
        return enEncontrado;
    }

    public void setEnEncontrado(boolean enEncontrado) {
        this.enEncontrado = enEncontrado;
    }

    public String getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(String tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public TipoMenuEnum[] getTiposMenu() {
        return TipoMenuEnum.values();
    }
    

    
}
