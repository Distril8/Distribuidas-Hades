/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.dao;

import ec.edu.espe.distribuidas.hades.model.TipoValor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author Hades Cruise Corp.
 */
public class TipoValorDAO extends BasicDAO<TipoValor, ObjectId>{
    
    public TipoValorDAO(Class<TipoValor> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
}
