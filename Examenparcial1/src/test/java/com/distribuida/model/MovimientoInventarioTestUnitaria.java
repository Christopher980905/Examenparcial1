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

        movimientoInventario  = new MovimientoInventario(1,"Entrada",20,new Date(),1);
        Categoria categoria = new Categoria(1, "Motor");
        productos = new Productos(1,"Filtro de aceite",15.50,100,new Date(),1);

        productos.setNombre("Filtro de aceite");
        productos.setPrecio(15.50);
        productos.setStock(100);
        productos.setFecharegistro(new Date());


        productos.setCategoria(categoria);

        movimientoInventario.setIdMovimientoinventario(1);
        movimientoInventario.setTipo_movimiento("Entrada");
        movimientoInventario.setCantidad(20);
        movimientoInventario.setFecha( new Date() );
        movimientoInventario.setProductos(productos);
    }

    @Test
    public void TestMovimientoInventarioConstructor(){
        assertAll("Validar datos movimientoinventario - Constructor",
                () -> assertEquals(1,movimientoInventario.getIdMovimientoinventario()),
                () -> assertEquals("Entrada",movimientoInventario.getTipo_movimiento()),
                () -> assertEquals(20,movimientoInventario.getCantidad()),
                () -> assertEquals("Filtro de aceite",movimientoInventario.getProductos().getNombre())

        );

    }

    @Test
    public void TestMovimientoInventarioSetters(){

        productos = new Productos(1,"Bomba de agua2",32.50,40,new Date(),1);
        Categoria categoria = new Categoria(1, "Motor");
        productos.setNombre("llantas");
        productos.setPrecio(32.50);
        productos.setStock(40);
        productos.setFecharegistro(new Date());
       productos.setCategoria(categoria);

        movimientoInventario.setIdMovimientoinventario(0);
        movimientoInventario.setTipo_movimiento("Salida");
        movimientoInventario.setCantidad(20);
        movimientoInventario.setFecha( new Date() );
        movimientoInventario.setProductos(productos);


        assertAll("Validar datos movimientoinventario - Setters",
                () -> assertEquals(0,movimientoInventario.getIdMovimientoinventario()),
                () -> assertEquals("Salida",movimientoInventario.getTipo_movimiento()),
                () -> assertEquals(20,movimientoInventario.getCantidad()),
                () -> assertEquals("llantas",movimientoInventario.getProductos().getNombre())

       );
}

    @Test
    public void testClienteToString() {
        String str = movimientoInventario.toString();
        assertAll("VALIDAR DATOS movimientoinventario - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Entrada")),
                () -> assertTrue(str.contains("20")),
                () -> assertTrue(str.contains("Filtro de aceite"))

        );

}
    }