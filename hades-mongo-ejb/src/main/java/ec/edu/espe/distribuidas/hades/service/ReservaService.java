/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.ReservaDAO;
import ec.edu.espe.distribuidas.hades.model.Reserva;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Hades Cruise Corp.
 */
@Stateless
@LocalBean
public class ReservaService {

    @EJB
    private MongoPersistence mp;
    private ReservaDAO reservaFacade;
    
    @PostConstruct
    public void init(){
    
        this.reservaFacade = new ReservaDAO(Reserva.class, mp.context());
    }
    
    public List<Reserva> obtenerTodos() {
        return this.reservaFacade.find().asList();
    }
    
    public Reserva obtenerPorIdentificacion(String codigo) {
        return this.reservaFacade.findOne("codigo",codigo);
    }
    
    public void crear(Reserva reserva) {
        this.reservaFacade.save(reserva);
    }
    
    public void modificar(Reserva reserva) {
        this.reservaFacade.save(reserva);
    }
    
    public void eliminar(String codigo) {
        Reserva cliente = this.reservaFacade.findOne("identificacion",codigo);
        this.reservaFacade.delete(cliente);
    }
    
}
