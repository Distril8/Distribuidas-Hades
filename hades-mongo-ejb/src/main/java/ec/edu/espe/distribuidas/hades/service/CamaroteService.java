/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.CamaroteDAO;
import ec.edu.espe.distribuidas.hades.model.Camarote;
import ec.edu.espe.distribuidas.hades.model.Crucero;
import ec.edu.espe.distribuidas.hades.model.TipoCamarote;
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
public class CamaroteService {
    
    @EJB
    private MongoPersistence mp;
    private CamaroteDAO camaroteFacade;
    
    @PostConstruct
    public void init() {
        this.camaroteFacade = new CamaroteDAO(Camarote.class, mp.context());
    }
    
    public List<Camarote> obtenerPorCruceroTipo(Crucero CruceroBusqueda, TipoCamarote TipoCamaroteBusqueda ) {
        return this.camaroteFacade.findByCruceroTipo(CruceroBusqueda,TipoCamaroteBusqueda);
    }
    
    public List<Camarote> obtenerPorCrucero(Crucero crucero) {
        return this.camaroteFacade.findByCrucero(crucero);
    }
    
    public List<Camarote> obtenerPorTipo(TipoCamarote TipoCamaroteBusqueda) {
        return this.camaroteFacade.findByTipo(TipoCamaroteBusqueda);
    }
    
    public List<Camarote> obtenerTodos() {
        return this.camaroteFacade.find().asList();
    }

    public void crear(Camarote camarote) {
        this.camaroteFacade.save(camarote);
    }
//    public Camarote obtenerInfoCamarote(String codCrucero, Integer numCamarote){
//        return this.camaroteFacade.findCodTipCamarote(codCrucero, numCamarote);
//    }
}
