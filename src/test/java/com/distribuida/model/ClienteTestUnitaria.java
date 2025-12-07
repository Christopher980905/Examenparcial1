package com.distribuida.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class ClienteTestUnitaria {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente(2, "María", "Lopez", "mlopez2@example.com", "0982345678", "Av. República 123", new Date());
    }
    @Test
    public void TestClienteConstructor(){
        assertAll("Validar datos cliente - Constructor",
                () -> assertEquals(2,cliente.getIdCliente()),
                () -> assertEquals("María",cliente.getNombre()),
                () -> assertEquals("Lopez",cliente.getApellido()),
                () -> assertEquals("mlopez2@example.com",cliente.getEmail()),
                () -> assertEquals("0982345678",cliente.getTelefono()),
                () -> assertEquals("Av. República 123",cliente.getDireccion())
                //() -> assertEquals(new Date(),cliente.getFecha_registro())
        );

    }

        @Test
        public void TestClienteSetters(){
        cliente.setIdCliente(1);
        cliente.setNombre("Nelly");
        cliente.setApellido("Coello");
        cliente.setEmail("ncoello@example.com");
        cliente.setTelefono("099180937");
        cliente.setDireccion("Av. checa la plazuela"
        );
        assertAll("Validar datos Cliente - Setters",
                () -> assertEquals(1,cliente.getIdCliente()),
                () -> assertEquals("Nelly",cliente.getNombre()),
                () -> assertEquals("Coello",cliente.getApellido()),
                () -> assertEquals("ncoello@example.com",cliente.getEmail()),
                () -> assertEquals("099180937",cliente.getTelefono()),
                () -> assertEquals("Av. checa la plazuela",cliente.getDireccion())
        );
    }
    @Test
    public void testClienteToString() {
        String str = cliente.toString();
        assertAll("VALIDAR DATOS CLIENTE - To String",
                () -> assertTrue(str.contains("2")),
                () -> assertTrue(str.contains("María")),
                () -> assertTrue(str.contains("Lopez")),
                () -> assertTrue(str.contains("mlopez2@example.com")),
                () -> assertTrue(str.contains("0982345678")),
                () -> assertTrue(str.contains("Av. República 123"))
        );
    }

}
