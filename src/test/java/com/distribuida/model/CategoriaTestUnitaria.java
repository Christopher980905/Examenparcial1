package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoriaTestUnitaria {
    private Categoria categoria;

    @BeforeEach
    public void setUp(){
        categoria = new Categoria(1,"Infantil","relato real antepasado");
    }
    @Test
    public void testCategoriaConstructor(){
        assertAll("Validar datos Categoria - Constructor",
                () -> assertEquals(1,categoria.getIdCategoria()),
                () -> assertEquals("Infantil",categoria.getCategoria()),
                () -> assertEquals("relato real antepasado",categoria.getDescripcion())
                );

    }
    @Test
    public void testCategoriaSetters(){
        categoria.setIdCategoria(2);
        categoria.setCategoria("Infantil dos");
        categoria.setDescripcion("relato real antepasado dos");

        assertAll("Validar datos Categoria - Setters",
                () -> assertEquals(2,categoria.getIdCategoria()),
                () -> assertEquals("Infantil dos",categoria.getCategoria()),
                () -> assertEquals("relato real antepasado dos",categoria.getDescripcion())
        );
    }
    @Test
    public void testCategoriaToString(){
        String str = categoria.toString();
        assertAll("VALIDAR DATOS CATEGORIA - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Infantil")),
                () -> assertTrue(str.contains("relato real antepasado"))
        );
    }
}
