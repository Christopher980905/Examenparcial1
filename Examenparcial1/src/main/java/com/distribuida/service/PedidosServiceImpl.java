package com.distribuida.service;

import com.distribuida.dao.PedidosRepository;
import com.distribuida.model.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PedidosServiceImpl implements PedidosService{


    @Autowired
    private PedidosRepository pedidosRepository;


    @Override
    public List<Pedidos> findAll(){
        return pedidosRepository.findAll();

    }

    @Override
    public Optional<Pedidos> findOne(int id){
        return pedidosRepository.findById(id);
    }

    @Override
    public Pedidos save(Pedidos pedidos){

        return pedidosRepository.save(pedidos);

    }

    @Override
    public Pedidos update(int id, Pedidos pedidos){

        Optional<Pedidos> pedidosExistente = pedidosRepository.findById(id);

        if (pedidosExistente == null){
            return null;
        }
        pedidosExistente.orElse(null).setFechapedido(pedidos.getFechapedido());
        pedidosExistente.orElse(null).setEstado(pedidos.getEstado());
        pedidosExistente.orElse(null).setTotalneto(pedidos.getTotalneto());
        pedidosExistente.orElse(null).setIVA(pedidos.getIVA());
        pedidosExistente.orElse(null).setMonto_pagar(pedidos.getMonto_pagar());
        pedidosExistente.orElse(null).setCliente(pedidos.getCliente());
        pedidosExistente.orElse(null).setPagos(pedidos.getPagos());


       return pedidosRepository.save(pedidosExistente.orElse(null));
    }

    @Override
    public void delete(int id){

        if (pedidosRepository.existsById(id)){
            pedidosRepository.deleteById(id);
        }
    }

}
