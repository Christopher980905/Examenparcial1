package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PagosTestUnitaria {

    private Pagos pagos;


    @BeforeEach
    public void setUp() {

        pagos = new Pagos(1,"Tarjeta de Crédito");

        pagos.setIdPago(1);
        pagos.setMetodo_pago("Tarjeta de Crédito");


}

    @Test
    public void TestPagosConstructor(){
        assertAll("Validar datos pagos - Constructor",
                () -> assertEquals(1,pagos.getIdPago()),
                () -> assertEquals("Tarjeta de Crédito",pagos.getMetodo_pago())

        );
    }

    @Test
    public void TestPagosSetters(){

        pagos.setIdPago(2);
        pagos.setMetodo_pago("Transferencia Bancaria");

        assertAll("Validar datos pagos - Setters",
                () -> assertEquals(2,pagos.getIdPago()),
                () -> assertEquals("Transferencia Bancaria",pagos.getMetodo_pago())

        );

    }

    @Test
    public void testPgosToString() {
        String str = pagos.toString();
        assertAll("VALIDAR DATOS pagos - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Tarjeta de Crédito"))

        );

    }
}
