package com.distribuida.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private int idPedido;
    @Column(name = "fecha_pedido")
    private Date fechapedido;
    @Column(name = "estado")
    private String estado;
    @Column(name = "IVA")
    private Double IVA;
    @Column(name = "total_neto")
    private Double totalneto;

    @ManyToOne
    @JoinColumn (name = "id_cliente")
    private Cliente cliente;

    public Pedidos(int idPedido, Date fechapedido, String pendiente, double iva, double totalneto, int i) {}

    public Pedidos(int idPedido, Date fechapedido, String estado, Double IVA, Double totalneto, Cliente cliente) {
        this.idPedido = idPedido;
        this.fechapedido = fechapedido;
        this.estado = estado;
        this.IVA = IVA;
        this.totalneto = totalneto;
        this.cliente = cliente;
    }

    public Pedidos() {

    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getIVA() {
        return IVA;
    }

    public void setIVA(Double IVA) {
        this.IVA = IVA;
    }

    public Double getTotalneto() {
        return totalneto;
    }

    public void setTotalneto(Double totalneto) {
        this.totalneto = totalneto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedidos{" +
                "idPedido=" + idPedido +
                ", fechapedido=" + fechapedido +
                ", estado='" + estado + '\'' +
                ", IVA=" + IVA +
                ", totalneto=" + totalneto +
                ", cliente=" + cliente +
                '}';
    }
}
