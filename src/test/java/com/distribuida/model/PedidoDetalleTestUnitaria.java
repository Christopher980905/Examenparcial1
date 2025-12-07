package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PedidoDetalleTestUnitaria {
    private PedidoDetalle pedidoDetalle;
    private Pedidos pedidos;
    private Cliente cliente;
    private Productos productos;
    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        pedidoDetalle = new PedidoDetalle();
        cliente = new Cliente(2,"María","Lopez","mlopez2@example.com","0982345678","Av. República 123",new Date());

        pedidos = new Pedidos(1, new Date(), "Pendiente", 12.00, 100.00, 2);
        pedidos.setIdPedido(1);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("Pendiente");
        pedidos.setIVA(12.00);
        pedidos.setTotalneto(120.50);
        pedidos.setCliente(cliente);

        categoria = new Categoria(1, "Motor");
        productos = new Productos(1, "Pistón 150cc", 32.50, 40, new Date(), "Disponible", 1);
        productos.setIdProducto(1);
        productos.setNombre("Piston 150cc");
        productos.setPrecio(32.50);
        productos.setStock(40);
        productos.setFecharegistro(new Date());
        productos.setEstado("Disponible");
        productos.setCategoria(categoria);

        pedidoDetalle.setIdPedidodetalle(1);
        pedidoDetalle.setFecha(new Date());
        pedidoDetalle.setCantidad(2);
        pedidoDetalle.setSubtotal(65.00);
        pedidoDetalle.setPedidos(pedidos);
        pedidoDetalle.setProductos(productos);

    }
    @Test
    public void TesPedidoDetalleConstructor(){
        assertAll("Validar datos pedidodetalle - Constructor",
                () -> assertEquals(1,pedidoDetalle.getIdPedidodetalle()),
                () -> assertEquals(2,pedidoDetalle.getCantidad()),
                () -> assertEquals(65.00,pedidoDetalle.getSubtotal()),

                () -> assertEquals("Pendiente",pedidos.getEstado()),
                () -> assertEquals("Piston 150cc",productos.getNombre())

        );
    }
    @Test
    public void TestPedidoDetalleSetters() {
        cliente = new Cliente(2,"María","Lopez","mlopez2@example.com","0982345678","Av. República 123",new Date());

        pedidos = new Pedidos(1, new Date(), "Pendiente", 12.00, 100.00, 2);
        pedidos.setIdPedido(1);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("En proceso");
        pedidos.setIVA(12.00);
        pedidos.setTotalneto(120.50);
        pedidos.setCliente(cliente);

        categoria = new Categoria(1, "Motor");
        productos = new Productos(1, "Pistón 150cc", 32.50, 40, new Date(), "Disponible", 1);
        productos.setIdProducto(1);
        productos.setNombre("Casco1");
        productos.setPrecio(32.50);
        productos.setStock(40);
        productos.setFecharegistro(new Date());
        productos.setEstado("Disponible");
        productos.setCategoria(categoria);

        pedidoDetalle.setIdPedidodetalle(2);
        pedidoDetalle.setFecha(new Date());
        pedidoDetalle.setCantidad(4);
        pedidoDetalle.setSubtotal(90.0);
        pedidoDetalle.setPedidos(pedidos);
        pedidoDetalle.setProductos(productos);

        assertAll("Validar datos pedidodetalle - Setters",
                () -> assertEquals(2,pedidoDetalle.getIdPedidodetalle()),
                () -> assertEquals(4,pedidoDetalle.getCantidad()),
                () -> assertEquals(90.0,pedidoDetalle.getSubtotal()),
                () -> assertEquals("En proceso",pedidos.getEstado()),
                () -> assertEquals("Casco1",productos.getNombre())

        );

    }
    @Test
    public void testPedidoDetalleToString() {
        String str = pedidoDetalle.toString();
        assertAll("VALIDAR DATOS pedidodetalle - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("2")),
                () -> assertTrue(str.contains("65.0")),
                () -> assertTrue(str.contains("Pendiente")),
                () -> assertTrue(str.contains("Piston 150cc"))

        );
    }
}
