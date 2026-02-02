package com.distribuida.controller;

import com.distribuida.model.Productos;
import com.distribuida.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")

public class ProductosController {

    @Autowired
    private ProductosService ProductosService;

    @GetMapping
    public ResponseEntity<List<Productos>> findAll(){
        List<Productos> Productoss = ProductosService.findAll();
        return ResponseEntity.ok().body(Productoss);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Productos> findOne(@PathVariable int id){
        Optional<Productos> Productos = ProductosService.findOne(id);
        if(Productos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Productos.orElse(null));
    }

    @PostMapping
    public ResponseEntity<Productos> save(@RequestBody Productos Productos){

        Productos ProductosNuevo = ProductosService.save(Productos);
        return ResponseEntity.ok(ProductosNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> update(@PathVariable int id, @RequestBody Productos Productos){
        Productos ProductosActualizado = ProductosService.update(id, Productos);
        if (ProductosActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ProductosActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        ProductosService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
