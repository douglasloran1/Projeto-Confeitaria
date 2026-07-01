package com.projetoconfeitaria.project.controller;

import com.projetoconfeitaria.project.enuns.Role;
import com.projetoconfeitaria.project.model.Estoque;
import com.projetoconfeitaria.project.repository.EstoqueRepository;
import com.projetoconfeitaria.project.security.RequireRole;
import com.projetoconfeitaria.project.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;
    private final EstoqueRepository estoqueRepository;

    public EstoqueController(EstoqueService estoqueService, EstoqueRepository estoqueRepository) {
        this.estoqueService = estoqueService;
        this.estoqueRepository = estoqueRepository;
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> listarTodos() {
        return ResponseEntity.ok(estoqueRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado.")));
    }

    @GetMapping("/baixo")
    public ResponseEntity<List<Estoque>> listarComEstoqueBaixo() {
        return ResponseEntity.ok(estoqueRepository.findByQuantidadeAtualLessThanEqual(0));
    }

    @PatchMapping("/{id}/atualizar")
    @RequireRole({ Role.ADMIN, Role.FUNCIONARIO })
    public ResponseEntity<Void> atualizar(@PathVariable UUID id, @RequestParam int quantidade) {
        estoqueService.atualizar(id, quantidade);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reservar")
    @RequireRole({ Role.ADMIN, Role.FUNCIONARIO })
    public ResponseEntity<Boolean> reservar(@PathVariable UUID id, @RequestParam int quantidade) {
        return ResponseEntity.ok(estoqueService.reservar(id, quantidade));
    }

    @PatchMapping("/{id}/liberar")
    @RequireRole({ Role.ADMIN, Role.FUNCIONARIO })
    public ResponseEntity<Void> liberarReserva(@PathVariable UUID id, @RequestParam int quantidade) {
        estoqueService.liberarReserva(id, quantidade);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/minimo")
    public ResponseEntity<Boolean> verificarMinimo(@PathVariable UUID id) {
        return ResponseEntity.ok(estoqueService.verificarMinimo(id));
    }
}
