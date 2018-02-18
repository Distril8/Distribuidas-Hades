/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.dao;

import ec.edu.espe.distribuidas.hades.model.Consumo;
import ec.edu.espe.distribuidas.hades.model.Cliente;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author hendrix
 */
public class ConsumoDAO  extends BasicDAO<Consumo, ObjectId> {
    
    public ConsumoDAO(Class<Consumo> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
    
}
