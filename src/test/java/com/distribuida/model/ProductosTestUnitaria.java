package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductosTestUnitaria {

    private Productos productos;
    private Categoria categoria;

    @BeforeEach
    public void setUp() {

        productos = new Productos(1,"Filtro de aceite",15.50,100,new Date(),1);
        categoria = new Categoria(1,"Motor");

        productos.setIdProducto(1);
        productos.setNombre("Filtro de aceite");
        productos.setPrecio(15.50);
        productos.setStock(100);
        productos.setFecharegistro(new Date());

        productos.setCategoria(categoria);
    }

    @Test
    public void TestProductosConstructor() {
        assertAll("Validar datos productos - Constructor",
                () -> assertEquals(1, productos.getIdProducto()),
                () -> assertEquals("Filtro de aceite", productos.getNombre()),
                () -> assertEquals(15.50, productos.getPrecio()),
                () -> assertEquals(100, productos.getStock()),

                () -> assertEquals("Motor", categoria.getNombreCategoria())
        );

}

    @Test
    public void TestProductosSetters(){
        categoria = new Categoria(1, "Llantas");

        productos.setIdProducto(2);
        productos.setNombre("Piston");
        productos.setPrecio(33.0);
        productos.setStock(80);
        productos.setFecharegistro(new Date());

        productos.setCategoria(categoria);

        assertAll("Validar datos productos - Setters",
                () -> assertEquals(2,productos.getIdProducto()),
                () -> assertEquals("Piston",productos.getNombre()),
                () -> assertEquals(33.0,productos.getPrecio()),
                () -> assertEquals(80,productos.getStock()),

                () -> assertEquals("Llantas",categoria.getNombreCategoria())

        );
}

    @Test
    public void testProductosToString() {
        String str = productos.toString();
        assertAll("VALIDAR DATOS productos - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Filtro de aceite")),
                () -> assertTrue(str.contains("15.5")),
                () -> assertTrue(str.contains("100")),
                () -> assertTrue(str.contains("Motor"))


        );
}
}
