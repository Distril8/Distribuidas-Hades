/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.ConsumoDAO;
import ec.edu.espe.distribuidas.hades.model.Consumo;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bson.types.ObjectId;

/**
 *
 * @author Hades Cruise Corp.
 */
@Stateless
@LocalBean
public class ConsumoService {
    
    @EJB
    private MongoPersistence mp;
    private ConsumoDAO consumoFacade;
    
    @PostConstruct
    public void init() {
        this.consumoFacade = new ConsumoDAO(Consumo.class, mp.context());
    }
    
    public List<Consumo> obtenerTodos() {
        return this.consumoFacade.find().asList();
    }
    
    public Consumo obtenerPorCodigo(String codigo) {
        return this.consumoFacade.findOne("codigo",codigo);
    }
    
     public void crear(Consumo consumo) {
        this.consumoFacade.save(consumo);
    }
     
    public void eliminar(String id) {
        Consumo consumo = this.consumoFacade.get(new ObjectId(id));
        this.consumoFacade.delete(consumo);
    }
    
}
