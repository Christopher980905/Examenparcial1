package com.distribuida.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_libro")
    private int idLibro;
    @Column (name = "titulo")
    private String titulo;
    @Column (name = "editorial")
    private String editorial;
    @Column (name = "num_paginas")
    private int numeroPaginas;
    @Column (name = "edicion")
    private String edicion;
    @Column (name = "idioma")
    private String idioma;
    @Column (name = "fecha_publicacion")
    private String fecha_de_publicacion;
    @Column (name = "descripcion")
    private String descripcion;
    @Column (name = "tipo_pasta")
    private String tipo_de_pasta;
    @Column (name = "ISBN")
    private String isbn;
    @Column (name = "num_ejemplares")
    private int num_ejemplares;
    @Column (name = "portada")
    private String portada;
    @Column (name = "presentacion")
    private String presentacion;
    @Column (name = "precio")
    private Double precio;
    @ManyToOne
    @JoinColumn (name = "id_categoria")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn (name = "id_autor")
    private Autor autor;
    public Libro() {}

    public Libro(int idLibro, String titulo, String editorial, int numeroPaginas, String edicion, String idioma, String fecha_de_publicacion, String descripcion, String tipo_de_pasta, String isbn, int num_ejemplares, String portada, String presentacion, Double precio, Categoria categoria, Autor autor) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.editorial = editorial;
        this.numeroPaginas = numeroPaginas;
        this.edicion = edicion;
        this.idioma = idioma;
        this.fecha_de_publicacion = fecha_de_publicacion;
        this.descripcion = descripcion;
        this.tipo_de_pasta = tipo_de_pasta;
        this.isbn = isbn;
        this.num_ejemplares = num_ejemplares;
        this.portada = portada;
        this.presentacion = presentacion;
        this.precio = precio;
        this.categoria = categoria;
        this.autor = autor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getFecha_de_publicacion() {
        return fecha_de_publicacion;
    }

    public void setFecha_de_publicacion(String fecha_de_publicacion) {
        this.fecha_de_publicacion = fecha_de_publicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo_de_pasta() {
        return tipo_de_pasta;
    }

    public void setTipo_de_pasta(String tipo_de_pasta) {
        this.tipo_de_pasta = tipo_de_pasta;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNum_ejemplares() {
        return num_ejemplares;
    }

    public void setNum_ejemplares(int num_ejemplares) {
        this.num_ejemplares = num_ejemplares;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", titulo='" + titulo + '\'' +
                ", editorial='" + editorial + '\'' +
                ", numeroPaginas=" + numeroPaginas +
                ", edicion='" + edicion + '\'' +
                ", idioma='" + idioma + '\'' +
                ", fecha_de_publicacion='" + fecha_de_publicacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo_de_pasta='" + tipo_de_pasta + '\'' +
                ", isbn='" + isbn + '\'' +
                ", num_ejemplares=" + num_ejemplares +
                ", portada='" + portada + '\'' +
                ", presentacion='" + presentacion + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                ", autor=" + autor +
                '}';
    }
}
