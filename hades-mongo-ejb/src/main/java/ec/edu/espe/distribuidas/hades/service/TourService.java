/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.TourDAO;
import ec.edu.espe.distribuidas.hades.model.TipoTour;
import ec.edu.espe.distribuidas.hades.model.Tour;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Hades Cruise Corp.
 */
@Stateless
@LocalBean
public class TourService {

    @EJB
    private MongoPersistence mp;
    private TourDAO tourDAO;

    @PostConstruct
    public void init() {
        this.tourDAO = new TourDAO(Tour.class, mp.context());
    }

    public List<Tour> obtenerTodos() {
        return this.tourDAO.find().asList();
    }

    public void crear(Tour tour) {
        List<Tour> aux = this.tourDAO.find().asList();
        Integer codigo;
        if (aux.isEmpty()) {
            codigo = 1;
        } else {
            Integer count = aux.size();
            Tour last = aux.get(count - 1);
            codigo = last.getCodigo() + 1;
        }
        tour.setCodigo(codigo);
        this.tourDAO.save(tour);
    }

    public void modificar(Tour tour) {
        Tour aux = this.tourDAO.findOne("codigo", tour.getCodigo());
        tour.setId(aux.getId());
        this.tourDAO.save(tour);
    }

    public void eliminar(String codigo) {
        Tour tour = this.tourDAO.findOne("codigo", codigo);
        this.tourDAO.delete(tour);
    }
    
    public List<Tour> obtenerPorCodigoTour(Tour codigo) {
        return this.tourDAO.findByCodigoTour(codigo);
    }

    public List<Tour> buscarPorFechas(Date fechaInicioBusqueda, Date fechaFinBusqueda) {
        return this.tourDAO.findByFechas(fechaInicioBusqueda, fechaFinBusqueda);
    }
    
    public List<Tour> buscarPorTipo(TipoTour tipoTourBusqueda) {
        return this.tourDAO.findByTipo(tipoTourBusqueda);
    }
}
