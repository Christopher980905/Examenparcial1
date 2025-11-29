package com.distribuida.dao;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
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
public class ClienteTestIntegracion {
    @Autowired
    private ClienteRepository clienteRepository;
    @Test
    public void testClienteFindAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
        for(Cliente item:clientes){
            System.out.println(item.toString());
        }
    }
    @Test
    public void testClienteFindOne(){
        Optional<Cliente> cliente = clienteRepository.findById(1);
        assertNotNull(cliente.isPresent());
        assertEquals("Puro",cliente.orElse(null).getNombre());
        assertEquals("Hueso",cliente.orElse(null).getApellido());

        System.out.println(cliente);

    }

    //GUARDAR DATOS
    @Test
    public void testClienteSave(){
        //Optional<Cliente> cliente = clienteRepository.findById(4);
        Cliente cliente =new Cliente();
        cliente.setIdCliente(0);
        cliente.setCedula("1751839067");
        cliente.setNombre("Cristo");
        cliente.setApellido("Pillajo");
        cliente.setDireccion("La tola");
        cliente.setTelefono("0994450452");
        cliente.setCorreo("cris@gmail.com");

        Cliente clienteGuardado = clienteRepository.save(cliente);

        assertNotNull( clienteGuardado);
        assertEquals("1751839067",clienteGuardado.getCedula());
        assertEquals("Cristo",clienteGuardado.getNombre());

    }
    //ACTUALIZAR DATOS
    @Test
    public void testClienteUpdate(){
        Optional<Cliente> cliente = clienteRepository.findById(39);

        cliente.orElse(null).setCedula("177788890");
        cliente.orElse(null).setNombre("Jose");
        cliente.orElse(null).setApellido("Marabu");
        cliente.orElse(null).setDireccion("Tumbaco");
        cliente.orElse(null).setTelefono("09998887794");
        cliente.orElse(null).setCorreo("jm@gmail.com");

        Cliente clienteActualizado = clienteRepository.save(cliente.orElse(null));


        clienteRepository.save(cliente.orElse(null));

        assertNotNull(clienteActualizado);
        assertEquals("Marabu",clienteActualizado.getApellido());
        assertEquals("Tumbaco", clienteActualizado.getDireccion());
    }
    //ELIMINAR DATOS DE LA BASE
    @Test
    public void testClienteDelete(){
        clienteRepository.deleteById(39);
    }
}
