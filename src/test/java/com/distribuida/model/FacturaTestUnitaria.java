package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaTestUnitaria {
    private Cliente cliente;
    private Factura factura;

    @BeforeEach
    public void setUp(){
        cliente = new Cliente(1,"17453839565","Juan","Taipe","Av. amazonas y rio","099676578","jtaipe@gmail.com");

        factura = new Factura();
        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        //Inyeccion de dependencias
        factura.setCliente(cliente);
    }
    @Test
    public void facturaTestConstructor(){
        assertAll("Validar Constrcutor - Factura",
                ()-> assertEquals(1,factura.getIdFactura()),
                () -> assertEquals("FAC-0001", factura.getNumFactura()),
                //() -> assertEquals(new Date(), factura.getFecha()),
                () -> assertEquals(100.00, factura.getTotalNeto()),
                () -> assertEquals(15.00, factura.getIva()),
                () -> assertEquals(115.00, factura.getTotal()),
                () -> assertEquals("Juan", factura.getCliente().getNombre())
                );

    }
    @Test
    public void facturaTestSetter(){
        cliente = new Cliente(2,"1745383956522","Juan2","Taipe2","Av. amazonas y rio2","09967657822","jtaipe@gmail.com2");
        factura.setIdFactura(2);
        factura.setNumFactura("FAC-0002");
        factura.setFecha(new Date());
        factura.setTotalNeto(200.00);
        factura.setIva(30.00);
        factura.setTotal(230.00);
        factura.setCliente(cliente);

        assertAll("Validar Setters - Factura",
                ()-> assertEquals(2,factura.getIdFactura()),
                () -> assertEquals("FAC-0002", factura.getNumFactura()),
                //() -> assertEquals(new Date(), factura.getFecha()),
                () -> assertEquals(200.00, factura.getTotalNeto()),
                () -> assertEquals(30.00, factura.getIva()),
                () -> assertEquals(230.00, factura.getTotal()),
                () -> assertEquals("Juan2", factura.getCliente().getNombre())
        );

    }
    @Test
    public void facturaTestToString(){
        String str= factura.toString();
        assertAll("Validar to String - Factura",
                () ->assertTrue(str.contains("1")),
                () ->assertTrue(str.contains("FAC-0001")),
                () ->assertTrue(str.contains("100.0")),
                () ->assertTrue(str.contains("15.0")),
                () ->assertTrue(str.contains("115.0")),
                () ->assertTrue(str.contains("Juan"))
                );
    }
    @Test
    public void facturaTestInyector(){
        assertAll("Validar metodo inyector - Factura",
                ()-> assertNotNull(factura.getCliente()),
                ()-> assertEquals("Taipe", factura.getCliente().getApellidos())
                );
    }
    @Test
    public void facturaTestValoresNegativo(){
        factura.setTotal(-100.00);
        assertAll("Validar datos negativos - Factura",
                () -> assertEquals(-100.00, factura.getTotal())
                );
        // Validar los campos negativos, solo deberian existir valores negativos en la factura
    }
}
