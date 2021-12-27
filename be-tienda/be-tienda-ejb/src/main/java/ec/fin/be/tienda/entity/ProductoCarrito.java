/*
 * Clase para guardar productos seleccionados por comprador
 */
package ec.fin.be.tienda.entity;

/**
 *
 * @author  
 */
public class ProductoCarrito {

    private Integer cantidad;
    private Producto producto;

    public ProductoCarrito() {
        //Contructor 
    }

    /***
     * 
     * @param cantidad Cantidad que van a comprar 
     * @param producto Objeto Producto de tienda
     */
    public ProductoCarrito(Integer cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
