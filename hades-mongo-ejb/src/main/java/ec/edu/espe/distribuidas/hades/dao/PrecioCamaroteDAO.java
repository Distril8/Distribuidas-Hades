/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.dao;
import ec.edu.espe.distribuidas.hades.model.PrecioCamarote;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author toshiba
 */
public class PrecioCamaroteDAO extends BasicDAO<PrecioCamarote, ObjectId> {
    
     public PrecioCamaroteDAO(Class<PrecioCamarote> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
   
}
