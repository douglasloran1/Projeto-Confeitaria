package com.projetoconfeitaria.project.service;

import org.springframework.stereotype.Service;

import com.projetoconfeitaria.project.enuns.CategoriaItem;
import com.projetoconfeitaria.project.model.ItemCatalogo;
import com.projetoconfeitaria.project.repository.ItemCatalogoRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ItemCatalogoService {

    private final ItemCatalogoRepository itemCatalogoRepository;
    private final EstoqueService estoqueService;

    public ItemCatalogoService(ItemCatalogoRepository itemCatalogoRepository, 
                               EstoqueService estoqueService) {
        this.itemCatalogoRepository = itemCatalogoRepository;
        this.estoqueService = estoqueService;
    }

    public ItemCatalogo cadastrar(ItemCatalogo item) {
        if (itemCatalogoRepository.existsByNome(item.getNome())) {
            throw new RuntimeException("Já existe um item com esse nome");
        }
        item.setAtivo(true);
        return itemCatalogoRepository.save(item);
    }

    public ItemCatalogo buscarPorId(UUID id) {
        return itemCatalogoRepository.findById(id).orElseThrow();
    }

    public List<ItemCatalogo> listarAtivos() {
        return itemCatalogoRepository.findByAtivo(true);
    }

    public List<ItemCatalogo> listarPorCategoria(CategoriaItem categoria) {
        return itemCatalogoRepository.findByCategoria(categoria);
    }

    public ItemCatalogo atualizar(UUID id, ItemCatalogo dadosNovos) {
        ItemCatalogo item = buscarPorId(id);
        item.setNome(dadosNovos.getNome());
        item.setDescricao(dadosNovos.getDescricao());
        item.setPrecoUnitario(dadosNovos.getPrecoUnitario());
        item.setUnidadeMedida(dadosNovos.getUnidadeMedida());
        item.setCategoria(dadosNovos.getCategoria());
        return itemCatalogoRepository.save(item);
    }

    public void desativar(UUID id) {
        ItemCatalogo item = buscarPorId(id);
        item.setAtivo(false);
        itemCatalogoRepository.save(item);
    }

    public boolean verificarDisponibilidade(UUID id, int quantidade) {
        ItemCatalogo item = buscarPorId(id);
        return item.verificarDisponibilidade(quantidade);
    }

    public double calcularPrecoTotal(UUID id, int quantidade) {
        ItemCatalogo item = buscarPorId(id);
        return item.calcularPrecoTotal(quantidade);
    }
}