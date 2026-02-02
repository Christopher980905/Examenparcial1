package com.distribuida.dao;

import com.distribuida.model.Categoria;
import com.distribuida.model.Cliente;
import com.distribuida.model.MovimientoInventario;
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
public class MovimientoInventarioTestIntengracion {

    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    @Autowired
    private ProductosRepository productosRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void TestMovimientoInventarioFindAll(){
        List<MovimientoInventario> movimientoInventarios = movimientoInventarioRepository.findAll();

        assertNotNull(movimientoInventarios);
        assertTrue(movimientoInventarios.size()>0);
        movimientoInventarios.forEach(System.out::println);
        }


    @Test
    public void TestMovimmientoInventarioFindOne(){
        Optional<MovimientoInventario> movimientoInventario = movimientoInventarioRepository.findById(1);

        assertTrue(movimientoInventario.isPresent());
        assertEquals("Entrada", movimientoInventario.orElse(null).getTipo_movimiento());
        assertEquals(20, movimientoInventario.orElse(null).getCantidad());
        System.out.println(movimientoInventario);
    }

    @Test
    public void testMovimientoInventarioSave(){
        Optional<Productos> productos  = productosRepository.findById(1);
        assertTrue(productos.isPresent());

        Optional<Categoria> categoria  = categoriaRepository.findById(1);
        assertTrue(categoria.isPresent());

        MovimientoInventario movimientoInventario = new MovimientoInventario(1,"Entrada",50,new Date(),1);
        movimientoInventario.setIdMovimientoinventario(0);
        movimientoInventario.setTipo_movimiento("Salida");
        movimientoInventario.setCantidad(50);
        movimientoInventario.setFecha(new Date());
        movimientoInventario.setProductos(productos.orElse(null));

        MovimientoInventario movimientoGuardado = movimientoInventarioRepository.save(movimientoInventario);
        assertNotNull(movimientoGuardado);
        assertEquals("Salida", movimientoGuardado.getTipo_movimiento());

}
    //ACTUALIZAR DATOS
    @Test
    public void testClienteUpdate(){

        Optional<Productos> productos = productosRepository.findById(1);
        assertTrue(productos.isPresent());

        Optional<MovimientoInventario> movimientoInventario = movimientoInventarioRepository.findById(149);
        assertTrue(movimientoInventario.isPresent());

        movimientoInventario.orElse(null).setTipo_movimiento("En proceso");
        movimientoInventario.orElse(null).setCantidad(100);
        movimientoInventario.orElse(null).setFecha(new Date());
        movimientoInventario.orElse(null).setProductos(productos.orElse(null));

        MovimientoInventario movimientoActualizado = movimientoInventarioRepository.save(movimientoInventario.orElse(null));
        assertNotNull(movimientoActualizado);

        assertEquals("En proceso", movimientoActualizado.getTipo_movimiento());
        assertEquals(100, movimientoActualizado.getCantidad());

    }



@Test
public void testMovimientoInventarioDelete(){
    if (movimientoInventarioRepository.existsById(52)) {
        movimientoInventarioRepository.deleteById(52);
    }
    assertFalse(movimientoInventarioRepository.existsById(52));
}
}


