package com.projetoconfeitaria.project.service;

import org.springframework.stereotype.Service;

import com.projetoconfeitaria.project.model.Estoque;
import com.projetoconfeitaria.project.repository.EstoqueRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public void atualizar(UUID id, int quantidade) {
        Estoque estoque = estoqueRepository.findById(id).orElseThrow();
        estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() + quantidade);
        estoque.setUltimaAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoque);
    }

    public boolean reservar(UUID id, int quantidade) {
        Estoque estoque = estoqueRepository.findById(id).orElseThrow();
        if (estoque.getQuantidadeAtual() >= quantidade) {
            estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() - quantidade);
            estoque.setUltimaAtualizacao(LocalDateTime.now());
            estoqueRepository.save(estoque);
            return true;
        }
        return false;
    }

    public void liberarReserva(UUID id, int quantidade) {
        Estoque estoque = estoqueRepository.findById(id).orElseThrow();
        estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() + quantidade);
        estoque.setUltimaAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoque);
    }

    public boolean verificarMinimo(UUID id) {
        Estoque estoque = estoqueRepository.findById(id).orElseThrow();
        return estoque.getQuantidadeAtual() <= estoque.getEstoqueMinimo();
    }

    public void gerarAlertaEstoque() {
        estoqueRepository.findByQuantidadeAtualLessThanEqual(0)
            .forEach(e -> System.out.println("Alerta: estoque zerado para id " + e.getId()));
    }
}