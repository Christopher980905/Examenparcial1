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
        cliente = new Cliente(1, "1712345678", "Juan", "Perez", "juan.perez@gmail.com","0998765432","Av. 10 de Agosto 123, Quito");
    }
    @Test
    public void TestClienteConstructor(){
        assertAll("Validar datos cliente - Constructor",
                () -> assertEquals(1,cliente.getIdCliente()),
                () -> assertEquals("1712345678",cliente.getCedula()),
                () -> assertEquals("Juan",cliente.getNombre()),
                () -> assertEquals("Perez",cliente.getApellido()),
                () -> assertEquals("juan.perez@gmail.com",cliente.getEmail()),
                () -> assertEquals("0998765432",cliente.getTelefono()),
                () -> assertEquals("Av. 10 de Agosto 123, Quito",cliente.getDireccion())

        );

    }

        @Test
        public void TestClienteSetters(){
        cliente.setIdCliente(1);
        cliente.setCedula("1785421486");
        cliente.setNombre("Nelly");
        cliente.setApellido("Coello");
        cliente.setEmail("ncoello@example.com");
        cliente.setTelefono("099180937");
        cliente.setDireccion("Av. checa la plazuela"
        );
        assertAll("Validar datos Cliente - Setters",
                () -> assertEquals(1,cliente.getIdCliente()),
                () -> assertEquals("1785421486",cliente.getCedula()),
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
        assertAll("VALIDAR DATOS cliente - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("1712345678")),
                () -> assertTrue(str.contains("Juan")),
                () -> assertTrue(str.contains("Perez")),
                () -> assertTrue(str.contains("juan.perez@gmail.com")),
                () -> assertTrue(str.contains("0998765432")),
                () -> assertTrue(str.contains("Av. 10 de Agosto 123, Quito"))


        );

    }

}
