package com.projetoconfeitaria.project.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoconfeitaria.project.Model.Produto;

public interface PedidoRepository extends JpaRepository<Produto, UUID>  {

    
}  
    

