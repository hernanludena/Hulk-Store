/*
 * Clase Base para heredar campos de productos.
 */
package ec.fin.be.tienda.entity;

import ec.fin.be.tienda.adaptadores.BigDecimalAdaptador;
import ec.fin.be.tienda.adaptadores.EnumTipoAdaptador;
import ec.fin.be.tienda.enums.TipoEnum;
import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author
 */
@XmlRootElement(name = "producto")
@XmlAccessorType(XmlAccessType.FIELD)
public class Producto {

    protected String id;
    protected String nombre;
    protected Integer cantExistencia;
    protected Integer cantVendido;
    @XmlJavaTypeAdapter(BigDecimalAdaptador.class)
    protected BigDecimal valorUnitario;
    @XmlJavaTypeAdapter(EnumTipoAdaptador.class)
    protected TipoEnum tipo;
    protected Boolean gravaIVA;
    @XmlElement(name = "detalleOperacion")
    protected List<DetalleOperacion> operaciones;

    public Producto() {
        //Constructor para XML
    }

    /**
     *
     * @param id de auto genera el momento de guardar
     * @param nombre nombre o detalle de producto
     * @param cantExistencia cantidad que esta ingresando
     * @param cantVendido cantidad de productos que venden
     * @param valorUnitario valor del produto
     * @param tipo MARVER/DC
     * @param gravaIVA True si el producto tiene IVA
     */
    public Producto(String id, String nombre, Integer cantExistencia, Integer cantVendido, BigDecimal valorUnitario, TipoEnum tipo, Boolean gravaIVA) {
        this.id = id;
        this.nombre = nombre;
        this.cantExistencia = cantExistencia;
        this.cantVendido = cantVendido;
        this.valorUnitario = valorUnitario;
        this.tipo = tipo;
        this.gravaIVA = gravaIVA;
    }

    public Producto getProducto() {
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getCantExistencia() {
        return cantExistencia;
    }

    public void setCantExistencia(Integer cantExistencia) {
        this.cantExistencia = cantExistencia;
    }

    public Integer getCantVendido() {
        return cantVendido;
    }

    public void setCantVendido(Integer cantVendido) {
        this.cantVendido = cantVendido;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public Boolean getGravaIVA() {
        return gravaIVA;
    }

    public void setGravaIVA(Boolean gravaIVA) {
        this.gravaIVA = gravaIVA;
    }

    public List<DetalleOperacion> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(List<DetalleOperacion> operaciones) {
        this.operaciones = operaciones;
    }

}
