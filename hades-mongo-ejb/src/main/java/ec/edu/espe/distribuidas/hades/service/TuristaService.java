/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.TuristaDAO;
import ec.edu.espe.distribuidas.hades.model.TuristaReserva;
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
public class TuristaService {

    @EJB
    private MongoPersistence mp;
    private TuristaDAO turistaDao;

    @PostConstruct
    public void init() {
        this.turistaDao = new TuristaDAO(TuristaReserva.class, mp.context());
    }

    public List<TuristaReserva> obtenerTodos() {
        return this.turistaDao.find().asList();
    }

    public TuristaReserva obtenerPorCodigo(String codigo) {
        return this.turistaDao.findOne("codigo", codigo);
    }

    public void crear(TuristaReserva turista) {
        this.turistaDao.save(turista);
    }

    public void modificar(TuristaReserva turista) {
        TuristaReserva aux = this.turistaDao.findOne("codigo", turista.getCodigo());
        turista.setId(aux.getId());
        this.turistaDao.save(turista);
    }

    public void eliminar(Integer codigo) {
        TuristaReserva turista = this.turistaDao.findOne("codigo", codigo);
        this.turistaDao.delete(turista);
    }

}
