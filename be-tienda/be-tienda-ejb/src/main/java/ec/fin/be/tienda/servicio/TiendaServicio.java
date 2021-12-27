/** *
 * Clase para gestionar todas las operaciones de la tienda
 */
package ec.fin.be.tienda.servicio;

import ec.fin.be.tienda.dao.TiendaDAO;
import ec.fin.be.tienda.utils.CustomException;
import ec.fin.be.tienda.entity.DetalleKardex;
import ec.fin.be.tienda.entity.DetalleOperacion;
import ec.fin.be.tienda.entity.Producto;
import ec.fin.be.tienda.entity.ProductoCarrito;
import ec.fin.be.tienda.entity.ProductoDC;
import ec.fin.be.tienda.entity.ProductoKardex;
import ec.fin.be.tienda.entity.ProductoMarvel;
import ec.fin.be.tienda.entity.Tienda;
import ec.fin.be.tienda.enums.TipoEnum;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class TiendaServicio {

    @EJB
    private TiendaDAO tiendaDAO;

    public TiendaServicio() {
    }

    public TiendaServicio(TiendaDAO tiendaDAO) {
        this.tiendaDAO = tiendaDAO;
    }

    /**
     * metodo para validar dispornibilidad de producto en tienda
     *
     * @param productoCarrito Objeto selecionado de carrito
     * @return
     * @throws CustomException
     */
    public Boolean validarDisponibilidad(ProductoCarrito productoCarrito) throws CustomException {
        if (productoCarrito.getProducto().getCantExistencia() == 0) {
            throw new CustomException("No existe inventario");
        } else if (productoCarrito.getProducto().getCantExistencia() < productoCarrito.getCantidad()) {
            throw new CustomException("Solo existe en inventario " + productoCarrito.getProducto().getCantExistencia());

        }
        return Boolean.TRUE;
    }

    /**
     * Metodo para comprar producto de tienda
     *
     * @param producto Objeto de tienda
     * @param carrito objeto de carrito seleccionado
     */
    public void comprarProducto(Producto producto, ProductoCarrito carrito) {
        registrarOperacion(producto, carrito.getCantidad(), "H");
        producto.setCantExistencia(producto.getCantExistencia() - carrito.getCantidad());
        producto.setCantVendido(producto.getCantVendido() + carrito.getCantidad());
    }

    /**
     * Metodo para guardar nuevo producto en tienda
     *
     * @param tienda Objeto Tienda de XML
     * @param producto Objeto nuevo a registrar
     * @return
     * @throws CustomException
     */
    public Boolean agregarProductoTienda(Tienda tienda, Producto producto) throws CustomException {
        return tiendaDAO.agregarProductoTienda(tienda, producto);
    }

    /**
     * Metodo para eliminar producto
     *
     * @param tienda objeto Tienda de XML
     * @param producto producto seleccionado a eliminar
     * @return
     * @throws CustomException
     */
    public Boolean eliminarProductoTienda(Tienda tienda, Producto producto) throws CustomException {
        return tiendaDAO.eliminarProductoTienda(tienda, producto);
    }

    /**
     * Metodo para calcular el iva de objetos que tengan iva true
     *
     * @param producto Objeto Producto selecionado para carrito
     * @return
     */
    public BigDecimal calcularPrecioIVA(Producto producto) {
        ComponenteProducto componenteProducto = new ProductoDC(producto);
        if (TipoEnum.MARVEL.equals(producto.getTipo())) {
            componenteProducto = new ProductoMarvel(producto);
        }
        return componenteProducto.getValorVenta();
    }

    /**
     * Metodo para registrar las operaciones de cada producto
     *
     * @param producto Objeto Producto de tienda
     * @param cantidad Cantidad a comprar
     * @param tipo H= Haber D= Debe
     */
    public void registrarOperacion(Producto producto, Integer cantidad, String tipo) {
        tiendaDAO.registrarOperacion(producto, cantidad, tipo);
    }

    /**
     * Metodo para obtener estado actual de productos para kardex
     *
     * @param tienda Objeto de XML
     * @return
     */
    public ProductoKardex getCantidadKardex(Tienda tienda) {
        DetalleKardex detalleKardexHaber = getCantidadDetalle(tienda, "H");
        DetalleKardex detalleKardexDebe = getCantidadDetalle(tienda, "D");

        return new ProductoKardex("Mercaderia", detalleKardexHaber, detalleKardexDebe);
    }

    /**
     * Metodo para calcular los totales del detalle de productos
     *
     * @param tienda Objeto de XML
     * @param tipo H = Haber D= Debe
     * @return
     */
    public DetalleKardex getCantidadDetalle(Tienda tienda, String tipo) {
        BigDecimal valor = BigDecimal.ZERO;
        Integer cantidad = 0;
        for (Producto producto : tienda.getProducto()) {
            for (DetalleOperacion operacion : producto.getOperaciones()) {
                if (tipo.equals(operacion.getTipo())) {
                    valor = valor
                            .add(producto.getValorUnitario()
                                    .multiply(new BigDecimal(operacion.getCantidad())))
                            .setScale(2, RoundingMode.CEILING);
                    cantidad = cantidad + operacion.getCantidad();
                }
            }
        }
        return new DetalleKardex(valor, cantidad);
    }

}
