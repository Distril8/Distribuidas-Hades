/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.ValorReservaDAO;
import ec.edu.espe.distribuidas.hades.model.ValorReserva;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import javax.annotation.PostConstruct;

/**
 *
 * @author Hades Cruise Corp.
 */

@Stateless
@LocalBean
public class ValorReservaService {
    
    @EJB
    private MongoPersistence mp;
    private ValorReservaDAO valorReservaDAO;
    
    
    @PostConstruct
    public void init() {
        this.valorReservaDAO = new ValorReservaDAO(ValorReserva.class, mp.context());
    }

    public List<ValorReserva> obtenerTodos() {
        return this.valorReservaDAO.find().asList();
    }

    

    public void crear(ValorReserva valorReserva) {
        this.valorReservaDAO.save(valorReserva);
    }

    public void modificar(ValorReserva valorReserva) {
        this.valorReservaDAO.save(valorReserva);
    }

    public void eliminar(String codigo) {
        ValorReserva valorReserva = this.valorReservaDAO.findOne("codigo", codigo);
        this.valorReservaDAO.delete(valorReserva);
    }
   
}
