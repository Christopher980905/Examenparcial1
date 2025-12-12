package com.distribuida.dao;


import com.distribuida.model.MovimientoInventario;
import com.distribuida.model.Pagos;
import com.distribuida.model.Pedidos;
import com.distribuida.model.Productos;
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
public class PagosTestIntegracion {

    @Autowired
    private PagosRepository pagosRepository;

    @Autowired
    private PedidosRepository pedidosRepository;
    @Autowired
    private ProductosRepository productosRepository;

    @Test
    public void TestPagosFindAll(){
        List<Pagos> pagos = pagosRepository.findAll();

        assertNotNull(pagos);
        assertTrue(pagos.size()>0);
        pagos.forEach(System.out::println);

        }


    @Test
    public void TestPagosFindOne(){
        Optional<Pagos> pagos = pagosRepository.findById(1);

        assertTrue(pagos.isPresent());
        assertEquals("Tarjeta de Crédito", pagos.orElse(null).getMetodo_pago());

        System.out.println(pagos);
    }

    @Test
    public void testMovimientoInventarioSave(){
        Optional<Pedidos> pedidos  = pedidosRepository.findById(1);
        assertTrue(pedidos.isPresent());

        Pagos pagos = new Pagos(1,"paypal",new Date(),1);
        pagos.setIdPago(0);
        pagos.setMetodo_pago("paypal");
        pagos.setFecha(new Date());
        pagos.setPedidos(pedidos.orElse(null));


        Pagos pagoGuardado = pagosRepository.save(pagos);
        assertNotNull(pagoGuardado);
        assertEquals("paypal", pagoGuardado.getMetodo_pago());
        assertEquals(400.0, pagoGuardado.getMetodo_pago());

        pagosRepository.save(pagos);

    }
    //ACTUALIZAR DATOS
    @Test
    public void testPagosUpdate(){

        Optional<Pedidos> pedidos = pedidosRepository.findById(2);
        assertTrue(pedidos.isPresent());

        Optional<Pagos> pagos = pagosRepository.findById(51);
        assertTrue(pagos.isPresent());

        pagos.orElse(null).setMetodo_pago("tarjeta");
        pagos.orElse(null).setFecha(new Date());
        pagos.orElse(null).setPedidos(pedidos.orElse(null));

        Pagos pagosActualizado = pagosRepository.save(pagos.orElse(null));
        assertNotNull(pagosActualizado);

        assertEquals("tarjeta", pagosActualizado.getMetodo_pago());


    }
    @Test
    public void testPagosDelete(){
        if (pagosRepository.existsById(51)) {
            pagosRepository.deleteById(51);
        }
        assertFalse(pagosRepository.existsById(51));
    }

}

