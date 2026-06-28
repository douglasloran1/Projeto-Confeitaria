package com.projetoconfeitaria.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoconfeitaria.project.model.Estoque;

import java.util.List;
import java.util.UUID;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, UUID> {

    List<Estoque> findByQuantidadeAtualLessThanEqual(int quantidade);
}
