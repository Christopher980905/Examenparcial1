package com.distribuida.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name = "pagos")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_pago")
    private int idPago;
    @Column (name = "metodo_pago")
    private String metodo_pago;
    @Column (name = "fecha_pago")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_pedidos")
    private Pedidos pedidos;

    public Pagos(int idPago, String efectivo,  Date fecha, int i){}

    public Pagos(int idPago, String metodo_pago, Date fecha, Pedidos pedidos) {
        this.idPago = idPago;
        this.metodo_pago = metodo_pago;
        this.fecha = fecha;
        this.pedidos = pedidos;
    }

    public Pagos() {

    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Pagos{" +
                "idPago=" + idPago +
                ", metodo_pago='" + metodo_pago + '\'' +
                ", fecha=" + fecha +
                ", pedidos=" + pedidos +
                '}';
    }
}
