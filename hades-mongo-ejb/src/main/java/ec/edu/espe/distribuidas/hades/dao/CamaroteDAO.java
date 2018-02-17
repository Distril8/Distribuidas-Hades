/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.dao;

import ec.edu.espe.distribuidas.hades.model.Camarote;
import ec.edu.espe.distribuidas.hades.model.Crucero;
import ec.edu.espe.distribuidas.hades.model.TipoCamarote;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author hendrix
 */
public class CamaroteDAO extends BasicDAO<Camarote, ObjectId> {

    public CamaroteDAO(Class<Camarote> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }

    public List<Camarote> findByCruceroTipo(Crucero crucero, TipoCamarote tipoCamarote) {
        Query<Camarote> qry = getDatastore().createQuery(Camarote.class);
        qry.and(
                qry.criteria("nomCrucero").equal(crucero.getNombre()),
                qry.criteria("nomTipo").equal(tipoCamarote.getNombre())
        );
        return qry.asList();
    }

    public List<Camarote> findByCrucero(Crucero crucero) {
        Query<Camarote> qry = getDatastore().createQuery(Camarote.class);
        qry.criteria("nomCrucero").equal(crucero.getNombre());
        return qry.asList();
    }

    public List<Camarote> findByTipo(TipoCamarote tipoCamarote) {
        Query<Camarote> qry = getDatastore().createQuery(Camarote.class);
        qry.criteria("tipo").equal(tipoCamarote);
        return qry.asList();
    }
}
