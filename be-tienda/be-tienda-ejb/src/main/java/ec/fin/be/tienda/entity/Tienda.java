/*
 * Objeto para almacenar todos los productos y transaciones que se realizan en la tienda
 */
package ec.fin.be.tienda.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author
 */
@XmlRootElement(name = "tienda")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tienda {

    private String nombreTienda;
    private String creador;
    private String motivo;
    @XmlElement(name = "producto")
    private List<Producto> producto;

    public Tienda() {
        //Constructor para XML
    }

    /***
     * Constructor
     * @param nombreTienda Nombe de tienda
     * @param creador Nombre quien realizó el ejecricio
     * @param motivo Motivo para realizar el ejercicio
     * @param producto Detalle de productos de tiendas
     */
    public Tienda(String nombreTienda, String creador, String motivo, List<Producto> producto) {
        this.nombreTienda = nombreTienda;
        this.creador = creador;
        this.motivo = motivo;
        this.producto = producto;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

}
