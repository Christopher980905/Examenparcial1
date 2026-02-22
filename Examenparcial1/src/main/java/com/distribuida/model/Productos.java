package com.distribuida.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table (name = "productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_producto")
    private int idProducto;
    @Column (name = "nombre")
    private String nombre;
    @Column (name = "precio")
    private Double precio;
    @Column (name = "stock")
    private int stock;
    @Column (name = "fondo")
    private String fondo;
    @Column (name = "fecha_registro")
    private Date fecharegistro;

    @ManyToOne
    @JoinColumn(name = "id_categoria")

    private Categoria categoria;

    public Productos () {}

    public Productos(int idProducto, String nombre, Double precio, int stock, String fondo, Date fecharegistro, Categoria categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.fondo = fondo;
        this.fecharegistro = fecharegistro;
        this.categoria = categoria;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", fondo='" + fondo + '\'' +
                ", fecharegistro=" + fecharegistro +
                ", categoria=" + categoria +
                '}';
    }
}

