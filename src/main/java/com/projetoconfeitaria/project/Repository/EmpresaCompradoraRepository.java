package com.projetoconfeitaria.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoconfeitaria.project.model.EmpresaCompradora;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmpresaCompradoraRepository extends JpaRepository<EmpresaCompradora, UUID> {

    Optional<EmpresaCompradora> findByCnpj(String cnpj);

    Optional<EmpresaCompradora> findByEmail(String email);

    List<EmpresaCompradora> findByAtivo(boolean ativo);

    List<EmpresaCompradora> findByLimiteCreditoGreaterThan(Double limite);
}