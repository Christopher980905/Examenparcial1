package com.distribuida.service;

import com.distribuida.model.Pedidos;

import java.util.List;
import java.util.Optional;

public interface PedidosService {

    public List<Pedidos> findAll();

    public  Optional<Pedidos> findOne(int id);

    public Pedidos save(Pedidos pedidos);

    public  Pedidos update(int id, Pedidos pedidos);

    public  void delete(int id);
}
