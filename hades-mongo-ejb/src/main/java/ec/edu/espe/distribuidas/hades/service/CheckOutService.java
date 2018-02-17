/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.CheckOutDAO;
import ec.edu.espe.distribuidas.hades.model.CheckOut;
import ec.edu.espe.distribuidas.hades.model.Cliente;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.bson.types.ObjectId;

/**
 *
 * @author Hades Cruise Corp.
 */
@Stateless
@LocalBean
public class CheckOutService {

    @EJB
    private MongoPersistence mp;
    private CheckOutDAO checkOutDAO;
    
    @PostConstruct
    public void init() {
        this.checkOutDAO = new CheckOutDAO(CheckOut.class, mp.context());
    }

    public List<CheckOut> obtenerTodos() {
        return this.checkOutDAO.find().asList();
    }

    public CheckOut obtenerPorCodigo(CheckOut checkOut) {
        CheckOut aux = this.checkOutDAO.get(new ObjectId(checkOut.getId()));
        return aux;
    }
    
    public List<CheckOut> obtenerPorCliente(Cliente cliente) {
        return this.checkOutDAO.findByCliente(cliente);
    }

    public void crear(CheckOut checkOut) {
        this.checkOutDAO.save(checkOut);
    }

    public void modificar(CheckOut checkOut) {
        CheckOut aux = this.checkOutDAO.get(new ObjectId(checkOut.getId()));
        checkOut.setId(aux.getId());
        this.checkOutDAO.save(checkOut);
    }

    public void eliminar(CheckOut checkOut) {
        CheckOut aux = this.checkOutDAO.get(new ObjectId(checkOut.getId()));
        checkOut.setId(aux.getId());
        this.checkOutDAO.delete(checkOut);
    }
    
}
