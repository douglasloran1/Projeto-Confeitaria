package com.projetoconfeitaria.project.controller;

import com.projetoconfeitaria.project.model.EmpresaFornecedora;
import com.projetoconfeitaria.project.service.EmpresaFornecedoraService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/empresas-fornecedoras")
public class EmpresaFornecedoraController {

    private final EmpresaFornecedoraService empresaFornecedoraService;

    public EmpresaFornecedoraController(
            EmpresaFornecedoraService empresaFornecedoraService) {

        this.empresaFornecedoraService = empresaFornecedoraService;
    }

    @PostMapping
    public EmpresaFornecedora cadastrar(
            @RequestBody EmpresaFornecedora empresa) {

        return empresaFornecedoraService.cadastrar(empresa);
    }

    @GetMapping
    public List<EmpresaFornecedora> listarTodas() {

        return empresaFornecedoraService.listarTodas();
    }

    @GetMapping("/ativas")
    public List<EmpresaFornecedora> listarAtivas() {

        return empresaFornecedoraService.listarAtivas();
    }

    @GetMapping("/{id}")
    public EmpresaFornecedora buscarPorId(
            @PathVariable UUID id) {

        return empresaFornecedoraService.buscarPorId(id);
    }

    @GetMapping("/cnpj/{cnpj}")
    public EmpresaFornecedora buscarPorCnpj(
            @PathVariable String cnpj) {

        return empresaFornecedoraService.buscarPorCnpj(cnpj);
    }

    @PutMapping("/{id}")
    public EmpresaFornecedora atualizar(
            @PathVariable UUID id,
            @RequestBody EmpresaFornecedora empresa) {

        return empresaFornecedoraService.atualizar(id, empresa);
    }

    @PatchMapping("/{id}/capacidade")
    public EmpresaFornecedora atualizarCapacidade(
            @PathVariable UUID id,
            @RequestParam int capacidade) {

        return empresaFornecedoraService.atualizarCapacidade(id, capacidade);
    }

    @PatchMapping("/{id}/desativar")
    public void desativar(
            @PathVariable UUID id) {

        empresaFornecedoraService.desativar(id);
    }

    @PatchMapping("/{id}/reativar")
    public void reativar(
            @PathVariable UUID id) {

        empresaFornecedoraService.reativar(id);
    }
}
