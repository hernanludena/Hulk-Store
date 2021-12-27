/*
 * Clase para extraer cantidad y valor en detale kardex
 */
package ec.fin.be.tienda.entity;

import java.math.BigDecimal;

/**
 *
 * @author
 */
public class DetalleKardex {

    private BigDecimal valor;
    private Integer cantidad;

    /***
     * Constructor 
     * @param valor valor acumulado de detalle operaciones de productos
     * @param cantidad  cantidad acumulado de detalle operaciones de productos
     */
    public DetalleKardex(BigDecimal valor, Integer cantidad) {
        this.valor = valor;
        this.cantidad = cantidad;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    
}
