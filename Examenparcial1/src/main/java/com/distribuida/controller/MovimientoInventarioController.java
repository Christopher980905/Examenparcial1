package com.distribuida.controller;

import com.distribuida.model.MovimientoInventario;
import com.distribuida.service.MovimientoInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movimientoinventarios")



public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService MovimientoInventarioService;

    @GetMapping
    public ResponseEntity<List<MovimientoInventario>> findAll(){
        List<MovimientoInventario> MovimientoInventarios = MovimientoInventarioService.findAll();
        return ResponseEntity.ok().body(MovimientoInventarios);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventario> findOne(@PathVariable int id){
        Optional<MovimientoInventario> MovimientoInventario = MovimientoInventarioService.findOne(id);
        if(MovimientoInventario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MovimientoInventario.orElse(null));
    }

    @PostMapping
    public ResponseEntity<MovimientoInventario> save(@RequestBody MovimientoInventario MovimientoInventario){

        MovimientoInventario MovimientoInventarioNuevo = MovimientoInventarioService.save(MovimientoInventario);
        return ResponseEntity.ok(MovimientoInventarioNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoInventario> update(@PathVariable int id, @RequestBody MovimientoInventario MovimientoInventario){
        MovimientoInventario MovimientoInventarioActualizado = MovimientoInventarioService.update(id, MovimientoInventario);
        if (MovimientoInventarioActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MovimientoInventarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        MovimientoInventarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
