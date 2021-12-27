/*
 * Clase para recoger el detalle de operaciones por producto en tienda
 */
package ec.fin.be.tienda.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author
 */
@XmlRootElement(name = "detalleOperacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class DetalleOperacion {

    private String tipo;
    private Integer cantidad;

    public DetalleOperacion() {
        //Constructor para XML
    }

    /**
     * 
     * @param tipo H= Haber D= Debe
     * @param cantidad numero de productos a registrar 
     */
    public DetalleOperacion(String tipo, Integer cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}
