package com.distribuida.dao;


import com.distribuida.model.Categoria;
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
public class ProductosTestIntegracion {

    @Autowired
    private ProductosRepository productosRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void TestProductosFindAll(){

        List<Productos> productos = productosRepository.findAll();

        assertNotNull(productos);
        assertTrue(productos.size()>0);
        productos.forEach(System.out::println);
    }


    @Test
    public void TestProductosFindOne(){
        Optional<Productos> productos = productosRepository.findById(1);

        assertTrue(productos.isPresent());
        assertEquals("Pistón 150cc", productos.orElse(null).getNombre());
        assertEquals(32.50, productos.orElse(null).getPrecio());
    }

    @Test
    public void testMovimientoInventarioSave(){
        Optional<Categoria> categoria  = categoriaRepository.findById(1);
        assertTrue(categoria.isPresent());

        Productos productos = new Productos(1,"Pistón 150cc",32.50,40,new Date(),"Disponible",1);
        productos.setIdProducto(0);
        productos.setNombre("cascoone");
        productos.setPrecio(32.4);
        productos.setStock(80);
        productos.setFecharegistro(new Date());
        productos.setEstado("Ocupado");
        productos.setCategoria(categoria.orElse(null));

        Productos productosGuardado = productosRepository.save(productos);
        assertNotNull(productosGuardado);
        assertEquals("cascoone", productosGuardado.getNombre());
        assertEquals(32.4, productosGuardado.getPrecio());

        productosRepository.save(productos);

}

    //ACTUALIZAR DATOS
    @Test
    public void testClienteUpdate(){

        Optional<Categoria> categoria = categoriaRepository.findById(2);
        assertTrue(categoria.isPresent());

        Optional<Productos> productos = productosRepository.findById(51);
        assertTrue(productos.isPresent());

        productos.orElse(null).setNombre("Casco conejo");
        productos.orElse(null).setPrecio(100.1);

        productos.orElse(null).setCategoria(categoria.orElse(null));

        Productos productoActualizado = productosRepository.save(productos.orElse(null));
        assertNotNull(productoActualizado);

        assertEquals("Casco conejo", productoActualizado.getNombre());
        assertEquals(100.1, productoActualizado.getPrecio());

        productosRepository.save(productos.orElse(null));

    }


    @Test
    public void testMovimientoInventarioDelete(){
        if (productosRepository.existsById(51)) {
            productosRepository.deleteById(51);
        }
        assertFalse(productosRepository.existsById(51));
    }
}

