/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.pablo.villarruel.tienda.marcas;

import ec.fin.be.tienda.entity.Producto;
import ec.fin.be.tienda.entity.ProductoMarvel;
import ec.fin.be.tienda.entity.Tienda;
import ec.fin.be.tienda.utils.Constantes;
import ec.fin.be.tienda.utils.Strings;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import javax.xml.bind.JAXBException;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author bmg
 */
public class LecturaArchivoXML {

    @Test
    public void testRaiz() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        Logger logger = Logger.getLogger(LecturaArchivoXML.class);
        logger.info("Comenzar lectura de archivo ...");
        try {
            byte[] bytes;
            String lines;
            bytes = Files.readAllBytes(Paths.get(Tienda.class.getResource("/tienda.xml").toURI()));
            lines = new String(bytes, Constantes.ISO8859);
            Tienda tienda = Strings.unmarshal(lines, Tienda.class, "application/xml");
            String nombreTienda = tienda.getNombreTienda();

            Assertions.assertEquals("HULK STORE", nombreTienda);
        } catch (JAXBException | IOException | RuntimeException ex) {
            Assertions.fail();
            logger.error(ex);
        } catch (URISyntaxException ex) {
            java.util.logging.Logger.getLogger(LecturaArchivoXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testNodo() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        Logger logger = Logger.getLogger(LecturaArchivoXML.class);
        logger.info("Comenzar lectura de archivo ...");
        try {
            byte[] bytes;
            String lines;
            bytes = Files.readAllBytes(Paths.get(Tienda.class.getResource("/tienda.xml").toURI()));
            lines = new String(bytes, Constantes.ISO8859);
            Tienda tienda = Strings.unmarshal(lines, Tienda.class, "application/xml");
            List<Producto> productos = tienda.getProducto();
            ProductoMarvel productoMarvelDC = new ProductoMarvel(productos.get(0));
            Assertions.assertEquals("Camiseta Hulk", productoMarvelDC.getNombre());
        } catch (JAXBException | IOException | RuntimeException ex) {
            Assertions.fail();
            logger.error(ex);
        } catch (URISyntaxException ex) {
            java.util.logging.Logger.getLogger(LecturaArchivoXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testConvertirNodoBigDecimal() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        Logger logger = Logger.getLogger(LecturaArchivoXML.class);
        logger.info("Comenzar lectura de archivo ...");
        try {
            byte[] bytes;
            String lines;
            bytes = Files.readAllBytes(Paths.get(Tienda.class.getResource("/tienda.xml").toURI()));
            lines = new String(bytes, Constantes.ISO8859);
            Tienda tienda = Strings.unmarshal(lines, Tienda.class, "application/xml");
            List<Producto> productos = tienda.getProducto();
            BigDecimal valor = new BigDecimal("15.5").setScale(2, RoundingMode.CEILING);

            Assertions.assertEquals(valor, productos.get(0).getValorUnitario());
        } catch (JAXBException | IOException | RuntimeException ex) {
            Assertions.fail();
            logger.error(ex);
        } catch (URISyntaxException ex) {
            java.util.logging.Logger.getLogger(LecturaArchivoXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
