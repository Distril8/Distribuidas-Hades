/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.CruceroDAO;
import ec.edu.espe.distribuidas.hades.model.Crucero;
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
public class CruceroService {
    
    @EJB
    private MongoPersistence mp;
    private CruceroDAO cruceroFacade;
    
    @PostConstruct
    public void init() {
        this.cruceroFacade = new CruceroDAO(Crucero.class, mp.context());
    }
    
    public List<Crucero> obtenerTodos() {
        return this.cruceroFacade.find().asList();
    }
    
    public Crucero obtenerPorCodigo(String codigo) {
        return this.cruceroFacade.findOne("codigo",codigo);
    }
    
    public void crear(Crucero crucero) {
        this.cruceroFacade.save(crucero);
    }
    
    public void modificar(Crucero crucero) {
        Crucero aux = this.cruceroFacade.findOne("codigo", crucero.getCodigo());
        crucero.setId(aux.getId());
        this.cruceroFacade.save(crucero);
    }
    
    public void eliminar(String codigo) {
        Crucero crucero = this.cruceroFacade.findOne("codigo", codigo);
        this.cruceroFacade.delete(crucero);
    }
}