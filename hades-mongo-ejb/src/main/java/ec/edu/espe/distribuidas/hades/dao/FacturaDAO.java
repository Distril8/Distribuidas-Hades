/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.dao;

import ec.edu.espe.distribuidas.hades.model.Cliente;
import ec.edu.espe.distribuidas.hades.model.Factura;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author jubenavides
 */
public class FacturaDAO extends BasicDAO<Factura, ObjectId> {

    public FacturaDAO(Class<Factura> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }

    public List<Factura> findByCliente(Cliente cliente){
        Query<Factura> q = getDatastore().createQuery(Factura.class);
        q.criteria("cliente").equal(cliente.getNombre());
        return q.asList();
    }

}
