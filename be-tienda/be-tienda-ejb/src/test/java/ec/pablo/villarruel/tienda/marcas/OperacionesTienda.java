/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.pablo.villarruel.tienda.marcas;

import ec.fin.be.tienda.dao.TiendaDAO;
import ec.fin.be.tienda.utils.CustomException;
import ec.fin.be.tienda.entity.Producto;
import ec.fin.be.tienda.entity.ProductoCarrito;
import ec.fin.be.tienda.entity.ProductoMarvel;
import ec.fin.be.tienda.entity.Tienda;
import ec.fin.be.tienda.enums.TipoEnum;
import ec.fin.be.tienda.servicio.TiendaServicio;
import ec.fin.be.tienda.utils.Constantes;
import ec.fin.be.tienda.utils.Strings;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import javax.xml.bind.JAXBException;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author bmg
 */
public class OperacionesTienda {

    @Test
    public void testSinInventario() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        Logger logger = Logger.getLogger(LecturaArchivoXML.class);
        logger.info("Comenzar lectura de archivo ...");
        try {
            byte[] bytes;
            String lines;
            bytes = Files.readAllBytes(Paths.get(Tienda.class.getResource("/tienda.xml").toURI()));
            lines = new String(bytes, Constantes.ISO8859);
            Tienda tienda = Strings.unmarshal(lines, Tienda.class, "application/xml");
            Producto producto = tienda.getProducto().get(0);
            producto.setCantExistencia(0);
            logger.info("Existe " + producto.getCantExistencia());
            logger.info("Comprando  " + 2);
            ProductoCarrito productoCarrito = new ProductoCarrito(2, producto);
            TiendaServicio tiendaServicio = new TiendaServicio();
            tiendaServicio.validarDisponibilidad(productoCarrito);
        } catch (JAXBException | IOException | RuntimeException ex) {
            logger.error(ex);
            Assertions.fail();
        } catch (URISyntaxException ex) {
            java.util.logging.Logger.getLogger(LecturaArchivoXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CustomException ex) {
            Assertions.assertEquals(ex.getMessage(), "No existe inventario");
            logger.error("No existe inventario");

        }

    }

    @Test
    public void testAgregarProducto() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        Logger logger = Logger.getLogger(LecturaArchivoXML.class);
        logger.info("Comenzar lectura de archivo ...");
        try {
            byte[] bytes;
            String lines;
            bytes = Files.readAllBytes(Paths.get(Tienda.class.getResource("/tienda.xml").toURI()));
            lines = new String(bytes, Constantes.ISO8859);
            Tienda tienda = Strings.unmarshal(lines, Tienda.class, "application/xml");
            Producto producto = new Producto();
            producto.setCantVendido(2);
            producto.setId("1");
            producto.setNombre("Lampara Spiderman");
            producto.setTipo(TipoEnum.MARVEL);
            producto.setCantExistencia(10);
            producto.setValorUnitario(new BigDecimal("20.00"));

            TiendaDAO servicio = new TiendaDAO();
            Boolean bacera = servicio.agregarProductoTienda(tienda, producto);
            tienda.getProducto().add(new ProductoMarvel(producto));
            Assertions.assertEquals(bacera, Boolean.TRUE);
        } catch (JAXBException | IOException | RuntimeException ex) {
            Assertions.fail();
            logger.error(ex);

        } catch (URISyntaxException ex) {
            java.util.logging.Logger.getLogger(LecturaArchivoXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CustomException ex) {
            java.util.logging.Logger.getLogger(OperacionesTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testSinCantidadSolicitada() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        Logger logger = Logger.getLogger(OperacionesTienda.class);
        logger.info("Comenzar lectura de archivo ...");
        try {
            byte[] bytes;
            String lines;
            bytes = Files.readAllBytes(Paths.get(Tienda.class.getResource("/tienda.xml").toURI()));
            lines = new String(bytes, Constantes.ISO8859);
            Tienda tienda = Strings.unmarshal(lines, Tienda.class, "application/xml");
            Producto producto = tienda.getProducto().get(0);
            logger.info("Existe " + producto.getCantExistencia());
            logger.info("Comprando " + 5);
            ProductoCarrito productoCarrito = new ProductoCarrito(5, producto);
            TiendaServicio tiendaServicio = new TiendaServicio();
            tiendaServicio.validarDisponibilidad(productoCarrito);

        } catch (JAXBException | IOException | RuntimeException ex) {
            logger.error(ex);
            Assertions.fail();
        } catch (URISyntaxException ex) {
            Assertions.fail();
            logger.error(ex);
        } catch (CustomException ex) {
            logger.error("Solo existe en inventario " + 4);
            Assertions.assertEquals(ex.getMessage(), "Solo existe en inventario " + 4);

        }
    }

    @Test
    public void testComprar() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        Logger logger = Logger.getLogger(OperacionesTienda.class);
        logger.info("Comenzar lectura de archivo ...");
        try {
            byte[] bytes;
            String lines;
            bytes = Files.readAllBytes(Paths.get(Tienda.class.getResource("/tienda.xml").toURI()));
            lines = new String(bytes, Constantes.ISO8859);
            Tienda tienda = Strings.unmarshal(lines, Tienda.class, "application/xml");
            Producto producto = tienda.getProducto().get(0);
            logger.info("Existe " + producto.getCantExistencia());

            logger.info("Comprando 2 producots");
            ProductoCarrito productoCarrito = new ProductoCarrito(2, producto);

            TiendaDAO tiendaDAO = new TiendaDAO();
            TiendaServicio tiendaServicio = new TiendaServicio(tiendaDAO);
            tiendaServicio.comprarProducto(producto, productoCarrito);

            logger.info("Existe " + producto.getCantExistencia());
            Assertions.assertEquals(producto.getCantExistencia(), 3);
        } catch (JAXBException | IOException | RuntimeException ex) {
            Assertions.fail();
            logger.error(ex);
        } catch (URISyntaxException ex) {

            Assertions.fail();
        }
    }
}
