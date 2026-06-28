package com.projetoconfeitaria.project.service;

import com.projetoconfeitaria.project.model.Cotacao;
import com.projetoconfeitaria.project.repository.CotacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CotacaoService {

    private final CotacaoRepository cotacaoRepository;

    public CotacaoService(CotacaoRepository cotacaoRepository) {
        this.cotacaoRepository = cotacaoRepository;
    }

    public Cotacao criar(Cotacao cotacao) {
        cotacao.setStatus("ATIVA");
        return cotacaoRepository.save(cotacao);
    }

    public Cotacao buscarPorId(UUID id) {
        return cotacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cotação não encontrada."));
    }

    public List<Cotacao> listarTodas() {
        return cotacaoRepository.findAll();
    }

    public List<Cotacao> listarPorFornecedor(UUID empresaFornecedoraId) {
        return cotacaoRepository.findByEmpresaFornecedoraId(empresaFornecedoraId);
    }

    public List<Cotacao> listarPorItem(UUID itemCatalogoId) {
        return cotacaoRepository.findByItemCatalogoId(itemCatalogoId);
    }

    public List<Cotacao> listarAtivas() {
        return cotacaoRepository.findByStatus("ATIVA");
    }

    public Cotacao atualizar(UUID id, Cotacao dadosAtualizados) {
        Cotacao cotacao = buscarPorId(id);
        cotacao.setQuantidade(dadosAtualizados.getQuantidade());
        cotacao.setPrecoOfertado(dadosAtualizados.getPrecoOfertado());
        cotacao.setDataValidade(dadosAtualizados.getDataValidade());
        return cotacaoRepository.save(cotacao);
    }

    public void cancelar(UUID id) {
        Cotacao cotacao = buscarPorId(id);
        cotacao.setStatus("CANCELADA");
        cotacaoRepository.save(cotacao);
    }

    public void aprovar(UUID id) {
        Cotacao cotacao = buscarPorId(id);
        cotacao.setStatus("APROVADA");
        cotacaoRepository.save(cotacao);
    }
}
