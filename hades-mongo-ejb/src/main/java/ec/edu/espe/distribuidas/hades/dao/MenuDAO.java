/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.dao;


import ec.edu.espe.distribuidas.hades.model.Menu;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author 
 */
public class MenuDAO extends BasicDAO<Menu, ObjectId> {
    
    public MenuDAO(Class<Menu> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
     public List<Menu> findByTipo(String tipo) {
        Query<Menu> qry = getDatastore().createQuery(Menu.class);
        qry.criteria("tipo").equal(tipo);
        return qry.asList();
    }
}
