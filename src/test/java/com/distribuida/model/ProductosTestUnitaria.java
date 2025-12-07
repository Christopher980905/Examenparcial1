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

         productos = new Productos(1,"Pistón 150cc",32.50,40,new Date(),"Disponible",1);
         categoria = new Categoria(1, "Motor");

        productos.setIdProducto(1);
        productos.setNombre("Piston 150cc");
        productos.setPrecio(32.50);
        productos.setStock(40);
        productos.setFecharegistro(new Date());
        productos.setEstado("Disponible");
        productos.setCategoria(categoria);
    }

    @Test
    public void TestProductosConstructor() {
        assertAll("Validar datos productos - Constructor",
                () -> assertEquals(1, productos.getIdProducto()),
                () -> assertEquals("Piston 150cc", productos.getNombre()),
                () -> assertEquals(32.50, productos.getPrecio()),
                () -> assertEquals(40, productos.getStock()),
                () -> assertEquals("Disponible", productos.getEstado()),
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
        productos.setEstado("En proceso");
        productos.setCategoria(categoria);

        assertAll("Validar datos productos - Setters",
                () -> assertEquals(2,productos.getIdProducto()),
                () -> assertEquals("Piston",productos.getNombre()),
                () -> assertEquals(33.0,productos.getPrecio()),
                () -> assertEquals(80,productos.getStock()),
                () -> assertEquals("En proceso",productos.getEstado()),
                () -> assertEquals("Llantas",categoria.getNombreCategoria())

        );
}

    @Test
    public void testProductosToString() {
        String str = productos.toString();
        assertAll("VALIDAR DATOS productos - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Piston 150cc")),
                () -> assertTrue(str.contains("32.5")),
                () -> assertTrue(str.contains("40")),
                () -> assertTrue(str.contains("Disponible")),
                () -> assertTrue(str.contains("Motor"))

        );
}
}
