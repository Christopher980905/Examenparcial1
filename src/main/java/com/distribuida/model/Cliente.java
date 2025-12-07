package com.distribuida.model;


import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "cliente")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_cliente")
    private int idCliente;

    @Column (name = "nombre")
    private String nombre;
    @Column (name = "apellido")
    private String apellido;
    @Column (name = "email")
    private String email;
    @Column (name = "telefono")
    private String telefono;
    @Column (name = "direccion")
    private String direccion;
    @Column (name = "fecha_registro")
    private Date fecha_registro;

    public Cliente() {}

    public Cliente(int idCliente, String nombre, String apellido, String email, String telefono, String direccion, Date fecha_registro) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha_registro = fecha_registro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fecha_registro=" + fecha_registro +
                '}';
    }
}
