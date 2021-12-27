package ec.fin.be.portal.generic.bean;

import ec.fin.be.portal.generic.Login;
import ec.fin.be.tienda.utils.CustomException;
import ec.fin.be.tienda.entity.DetalleKardex;
import ec.fin.be.tienda.entity.Producto;
import ec.fin.be.tienda.entity.ProductoCarrito;
import ec.fin.be.tienda.entity.ProductoDC;
import ec.fin.be.tienda.entity.ProductoKardex;
import ec.fin.be.tienda.entity.ProductoMarvel;
import ec.fin.be.tienda.entity.Tienda;
import ec.fin.be.tienda.enums.TipoEnum;
import ec.fin.be.tienda.servicio.ComponenteProducto;
import ec.fin.be.tienda.servicio.TiendaServicio;
import ec.fin.be.tienda.utils.FechaUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author
 */
@Named(value = "tiendaBean")
@ViewScoped
public class TiendaBean extends BaseBean {

    @Inject
    private ApplicationBean applicationBean;
    @Inject
    private Login login;
    @EJB
    private TiendaServicio tiendaServicio;
    private Producto producto;
    private Tienda tienda;
    private Integer cantidad;
    private List<ProductoCarrito> carrito;
    private List<ProductoKardex> productosKardex;
    private Boolean pagar;
    private BigDecimal ivaCobrado;

    @PostConstruct
    void init() {
        pagar = Boolean.FALSE;
        tienda = applicationBean.getTienda();
        generarKardex();
    }

    /**
     * Metodo para ocultar carrito de compras dependiendo del rol
     */
    public void pagarCarrito() {
        pagar = !pagar;
    }

    /**
     * Metodo para agredar producto a tiendas
     */
    public void agregarProducto() {
        try {
            if (producto.getId() == null) {
                tiendaServicio.agregarProductoTienda(tienda, producto);
                enviarMensajeInfo(null, "En hora buena", "Producto Agregado");
                return;
            }
            enviarMensajeInfo(null, "En hora buena", "Producto Editado");
        } catch (CustomException ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
            enviarMensajeError(null, "Error", ex.getMessage());
        }
    }

    /**
     * * Metodo para agredar producto a carrio de compras
     */
    public void agregarCarrito() {
        try {
            if (cantidad == null || cantidad < 1) {
                enviarMensajeError(null, "Error", "Cantidad no puede ser menor a 1");
                return;
            }
            ProductoCarrito productoCarrito = new ProductoCarrito(cantidad, producto);
            tiendaServicio.validarDisponibilidad(productoCarrito);
            carrito.add(productoCarrito);
            cantidad = null;
            enviarMensajeInfo(null, "Prodcuto Agregado", "Se ha agregado produco a Carrito");
            PrimeFaces.current().executeScript("PF('manageProductDialog').hide();");
        } catch (NullPointerException ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
            carrito = new ArrayList<>();
            agregarCarrito();
        } catch (CustomException ex) {
            Logger.getLogger(TiendaBean.class.getName()).log(Level.SEVERE, null, ex);
            enviarMensajeError(null, "Error", ex.getMessage());
        }
    }

    /**
     * Metodo para pagar los productos de carrito de compras
     */
    public void pagarValoresCarrito() {
        for (ProductoCarrito productoCarrito : carrito) {
            tiendaServicio.comprarProducto(productoCarrito.getProducto(), productoCarrito);
        }
        reiniciarCarrito();
        enviarMensajeInfo(null, "Pagado", "Productos serán enviados");
    }

    /**
     * Metodo para cancelar compra de carrito
     */
    public void cancelarCarrito() {
        reiniciarCarrito();
        enviarMensajeWarning(null, "Cancelado", "Los productos del carrito han sido eliminados");
    }

    /**
     * Metodo que coparte compra y calcelar de carrito
     */
    public void reiniciarCarrito() {
        carrito.clear();
        PrimeFaces.current().executeScript("PF('compraDialog').hide();");
    }

    /**
     * metodo para generar kardex desde constructor
     */
    public void generarKardex() {
        ivaCobrado = BigDecimal.ZERO;
        productosKardex = new ArrayList<>();
        Integer cantidadIva = 0;
        for (Producto productoInventario : tienda.getProducto()) {
            ComponenteProducto componenteProducto = getComponenteProducto(productoInventario);
            ivaCobrado = ivaCobrado.add(componenteProducto.getIVAProducto());
            if (productoInventario.getGravaIVA()) {
                cantidadIva++;
            }
        }
        productosKardex.add(tiendaServicio.getCantidadKardex(tienda));
        productosKardex.add(new ProductoKardex("IVA Cobrado", new DetalleKardex(ivaCobrado, cantidadIva), new DetalleKardex(null, null)));

    }

    /**
     * *
     * Meteodo para obtener interfaz de producto
     *
     * @param producto
     * @return
     */
    public ComponenteProducto getComponenteProducto(Producto producto) {
        if (producto.getTipo().equals(TipoEnum.MARVEL)) {
            return new ProductoMarvel(producto);
        }
        return new ProductoDC(producto);
    }

    /**
     * *
     * MEtodo para calcualr el iva si el producto tiene true en gravaIVA
     *
     * @return
     */
    public BigDecimal calcularValorIVA() {

        return calcularIvaGenerico(producto);
    }

    /**
     * MEtodo para generarel iva desde metodos en comun
     *
     * @param prodCalcular
     * @return
     */
    public BigDecimal calcularIvaGenerico(Producto prodCalcular) {
        return tiendaServicio.calcularPrecioIVA(prodCalcular);
    }

    public void nuevoProducto() {
        producto = new Producto();
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<ProductoCarrito> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<ProductoCarrito> carrito) {
        this.carrito = carrito;
    }

    public Boolean getPagar() {
        return pagar;
    }

    public void setPagar(Boolean pagar) {
        this.pagar = pagar;
    }

    public BigDecimal getIvaCobrado() {
        return ivaCobrado;
    }

    public void setIvaCobrado(BigDecimal ivaCobrado) {
        this.ivaCobrado = ivaCobrado;
    }

    public List<ProductoKardex> getProductosKardex() {
        return productosKardex;
    }

    public void setProductosKardex(List<ProductoKardex> productosKardex) {
        this.productosKardex = productosKardex;
    }

}
