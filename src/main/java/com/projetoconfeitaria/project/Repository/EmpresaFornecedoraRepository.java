package com.projetoconfeitaria.project.repository;

import com.projetoconfeitaria.project.model.EmpresaFornecedora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmpresaFornecedoraRepository extends JpaRepository<EmpresaFornecedora, UUID> {

    Optional<EmpresaFornecedora> findByCnpj(String cnpj);

    Optional<EmpresaFornecedora> findByEmail(String email);

    List<EmpresaFornecedora> findByAtivo(boolean ativo);

    List<EmpresaFornecedora> findByCapacidadeProducaoGreaterThan(int capacidade);

    List<EmpresaFornecedora> findByRazaoSocialContainingIgnoreCase(String razaoSocial);
}