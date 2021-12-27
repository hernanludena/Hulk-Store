/*
 * Clase para controlar las Exception
 */
package ec.fin.be.tienda.utils;

/**
 *
 * @author
 */
public class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

}
