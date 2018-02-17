/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.dao;


import ec.edu.espe.distribuidas.hades.model.Menu;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author 
 */
public class MenuDAO extends BasicDAO<Menu, ObjectId> {
    
    public MenuDAO(Class<Menu> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
    
}
