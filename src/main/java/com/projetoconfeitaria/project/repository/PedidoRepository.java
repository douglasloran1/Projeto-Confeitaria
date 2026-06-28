package com.projetoconfeitaria.project.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoconfeitaria.project.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    List<Pedido> findByUsuarioId(UUID usuarioId);

    List<Pedido> findByStatus(String status);
}