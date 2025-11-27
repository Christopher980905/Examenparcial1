package com.distribuida.dao;

import com.distribuida.model.Autor;
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
public class AutorTestIntegracion {
    @Autowired
    private AutorRepository autorRepository;
    @Test
    public void testAutorFindAll(){
        List<Autor> autores = autorRepository.findAll();
        for (Autor item : autores) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void testAutorFindOne(){
        Optional<Autor> autor = autorRepository.findById(1);

        System.out.println(autor);

    }

    //GUARDAR DATOS
    @Test
    public void testAutorSave(){
        //Optional<Cliente> cliente = clienteRepository.findById(4);
        Autor autor = new Autor();
        autor.setIdAutor(0);
        autor.setNombre("Romeo");
        autor.setApellido("Santos");
        autor.setPais("Brasil");
        autor.setDireccion("a lado de la casa de neymar");
        autor.setTelefono("0995667899");
        autor.setCorreo("rsantos@gamil.com");


        autorRepository.save(autor);

    }
    //ACTUALIZAR DATOS
    @Test
    public void testAutorUpdate(){
        Optional<Autor> autor = autorRepository.findById(54);

        autor.orElse(null).setNombre("Ozuna");
        autor.orElse(null).setApellido("El negrito ojos claros");
        autor.orElse(null).setPais("EE.UU");
        autor.orElse(null).setDireccion("Av. whashingthon");
        autor.orElse(null).setTelefono("999667543");
        autor.orElse(null).setCorreo("ojitos@gmail,com");

        autorRepository.save(autor.orElse(null));



    }
    //ELIMINAR DATOS DE LA BASE
    @Test
    public void testAutorDelete(){
        autorRepository.deleteById(55);
    }

}
