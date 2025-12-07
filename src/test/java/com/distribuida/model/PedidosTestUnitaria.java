package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PedidosTestUnitaria {

    private Pedidos pedidos;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        pedidos = new Pedidos(1,new Date(),"Pendiente",12.00,100.00,2);
        cliente = new Cliente(2,"María","Lopez","mlopez2@example.com","0982345678","Av. República 123",new Date());

        pedidos.setIdPedido(1);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("Pendiente");
        pedidos.setIVA(12.00);
        pedidos.setTotalneto(100.00);
        pedidos.setCliente(cliente);
    }
    @Test
    public void TestPedidosConstructor() {
        assertAll("Validar datos pedidos - Constructor",
                () -> assertEquals(1, pedidos.getIdPedido()),
                () -> assertEquals("Pendiente", pedidos.getEstado()),
                () -> assertEquals(12.00, pedidos.getIVA()),
                () -> assertEquals(100.00, pedidos.getTotalneto()),

                () -> assertEquals("María", cliente.getNombre())
        );
    }
    @Test
    public void TestPedidosSetters(){
        cliente = new Cliente(2,"Mario","Lopez","mlopez2@example.com","0982345678","Av. República 123",new Date());

        pedidos.setIdPedido(2);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("Pagado");
        pedidos.setIVA(15.00);
        pedidos.setTotalneto(200.00);
        pedidos.setCliente(cliente);

        assertAll("Validar datos pedidos - Setters",
                () -> assertEquals(2,pedidos.getIdPedido()),
                () -> assertEquals("Pagado",pedidos.getEstado()),
                () -> assertEquals(15.0,pedidos.getIVA()),
                () -> assertEquals(200.0,pedidos.getTotalneto()),
                () -> assertEquals("Mario",cliente.getNombre())

        );
    }
    @Test
    public void testPedidosToString() {
        String str = pedidos.toString();
        assertAll("VALIDAR DATOS pedidos - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Pendiente")),
                () -> assertTrue(str.contains("12.0")),
                () -> assertTrue(str.contains("100.0")),
                () -> assertTrue(str.contains("María"))

        );
    }

}
