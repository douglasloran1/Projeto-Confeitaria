package com.projetoconfeitaria.project.controller;

import com.projetoconfeitaria.project.model.Cotacao;
import com.projetoconfeitaria.project.service.CotacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cotacoes")
public class CotacaoController {

    private final CotacaoService cotacaoService;

    public CotacaoController(CotacaoService cotacaoService) {
        this.cotacaoService = cotacaoService;
    }

    @PostMapping
    public ResponseEntity<Cotacao> criar(@RequestBody Cotacao cotacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cotacaoService.criar(cotacao));
    }

    @GetMapping
    public ResponseEntity<List<Cotacao>> listarTodas() {
        return ResponseEntity.ok(cotacaoService.listarTodas());
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<Cotacao>> listarAtivas() {
        return ResponseEntity.ok(cotacaoService.listarAtivas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cotacao> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(cotacaoService.buscarPorId(id));
    }

    @GetMapping("/fornecedor/{empresaFornecedoraId}")
    public ResponseEntity<List<Cotacao>> listarPorFornecedor(@PathVariable UUID empresaFornecedoraId) {
        return ResponseEntity.ok(cotacaoService.listarPorFornecedor(empresaFornecedoraId));
    }

    @GetMapping("/item/{itemCatalogoId}")
    public ResponseEntity<List<Cotacao>> listarPorItem(@PathVariable UUID itemCatalogoId) {
        return ResponseEntity.ok(cotacaoService.listarPorItem(itemCatalogoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cotacao> atualizar(@PathVariable UUID id, @RequestBody Cotacao cotacao) {
        return ResponseEntity.ok(cotacaoService.atualizar(id, cotacao));
    }

    @PatchMapping("/{id}/aprovar")
    public ResponseEntity<Void> aprovar(@PathVariable UUID id) {
        cotacaoService.aprovar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable UUID id) {
        cotacaoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
