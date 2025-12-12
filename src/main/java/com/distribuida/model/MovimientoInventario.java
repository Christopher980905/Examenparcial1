package com.distribuida.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table (name = "movimientos_inventario")
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_movimiento")
    private int idMovimientoinventario;
    @Column (name = "tipo_movimiento")
    private String tipo_movimiento;
    @Column (name = "cantidad")
    private int cantidad;
    @Column (name = "fecha")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Productos productos;

    public MovimientoInventario (int idMovimientoinventario, String tipo_movimiento, int cantidad, Date fecha, int i){}

    public MovimientoInventario(int idMovimientoinventario, String tipo_movimiento, int cantidad, Date fecha, Productos productos) {
        this.idMovimientoinventario = idMovimientoinventario;
        this.tipo_movimiento = tipo_movimiento;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.productos = productos;
    }

    public MovimientoInventario() {

    }

    public int getIdMovimientoinventario() {
        return idMovimientoinventario;
    }

    public void setIdMovimientoinventario(int idMovimientoinventario) {
        this.idMovimientoinventario = idMovimientoinventario;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "MovimientoInventario{" +
                "idMovimientoinventario=" + idMovimientoinventario +
                ", tipo_movimiento='" + tipo_movimiento + '\'' +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                ", productos=" + productos +
                '}';
    }
}
