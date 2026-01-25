package com.distribuida.controller;

import com.distribuida.model.Pedidos;
import com.distribuida.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")

public class PedidosController {

    @Autowired
    private PedidosService PedidosService;

    @GetMapping
    public ResponseEntity<List<Pedidos>> findAll(){
        List<Pedidos> Pedidoss = PedidosService.findAll();
        return ResponseEntity.ok().body(Pedidoss);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> findOne(@PathVariable int id){
        Optional<Pedidos> Pedidos = PedidosService.findOne(id);
        if(Pedidos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Pedidos.orElse(null));
    }

    @PostMapping
    public ResponseEntity<Pedidos> save(@RequestBody Pedidos Pedidos){

        Pedidos PedidosNuevo = PedidosService.save(Pedidos);
        return ResponseEntity.ok(PedidosNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> update(@PathVariable int id, @RequestBody Pedidos Pedidos){
        Pedidos PedidosActualizado = PedidosService.update(id, Pedidos);
        if (PedidosActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PedidosActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        PedidosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
