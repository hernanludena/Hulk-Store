package ec.fin.be.portal.generic;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import ec.fin.be.portal.generic.bean.BaseBean;
import ec.fin.be.tienda.enums.RolEnum;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.PrimeFaces;

@Named(value = "login")
@SessionScoped
public class Login extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;

    private MenuModel model;

    /**
     * Se inicializa los valores para agregar la fotografia o un imagen estandar
     * deacuerdo el genero del funcionario.
     */
    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();

    }

    /**
     * Realiza la validacion de la autenticacion mediante el sevicio de
     * seguridad en el AD y o COBIS
     */
    public void validateUsernamePassword() {
        try {
            Logger.getLogger(Login.class.getName()).log(Level.INFO, null, "Inicia el proceso de Autenticacion");
            msg = "";
            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ctx.getRequest();
            HttpServletResponse response = (HttpServletResponse) ctx.getResponse();
            if (!"".equals(user) && !"".equals(pwd) && !user.isEmpty() && !pwd.isEmpty()) {
                validarCredenciales(request, response);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o Password Incorrecto", "valide sus credenciales"));
                msg = "Usuario o Password Incorrecto valide sus credenciales";
            }

        } catch (IOException e) {
            msg = "Errores técnicos favor comunicarse con el Administrador del sistema";
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, msg);
        }
//     
        Logger.getLogger(Login.class.getName()).log(Level.INFO, null, "Finaliza el proceso de Autenticacion");
    }

    private void validarCredenciales(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("username", user);
        request.getSession().setAttribute("pwd", pwd);
        if (user != null && pwd != null && user.equals(RolEnum.VENDEDOR.toString()) && pwd.equals(RolEnum.VENDEDOR.toString())) {
            setCredecialesSession(request, response, RolEnum.VENDEDOR, "Vendedor 1");
            return;
        }
        if (user != null && pwd != null && user.equals(RolEnum.COMPRADOR.toString()) && pwd.equals(RolEnum.COMPRADOR.toString())) {
             setCredecialesSession(request, response, RolEnum.COMPRADOR, "Comprador 1");
            return;
        }

        msg = "Error en autenticación";
        enviarMensajeError(null, "Error", msg);
        response.sendRedirect(request.getContextPath());

    }

    public void setCredecialesSession(HttpServletRequest request, HttpServletResponse response, RolEnum rol, String nombre) throws IOException {
        request.getSession().setAttribute("ROL", rol.toString());
        request.getSession().setAttribute("Nombre", nombre);
        PrimeFaces.current().executeScript("PF('login').hide();");
        enviarMensajeInfo(null, "Ingreso a Sistema", "Bienvenido " + user);
    }

    /**
     * Realiza el deslogueo de la sesion
     */
    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        borrarTodoDelSession(request);
        if (session != null) {
            session.invalidate();
        }
//        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/tienda.xhtml");
    }

    /**
     * Borra la informacion de la sesion
     *
     * @param request
     */
    public void borrarTodoDelSession(HttpServletRequest request) {
        Enumeration<?> datosSession = request.getSession().getAttributeNames();
        while (datosSession.hasMoreElements()) {
            Object datoSession = datosSession.nextElement();
            request.getSession().removeAttribute((String) datoSession);
        }

    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

}
