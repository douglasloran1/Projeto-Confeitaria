package com.projetoconfeitaria.project.controller;

import com.projetoconfeitaria.project.model.NotaFiscal;
import com.projetoconfeitaria.project.service.NotaFiscalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notas-fiscais")
public class NotaFiscalController {

    private final NotaFiscalService notaFiscalService;

    public NotaFiscalController(NotaFiscalService notaFiscalService) {
        this.notaFiscalService = notaFiscalService;
    }

    @PostMapping
    public ResponseEntity<NotaFiscal> emitir(@RequestBody NotaFiscal notaFiscal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notaFiscalService.emitir(notaFiscal));
    }

    @GetMapping
    public ResponseEntity<List<NotaFiscal>> listarTodas() {
        return ResponseEntity.ok(notaFiscalService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaFiscal> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(notaFiscalService.buscarPorId(id));
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<NotaFiscal> buscarPorNumero(@PathVariable String numero) {
        return ResponseEntity.ok(notaFiscalService.buscarPorNumero(numero));
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<NotaFiscal> buscarPorPedido(@PathVariable UUID pedidoId) {
        return ResponseEntity.ok(notaFiscalService.buscarPorPedido(pedidoId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<NotaFiscal>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(notaFiscalService.listarPorStatus(status));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable UUID id) {
        notaFiscalService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
