package com.distribuida.service;


import com.distribuida.dao.PagosRepository;
import com.distribuida.model.Pagos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagosServiceImpl implements PagosService {

    @Autowired
    private PagosRepository pagosRepository;


    @Override
    public List<Pagos> findAll(){
        return pagosRepository.findAll();

    }

    @Override
    public Optional<Pagos> findOne(int id){
        return pagosRepository.findById(id);
    }

    @Override
    public Pagos save(Pagos pagos){

        return pagosRepository.save(pagos);

    }

    @Override
    public Pagos update(int id, Pagos pagos){

        Optional<Pagos> pagosExistente = pagosRepository.findById(id);

        if (pagosExistente == null){
            return null;
        }
        pagosExistente.orElse(null).setMetodo_pago(pagos.getMetodo_pago());

        pagosExistente.orElse(null).setFecha(pagos.getFecha());
        pagosExistente.orElse(null).setPedidos(pagos.getPedidos());

        return pagosRepository.save(pagosExistente.orElse(null));
    }

    @Override
    public void delete(int id){

        if (pagosRepository.existsById(id)){
            pagosRepository.deleteById(id);
        }
}
}

