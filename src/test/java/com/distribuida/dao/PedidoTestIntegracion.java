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
public class PedidoTestIntegracion {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ClienteRepository  clienteRepository;

    @Test
    public void TestPedidoFindAll(){
        List<Pedidos> pedidos = pedidosRepository.findAll();

        assertNotNull(pedidos);
        assertTrue(pedidos.size()>0);
        pedidos.forEach(System.out::println);
    }

    @Test
    public void TestPedidoFindOne(){
        Optional<Pedidos> pedidos = pedidosRepository.findById(1);

        assertTrue(pedidos.isPresent());
        assertEquals("Pendiente", pedidos.orElse(null).getEstado());
        assertEquals(12.0, pedidos.orElse(null).getIVA());
        System.out.println(pedidos);
    }

    @Test
    public void testMovimientoInventarioSave(){
        Optional<Cliente> cliente  = clienteRepository.findById(1);
        assertTrue(cliente.isPresent());



        Pedidos pedidos = new Pedidos(1,new Date(),"Pendiente",12.00,100.00,2);
        pedidos.setIdPedido(0);
        pedidos.setFechapedido(new Date());
        pedidos.setEstado("Anulado");
        pedidos.setIVA(12.0);
        pedidos.setTotalneto(15.5);
        pedidos.setCliente(cliente.orElse(null));

        Pedidos pedidoGuardado = pedidosRepository.save(pedidos);
        assertNotNull(pedidoGuardado);
        assertEquals("Anulado", pedidoGuardado.getEstado());
        assertEquals(12.0, pedidoGuardado.getIVA());

    }
    //ACTUALIZAR DATOS
    @Test
    public void testClienteUpdate(){

        Optional<Cliente> cliente = clienteRepository.findById(2);
        assertTrue(cliente.isPresent());

        Optional<Pedidos> pedidos = pedidosRepository.findById(51);
        assertTrue(pedidos.isPresent());

        pedidos.orElse(null).setFechapedido(new Date());
        pedidos.orElse(null).setEstado("Anulado");
        pedidos.orElse(null).setIVA(15.0);
        pedidos.orElse(null).setTotalneto(155.0);
        pedidos.orElse(null).setCliente(cliente.orElse(null));

        Pedidos pedidoActualizado = pedidosRepository.save(pedidos.orElse(null));
        assertNotNull(pedidoActualizado);

        assertEquals("Anulado", pedidoActualizado.getEstado());
        assertEquals(15.0, pedidoActualizado.getIVA());

    }
    @Test
    public void testPedidoDelete(){
        if (pedidosRepository.existsById(51)) {
            pedidosRepository.deleteById(51);
        }
        assertFalse(pedidosRepository.existsById(51));
    }
}
