package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibroTestUnitaria {
    private Categoria categoria;
    private Autor autor;
    private Libro libro1;

    @BeforeEach
    public void setUp() {
        categoria = new Categoria(1,"Infantil","relato real antepasado");
        autor = new Autor(1,"Mario","Benalcazar","Alemania","Av.rio blanco y rio negro","09945245663","mbenalcazar@gmail.com");

        libro1 = new Libro();
        libro1.setIdLibro(1);
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
        libro1.setCategoria(categoria);
        libro1.setAutor(autor);

    }

    @Test
    public void libroTestContructor(){
        assertAll("Validar Constrcutor - Libro",
                ()-> assertEquals(1,libro1.getIdLibro()),
                ()-> assertEquals("La dama tapada",libro1.getTitulo()),
                ()-> assertEquals("la noche oscura",libro1.getEditorial()),
                ()-> assertEquals(100,libro1.getNumeroPaginas()),
                ()-> assertEquals("ISMAC SOFTWARE",libro1.getEdicion()),
                ()-> assertEquals("Español",libro1.getIdioma()),
                ()-> assertEquals("12-11-2025",libro1.getFecha_de_publicacion()),
                ()-> assertEquals("es un relato hecho para los hombres borrachos",libro1.getDescripcion()),
                ()-> assertEquals("TRANS",libro1.getTipo_de_pasta()),
                ()-> assertEquals("123456789",libro1.getIsbn()),
                ()-> assertEquals(10,libro1.getNum_ejemplares()),
                ()-> assertEquals("Vestido de negro",libro1.getPortada()),
                ()-> assertEquals("libro x 100pag",libro1.getPresentacion()),
                ()-> assertEquals(12.50,libro1.getPrecio()),
                () -> assertEquals("Infantil", libro1.getCategoria().getCategoria()),
                () -> assertEquals("Mario", libro1.getAutor().getNombreAutor())
        );

    }
    @Test
    public void libroTestSetter(){
        categoria = new Categoria(2,"Infantil2","relato real antepasado2");
        autor = new Autor(2,"Mario2","Benalcazar2","Alemania2","Av.rio blanco y rio negro2","0994524566322","mbenalcazar@gmail.com2");
        libro1.setIdLibro(2);
        libro1.setTitulo("La dama tapada2");
        libro1.setEditorial("la noche oscura2");
        libro1.setNumeroPaginas(200);
        libro1.setEdicion("ISMAC SOFTWARE2");
        libro1.setIdioma("Español2");
        libro1.setFecha_de_publicacion("1222-11-2025");
        libro1.setDescripcion("es un relato hecho para los hombres borrachos2");
        libro1.setTipo_de_pasta("TRANS2");
        libro1.setIsbn("12345678922");
        libro1.setNum_ejemplares(20);
        libro1.setPortada("Vestido de negro2");
        libro1.setPresentacion("libro x 200pag");
        libro1.setPrecio(24.50);
        libro1.setCategoria(categoria);
        libro1.setAutor(autor);

        assertAll("Validar Setters - Libro",
                ()-> assertEquals(2,libro1.getIdLibro()),
                ()-> assertEquals("La dama tapada2",libro1.getTitulo()),
                ()-> assertEquals("la noche oscura2",libro1.getEditorial()),
                ()-> assertEquals(200,libro1.getNumeroPaginas()),
                ()-> assertEquals("ISMAC SOFTWARE2",libro1.getEdicion()),
                ()-> assertEquals("Español2",libro1.getIdioma()),
                ()-> assertEquals("1222-11-2025",libro1.getFecha_de_publicacion()),
                ()-> assertEquals("es un relato hecho para los hombres borrachos2",libro1.getDescripcion()),
                ()-> assertEquals("TRANS2",libro1.getTipo_de_pasta()),
                ()-> assertEquals("12345678922",libro1.getIsbn()),
                ()-> assertEquals(20,libro1.getNum_ejemplares()),
                ()-> assertEquals("Vestido de negro2",libro1.getPortada()),
                ()-> assertEquals("libro x 200pag",libro1.getPresentacion()),
                ()-> assertEquals(24.50,libro1.getPrecio()),
                () -> assertEquals("Infantil2", libro1.getCategoria().getCategoria()),
                () -> assertEquals("Mario2", libro1.getAutor().getNombreAutor())
        );

    }
    @Test
    public void libroTestToString(){
        String str= libro1.toString();
        assertAll("Validar to String - Libro",
                ()-> assertTrue(str.contains("1")),
                ()-> assertTrue(str.contains("La dama tapada")),
                ()-> assertTrue(str.contains("la noche oscura")),
                ()-> assertTrue(str.contains("100")),
                ()-> assertTrue(str.contains("ISMAC SOFTWARE")),
                ()-> assertTrue(str.contains("Español")),
                ()-> assertTrue(str.contains("12-11-2025")),
                ()-> assertTrue(str.contains("es un relato hecho para los hombres borrachos")),
                ()-> assertTrue(str.contains("TRANS")),
                ()-> assertTrue(str.contains("123456789")),
                ()-> assertTrue(str.contains("10")),
                ()-> assertTrue(str.contains("Vestido de negro")),
                ()-> assertTrue(str.contains("libro x 100pag")),
                ()-> assertTrue(str.contains("12.50")),
                ()-> assertTrue(str.contains("Infantil")),
                ()-> assertTrue(str.contains("Mario"))
        );
    }
    @Test
    public void libroTestInyector() {
        assertAll("Validar metodo inyector - Libro",
                () -> assertNotNull(libro1.getCategoria()),
                () -> assertEquals("relato real antepasado", libro1.getCategoria().getDescripcion()),
                () -> assertNotNull(libro1.getAutor()),
                () -> assertEquals("Alemania", libro1.getAutor().getPaisAutor())
        );

    }
}
