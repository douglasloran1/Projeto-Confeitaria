package com.projetoconfeitaria.project.service;

import org.springframework.stereotype.Service;

import com.projetoconfeitaria.project.model.EmpresaCompradora;
import com.projetoconfeitaria.project.repository.EmpresaCompradoraRepository;

import java.util.List;
import java.util.UUID;

@Service
public class EmpresaCompradoraService {

    private final EmpresaCompradoraRepository empresaCompradoraRepository;

    public EmpresaCompradoraService(EmpresaCompradoraRepository empresaCompradoraRepository) {
        this.empresaCompradoraRepository = empresaCompradoraRepository;
    }

    public EmpresaCompradora cadastrar(EmpresaCompradora empresa) {
        if (empresaCompradoraRepository.findByCnpj(empresa.getCnpj()).isPresent()) {
            throw new RuntimeException("Já existe uma empresa cadastrada com este CNPJ.");
        }

        if (empresaCompradoraRepository.findByEmail(empresa.getEmail()).isPresent()) {
            throw new RuntimeException("Já existe uma empresa cadastrada com este e-mail.");
        }

        empresa.setAtivo(true);
        return empresaCompradoraRepository.save(empresa);
    }

    public EmpresaCompradora buscarPorId(UUID id) {
        return empresaCompradoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa compradora não encontrada."));
    }

    public List<EmpresaCompradora> listarTodas() {
        return empresaCompradoraRepository.findAll();
    }

    public List<EmpresaCompradora> listarAtivas() {
        return empresaCompradoraRepository.findByAtivo(true);
    }

    public EmpresaCompradora atualizar(UUID id, EmpresaCompradora dadosAtualizados) {
        EmpresaCompradora empresa = buscarPorId(id);
        empresa.setRazaoSocial(dadosAtualizados.getRazaoSocial());
        empresa.setEmail(dadosAtualizados.getEmail());
        empresa.setTelefone(dadosAtualizados.getTelefone());
        empresa.setEndereco(dadosAtualizados.getEndereco());
        empresa.setLimiteCredito(dadosAtualizados.getLimiteCredito());
        return empresaCompradoraRepository.save(empresa);
    }

    public void desativar(UUID id) {
        EmpresaCompradora empresa = buscarPorId(id);
        empresa.setAtivo(false);
        empresaCompradoraRepository.save(empresa);
    }

    public EmpresaCompradora buscarPorCnpj(String cnpj) {
        return empresaCompradoraRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada."));
    }

    public EmpresaCompradora atualizarLimiteCredito(UUID id, Double novoLimite) {
        EmpresaCompradora empresa = buscarPorId(id);
        empresa.setLimiteCredito(novoLimite);
        return empresaCompradoraRepository.save(empresa);
    }

    public Double consultarLimiteCredito(UUID id) {
        return buscarPorId(id).getLimiteCredito();
    }
}
