package com.projetoconfeitaria.project.repository;

import com.projetoconfeitaria.project.model.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, UUID> {

    List<Cotacao> findByEmpresaFornecedoraId(UUID empresaFornecedoraId);

    List<Cotacao> findByItemCatalogoId(UUID itemCatalogoId);

    List<Cotacao> findByStatus(String status);
}
