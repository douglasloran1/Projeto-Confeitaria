package com.projetoconfeitaria.project.service;

import com.projetoconfeitaria.project.model.EmpresaFornecedora;
import com.projetoconfeitaria.project.repository.EmpresaFornecedoraRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmpresaFornecedoraService {

    private final EmpresaFornecedoraRepository empresaFornecedoraRepository;

    public EmpresaFornecedoraService(
            EmpresaFornecedoraRepository empresaFornecedoraRepository) {

        this.empresaFornecedoraRepository = empresaFornecedoraRepository;
    }

    public EmpresaFornecedora cadastrar(EmpresaFornecedora empresa) {

        if (empresaFornecedoraRepository.findByCnpj(empresa.getCnpj()).isPresent()) {
            throw new RuntimeException("Já existe uma empresa cadastrada com este CNPJ.");
        }

        if (empresaFornecedoraRepository.findByEmail(empresa.getEmail()).isPresent()) {
            throw new RuntimeException("Já existe uma empresa cadastrada com este e-mail.");
        }

        empresa.setAtivo(true);

        return empresaFornecedoraRepository.save(empresa);
    }

    public EmpresaFornecedora buscarPorId(UUID id) {

        return empresaFornecedoraRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Empresa fornecedora não encontrada."));
    }

    public List<EmpresaFornecedora> listarTodas() {
        return empresaFornecedoraRepository.findAll();
    }

    public List<EmpresaFornecedora> listarAtivas() {
        return empresaFornecedoraRepository.findByAtivo(true);
    }

    public EmpresaFornecedora atualizar(UUID id, EmpresaFornecedora dadosAtualizados) {

        EmpresaFornecedora empresa = buscarPorId(id);

        empresa.setRazaoSocial(dadosAtualizados.getRazaoSocial());
        empresa.setEmail(dadosAtualizados.getEmail());
        empresa.setTelefone(dadosAtualizados.getTelefone());
        empresa.setEndereco(dadosAtualizados.getEndereco());
        empresa.setCapacidadeProducao(dadosAtualizados.getCapacidadeProducao());

        return empresaFornecedoraRepository.save(empresa);
    }

    public void desativar(UUID id) {

        EmpresaFornecedora empresa = buscarPorId(id);

        empresa.setAtivo(false);

        empresaFornecedoraRepository.save(empresa);
    }

    public void reativar(UUID id) {

        EmpresaFornecedora empresa = buscarPorId(id);

        empresa.setAtivo(true);

        empresaFornecedoraRepository.save(empresa);
    }

    public EmpresaFornecedora atualizarCapacidade(UUID id, int novaCapacidade) {

        EmpresaFornecedora empresa = buscarPorId(id);

        empresa.setCapacidadeProducao(novaCapacidade);

        return empresaFornecedoraRepository.save(empresa);
    }

    public EmpresaFornecedora buscarPorCnpj(String cnpj) {

        return empresaFornecedoraRepository.findByCnpj(cnpj)
                .orElseThrow(() ->
                        new RuntimeException("Empresa não encontrada."));
    }
}
