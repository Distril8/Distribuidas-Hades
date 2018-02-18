/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.ConsumoDAO;
import ec.edu.espe.distribuidas.hades.model.Consumo;
import ec.edu.espe.distribuidas.hades.model.Reserva;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Hades Cruise Corp.
 */
@Stateless
@LocalBean
public class ConsumoService {

    @EJB
    private MongoPersistence mp;
    private ConsumoDAO consumoDAO;

    @PostConstruct
    public void init() {
        this.consumoDAO = new ConsumoDAO(Consumo.class, mp.context());
    }

    public List<Consumo> obtenerTodos() {
        return this.consumoDAO.find().asList();
    }

    public void crear(Consumo consumo) {
        List<Consumo> aux = this.consumoDAO.find().asList();
        Integer codigo;
        if (aux.isEmpty()) {
            codigo = 1;
        } else {
            Integer count = aux.size();
            Consumo last = aux.get(count - 1);
            codigo = last.getCodigo() + 1;
        }
        consumo.setCodigo(codigo);
        this.consumoDAO.save(consumo);
    }

    public void modificar(Consumo consumo) {
        Consumo aux = this.consumoDAO.findOne("codigo", consumo.getCodigo());
        consumo.setId(aux.getId());
        this.consumoDAO.save(consumo);
    }

    public void eliminar(String codigo) {
        Consumo consumo = this.consumoDAO.findOne("codigo", codigo);
        this.consumoDAO.delete(consumo);
    }
    
    public List<Consumo> obtenerPorCodigo(Consumo codigo) {
        return this.consumoDAO.findByCodigoConsumo(codigo);
    }
    
    public List<Consumo> buscarPorReserva(Reserva reservaBusqueda) {
        return this.consumoDAO.findByReserva(reservaBusqueda);
    }
}
