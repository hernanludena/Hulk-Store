/**
 * Clase para simular persistencia
 */
package ec.fin.be.tienda.dao;

import ec.fin.be.tienda.utils.CustomException;
import ec.fin.be.tienda.entity.DetalleOperacion;
import ec.fin.be.tienda.entity.Producto;
import ec.fin.be.tienda.entity.ProductoDC;
import ec.fin.be.tienda.entity.ProductoMarvel;
import ec.fin.be.tienda.entity.Tienda;
import ec.fin.be.tienda.enums.TipoEnum;
import ec.fin.be.tienda.servicio.ComponenteProducto;
import ec.fin.be.tienda.utils.Constantes;
import ec.fin.be.tienda.utils.Strings;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

@Stateless
@LocalBean
public class TiendaDAO {

    /**
     * *
     * Metodo para agregar producto a tienda
     *
     * @param tienda Objto tienda extraido de XML
     * @param producto Objeto nuevoa ingresar entienda
     * @return True si el producto se almacena correctamente
     * @throws CustomException
     */
    public Boolean agregarProductoTienda(Tienda tienda, Producto producto) throws CustomException {
        try {
            if (tienda.getProducto() == null) {
                tienda.setProducto(new ArrayList<>());
            }
            generarCodigo(producto);
            List<DetalleOperacion> invinicial = new ArrayList<>();
            producto.setOperaciones(invinicial);
            registrarOperacion(producto, producto.getCantExistencia(), "D");
            tienda.getProducto().add(producto);
            return Boolean.TRUE;
        } catch (NullPointerException ex) {
            Logger.getLogger(TiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new CustomException("Error al iniciar a lista de la tienda");
        }
    }

    /**
     * *
     * Metodo para guardar historial de ventas
     *
     * @param producto Producto de tienda a ingresar o salir
     * @param cantidad Cantidad del producto
     * @param tipo H= Haber D=Debe
     */
    public void registrarOperacion(Producto producto, Integer cantidad, String tipo) {
        producto.getOperaciones().add(new DetalleOperacion(tipo, cantidad));
    }

    /**
     * *
     * Metodo para generar código dependiendo del tipo de clase
     *
     * @param producto Producto de tienda
     */
    public void generarCodigo(Producto producto) {
        ComponenteProducto componenteProducto = new ProductoDC(producto);
        if (TipoEnum.MARVEL.equals(producto.getTipo())) {
            componenteProducto = new ProductoMarvel(producto);
        }
        producto.setId(componenteProducto.generarID());
    }

    /**
     * *
     * Metodo pera remover de tienda
     *
     * @param tienda Objeto Tienda extraido de xml
     * @param producto Producto a ser removido
     * @return True si la operación es satisfactoria
     * @throws CustomException
     */
    public Boolean eliminarProductoTienda(Tienda tienda, Producto producto) throws CustomException {
        try {
            if (tienda.getProducto() == null) {
                tienda.setProducto(new ArrayList<>());
            }
            tienda.getProducto().remove(producto);
            return Boolean.TRUE;
        } catch (NullPointerException ex) {
            Logger.getLogger(TiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new CustomException("Error al iniciar a lista de la tienda");
        }
    }

    /**
     * *
     * Metodo para constrir Objeto Tienda de XML
     *
     * @return Objeto Tienda
     * @throws CustomException
     */
    public Tienda getTienda() throws CustomException {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        try {
            byte[] bytes;
            String lines;
            InputStream inp = new FileInputStream(Constantes.PATHCARGA);
            bytes = IOUtils.toByteArray(inp);
            lines = new String(bytes, Charsets.ISO_8859_1);
            return Strings.unmarshal(lines, Tienda.class, "application/xml");

        } catch (JAXBException | IOException | RuntimeException ex) {
            Logger.getLogger(TiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new CustomException(ex.getMessage());
        }
    }

}
