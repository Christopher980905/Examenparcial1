package com.distribuida.dao;

import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import com.distribuida.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarritoRepository extends JpaRepository<CarritoItem, Long> {

    Optional<Carrito> findByCliente(Cliente cliente);
    Optional<Carrito> findByToken(String token);
}
