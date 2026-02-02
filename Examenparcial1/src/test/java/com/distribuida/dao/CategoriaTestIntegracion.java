package com.distribuida.dao;

import com.distribuida.model.Categoria;
import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class CategoriaTestIntegracion {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Test
    public void testCategoriaFindAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        for (Categoria item : categorias) {
            System.out.println(item.toString());
        }
    }
    @Test
    public void testCategoriaFindOne() {
        Optional<Categoria> categoria = categoriaRepository.findById(1);

        System.out.println(categoria);
    }

    //GUARDAR DATOS
    @Test
    public void testCategoriaSave(){
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(0);
        categoria.setNombreCategoria("Casco1");

        categoriaRepository.save(categoria);

    }
    //ACTUALIZAR DATOS
    @Test
    public void testCategoriaUpdate(){
        Optional<Categoria> categoria = categoriaRepository.findById(7);

        categoria.orElse(null).setNombreCategoria("Accesorios");

        categoriaRepository.save(categoria.orElse(null));



    }
    //ELIMINAR DATOS DE LA BASE
    @Test
    public void testCategoriaDelete(){
        categoriaRepository.deleteById(7);

    }

}
