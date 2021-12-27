package ec.fin.be.portal.generic;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import ec.fin.be.comunes.log.LoggerBEUtil;

public class Filtro implements Filter {

    private FilterConfig filterConfig;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        if (servletRequest.getSession(false) != null) {

        } else {
            servletResponse.sendRedirect(servletRequest.getContextPath());
        }

    }

    /**
     * Verifica si el usuario tiene permiso de acceder a la URL invocada.
     *
     * @param direccion
     * @param path
     * @param cedulaUsuario
     * @return
     */
    public Boolean verificaURL(String direccion, String path, Long cedulaUsuario) {
        return true;
    }

    private void doFilterChain(FilterChain filterChain, ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ServletException ex) {
            Logger.getLogger(Filtro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

}
