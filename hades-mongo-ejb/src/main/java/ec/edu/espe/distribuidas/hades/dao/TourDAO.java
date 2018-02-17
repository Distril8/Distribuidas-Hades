/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.dao;

import ec.edu.espe.distribuidas.hades.model.TipoTour;
import ec.edu.espe.distribuidas.hades.model.Tour;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author hendrix
 */
public class TourDAO extends BasicDAO<Tour, ObjectId> {

    public TourDAO(Class<Tour> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
    
     public  List<Tour> findByCodigoTour(Tour codigo){
        Query<Tour> qry = getDatastore().createQuery(Tour.class);
        qry.criteria("codigo").equal(codigo);
        return  qry.asList();
     }

    public List<Tour> findByFechas(Date fechaInicio, Date fechaFin) {
        Query<Tour> qry = getDatastore().createQuery(Tour.class);
        qry.and(qry.criteria("fechaInicio").greaterThanOrEq(fechaInicio), qry.criteria("fechaFin").lessThanOrEq(fechaFin));
        return qry.asList();
    }

    public List<Tour> findByTipo(TipoTour tipoTour) {
        Query<Tour> qry = getDatastore().createQuery(Tour.class);
        qry.criteria("tipoTour").equal(tipoTour);
        return qry.asList();

    }
}
