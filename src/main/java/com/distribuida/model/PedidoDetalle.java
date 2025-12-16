package com.distribuida.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table (name = "pedido_detalle")
public class PedidoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_detalle")
    private int idPedidodetalle;

    @Column (name = "fecha")
    private Date fecha;
    @Column (name = "cantidad")
    private int cantidad;
    @Column (name = "precio_unitario")
    private double precio_unitario;
    @Column (name = "subtotal")
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedidos pedidos;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Productos productos;

    public PedidoDetalle () {}

    public PedidoDetalle(int idPedidodetalle, Date fecha, int cantidad, double precio_unitario, Double subtotal, Pedidos pedidos, Productos productos) {
        this.idPedidodetalle = idPedidodetalle;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
        this.pedidos = pedidos;
        this.productos = productos;
    }

    public int getIdPedidodetalle() {
        return idPedidodetalle;
    }

    public void setIdPedidodetalle(int idPedidodetalle) {
        this.idPedidodetalle = idPedidodetalle;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "PedidoDetalle{" +
                "idPedidodetalle=" + idPedidodetalle +
                ", fecha=" + fecha +
                ", cantidad=" + cantidad +
                ", precio_unitario=" + precio_unitario +
                ", subtotal=" + subtotal +
                ", pedidos=" + pedidos +
                ", productos=" + productos +
                '}';
    }
}

