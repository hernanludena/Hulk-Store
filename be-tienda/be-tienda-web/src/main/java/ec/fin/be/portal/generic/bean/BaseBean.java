package ec.fin.be.portal.generic.bean;

import ec.fin.be.tienda.enums.RolEnum;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author
 */
public class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public BaseBean() {
    }

    public static FacesMessage enviarMensaje(String idComponente, FacesMessage.Severity severidad, String resumen,
            String detalle) {
        FacesMessage mensaje = new FacesMessage(severidad, resumen, detalle);
        FacesContext.getCurrentInstance().addMessage(idComponente, mensaje);
        return mensaje;
    }

    public void enviarMensajeInfo(String idComponente, String resumen, String detalle) {
        enviarMensaje(idComponente, FacesMessage.SEVERITY_INFO, resumen, detalle);
    }

    public void enviarMensajeWarning(String idComponente, String resumen, String detalle) {
        enviarMensaje(idComponente, FacesMessage.SEVERITY_WARN, resumen, detalle);
    }

    public void enviarMensajeError(String idComponente, String resumen, String detalle) {
        enviarMensaje(idComponente, FacesMessage.SEVERITY_ERROR, resumen, detalle);
    }

    public String getIp() {
        String ip = null;
        if (FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().getExternalContext() != null) {
            ip = calcularIP((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        }
        return ip;
    }

    public HttpSession getHttpSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static String calcularIP(HttpServletRequest request) {
        String ip = "Indefinida";
        ip = request.getHeader("X-Real-IP");
        if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
            // La primera IP entregada por el proxy
            int index = ip.indexOf(',');
            return index != -1 ? ip.substring(0, index) : ip;
        }
        ip = request.getRemoteAddr();
        return ip;
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String getNombre() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("Nombre").toString();
    }

    public Boolean validarSesion() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session == null) {
                return Boolean.FALSE;
            }
            String nombre = session.getAttribute("Nombre").toString();
            return nombre == null || nombre.isEmpty() ? Boolean.FALSE : Boolean.TRUE;
        } catch (NullPointerException ex) {
            return Boolean.FALSE;
        }

    }
    
    public Boolean getRolVendedor() {
        return RolEnum.valueOf(getRol()).equals(RolEnum.VENDEDOR);
    }

    public String getRol() {
        HttpSession session = getHttpSession();
        if (session != null) {
            return (String) session.getAttribute("ROL");
        }
        return null;
    }

    public String reset(String path, String controller) {
        getFacesContext().getViewRoot().getViewMap().remove(controller);
        return path.concat("?faces-redirect=true");

    }

    protected ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected ServletContext getServletContext() {
        return (ServletContext) getExternalContext().getContext();
    }

}
