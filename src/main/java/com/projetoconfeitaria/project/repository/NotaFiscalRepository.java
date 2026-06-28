package com.projetoconfeitaria.project.repository;

import com.projetoconfeitaria.project.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, UUID> {

    Optional<NotaFiscal> findByNumero(String numero);

    Optional<NotaFiscal> findByPedidoId(UUID pedidoId);

    List<NotaFiscal> findByStatus(String status);
}
