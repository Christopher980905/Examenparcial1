package com.distribuida.controller;

import com.distribuida.model.Categoria;
import com.distribuida.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")

public class CategoriaController {

    @Autowired
    private CategoriaService CategoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(){
        List<Categoria> Categorias = CategoriaService.findAll();
        return ResponseEntity.ok().body(Categorias);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findOne(@PathVariable int id){
        Optional<Categoria> Categoria = CategoriaService.findOne(id);
        if(Categoria == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Categoria.orElse(null));
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria Categoria){

        Categoria CategoriaNuevo = CategoriaService.save(Categoria);
        return ResponseEntity.ok(CategoriaNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable int id, @RequestBody Categoria Categoria){
        Categoria CategoriaActualizado = CategoriaService.update(id, Categoria);
        if (CategoriaActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CategoriaActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        CategoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
