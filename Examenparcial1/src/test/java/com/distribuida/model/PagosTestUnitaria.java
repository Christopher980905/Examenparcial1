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

         pagos = new Pagos(1,"Tarjeta de Crédito",new Date(),1);
         pedidos = new Pedidos(1,new Date(),"Pendiente",31.00,0.15,35.65,1);

        cliente = new Cliente(1, "1712345678", "Juan", "Perez", "juan.perez@gmail.com","0998765432","Av. 10 de Agosto 123, Quito");

        pedidos.setIdPedido(1);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("Pendiente");
        pedidos.setTotalneto(31.00);
        pedidos.setIVA(0.15);
        pedidos.setMonto_pagar(35.65);
        pedidos.setCliente(cliente);

        pagos.setIdPago(1);
        pagos.setMetodo_pago("Tarjeta de Crédito");
        pagos.setFecha(new Date());
        pagos.setPedidos(pedidos);
}

    @Test
    public void TestPagosConstructor(){
        assertAll("Validar datos pagos - Constructor",
                () -> assertEquals(1,pagos.getIdPago()),
                () -> assertEquals("Tarjeta de Crédito",pagos.getMetodo_pago()),


                () -> assertEquals("Pendiente",pedidos.getEstado())

        );
    }

    @Test
    public void TestMovimientoInventarioSetters(){

        pedidos = new Pedidos(1,new Date(),"Pendiente",31.00,0.15,35.65,1);

        cliente = new Cliente(1, "1712345678", "Juan", "Perez", "juan.perez@gmail.com","0998765432","Av. 10 de Agosto 123, Quito");

        pedidos.setIdPedido(1);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("anulado");
        pedidos.setTotalneto(32.00);
        pedidos.setIVA(0.13);
        pedidos.setMonto_pagar(35.65);
        pedidos.setCliente(cliente);

        pagos.setIdPago(2);
        pagos.setMetodo_pago("Transferencia Bancaria");
        pagos.setFecha(new Date());
        pagos.setPedidos(pedidos);

        assertAll("Validar datos pagos - Setters",
                () -> assertEquals(2,pagos.getIdPago()),
                () -> assertEquals("Transferencia Bancaria",pagos.getMetodo_pago()),

                () -> assertEquals("anulado",pedidos.getEstado())

        );

    }

    @Test
    public void testPgosToString() {
        String str = pagos.toString();
        assertAll("VALIDAR DATOS pagos - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Tarjeta de Crédito")),

                () -> assertTrue(str.contains("Pendiente"))

        );

    }
}
