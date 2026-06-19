package com.projetoconfeitaria.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projetoconfeitaria.project.model.EmpresaCompradora;
import com.projetoconfeitaria.project.service.EmpresaCompradoraService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/compradora")
public class EmpresaCompradoraController {

    private final EmpresaCompradoraService empresaCompradoraService;

    public EmpresaCompradoraController(EmpresaCompradoraService empresaCompradoraService) {
        this.empresaCompradoraService = empresaCompradoraService;
    }

    @PostMapping
    public ResponseEntity<EmpresaCompradora> cadastrar(@RequestBody EmpresaCompradora empresa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCompradoraService.cadastrar(empresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaCompradora> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(empresaCompradoraService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaCompradora>> listarTodas() {
        return ResponseEntity.ok(empresaCompradoraService.listarTodas());
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<EmpresaCompradora>> listarAtivas() {
        return ResponseEntity.ok(empresaCompradoraService.listarAtivas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaCompradora> atualizar(@PathVariable UUID id, @RequestBody EmpresaCompradora empresa) {
        return ResponseEntity.ok(empresaCompradoraService.atualizar(id, empresa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        empresaCompradoraService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<EmpresaCompradora> buscarPorCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok(empresaCompradoraService.buscarPorCnpj(cnpj));
    }

    @PatchMapping("/{id}/limite-credito")
    public ResponseEntity<EmpresaCompradora> atualizarLimiteCredito(@PathVariable UUID id, @RequestBody Double novoLimite) {
        return ResponseEntity.ok(empresaCompradoraService.atualizarLimiteCredito(id, novoLimite));
    }

    @GetMapping("/{id}/limite-credito")
    public ResponseEntity<Double> consultarLimiteCredito(@PathVariable UUID id) {
        return ResponseEntity.ok(empresaCompradoraService.consultarLimiteCredito(id));
    }
}