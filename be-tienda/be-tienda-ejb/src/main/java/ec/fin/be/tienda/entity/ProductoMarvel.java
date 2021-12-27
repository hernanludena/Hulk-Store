/*
 *Clase para productos MArvel  implementa clase ComponenteProducto encaso de metodos 
 *que deban ser implementados de diferente forma para la tienda 
 */
package ec.fin.be.tienda.entity;

import ec.fin.be.tienda.servicio.ComponenteProducto;
import ec.fin.be.tienda.utils.Constantes;
import ec.fin.be.tienda.utils.FechaUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author
 */
@XmlRootElement(name = "producto")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductoMarvel extends Producto implements ComponenteProducto {

    public ProductoMarvel() {
        //Constructor para XML
    }

    public ProductoMarvel(Producto producto) {
        super(producto.getId(), producto.getNombre(), producto.getCantExistencia(), producto.getCantVendido(), producto.getValorUnitario(), producto.getTipo(), producto.gravaIVA);
    }

    /**
     * *
     * Sobreexcribir catidad existencia
     *
     * @param value
     */
    @Override
    public void setCantExistencia(Integer value) {
        this.cantExistencia = value;
    }

    /**
     * *
     * Metodo para sobreescribir la lectura de cantidad existencia
     *
     * @return
     */
    @Override
    public Integer getCantExistencia() {
        return this.cantExistencia;
    }

    /**
     * Metodo para sobre escribir el valor unitario
     *
     * @return
     */
    @Override
    public BigDecimal getValorUnitario() {
        return this.valorUnitario;
    }

    /**
     * *
     * Metodo para sobre escribir elser de valor unitario
     * @param valor
     */
    @Override
    public void setValorUnitario(BigDecimal valor) {
        this.valorUnitario = valor;
    }

    /***
     * Metodo para sobre esribir el valor de existencia 
     * @return 
     */
    @Override
    public BigDecimal getValTotalExistecia() {
        return this.valorUnitario.multiply(new BigDecimal(this.cantExistencia));
    }

    /***
     * Metodo para sobre escribir valores de ventas
     * @return 
     */
    @Override
    public BigDecimal getValTotalVendida() {
        return this.valorUnitario.multiply(new BigDecimal(this.cantVendido));
    }

    
    @Override
    public String generarID() {
        return "Marvel-" + FechaUtil.formatearFechaString(new Date(), Constantes.FECHAYYMMDDHHMMSS);
    }

    @Override
    public Producto convertirAproProducto() {
        return getProducto();
    }

    @Override
    public BigDecimal getValorVenta() {
        if (getGravaIVA()) {
            return getValorUnitario().add(getValorUnitario().multiply(Constantes.IVA).setScale(2, RoundingMode.CEILING));
        }
        return this.valorUnitario;
    }

    @Override
    public BigDecimal getIVAProducto() {
        if (getGravaIVA()) {
            return getValorUnitario().multiply(Constantes.IVA).setScale(2, RoundingMode.CEILING);
        }
        return BigDecimal.ZERO;
    }

}
