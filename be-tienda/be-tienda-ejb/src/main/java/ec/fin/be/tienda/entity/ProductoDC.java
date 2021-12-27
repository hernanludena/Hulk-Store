/*
 *Clase para productos DC  implementa clase ComponenteProducto encaso de metodos 
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
public class ProductoDC extends Producto implements ComponenteProducto {

    public ProductoDC() {
        //Constructor
    }

    /**
     * *
     * Contructor de Obeto Producto
     *
     * @param producto Objeto Producto de tienda
     */
    public ProductoDC(Producto producto) {
        super(producto.getId(), producto.getNombre(), producto.getCantExistencia(), producto.getCantVendido(), producto.getValorUnitario(), producto.getTipo(), producto.gravaIVA);
    }

    /**
     * *
     * metodo para sobreescribir cantidad dependidiendo de futuros
     * requerimientos
     *
     * @param value
     */
    @Override
    public void setCantExistencia(Integer value) {
        this.cantExistencia = value;
    }

    /**
     * *
     ** metodo para sobreescribir cantidad dependidiendo de futuros
     *
     * @return
     */
    @Override
    public Integer getCantExistencia() {
        return this.cantExistencia;
    }

    /**
     * * metodo para sobreescribir valor dependidiendo de futuros
     *
     * @return
     */
    @Override
    public BigDecimal getValorUnitario() {
        return this.valorUnitario;
    }

    /**
     * * metodo para sobreescribir valor dependidiendo de futuros
     *
     * @param valor
     */
    @Override
    public void setValorUnitario(BigDecimal valor) {
        this.valorUnitario = valor;
    }

    /**
     * *
     * * metodo para sobreescribir el valor en caso nuevos requerimientos
     *
     * @return
     */
    @Override
    public BigDecimal getValTotalExistecia() {
        return this.valorUnitario.multiply(new BigDecimal(this.cantExistencia));
    }

    /**
     * *
     * * metodo para sobreescribir valor de venta dependidiendo de futuros
     *
     * @return
     */
    @Override
    public BigDecimal getValTotalVendida() {
        return this.valorUnitario.multiply(getValorVenta());
    }

    /**
     * *
     * Metodo para gerar codigo dependiendo de objeto
     *
     * @return
     */
    @Override
    public String generarID() {
        return "DC-" + FechaUtil.formatearFechaString(new Date(), Constantes.FECHAYYMMDDHHMMSS);
    }

    /**
     * metdo para covertir productoDC a objet Producto
     *
     * @return
     */
    @Override
    public Producto convertirAproProducto() {
        return getProducto();
    }

    /**
     * *
     * * metodo para sobreescribir valor dependiendo del objeto y del iva
     *
     * @return
     */
    @Override
    public BigDecimal getValorVenta() {
        if (getGravaIVA()) {
            return getValorUnitario().add(getValorUnitario().multiply(Constantes.IVA).setScale(2, RoundingMode.CEILING));
        }
        return this.valorUnitario;
    }

    /**
     * *
     *Metodo para obtener iva dependiendo del objeto
     * @return
     */
    @Override
    public BigDecimal getIVAProducto() {
        if (getGravaIVA()) {
            return getValorUnitario().multiply(Constantes.IVA).setScale(2, RoundingMode.CEILING);
        }
        return BigDecimal.ZERO;
    }

}
