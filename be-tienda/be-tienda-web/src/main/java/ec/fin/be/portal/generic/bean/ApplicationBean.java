package ec.fin.be.portal.generic.bean;

import ec.fin.be.tienda.dao.TiendaDAO;
import ec.fin.be.tienda.utils.CustomException;
import ec.fin.be.tienda.entity.Tienda;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class ApplicationBean {

    private Tienda tienda;
    @EJB
    private TiendaDAO tiendaDAO;

    @PostConstruct
    public void init() {
        try {
            tienda = tiendaDAO.getTienda();
        } catch (CustomException ex) {
            Logger.getLogger(ApplicationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Tienda getTienda() {
        return tienda;
    }

}
