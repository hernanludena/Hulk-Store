/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.be.tienda.servicio;

import ec.fin.be.tienda.entity.Producto;
import java.math.BigDecimal;

/**
 *
 * @author
 */
public interface ComponenteProducto {

    String generarID();

    BigDecimal getValTotalExistecia();

    BigDecimal getValTotalVendida();

    BigDecimal getIVAProducto();

    Producto convertirAproProducto();

    BigDecimal getValorVenta();

    Producto getProducto();

}
