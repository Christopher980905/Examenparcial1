package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutorTestUnitaria {
    private Autor autor;

    @BeforeEach
    public void setUp() {
        autor = new Autor(1,"Mario","Benalcazar","Alemania","Av.rio blanco y rio negro","09945245663","mbenalcazar@gmail.com");
    }
    @Test
    public void testAutorConstructor() {
        assertAll("Validar datos Autor - Constructor",
                ()-> assertEquals(1,autor.getIdAutor()),
                ()-> assertEquals("Mario",autor.getNombreAutor()),
                ()-> assertEquals("Benalcazar",autor.getApellidoAutor()),
                ()-> assertEquals("Alemania",autor.getPaisAutor()),
                ()-> assertEquals("Av.rio blanco y rio negro",autor.getDireccionAutor()),
                ()-> assertEquals("09945245663",autor.getTelefonoAutor()),
                ()-> assertEquals("mbenalcazar@gmail.com",autor.getEmailAutor())

                );

    }
    @Test
    public void testAutorSetters() {
        autor.setIdAutor(2);
        autor.setNombreAutor("Mario2");
        autor.setApellidoAutor("Benalcazar2");
        autor.setPaisAutor("Alemania2");
        autor.setDireccionAutor("Av.rio2");
        autor.setTelefonoAutor("0994524566322");
        autor.setEmailAutor("mbenalcazar@gmail.com22");

        assertAll("Validar datos Autor - Setters",
                ()-> assertEquals(2,autor.getIdAutor()),
                ()-> assertEquals("Mario2",autor.getNombreAutor()),
                ()-> assertEquals("Benalcazar2",autor.getApellidoAutor()),
                ()-> assertEquals("Alemania2",autor.getPaisAutor()),
                ()-> assertEquals("Av.rio2",autor.getDireccionAutor()),
                ()-> assertEquals("0994524566322",autor.getTelefonoAutor()),
                ()-> assertEquals("mbenalcazar@gmail.com22",autor.getEmailAutor())
        );

    }
    @Test
    public void testAutorToString() {
        String str = autor.toString();
        assertAll("Validar datos autor- To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Mario")),
                () -> assertTrue(str.contains("Benalcazar")),
                () -> assertTrue(str.contains("Alemania")),
                () -> assertTrue(str.contains("Av.rio blanco y rio negro")),
                () -> assertTrue(str.contains("09945245663")),
                () -> assertTrue(str.contains("mbenalcazar@gmail.com"))

        );
    }
}
