package com.projetoconfeitaria.project.service;

import com.projetoconfeitaria.project.model.Pedido;
import com.projetoconfeitaria.project.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido criar(Pedido pedido) {
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus("PENDENTE");
        return pedidoRepository.save(pedido);
    }

    public Pedido buscarPorId(UUID id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> listarPorUsuario(UUID usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    public List<Pedido> listarPorStatus(String status) {
        return pedidoRepository.findByStatus(status);
    }

    public Pedido atualizarStatus(UUID id, String novoStatus) {
        Pedido pedido = buscarPorId(id);
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

    public void cancelar(UUID id) {
        atualizarStatus(id, "CANCELADO");
    }
}
