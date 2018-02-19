/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.UsuarioDAO;
import ec.edu.espe.distribuidas.hades.model.Usuario;
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

public class AutenticacionService {

    @EJB
    private MongoPersistence mp;
    private UsuarioDAO usuarioDao;
    
    @PostConstruct
    public void init() {
        this.usuarioDao = new UsuarioDAO(Usuario.class, mp.context());
    }

    public List<Usuario> obtenerTodos() {
        return this.usuarioDao.find().asList();
    }

    public Usuario obtenerPorCodigo(String codigo) {
        return this.usuarioDao.findOne("codigo", codigo);
    }
    
    public void crear(Usuario usuario) {
        this.usuarioDao.save(usuario);
    }

    public void modificar(Usuario usuario) {
        Usuario aux = this.usuarioDao.findOne("codigo", usuario.getCodigo());
        usuario.setId(aux.getId());
        this.usuarioDao.save(usuario);
    }

    public void eliminar(String codigo) {
        Usuario usuario = this.usuarioDao.findOne("codigo", codigo);
        this.usuarioDao.delete(usuario);
    }
    
    public Usuario login(String codigo, String clave) {
        Usuario usuarioAuxiliar = this.obtenerPorCodigo(codigo);
        if (usuarioAuxiliar!=null && usuarioAuxiliar.getClave().equals(clave)) {
            return usuarioAuxiliar;
        } else {
            return null;
        }
    }
}
