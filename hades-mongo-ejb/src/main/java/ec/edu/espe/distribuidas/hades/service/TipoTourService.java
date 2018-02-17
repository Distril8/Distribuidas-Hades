/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.TipoTourDAO;
import ec.edu.espe.distribuidas.hades.model.TipoTour;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Hades Cruise Corp.
 */
@Stateless
@LocalBean
public class TipoTourService {

    @EJB
    private MongoPersistence mp;
    private TipoTourDAO tipoTourDao;
    
    @PostConstruct
    public void init() {
        this.tipoTourDao = new TipoTourDAO(TipoTour.class, mp.context());
    }

    public List<TipoTour> obtenerTodos() {
        return this.tipoTourDao.find().asList();
    }

    public TipoTour obtenerPorCodigo(String codigo) {
        return this.tipoTourDao.findOne("codigo", codigo);
    }

    public void crear(TipoTour tipoTour) {
        this.tipoTourDao.save(tipoTour);
    }

    public void modificar(TipoTour tipoTour) {
        TipoTour aux = this.tipoTourDao.findOne("codigo", tipoTour.getCodigo());
        tipoTour.setId(aux.getId());
        this.tipoTourDao.save(tipoTour);
        
    }

    public void eliminar(String codigo) {
        TipoTour tipoTour = this.tipoTourDao.findOne("codigo", codigo);
        this.tipoTourDao.delete(tipoTour);
    }
}
