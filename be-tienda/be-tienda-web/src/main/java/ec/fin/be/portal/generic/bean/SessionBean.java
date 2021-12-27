package ec.fin.be.portal.generic.bean;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

@Named
@SessionScoped
public class SessionBean extends BaseBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SessionBean() {
        // construtor del bean de sesion
    }

    /**
     * Retorna el context path de la navegación en la que se encuentre el
     * usuario.
     *
     * @return
     */
    public String getContextPath() {
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        return context.getContextPath();
    }

}
