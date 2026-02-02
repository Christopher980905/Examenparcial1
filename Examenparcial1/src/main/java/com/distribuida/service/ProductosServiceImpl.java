package com.distribuida.service;


import com.distribuida.dao.ProductosRepository;
import com.distribuida.model.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosServiceImpl implements ProductosService {

    @Autowired
    private ProductosRepository productosRepository;


    @Override
    public List<Productos> findAll(){
        return productosRepository.findAll();

    }

    @Override
    public Optional<Productos> findOne(int id){
        return productosRepository.findById(id);
    }

    @Override
    public Productos save(Productos productos){

        return productosRepository.save(productos);

    }

    @Override
    public Productos update(int id, Productos productos){

        Optional<Productos> productosExistente = productosRepository.findById(id);

        if (productosExistente == null){
            return null;
        }
        productosExistente.orElse(null).setNombre(productos.getNombre());
        productosExistente.orElse(null).setPrecio(productos.getPrecio());
        productosExistente.orElse(null).setStock(productos.getStock());
        productosExistente.orElse(null).setFecharegistro(productos.getFecharegistro());

        productosExistente.orElse(null).setCategoria(productos.getCategoria());

        return productosRepository.save(productosExistente.orElse(null));
    }

    @Override
    public void delete(int id){

        if (productosRepository.existsById(id)){
            productosRepository.deleteById(id);
        }
    }


}
