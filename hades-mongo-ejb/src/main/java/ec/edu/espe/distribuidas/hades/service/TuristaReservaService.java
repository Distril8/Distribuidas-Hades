/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.ReservaDAO;
import ec.edu.espe.distribuidas.hades.dao.TuristaReservaDAO;
import ec.edu.espe.distribuidas.hades.model.TuristaReserva;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.bson.types.ObjectId;

/**
 *
 * @author Hades Cruise Corp.
 */
@Stateless
@LocalBean
public class TuristaReservaService {

    @EJB
    private MongoPersistence mp;
    private TuristaReservaDAO turistaFacade;
    private ReservaDAO reservaFacade;

    @PostConstruct
    public void init() {
        this.turistaFacade = new TuristaReservaDAO(TuristaReserva.class, mp.context());
    }

    public List<TuristaReserva> obtenerTodos() {
        return this.turistaFacade.find().asList();
    }

    public TuristaReserva obtenerPorIdentificacion(String identificacion) {
        return this.turistaFacade.findOne("identificacion", identificacion);
    }
    
    public List<TuristaReserva> obtenerPorReserva(String codReserva) {
        return this.turistaFacade.findByReserva(codReserva);
    }

    public void crear(TuristaReserva turista) {
        this.turistaFacade.save(turista);
    }

    public void modificar(TuristaReserva turista) {
        TuristaReserva aux = this.turistaFacade.findOne("identificacion", turista.getIdentificacion());
        turista.setId(aux.getId());
        this.turistaFacade.save(turista);
    }

  
    public void eliminar(String id) {
        TuristaReserva turista = this.turistaFacade.get(new ObjectId(id));
        this.turistaFacade.delete(turista);
    }
    
}
