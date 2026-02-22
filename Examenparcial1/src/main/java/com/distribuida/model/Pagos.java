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



    public Pagos(){}

    public Pagos(int idPago, String metodo_pago) {
        this.idPago = idPago;
        this.metodo_pago = metodo_pago;
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

    @Override
    public String toString() {
        return "Pagos{" +
                "idPago=" + idPago +
                ", metodo_pago='" + metodo_pago + '\'' +
                '}';
    }
}
