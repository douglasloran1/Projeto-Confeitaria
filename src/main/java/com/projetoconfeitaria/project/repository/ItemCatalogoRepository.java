package com.projetoconfeitaria.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoconfeitaria.project.enuns.CategoriaItem;
import com.projetoconfeitaria.project.model.ItemCatalogo;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemCatalogoRepository extends JpaRepository<ItemCatalogo, UUID> {

    List<ItemCatalogo> findByAtivo(boolean ativo);

    List<ItemCatalogo> findByCategoria(CategoriaItem categoria);

    boolean existsByNome(String nome);
}
