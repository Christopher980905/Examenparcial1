package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTestUnitaria {
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente(1,"17453839565","Juan","Taipe","Av. amazonas y rio","099676578","jtaipe@gmail.com");
    }
    @Test
    public void testClienteConstructor(){
        assertAll("Validar datos Cliente - Constructor",
                () -> assertEquals(1,cliente.getIdCliente()),
                () -> assertEquals("17453839565",cliente.getCedula()),
                () -> assertEquals("Juan",cliente.getNombre()),
                () -> assertEquals("Taipe",cliente.getApellidos()),
                () -> assertEquals("Av. amazonas y rio",cliente.getDireccion()),
                () -> assertEquals("099676578",cliente.getTelefono()),
                () -> assertEquals("jtaipe@gmail.com",cliente.getCorreo())
                );
    }
    @Test
    public void testClienteSetters(){
        cliente.setIdCliente(2);
        cliente.setCedula("175487997822");
        cliente.setNombre("Juan2");
        cliente.setApellidos("Taipe2");
        cliente.setDireccion("Av. amazonas y rio2");
        cliente.setTelefono("09967657822");
        cliente.setCorreo("jtaipe22@gmail.com");

        assertAll("Validar datos Cliente - Setters",
                () -> assertEquals(2,cliente.getIdCliente()),
                () -> assertEquals("175487997822",cliente.getCedula()),
                () -> assertEquals("Juan2",cliente.getNombre()),
                () -> assertEquals("Taipe2",cliente.getApellidos()),
                () -> assertEquals("Av. amazonas y rio2",cliente.getDireccion()),
                () -> assertEquals("09967657822",cliente.getTelefono()),
                () -> assertEquals("jtaipe22@gmail.com",cliente.getCorreo())
        );
    }
    @Test
    public void testClienteToString(){
        String str = cliente.toString();
        assertAll("VALIDAR DATOS CLIENTE - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("17453839565")),
                () -> assertTrue(str.contains("Juan")),
                () -> assertTrue(str.contains("Taipe")),
                () -> assertTrue(str.contains("Av. amazonas y rio")),
                () -> assertTrue(str.contains("099676578")),
                () -> assertTrue(str.contains("jtaipe@gmail.com"))
        );
    }
}
