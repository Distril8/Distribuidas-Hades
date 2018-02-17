/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.dao;

import ec.edu.espe.distribuidas.hades.model.Cliente;
import ec.edu.espe.distribuidas.hades.model.TuristaReserva;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Hades Cruise Corp.
 */
public class TuristaDAO extends BasicDAO<TuristaReserva, ObjectId> {

    public TuristaDAO(Class<TuristaReserva> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
    
    public  List<Cliente> findByID(Cliente identificacion){
        Query<Cliente> qry = getDatastore().createQuery(Cliente.class);
        qry.criteria("identificacion").equal(identificacion);
        return  qry.asList();
    }
}
