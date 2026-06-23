package com.projetoconfeitaria.project.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "empresa_fornecedora")
public class EmpresaFornecedora extends Empresa {

    @Column(nullable = false)
    private int capacidadeProducao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa_fornecedora_id")
    private List<ItemCatalogo> catalogo;

    public EmpresaFornecedora() {}

    // Getters
    public int getCapacidadeProducao() { return capacidadeProducao; }
    public List<ItemCatalogo> getCatalogo() { return catalogo; }

    // Setters
    public void setCapacidadeProducao(int capacidadeProducao) { this.capacidadeProducao = capacidadeProducao; }
    public void setCatalogo(List<ItemCatalogo> catalogo) { this.catalogo = catalogo; }
}