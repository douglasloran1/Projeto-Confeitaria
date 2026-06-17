package com.projetoconfeitaria.project.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoconfeitaria.project.model.Produto;

public interface PedidoRepository extends JpaRepository<Produto, UUID>  {

    
}  
    

