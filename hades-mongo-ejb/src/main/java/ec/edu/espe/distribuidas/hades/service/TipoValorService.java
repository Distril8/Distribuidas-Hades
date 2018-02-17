/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.TipoValorDAO;
import ec.edu.espe.distribuidas.hades.model.TipoValor;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
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
public class TipoValorService {
    
    @EJB
    private MongoPersistence mp;
    private TipoValorDAO tipoValorDAO;
    
    @PostConstruct
    public void init() {
        this.tipoValorDAO = new TipoValorDAO(TipoValor.class, mp.context());
    }

    public List<TipoValor> obtenerTodos() {
        return this.tipoValorDAO.find().asList();
    }

    public TipoValor obtenerPorCodigo(String codigo) {
        return this.tipoValorDAO.findOne("codigo", codigo);
    }

    public void crear(TipoValor tipoValor) {
        this.tipoValorDAO.save(tipoValor);
    }

    public void modificar(TipoValor tipoValor) {
        TipoValor aux = this.tipoValorDAO.findOne("codigo", tipoValor.getCodigo());
        tipoValor.setId(aux.getId());
        this.tipoValorDAO.save(tipoValor);
    }

    public void eliminar(String codigo) {
        TipoValor tipoValor = this.tipoValorDAO.findOne("codigo", codigo);
        this.tipoValorDAO.delete(tipoValor);
    }
}
