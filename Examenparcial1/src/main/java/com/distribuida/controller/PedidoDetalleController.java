package com.distribuida.controller;

import com.distribuida.model.PedidoDetalle;
import com.distribuida.service.PedidoDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidoDetalles")

public class PedidoDetalleController {
    @Autowired
    private PedidoDetalleService PedidoDetalleService;

    @GetMapping
    public ResponseEntity<List<PedidoDetalle>> findAll(){
        List<PedidoDetalle> PedidoDetalles = PedidoDetalleService.findAll();
        return ResponseEntity.ok().body(PedidoDetalles);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalle> findOne(@PathVariable int id){
        Optional<PedidoDetalle> PedidoDetalle = PedidoDetalleService.findOne(id);
        if(PedidoDetalle == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PedidoDetalle.orElse(null));
    }

    @PostMapping
    public ResponseEntity<PedidoDetalle> save(@RequestBody PedidoDetalle PedidoDetalle){

        PedidoDetalle PedidoDetalleNuevo = PedidoDetalleService.save(PedidoDetalle);
        return ResponseEntity.ok(PedidoDetalleNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDetalle> update(@PathVariable int id, @RequestBody PedidoDetalle PedidoDetalle){
        PedidoDetalle PedidoDetalleActualizado = PedidoDetalleService.update(id, PedidoDetalle);
        if (PedidoDetalleActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PedidoDetalleActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        PedidoDetalleService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
