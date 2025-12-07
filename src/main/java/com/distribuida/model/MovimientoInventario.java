package com.distribuida.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "movimientos_inventario")
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private int idMovimientoinventario;
    @Column (name = "tipo")
    private String tipo;
    @Column (name = "cantidad")
    private int cantidad;
    @Column (name = "fecha")
    private Date fecha;
    @ManyToOne
    @JoinColumn (name = "id_producto")
    private Productos productos;

    public MovimientoInventario (int idMovimientoinventario, String tipo, int cantidad, Date fecha, int i){}

    public MovimientoInventario(int idMovimientoinventario, String tipo, int cantidad, Date fecha, Productos productos) {

        this.idMovimientoinventario = idMovimientoinventario;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
                ", tipo='" + tipo + '\'' +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                ", productos=" + productos +
                '}';
    }
}
