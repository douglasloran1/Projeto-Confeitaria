package com.projetoconfeitaria.project.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoconfeitaria.project.Model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}