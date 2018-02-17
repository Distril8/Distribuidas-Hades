/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.FacturaDAO;
import ec.edu.espe.distribuidas.hades.model.Factura;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import javax.annotation.PostConstruct;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bson.types.ObjectId;

/**
 *
 * @author jubenavides
 */
@Stateless
@LocalBean
public class FacturaService {

    @EJB
    private MongoPersistence mp;
    private FacturaDAO facturaFacade;

    @PostConstruct
    public void init() {

        this.facturaFacade = new FacturaDAO(Factura.class, mp.context());
    }

    public List<Factura> obtenerTodos() {
        return this.facturaFacade.find().asList();
    }

    public Factura obtenerPorCodigo(ObjectId codigo) {
        return this.facturaFacade.get(codigo);
    }

    public void crear(Factura factura) {
        this.facturaFacade.save(factura);
    }

    public void modificar(Factura factura) {
        this.facturaFacade.save(factura);
    }

    public void eliminar(ObjectId codigo) {
        Factura factura = this.facturaFacade.get(codigo);
        this.facturaFacade.delete(factura);
    }

}
