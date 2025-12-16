package com.distribuida.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table (name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_pedido")
    private int idPedido;
    @Column (name = "fecha_pedido")
    private Date fechapedido;
    @Column (name = "estado")
    private String estado;
    @Column (name = "total_neto")
    private Double totalneto;
    @Column (name = "IVA")
    private Double IVA;
    @Column (name = "monto_pagar")
    private Double monto_pagar;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;



    public Pedidos(int idPedido, Date fechapedido, String estado, double totalneto, double iva, double monto_pagar, int i) {}

    public Pedidos(int idPedido, Date fechapedido, String estado, Double totalneto, Double IVA, Double monto_pagar, Cliente cliente) {
        this.idPedido = idPedido;
        this.fechapedido = fechapedido;
        this.estado = estado;
        this.totalneto = totalneto;
        this.IVA = IVA;
        this.monto_pagar = monto_pagar;
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

    public Double getTotalneto() {
        return totalneto;
    }

    public void setTotalneto(Double totalneto) {
        this.totalneto = totalneto;
    }

    public Double getIVA() {
        return IVA;
    }

    public void setIVA(Double IVA) {
        this.IVA = IVA;
    }

    public Double getMonto_pagar() {
        return monto_pagar;
    }

    public void setMonto_pagar(Double monto_pagar) {
        this.monto_pagar = monto_pagar;
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
                ", totalneto=" + totalneto +
                ", IVA=" + IVA +
                ", monto_pagar=" + monto_pagar +
                ", cliente=" + cliente +
                '}';
    }
}
