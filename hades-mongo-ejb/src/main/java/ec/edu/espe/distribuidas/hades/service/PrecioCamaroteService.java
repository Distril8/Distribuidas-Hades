/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.PrecioCamaroteDAO;
import ec.edu.espe.distribuidas.hades.model.PrecioCamarote;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author toshiba
 */
@Stateless
@LocalBean
public class PrecioCamaroteService {
    
    @EJB
    private MongoPersistence mp;
    private PrecioCamaroteDAO precioCamaroteDAO;

    @PostConstruct
    public void init() {
        this.precioCamaroteDAO = new PrecioCamaroteDAO(PrecioCamarote.class, mp.context());
    }

    public List<PrecioCamarote> obtenerTodos() {
        return this.precioCamaroteDAO.find().asList();
    }

    public void crear(PrecioCamarote precioCamarote) {
        List<PrecioCamarote> aux = this.precioCamaroteDAO.find().asList();
        Integer codigo;
        if (aux.isEmpty()) {
            codigo = 1;
        } else {
            Integer count = aux.size();
            PrecioCamarote last = aux.get(count - 1);
            codigo = last.getCodigo() + 1;
        }
        precioCamarote.setCodigo(codigo);
        this.precioCamaroteDAO.save(precioCamarote);
    }

    public void modificar(PrecioCamarote precioCamarote) {
        PrecioCamarote aux = this.precioCamaroteDAO.findOne("codigo", precioCamarote.getCodigo());
        precioCamarote.setCodigo(aux.getCodigo());
        this.precioCamaroteDAO.save(precioCamarote);
    }

    public void eliminar(String codigo) {
        PrecioCamarote precioCamarote = this.precioCamaroteDAO.findOne("codigo", codigo);
        this.precioCamaroteDAO.delete(precioCamarote);
    }
    
}
