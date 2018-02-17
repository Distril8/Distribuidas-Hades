/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.service;

import ec.edu.espe.distribuidas.hades.dao.MenuDAO;
import ec.edu.espe.distribuidas.hades.model.Menu;
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
public class MenuService {

    @EJB
    private MongoPersistence mp;
    private MenuDAO menuDao;
    
    @PostConstruct
    public void init() {
        this.menuDao = new MenuDAO(Menu.class, mp.context());
    }

    public List<Menu> obtenerTodos() {
        return this.menuDao.find().asList();
    }

    public Menu obtenerPorCodigo(Integer codigo) {
        return this.menuDao.findOne("codigo", codigo);
    }

    public void crear(Menu menu) {
        List<Menu> aux = this.menuDao.find().asList();
        Integer codigo;
        if (aux.isEmpty()) {
            codigo = 1;
        } else {
            Integer count = aux.size();
            Menu last = aux.get(count - 1);
            codigo = last.getCodigo() + 1;
        }
        menu.setCodigo(codigo);
        this.menuDao.save(menu);
    }

    public void modificar(Menu menu) {
        Menu aux = this.menuDao.findOne("codigo", menu.getCodigo());
        menu.setId(aux.getId());
        this.menuDao.save(menu);
    }

    public void eliminar(Integer codigo) {
        Menu menu = this.menuDao.findOne("codigo", codigo);
        this.menuDao.delete(menu);
    }
}
