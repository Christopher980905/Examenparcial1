package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PedidosTestUnitaria {

    private Pedidos pedidos;
    private Cliente cliente;
    private Pagos pagos;

    @BeforeEach
    public void setUp() {
        pedidos = new Pedidos();
        pagos = new Pagos(1,"Tarjeta de Crédito",new Date());
        cliente = new Cliente(1, "1712345678", "Juan", "Perez", "juan.perez@gmail.com","0998765432","Av. 10 de Agosto 123, Quito");

        pedidos.setIdPedido(1);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("Pendiente");
        pedidos.setTotalneto(31.00);
        pedidos.setIVA(0.15);
        pedidos.setMonto_pagar(35.65);
        pedidos.setCliente(cliente);
        pedidos.setPagos(pagos);
    }
    @Test
    public void TestPedidosConstructor() {
        assertAll("Validar datos pedidos - Constructor",
                () -> assertEquals(1, pedidos.getIdPedido()),
                () -> assertEquals("Pendiente", pedidos.getEstado()),
                () -> assertEquals(31.00, pedidos.getTotalneto()),
                () -> assertEquals(0.15, pedidos.getIVA()),
                () -> assertEquals(35.65, pedidos.getMonto_pagar()),

                () -> assertEquals("Juan", cliente.getNombre())

        );
    }
    @Test
    public void TestPedidosSetters(){
        cliente = new Cliente(5, "1711122233", "Luis", "Vásquez", "luis.vasquez@hotmail.com","0991234567","Av. Eloy Alfaro 654, Quito");
        pagos = new Pagos(1,"Tarjeta de Crédito",new Date());
        pedidos.setIdPedido(2);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("Pagado");
        pedidos.setTotalneto(35.0);
        pedidos.setIVA(15.00);
        pedidos.setMonto_pagar(200.00);
        pedidos.setCliente(cliente);
        pedidos.setPagos(pagos);

        assertAll("Validar datos pedidos - Setters",
                () -> assertEquals(2,pedidos.getIdPedido()),
                () -> assertEquals("Pagado",pedidos.getEstado()),
                () -> assertEquals(35.0,pedidos.getTotalneto()),
                () -> assertEquals(15.00,pedidos.getIVA()),
                () -> assertEquals(200.00,pedidos.getMonto_pagar()),
                () -> assertEquals("Luis",cliente.getNombre()),
                () -> assertEquals("Tarjeta de Crédito",pagos.getMetodo_pago())

        );
    }
    @Test
    public void testPedidoToString() {
        String str = pedidos.toString();
        assertAll("VALIDAR DATOS movimientoinventario - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Pendiente")),
                () -> assertTrue(str.contains("31")),
                () -> assertTrue(str.contains("0.15")),
                () -> assertTrue(str.contains("35.65")),
                () -> assertTrue(str.contains("Juan")),
                () -> assertTrue(str.contains("Tarjeta de Crédito"))


        );

    }
    }


