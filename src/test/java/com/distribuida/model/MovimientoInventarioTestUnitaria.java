package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovimientoInventarioTestUnitaria {

    private MovimientoInventario movimientoInventario;
    private Productos productos;
    private Categoria categoria;

    @BeforeEach
    public void setUp() {

        movimientoInventario  = new MovimientoInventario(1,"Entrada",50,new Date(),1);
        Categoria categoria = new Categoria(1, "Motor");
        productos = new Productos(1,"Pistón 150cc",32.50,40,new Date(),"Disponible",1);

        productos.setNombre("Piston 150cc");
        productos.setPrecio(32.50);
        productos.setStock(40);
        productos.setFecharegistro(new Date());
        productos.setEstado("Disponible");

        productos.setCategoria(categoria);

        movimientoInventario.setIdMovimientoinventario(1);
        movimientoInventario.setTipo("Entrada");
        movimientoInventario.setCantidad(50);
        movimientoInventario.setFecha( new Date() );
        movimientoInventario.setProductos(productos);
    }

    @Test
    public void TestMovimientoInventarioConstructor(){
        assertAll("Validar datos movimientoinventario - Constructor",
                () -> assertEquals(1,movimientoInventario.getIdMovimientoinventario()),
                () -> assertEquals("Entrada",movimientoInventario.getTipo()),
                () -> assertEquals(50,movimientoInventario.getCantidad()),
                () -> assertEquals("Piston 150cc",movimientoInventario.getProductos().getNombre())

        );

    }

    @Test
    public void TestMovimientoInventarioSetters(){

        productos = new Productos(1,"Pistón 150cc",32.50,40,new Date(),"Disponible",1);
        Categoria categoria = new Categoria(1, "Motor");
        productos.setNombre("casco");
        productos.setPrecio(32.50);
        productos.setStock(40);
        productos.setFecharegistro(new Date());
        productos.setEstado("Disponible");
        productos.setCategoria(categoria);

        movimientoInventario.setIdMovimientoinventario(0);
        movimientoInventario.setTipo("Salida");
        movimientoInventario.setCantidad(20);
        movimientoInventario.setFecha( new Date() );
        movimientoInventario.setProductos(productos);


        assertAll("Validar datos movimientoinventario - Setters",
                () -> assertEquals(0,movimientoInventario.getIdMovimientoinventario()),
                () -> assertEquals("Salida",movimientoInventario.getTipo()),
                () -> assertEquals(20,movimientoInventario.getCantidad()),
                () -> assertEquals("casco",movimientoInventario.getProductos().getNombre())

       );
}

    @Test
    public void testClienteToString() {
        String str = movimientoInventario.toString();
        assertAll("VALIDAR DATOS movimientoinventario - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Entrada")),
                () -> assertTrue(str.contains("50")),
                () -> assertTrue(str.contains("Piston 150cc"))

        );

}
    }