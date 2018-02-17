/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.TipoAlimentacionDAO;
import ec.edu.espe.distribuidas.hades.model.TipoAlimentacion;
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

public class TipoAlimentacionService {

    @EJB
    private MongoPersistence mp;
    private TipoAlimentacionDAO tipoAlimentacionDao;
    
    @PostConstruct
    public void init() {
        this.tipoAlimentacionDao = new TipoAlimentacionDAO(TipoAlimentacion.class, mp.context());
    }

    public List<TipoAlimentacion> obtenerTodos() {
        return this.tipoAlimentacionDao.find().asList();
    }

    public TipoAlimentacion obtenerPorCodigo(String codigo) {
        return this.tipoAlimentacionDao.findOne("codigo", codigo);
    }
    
    public void crear(TipoAlimentacion tipoAlimentacion) {
        this.tipoAlimentacionDao.save(tipoAlimentacion);
    }

    public void modificar(TipoAlimentacion tipoAlimentacion) {
        TipoAlimentacion aux = this.tipoAlimentacionDao.findOne("codigo", tipoAlimentacion.getCodigo());
        tipoAlimentacion.setId(aux.getId());
        this.tipoAlimentacionDao.save(tipoAlimentacion);
    }

    public void eliminar(String codigo) {
        TipoAlimentacion tipoAlimentacion = this.tipoAlimentacionDao.findOne("codigo", codigo);
        this.tipoAlimentacionDao.delete(tipoAlimentacion);
    }
    
}
