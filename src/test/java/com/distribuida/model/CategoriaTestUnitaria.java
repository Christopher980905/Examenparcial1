package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoriaTestUnitaria {
    private Categoria categoria;

    @BeforeEach
    public void setUp(){
        categoria = new Categoria(1,"Motor");
    }
    @Test
    public void testCategoriaConstructor(){
        assertAll("Validar datos Categoria - Constructor",
                () -> assertEquals(1,categoria.getIdCategoria()),
                () -> assertEquals("Motor",categoria.getNombreCategoria())
                );

    }
    @Test
    public void testCategoriaSetters(){
        categoria.setIdCategoria(0);
        categoria.setNombreCategoria("Motor dos");

        assertAll("Validar datos Categoria - Setters",
                () -> assertEquals(0,categoria.getIdCategoria()),
                () -> assertEquals("Motor dos",categoria.getNombreCategoria())
        );
    }
    @Test
    public void testCategoriaToString(){
        String str = categoria.toString();
        assertAll("VALIDAR DATOS CATEGORIA - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Motor"))
        );
    }
}
