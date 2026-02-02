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
    @Column (name = "fecha_registro")
    private Date fecharegistro;

    @ManyToOne
    @JoinColumn(name = "id_categoria")

    private Categoria categoria;

    public Productos (int idProducto, String nombre, double precio, int stock, Date fecharegistro, int i) {}

    public Productos(int idProducto, String nombre, Double precio, int stock, Date fecharegistro, Categoria categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.fecharegistro = fecharegistro;
        this.categoria = categoria;
    }

    public Productos() {

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
                ", fecharegistro=" + fecharegistro +
                ", categoria=" + categoria +
                '}';
    }
}

