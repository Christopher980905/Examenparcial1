package com.distribuida.dao;

import com.distribuida.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<Pedidos, Integer> {
}
