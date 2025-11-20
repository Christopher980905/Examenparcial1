package com.distribuida.model;

public class FacturaDetalle {
    private int idFacturadetalle;
    private int cantidad;
    private Double subtotal;
    private Factura factura;
    public  FacturaDetalle() {}

    public FacturaDetalle(int idFacturadetalle, int cantidad, Factura factura, Double subtotal) {
        this.idFacturadetalle = idFacturadetalle;
        this.cantidad = cantidad;
        this.factura = factura;
        this.subtotal = subtotal;
    }

    public int getIdFacturadetalle() {
        return idFacturadetalle;
    }

    public void setIdFacturadetalle(int idFacturadetalle) {
        this.idFacturadetalle = idFacturadetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public String toString() {
        return "FacturaDetalle{" +
                "idFacturadetalle=" + idFacturadetalle +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", factura=" + factura +
                '}';
    }
}
