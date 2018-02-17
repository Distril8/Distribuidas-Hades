/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.ClienteDAO;
import ec.edu.espe.distribuidas.hades.model.Cliente;
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
public class ClienteService {

    @EJB
    private MongoPersistence mp;
    private ClienteDAO clienteFacade;

    @PostConstruct
    public void init() {
        this.clienteFacade = new ClienteDAO(Cliente.class, mp.context());
    }

    public List<Cliente> obtenerTodos() {
        return this.clienteFacade.find().asList();
    }

    public Cliente obtenerPorIdentificacion(String identificacion) {
        return this.clienteFacade.findOne("identificacion", identificacion);
    }

    public void crear(Cliente cliente) {
        this.clienteFacade.save(cliente);
    }

    public void modificar(Cliente cliente) {
        Cliente aux = this.clienteFacade.findOne("identificacion", cliente.getIdentificacion());
        cliente.setId(aux.getId());
        this.clienteFacade.save(cliente);
    }

  
    public void eliminar(String identificacion) {
        Cliente cliente = this.clienteFacade.findOne("identificacion", identificacion);
        this.clienteFacade.delete(cliente);
    }
    
    public List<Cliente> obtenerPorCodigo(Cliente identificacion) {
        return this.clienteFacade.findByID(identificacion);
    }
}
