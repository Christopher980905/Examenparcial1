package com.distribuida.dao;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Factura;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class LibroTestIntegracion {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void testLibroFindAll(){
        List<Libro> libros = libroRepository.findAll();
        assertNotNull(libros);
        assertTrue(libros.size()>0);
        libros.forEach(System.out::println);
    }

    @Test
    public void testLibroFindOne(){
        Optional<Libro> libro = libroRepository.findById(1);
        assertTrue(libro.isPresent());
        assertEquals("Spring in Action", libro.orElse(null).getTitulo());
        assertEquals(700, libro.orElse(null).getNumeroPaginas());

        System.out.println(libro);

    }
    //GUARDAR DATOS
    @Test
    public void testLibroSave(){
        Optional<Categoria> categoria = categoriaRepository.findById(1);
        assertTrue(categoria.isPresent());

        Optional<Autor> autor = autorRepository.findById(1);
        assertTrue(autor.isPresent());

        Libro libro1 = new Libro();
        libro1.setIdLibro(0);
        libro1.setTitulo("La dama tapada");
        libro1.setEditorial("la noche oscura");
        libro1.setNumeroPaginas(100);
        libro1.setEdicion("ISMAC SOFTWARE");
        libro1.setIdioma("Español");
        libro1.setFecha_de_publicacion("12-11-2025");
        libro1.setDescripcion("es un relato hecho para los hombres borrachos");
        libro1.setTipo_de_pasta("TRANS");
        libro1.setIsbn("123456789");
        libro1.setNum_ejemplares(10);
        libro1.setPortada("Vestido de negro");
        libro1.setPresentacion("libro x 100pag");
        libro1.setPrecio(12.50);
        libro1.setCategoria(categoria.orElse(null));
        libro1.setAutor(autor.orElse(null));

        Libro libroGuardado = libroRepository.save(libro1);
        assertNotNull(libroGuardado);
        assertEquals("La dama tapada", libroGuardado.getTitulo());
        assertEquals(100, libroGuardado.getNumeroPaginas());

        libroRepository.save(libro1);


    }
    //ACTUALIZAR DATOS
    @Test
    public void testLibroUpdate(){
        Optional<Libro> libro = libroRepository.findById(79);
        assertTrue(libro.isPresent());
        Optional<Categoria> categoria = categoriaRepository.findById(2);
        assertTrue(categoria.isPresent());
        Optional<Autor> autor = autorRepository.findById(2);
        assertTrue(autor.isPresent());

        libro.orElse(null).setTitulo("La dama tapada22");
        libro.orElse(null).setEditorial("la noche oscura22");
        libro.orElse(null).setNumeroPaginas(200);
        libro.orElse(null).setEdicion("ISMAC SOFTWARE2");
        libro.orElse(null).setIdioma("Ingles");
        libro.orElse(null).setFecha_de_publicacion("22-11-2025");
        libro.orElse(null).setDescripcion("es un relato hecho para los hombres borrachosss");
        libro.orElse(null).setTipo_de_pasta("TRANS2");
        libro.orElse(null).setIsbn("1144455678");
        libro.orElse(null).setNum_ejemplares(20);
        libro.orElse(null).setPortada("Vestido de blanco");
        libro.orElse(null).setPresentacion("libro x 100pag");
        libro.orElse(null).setPrecio(24.50);
        libro.orElse(null).setCategoria(categoria.orElse(null));
        libro.orElse(null).setAutor(autor.orElse(null));

        Libro libroActualizado = libroRepository.save(libro.orElse(null));
        assertNotNull(libroActualizado);
        assertEquals("La dama tapada22", libroActualizado.getTitulo());
        assertEquals(200, libroActualizado.getNumeroPaginas());

        libroRepository.save(libro.orElse(null));




    }
    //ELIMINAR DATOS DE LA BASE
    @Test
    public void testLibroDelete(){
        libroRepository.deleteById(79);{
            libroRepository.deleteById(79);
        }
        assertFalse(libroRepository.existsById(79));
    }


}
