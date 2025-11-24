package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaDetalleTestUnitaria {
    private FacturaDetalle facturaDetalle;
    private Cliente cliente;
    private Factura factura;
    private Libro libro1;

    @BeforeEach
    public void setUp(){
        facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturadetalle(1);
        facturaDetalle.setCantidad(20);
        facturaDetalle.setSubtotal(200.00);

        cliente = new Cliente(1,"1751839067","Juan","Taipe","Av. por ahi y mas alla","0994450452","jtaipe@gmail.com");

        factura = new Factura();
        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        facturaDetalle.setFactura(factura);
        factura.setCliente(cliente);

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
        Categoria categoria = new Categoria(1, "Infantil", "relato real antepasado");
        libro1.setCategoria(categoria);
        Autor autor = new Autor(1, "Mario", "Benalcazar", "Alemania", "Av.rio blanco y rio negro", "09945245663", "mbenalcazar@gmail.com");
        libro1.setAutor(autor);
        facturaDetalle.setLibro(libro1);

    }
    @Test
    public void facturaDetalleTestConstructor(){
        assertAll("Validar Constrcutor - FacturaDetalle",
                ()-> assertEquals(1,facturaDetalle.getIdFacturadetalle()),
                ()-> assertEquals(20,facturaDetalle.getCantidad()),
                ()-> assertEquals(200.00,facturaDetalle.getSubtotal()),
                ()-> assertEquals("FAC-0001",facturaDetalle.getFactura().getNumFactura()),
                ()-> assertEquals("La dama tapada",facturaDetalle.getLibro().getTitulo())
                );
    }
    @Test
    public void facturaDetalleTestSetter(){
        facturaDetalle.setIdFacturadetalle(2);
        facturaDetalle.setCantidad(30);
        facturaDetalle.setSubtotal(230.00);
        factura.setNumFactura("FAC-0002");
        libro1.setTitulo("La dama tapada2");
        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro1);

        assertAll("Validar Setters - FacturaDetalle",
                ()-> assertEquals(2,facturaDetalle.getIdFacturadetalle()),
                ()-> assertEquals(30,facturaDetalle.getCantidad()),
                ()-> assertEquals(230.00,facturaDetalle.getSubtotal()),
                ()-> assertEquals("FAC-0002",facturaDetalle.getFactura().getNumFactura()),
                ()-> assertEquals("La dama tapada2",facturaDetalle.getLibro().getTitulo())
                );

    }
    @Test
    public void facturaDetalleTestToString(){
        String str= facturaDetalle.toString();
        assertAll("Validar to String - FacturaDetalle",
                () ->assertTrue(str.contains("1")),
                () ->assertTrue(str.contains("20")),
                () ->assertTrue(str.contains("200.0")),
                () ->assertTrue(str.contains("FAC-0001")),
                () ->assertTrue(str.contains("La dama tapada"))
        );

    }
    @Test
    public void facturaDetalleTestInyector(){
        assertAll("Validar metodo inyector - FacturaDetalle",
                ()-> assertNotNull(facturaDetalle.getFactura()),
                ()-> assertEquals(100.00, facturaDetalle.getFactura().getTotalNeto()),
                ()-> assertNotNull(facturaDetalle.getLibro()),
                ()-> assertEquals("ISMAC SOFTWARE", facturaDetalle.getLibro().getEdicion())
            );



    }





}
