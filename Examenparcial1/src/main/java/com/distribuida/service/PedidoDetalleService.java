package com.distribuida.service;

import com.distribuida.model.PedidoDetalle;

import java.util.List;
import java.util.Optional;

public interface PedidoDetalleService {

    public List<PedidoDetalle> findAll();

    public Optional<PedidoDetalle> findOne(int id);

    public  PedidoDetalle save(PedidoDetalle pedidoDetalle);

    public PedidoDetalle update(int id, PedidoDetalle pedidoDetalle);

    public void delete(int id);
}
