package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PagosTestUnitaria {

    private Pagos pagos;
    private Pedidos pedidos;
    private Cliente cliente;


    @BeforeEach
    public void setUp() {

         pagos = new Pagos(1,"Efectivo",250.00,new Date(),1);
         pedidos = new Pedidos(1,new Date(),"Pendiente",12.00,100.00,2);

         cliente = new Cliente(2,"María","Lopez","mlopez2@example.com","0982345678","Av. República 123",new Date());

        pedidos.setIdPedido(1);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("Pendiente");
        pedidos.setIVA(12.00);
        pedidos.setTotalneto(100.00);
        pedidos.setCliente(cliente);

        pagos.setIdPago(1);
        pagos.setMetodo_pago("Efectivo");
        pagos.setMonto(250.00);
        pagos.setFecha(new Date());
        pagos.setPedidos(pedidos);
}

    @Test
    public void TestPagosConstructor(){
        assertAll("Validar datos pagos - Constructor",
                () -> assertEquals(1,pagos.getIdPago()),
                () -> assertEquals("Efectivo",pagos.getMetodo_pago()),
                () -> assertEquals(250.0,pagos.getMonto()),

                () -> assertEquals("Pendiente",pedidos.getEstado())

        );
    }

    @Test
    public void TestMovimientoInventarioSetters(){

        pedidos = new Pedidos(1,new Date(),"Pendiente",12.00,100.00,2);

        cliente = new Cliente(2,"María","Lopez","mlopez2@example.com","0982345678","Av. República 123",new Date());

        pedidos.setIdPedido(1);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("En proceso");
        pedidos.setIVA(12.00);
        pedidos.setTotalneto(100.00);
        pedidos.setCliente(cliente);

        pagos.setIdPago(2);
        pagos.setMetodo_pago("tarjeta");
        pagos.setMonto(100.0);
        pagos.setFecha(new Date());
        pagos.setPedidos(pedidos);

        assertAll("Validar datos pagos - Setters",
                () -> assertEquals(2,pagos.getIdPago()),
                () -> assertEquals("tarjeta",pagos.getMetodo_pago()),
                () -> assertEquals(100.0,pagos.getMonto()),
                () -> assertEquals("En proceso",pedidos.getEstado())

        );

    }

    @Test
    public void testPgosToString() {
        String str = pagos.toString();
        assertAll("VALIDAR DATOS pagos - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Efectivo")),
                () -> assertTrue(str.contains("250.0")),
                () -> assertTrue(str.contains("Pendiente"))

        );

    }
}
