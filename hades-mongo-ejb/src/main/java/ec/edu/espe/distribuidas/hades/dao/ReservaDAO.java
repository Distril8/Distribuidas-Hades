/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.dao;

import ec.edu.espe.distribuidas.hades.model.Cliente;
import ec.edu.espe.distribuidas.hades.model.Crucero;
import ec.edu.espe.distribuidas.hades.model.Reserva;
import ec.edu.espe.distribuidas.hades.model.TipoTour;
import ec.edu.espe.distribuidas.hades.model.Tour;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author joel
 */
public class ReservaDAO extends BasicDAO<Reserva, ObjectId>{
    
    public ReservaDAO(Class<Reserva> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
    
    public List<Reserva> findByCliente(Cliente identificacion){
        Query<Reserva> qry = getDatastore().createQuery(Reserva.class);
        qry.criteria("identificacion").equal(identificacion);
        return qry.asList();
    }
    
    public List<Reserva> findByTour(Tour codigo, TipoTour codTipoTour, Crucero crucero)
    {
        Query<Reserva> qry = getDatastore().createQuery(Reserva.class);
        qry.and(
            qry.criteria("codigo").equal(codigo),
            qry.criteria("codTipoTour").equal(codTipoTour),
            qry.criteria("crucero").equal(crucero)
        );
        return qry.asList();
    }
}
