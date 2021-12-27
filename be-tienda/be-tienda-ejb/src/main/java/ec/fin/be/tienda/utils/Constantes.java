/**
 * Contantes de sistema
 */
package ec.fin.be.tienda.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author
 */
public class Constantes {

    public static final String NOMBRE_SISTEMA = "Nombre del sistema";
    public static final BigDecimal IVA = new BigDecimal("0.12").setScale(2, RoundingMode.CEILING);
    public static final String PATHCARGA = "/opt/properties/tienda/tienda.xml";
    public static final String FECHAYYMMDDHHMMSS = "yyyymmddHHMMss";
    public static final String ID_SISTEMA = "";
    public static final String MENSAJE_NO_AUTORIZADO = "No se encuentra autorizado para acceder al sistema";
    public static final String MENSAJE_USUARIO = "Usuario invalido";
    public static final String MENSAJE_OK = "Usuario autenticado";
    public static final String PAGINA_PRINCIPAL = "/tienda.jsf";
    public static final String PAGINA_LOGIN = "/tienda.jsf";
    public static final String LOGIN_SERVLET = "LoginServlet";
    public static final String ISO8859 = "ISO-8859-1";

    private Constantes() {

    }

}
