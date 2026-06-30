package com.projetoconfeitaria.project.model;   
import java.util.UUID;

import com.projetoconfeitaria.project.enuns.CategoriaItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_catalogo")
public class ItemCatalogo {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaItem categoria;

    @Column(nullable = false)
    private double precoUnitario;

    @Column(nullable = false)
    private String unidadeMedida;

    @Column(nullable = false)
    private int estoqueDisponivel;

    @Column(nullable = false)
    private int estoqueMinimo;

    @Column(nullable = false)
    private boolean ativo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;


    // Getters
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public CategoriaItem getCategoria() { return categoria; }
    public double getPrecoUnitario() { return precoUnitario; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public int getEstoqueDisponivel() { return estoqueDisponivel; }
    public int getEstoqueMinimo() { return estoqueMinimo; }
    public boolean isAtivo() { return ativo; }
    public Estoque getEstoque() { return estoque; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setCategoria(CategoriaItem categoria) { this.categoria = categoria; }
    public void setPrecoUnitario(double precoUnitario) { this.precoUnitario = precoUnitario; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }
    public void setEstoqueDisponivel(int estoqueDisponivel) { this.estoqueDisponivel = estoqueDisponivel; }
    public void setEstoqueMinimo(int estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public void setEstoque(Estoque estoque) { this.estoque = estoque; }

    public boolean verificarDisponibilidade(int quantidade){
        if(estoqueDisponivel > quantidade){
            return true;
        }
        return false;
    }

    public double calcularPrecoTotal(int quantidade){
        return precoUnitario * quantidade;
    }

}
