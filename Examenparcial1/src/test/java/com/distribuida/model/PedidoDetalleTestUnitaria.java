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
        cliente = new Cliente(1, "1712345678", "Juan", "Perez", "juan.perez@gmail.com","0998765432","Av. 10 de Agosto 123, Quito");

        pedidos = new Pedidos();
        pedidos.setIdPedido(1);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("Pendiente");
        pedidos.setTotalneto(120.50);
        pedidos.setIVA(12.00);
        pedidos.setMonto_pagar(120.50);
        pedidos.setCliente(cliente);

        categoria = new Categoria(1, "Motor");
        productos = new Productos(1,"Filtro de aceite",15.50,100,new Date(),1);

        productos.setIdProducto(1);
        productos.setNombre("Filtro de aceite");
        productos.setPrecio(15.50);
        productos.setStock(100);
        productos.setFecharegistro(new Date());


        productos.setCategoria(categoria);

        pedidoDetalle.setIdPedidodetalle(1);
        pedidoDetalle.setFecha(new Date());
        pedidoDetalle.setCantidad(2);
        pedidoDetalle.setPrecio_unitario(15.5);
        pedidoDetalle.setSubtotal(31.00);


        pedidoDetalle.setPedidos(pedidos);
        pedidoDetalle.setProductos(productos);

    }
    @Test
    public void TesPedidoDetalleConstructor(){
        assertAll("Validar datos pedidodetalle - Constructor",
                () -> assertEquals(1,pedidoDetalle.getIdPedidodetalle()),
                () -> assertEquals(2,pedidoDetalle.getCantidad()),
                () -> assertEquals(15.5,pedidoDetalle.getPrecio_unitario()),
                () -> assertEquals(31.00,pedidoDetalle.getSubtotal()),

                () -> assertEquals("Pendiente",pedidos.getEstado()),
                () -> assertEquals("Filtro de aceite",productos.getNombre())

        );
    }
    @Test
    public void TestPedidoDetalleSetters() {
        cliente = new Cliente(1, "1789012345", "Carlos", "Ramírez", "carlos.ramirez@yahoo.com","0976543210","Av. Amazonas 789, Quito");

        pedidos = new Pedidos();
        pedidos.setIdPedido(1);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("En proceso");
        pedidos.setTotalneto(120.50);
        pedidos.setIVA(12.00);
        pedidos.setMonto_pagar(121.50);
        pedidos.setCliente(cliente);

        categoria = new Categoria(1, "Motor");
        productos = new Productos(1,"Filtro de aceite",15.50,100,new Date(),1);
        productos.setIdProducto(1);
        productos.setNombre("filtro de aire");
        productos.setPrecio(32.50);
        productos.setStock(40);
        productos.setFecharegistro(new Date());

        productos.setCategoria(categoria);

        pedidoDetalle.setIdPedidodetalle(2);
        pedidoDetalle.setFecha(new Date());
        pedidoDetalle.setCantidad(4);
        pedidoDetalle.setPrecio_unitario(33.00);
        pedidoDetalle.setSubtotal(90.0);
        pedidoDetalle.setPedidos(pedidos);
        pedidoDetalle.setProductos(productos);

        assertAll("Validar datos pedidodetalle - Setters",
                () -> assertEquals(2,pedidoDetalle.getIdPedidodetalle()),
                () -> assertEquals(4,pedidoDetalle.getCantidad()),
                () -> assertEquals(33.0,pedidoDetalle.getPrecio_unitario()),
                () -> assertEquals(90.0,pedidoDetalle.getSubtotal()),
                () -> assertEquals("En proceso",pedidos.getEstado()),
                () -> assertEquals("filtro de aire",productos.getNombre())

        );

    }

    @Test
    public void testPedidoDetalleToString() {
        String str = pedidoDetalle.toString();
        assertAll("VALIDAR DATOS pedidoDetalle - To String",
                () -> assertTrue(str.contains("2")),
                () -> assertTrue(str.contains("15.5")),
                () -> assertTrue(str.contains("31")),
                () -> assertTrue(str.contains("Pendiente")),
                () -> assertTrue(str.contains("Filtro de aceite"))

        );

    }

    }

