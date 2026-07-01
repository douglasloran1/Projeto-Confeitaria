package com.projetoconfeitaria.project.controller;

import com.projetoconfeitaria.project.enuns.Role;
import com.projetoconfeitaria.project.model.EmpresaFornecedora;
import com.projetoconfeitaria.project.security.RequireRole;
import com.projetoconfeitaria.project.service.EmpresaFornecedoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/empresas-fornecedoras")
public class EmpresaFornecedoraController {

    private final EmpresaFornecedoraService empresaFornecedoraService;

    public EmpresaFornecedoraController(EmpresaFornecedoraService empresaFornecedoraService) {
        this.empresaFornecedoraService = empresaFornecedoraService;
    }

    @PostMapping
    @RequireRole({ Role.ADMIN, Role.FUNCIONARIO })
    public ResponseEntity<EmpresaFornecedora> cadastrar(@RequestBody EmpresaFornecedora empresa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaFornecedoraService.cadastrar(empresa));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaFornecedora>> listarTodas() {
        return ResponseEntity.ok(empresaFornecedoraService.listarTodas());
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<EmpresaFornecedora>> listarAtivas() {
        return ResponseEntity.ok(empresaFornecedoraService.listarAtivas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaFornecedora> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(empresaFornecedoraService.buscarPorId(id));
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<EmpresaFornecedora> buscarPorCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok(empresaFornecedoraService.buscarPorCnpj(cnpj));
    }

    @PutMapping("/{id}")
    @RequireRole({ Role.ADMIN, Role.FUNCIONARIO })
    public ResponseEntity<EmpresaFornecedora> atualizar(@PathVariable UUID id, @RequestBody EmpresaFornecedora empresa) {
        return ResponseEntity.ok(empresaFornecedoraService.atualizar(id, empresa));
    }

    @PatchMapping("/{id}/capacidade")
    @RequireRole({ Role.ADMIN, Role.FUNCIONARIO })
    public ResponseEntity<EmpresaFornecedora> atualizarCapacidade(@PathVariable UUID id, @RequestParam int capacidade) {
        return ResponseEntity.ok(empresaFornecedoraService.atualizarCapacidade(id, capacidade));
    }

    @PatchMapping("/{id}/desativar")
    @RequireRole(Role.ADMIN)
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        empresaFornecedoraService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reativar")
    @RequireRole(Role.ADMIN)
    public ResponseEntity<Void> reativar(@PathVariable UUID id) {
        empresaFornecedoraService.reativar(id);
        return ResponseEntity.noContent().build();
    }
}
