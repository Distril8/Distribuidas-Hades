/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.dao;

import ec.edu.espe.distribuidas.hades.model.TipoAlimentacion;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author hendrix
 */
public class TipoAlimentacionDAO extends BasicDAO<TipoAlimentacion, ObjectId> {
    
    public TipoAlimentacionDAO(Class<TipoAlimentacion> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
}
