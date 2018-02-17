package ec.edu.espe.distribuidas.hades.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hendrix
 */
public class FacesUtil {

    /**
     * Constructor por defecto.
     */
    private FacesUtil() {
    }

    /**
     * Obtiene la referencia al FacesContext.
     *
     * @return FacesContext.
     */
    public static FacesContext facesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Obtiene un componente del �rbol de componentes.
     *
     * @param id Id del componente en la interfaz de usuario.
     * @return UIComponent
     */
    public static UIComponent findComponent(String id) {
        return facesContext().getViewRoot().findComponent(id);
    }

    /**
     * Obtiene la referencia a cualquier tipo de Bean (request, session o application); los beans de
     * scope 'request' deben estar ejecut�ndose para poder acceder a ellos, en cambio que los Beans
     * de scope 'session' pueden ser accedidos incluso si aun no han sido cargados en session.
     *
     * @param <T> Cualquier tipo de dato.
     * @param nombreBean Nombre del Bean, este nombre es el definido en el faces-config.xml
     * @return La referencia al Bean
     */
    @SuppressWarnings({"deprecation", "unchecked"})
    public static <T> T instanciaBean(String nombreBean) {
        return (T) facesContext().getApplication().getVariableResolver().resolveVariable(
                FacesContext.getCurrentInstance(), nombreBean);
    }

    

    
    /**
     * M�todo que permite agregar un mensaje global informativo.
     *
     * @param mensaje Mensaje informativo.
     */
    public static void addMessageInfo(String mensaje) {
        facesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " ", mensaje));
    }

    /**
     * Agrega un mensaje de advertencia.
     *
     * @param clientId ClientId del componente asociado al mensaje.
     * @param mensaje Mensaje de advertencia.
     */
    public static void addMessageWarn(String clientId, String mensaje) {
        facesContext().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", mensaje));
    }

    /**
     * Agrega un mensaje de error.
     *
     * @param clientId ClientId del componente asociado al mensaje.
     * @param mensaje Mensaje de error.
     */
    public static void addMessageError(String clientId, String mensaje) {
        facesContext().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje));
    }

}
