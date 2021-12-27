package ec.fin.be.portal.generic.servlet;

import java.io.IOException;
import java.rmi.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author
 */
public class PostIndexServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("j_username");
        String password = request.getParameter("j_password");

        if (usuario == null || password == null) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("username", usuario);
            request.getSession().setAttribute("password", password);
            getServletContext().getRequestDispatcher("/validacion.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (UnknownHostException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
