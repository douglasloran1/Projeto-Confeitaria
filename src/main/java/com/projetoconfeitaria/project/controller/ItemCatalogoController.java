package com.projetoconfeitaria.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projetoconfeitaria.project.enuns.CategoriaItem;
import com.projetoconfeitaria.project.model.ItemCatalogo;
import com.projetoconfeitaria.project.service.ItemCatalogoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/catalogo")
public class ItemCatalogoController {

    private final ItemCatalogoService itemCatalogoService;

    public ItemCatalogoController(ItemCatalogoService itemCatalogoService) {
        this.itemCatalogoService = itemCatalogoService;
    }

    @GetMapping
    public ResponseEntity<List<ItemCatalogo>> listarAtivos() {
        return ResponseEntity.ok(itemCatalogoService.listarAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCatalogo> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(itemCatalogoService.buscarPorId(id));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ItemCatalogo>> listarPorCategoria(@PathVariable CategoriaItem categoria) {
        return ResponseEntity.ok(itemCatalogoService.listarPorCategoria(categoria));
    }

    @PostMapping
    public ResponseEntity<ItemCatalogo> cadastrar(@RequestBody ItemCatalogo item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemCatalogoService.cadastrar(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCatalogo> atualizar(@PathVariable UUID id, @RequestBody ItemCatalogo item) {
        return ResponseEntity.ok(itemCatalogoService.atualizar(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        itemCatalogoService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/disponibilidade")
    public ResponseEntity<Boolean> verificarDisponibilidade(@PathVariable UUID id, @RequestParam int quantidade) {
        return ResponseEntity.ok(itemCatalogoService.verificarDisponibilidade(id, quantidade));
    }

    @GetMapping("/{id}/preco")
    public ResponseEntity<Double> calcularPrecoTotal(@PathVariable UUID id, @RequestParam int quantidade) {
        return ResponseEntity.ok(itemCatalogoService.calcularPrecoTotal(id, quantidade));
    }
}