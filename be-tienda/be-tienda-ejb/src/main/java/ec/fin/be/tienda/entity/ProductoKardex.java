/*
 * Clase para registrar detalle de Kardex
 */
package ec.fin.be.tienda.entity;

/**
 *
 * @author
 */
public class ProductoKardex {

    private String detalle;
    private DetalleKardex detalleHaber;
    private DetalleKardex detalleDebe;

   /***
    * Constructor para registrar detalle de kardex
    * @param detalle Detalle a mostrar en KArdex
    * @param detalleHaber Detalle de haberes en kardex
    * @param detalleDebe  DEtalle de debes en kardex
    */
    public ProductoKardex(String detalle, DetalleKardex detalleHaber, DetalleKardex detalleDebe) {
        this.detalle = detalle;
        this.detalleHaber = detalleHaber;
        this.detalleDebe = detalleDebe;
    }
    
    

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public DetalleKardex getDetalleHaber() {
        return detalleHaber;
    }

    public void setDetalleHaber(DetalleKardex detalleHaber) {
        this.detalleHaber = detalleHaber;
    }

    public DetalleKardex getDetalleDebe() {
        return detalleDebe;
    }

    public void setDetalleDebe(DetalleKardex detalleDebe) {
        this.detalleDebe = detalleDebe;
    }
    
    
}
