package com.distribuida.dao;


import com.distribuida.model.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class PedidoDetalleTestIntegracion {

    @Autowired
    private  PedidoDetalleRepository pedidoDetalleRepository;

    @Autowired
    private ProductosRepository productosRepository;

    @Autowired
    private PedidosRepository pedidosRepository;

    @Test
    public void TestPedidoDettalleFindAll(){
        List<PedidoDetalle> pedidoDetalles = pedidoDetalleRepository.findAll();

        assertNotNull(pedidoDetalles);
        assertTrue(pedidoDetalles.size()>0);

        pedidoDetalles.forEach(System.out::println);
        }

    @Test
    public void TestPedidoDetalleFindOne(){
        Optional<PedidoDetalle> pedidoDetalle = pedidoDetalleRepository.findById(1);

        assertTrue(pedidoDetalle.isPresent());
        assertEquals(2, pedidoDetalle.orElse(null).getCantidad());
        assertEquals(31.00, pedidoDetalle.orElse(null).getSubtotal());
        System.out.println(pedidoDetalle);
    }

    @Test
    public void testPedidoDetalleSave(){
        Optional<Pedidos> pedidos  = pedidosRepository.findById(1);
        assertTrue(pedidos.isPresent());

        Optional<Productos> productos  = productosRepository.findById(1);
        assertTrue(productos.isPresent());

        PedidoDetalle pedidoDetalle = new PedidoDetalle();
        pedidoDetalle.setIdPedidodetalle(0);
        pedidoDetalle.setFecha(new Date());
        pedidoDetalle.setCantidad(99);
        pedidoDetalle.setPrecio_unitario(12.00);
        pedidoDetalle.setSubtotal(11.00);
        pedidoDetalle.setPedidos(pedidos.orElse(null));
        pedidoDetalle.setProductos(productos.orElse(null));


        PedidoDetalle pedidoDetalleGuardado = pedidoDetalleRepository.save(pedidoDetalle);
        assertNotNull(pedidoDetalleGuardado);
        assertEquals(99, pedidoDetalleGuardado.getCantidad());
        assertEquals(11.00, pedidoDetalleGuardado.getSubtotal());

        pedidoDetalleRepository.save(pedidoDetalle);

    }

    //ACTUALIZAR DATOS
    @Test
    public void testPedidoDetalleUpdate(){

        Optional<Productos> productos = productosRepository.findById(2);
        assertTrue(productos.isPresent());

        Optional<Pedidos> pedidos = pedidosRepository.findById(2);
        assertTrue(pedidos.isPresent());

        Optional<PedidoDetalle> pedidoDetalle = pedidoDetalleRepository.findById(52);
        assertTrue(pedidoDetalle.isPresent());


        pedidoDetalle.orElse(null).setCantidad(5555);
        pedidoDetalle.orElse(null).setPrecio_unitario(55.0);

        pedidoDetalle.orElse(null).setSubtotal(500.5);
        pedidoDetalle.orElse(null).setProductos(productos.orElse(null));
        pedidoDetalle.orElse(null).setPedidos(pedidos.orElse(null));

        PedidoDetalle pedidoDetalleActualizado = pedidoDetalleRepository.save(pedidoDetalle.orElse(null));
        assertNotNull(pedidoDetalleActualizado);

        assertEquals(5555, pedidoDetalleActualizado.getCantidad());
        assertEquals(55.0, pedidoDetalleActualizado.getPrecio_unitario());
        assertEquals(500.5, pedidoDetalleActualizado.getSubtotal());

    }

    @Test
    public void testpedidoDetalleDelete(){
        if (pedidoDetalleRepository.existsById(52)) {
            pedidoDetalleRepository.deleteById(52);
        }
        assertFalse(pedidoDetalleRepository.existsById(52));
    }


}

