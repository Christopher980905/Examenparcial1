package com.distribuida.controller;

import com.distribuida.model.Pagos;
import com.distribuida.service.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")

public class PagosController {

    @Autowired
    private PagosService PagosService;

    @GetMapping
    public ResponseEntity<List<Pagos>> findAll(){
        List<Pagos> Pagoss = PagosService.findAll();
        return ResponseEntity.ok().body(Pagoss);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pagos> findOne(@PathVariable int id){
        Optional<Pagos> Pagos = PagosService.findOne(id);
        if(Pagos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Pagos.orElse(null));
    }

    @PostMapping
    public ResponseEntity<Pagos> save(@RequestBody Pagos Pagos){

        Pagos PagosNuevo = PagosService.save(Pagos);
        return ResponseEntity.ok(PagosNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagos> update(@PathVariable int id, @RequestBody Pagos Pagos){
        Pagos PagosActualizado = PagosService.update(id, Pagos);
        if (PagosActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PagosActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        PagosService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
