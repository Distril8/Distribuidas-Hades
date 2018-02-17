/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.TipoCamaroteDAO;
import ec.edu.espe.distribuidas.hades.model.TipoCamarote;
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
public class TipoCamaroteService {

    @EJB
    private MongoPersistence mp;
    private TipoCamaroteDAO tipoCamaroteFacade;
    
    @PostConstruct
    public void init() {
        this.tipoCamaroteFacade = new TipoCamaroteDAO(TipoCamarote.class, mp.context());
    }

    public List<TipoCamarote> obtenerTodos() {
        return this.tipoCamaroteFacade.find().asList();
    }

    public TipoCamarote obtenerPorCodigo(String codigo) {
        return this.tipoCamaroteFacade.findOne("codigo", codigo);
    }

    public void crear(TipoCamarote tipoCamarote) {
        this.tipoCamaroteFacade.save(tipoCamarote);
    }

    public void modificar(TipoCamarote tipoCamarote) {
        TipoCamarote aux = this.tipoCamaroteFacade.findOne("codigo", tipoCamarote.getCodigo());
        tipoCamarote.setId(aux.getId());
        this.tipoCamaroteFacade.save(tipoCamarote);
    }

    public void eliminar(String codigo) {
        TipoCamarote tipoCamarote = this.tipoCamaroteFacade.findOne("codigo", codigo);
        this.tipoCamaroteFacade.delete(tipoCamarote);
    }
}
