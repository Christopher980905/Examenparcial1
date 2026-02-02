package com.distribuida.dao;

import com.distribuida.model.Categoria;
import com.distribuida.model.Cliente;
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


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class ClienteTestIntegracion {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void TestClienteFindAll(){
        List<Cliente> clientes = clienteRepository.findAll();

        assertNotNull(clientes);
        assertTrue(clientes.size()>0);
        clientes.forEach(System.out::println);

    }
    @Test
    public void TestClienteFindOne(){
        Optional<Cliente> cliente = clienteRepository.findById(1);

        assertNotNull( cliente.isPresent());
        assertEquals("Juan", cliente.orElse(null).getNombre());
        assertEquals("Pérez", cliente.orElse(null).getApellido());

        System.out.println(cliente);

    }
    //GUARDAR DATOS
    @Test
    public void testClienteSave(){
        Cliente  cliente = new Cliente();

        cliente.setIdCliente(0);
        cliente.setCedula("175896578");
        cliente.setNombre("Alisson");
        cliente.setApellido("Simba");
        cliente.setEmail("asimba@example.com");
        cliente.setTelefono("0998796456");
        cliente.setDireccion("Av azcazubi");


        Cliente clienteGuardado = clienteRepository.save(cliente);
        assertNotNull( clienteGuardado );
        assertEquals("Alisson", clienteGuardado.getNombre());
        assertEquals("Simba", clienteGuardado.getApellido());

    }
    //ACTUALIZAR DATOS
    @Test
    public void testClienteUpdate(){
        Optional<Cliente> cliente = clienteRepository.findById(3);

        cliente.orElse(null).setCedula("178545418");
        cliente.orElse(null).setNombre("Ingrid");
        cliente.orElse(null).setApellido("Farinango");
        cliente.orElse(null).setEmail("ifarinango@example.com");
        cliente.orElse(null).setTelefono("0990877564");
        cliente.orElse(null).setDireccion("Av. checa la piscina");


        Cliente clienteActualizado = clienteRepository.save(cliente.orElse(null));
        assertNotNull(clienteActualizado);
        assertEquals("Ingrid", clienteActualizado.getNombre());
        assertEquals("Farinango", clienteActualizado.getApellido());

    }
    @Test
    public void testClienteBorrar(){
        clienteRepository.deleteById(51);
    }


}
