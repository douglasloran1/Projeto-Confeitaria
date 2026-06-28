package com.projetoconfeitaria.project.service;

import com.projetoconfeitaria.project.model.NotaFiscal;
import com.projetoconfeitaria.project.repository.NotaFiscalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NotaFiscalService {

    private final NotaFiscalRepository notaFiscalRepository;

    public NotaFiscalService(NotaFiscalRepository notaFiscalRepository) {
        this.notaFiscalRepository = notaFiscalRepository;
    }

    public NotaFiscal emitir(NotaFiscal notaFiscal) {
        if (notaFiscalRepository.findByPedidoId(notaFiscal.getPedido().getId()).isPresent()) {
            throw new RuntimeException("Já existe uma nota fiscal para este pedido.");
        }
        notaFiscal.setDataEmissao(LocalDateTime.now());
        notaFiscal.setValorTotal(notaFiscal.getPedido().calcularTotal());
        notaFiscal.setStatus("EMITIDA");
        return notaFiscalRepository.save(notaFiscal);
    }

    public NotaFiscal buscarPorId(UUID id) {
        return notaFiscalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota fiscal não encontrada."));
    }

    public NotaFiscal buscarPorNumero(String numero) {
        return notaFiscalRepository.findByNumero(numero)
                .orElseThrow(() -> new RuntimeException("Nota fiscal não encontrada."));
    }

    public NotaFiscal buscarPorPedido(UUID pedidoId) {
        return notaFiscalRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new RuntimeException("Nota fiscal não encontrada para este pedido."));
    }

    public List<NotaFiscal> listarTodas() {
        return notaFiscalRepository.findAll();
    }

    public List<NotaFiscal> listarPorStatus(String status) {
        return notaFiscalRepository.findByStatus(status);
    }

    public void cancelar(UUID id) {
        NotaFiscal nota = buscarPorId(id);
        nota.setStatus("CANCELADA");
        notaFiscalRepository.save(nota);
    }
}
