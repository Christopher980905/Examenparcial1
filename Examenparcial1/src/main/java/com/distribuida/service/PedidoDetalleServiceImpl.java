package com.distribuida.service;

import com.distribuida.dao.PedidoDetalleRepository;
import com.distribuida.model.PedidoDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoDetalleServiceImpl implements PedidoDetalleService {

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;


    @Override
    public List<PedidoDetalle> findAll(){
        return pedidoDetalleRepository.findAll();

    }

    @Override
    public Optional<PedidoDetalle> findOne(int id){
        return pedidoDetalleRepository.findById(id);
    }

    @Override
    public PedidoDetalle save(PedidoDetalle pedidoDetalle){

        return pedidoDetalleRepository.save(pedidoDetalle);

    }

    @Override
    public PedidoDetalle update(int id, PedidoDetalle pedidoDetalle){

        Optional<PedidoDetalle> pedidoDetalleExistente = pedidoDetalleRepository.findById(id);

        if (pedidoDetalleExistente == null){
            return null;
        }
        pedidoDetalleExistente.orElse(null).setFecha(pedidoDetalle.getFecha());
        pedidoDetalleExistente.orElse(null).setCantidad(pedidoDetalle.getCantidad());
        pedidoDetalleExistente.orElse(null).setPrecio_unitario(pedidoDetalle.getPrecio_unitario());
        pedidoDetalleExistente.orElse(null).setSubtotal(pedidoDetalle.getSubtotal());
        pedidoDetalleExistente.orElse(null).setPedidos(pedidoDetalle.getPedidos());
        pedidoDetalleExistente.orElse(null).setProductos(pedidoDetalle.getProductos());


     return pedidoDetalleRepository.save(pedidoDetalleExistente.orElse(null));
    }

    @Override
    public void delete(int id){

        if (pedidoDetalleRepository.existsById(id)){
            pedidoDetalleRepository.deleteById(id);
        }
    }


}
