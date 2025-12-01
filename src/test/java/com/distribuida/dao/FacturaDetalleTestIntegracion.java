package com.distribuida.dao;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
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
public class FacturaDetalleTestIntegracion {
    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Test
    public void testFacturaDetalleFindAll(){
        List<FacturaDetalle> facturaDetalles = facturaDetalleRepository.findAll();
        assertNotNull(facturaDetalles);
        assertTrue(facturaDetalles.size()>0);
        facturaDetalles.forEach(System.out::println);
    }

    @Test
    public void testFacturaDetalleFindOne(){
        Optional<FacturaDetalle> facturaDetalle = facturaDetalleRepository.findById(1L);
        assertTrue(facturaDetalle.isPresent());
        assertEquals(2, facturaDetalle.orElse(null).getCantidad());
        assertEquals(30.84, facturaDetalle.orElse(null).getSubtotal());

        System.out.println(facturaDetalle);

    }
    //GUARDAR DATOS
    @Test
    public void testFacturaDetalleSave(){
        Optional<Factura> factura = facturaRepository.findById(2);
        assertTrue(factura.isPresent());
        Optional<Libro> libro = libroRepository.findById(4);
        assertTrue(libro.isPresent());

        FacturaDetalle facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturadetalle(0);
        facturaDetalle.setCantidad(20);
        facturaDetalle.setSubtotal(200.00);

        FacturaDetalle facturaDetalleGuardada = facturaDetalleRepository.save(facturaDetalle);
        assertNotNull(facturaDetalleGuardada);
        assertEquals(20, facturaDetalle.getCantidad());
        assertEquals(200.00, facturaDetalle.getSubtotal());

        facturaDetalleRepository.save(facturaDetalle);
    }
    //ACTUALIZAR DATOS
    @Test
    public void testFacturaDetalleUpdate(){
        Optional<Factura> factura = facturaRepository.findById(2);
        assertTrue(factura.isPresent());
        Optional<Libro> libro = libroRepository.findById(3);
        assertTrue(libro.isPresent());
        Optional<FacturaDetalle> facturaDetalle = facturaDetalleRepository.findById(210L);
        assertTrue(facturaDetalle.isPresent());




        facturaDetalle.orElse(null).setCantidad(40);
        facturaDetalle.orElse(null).setSubtotal(400.00);
        facturaDetalle.orElse(null).setFactura(factura.orElse(null));
        facturaDetalle.orElse(null).setLibro(libro.orElse(null));



        FacturaDetalle facturaDetalleActualizada = facturaDetalleRepository.save(facturaDetalle.orElse(null));
        assertNotNull(facturaDetalleActualizada);
        assertEquals(40, facturaDetalleActualizada.getCantidad());
        assertEquals(400.00, facturaDetalleActualizada.getSubtotal());

        facturaDetalleRepository.save(facturaDetalle.orElse(null));

    }
    //ELIMINAR DATOS DE LA BASE
    @Test
    public void testFacturaDetalleDelete(){
        facturaDetalleRepository.deleteById(210L);{
            facturaDetalleRepository.deleteById(210L);
        }
        assertFalse(facturaDetalleRepository.existsById(210L));
    }

}
