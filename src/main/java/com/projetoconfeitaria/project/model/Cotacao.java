package com.projetoconfeitaria.project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cotacao")
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "empresa_fornecedora_id", nullable = false)
    private EmpresaFornecedora empresaFornecedora;

    @ManyToOne
    @JoinColumn(name = "item_catalogo_id", nullable = false)
    private ItemCatalogo itemCatalogo;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private double precoOfertado;

    @Column(nullable = false)
    private LocalDateTime dataValidade;

    @Column(nullable = false)
    private String status;

    public Cotacao() {}

    public UUID getId() { return id; }
    public EmpresaFornecedora getEmpresaFornecedora() { return empresaFornecedora; }
    public ItemCatalogo getItemCatalogo() { return itemCatalogo; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoOfertado() { return precoOfertado; }
    public LocalDateTime getDataValidade() { return dataValidade; }
    public String getStatus() { return status; }

    public void setEmpresaFornecedora(EmpresaFornecedora empresaFornecedora) { this.empresaFornecedora = empresaFornecedora; }
    public void setItemCatalogo(ItemCatalogo itemCatalogo) { this.itemCatalogo = itemCatalogo; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setPrecoOfertado(double precoOfertado) { this.precoOfertado = precoOfertado; }
    public void setDataValidade(LocalDateTime dataValidade) { this.dataValidade = dataValidade; }
    public void setStatus(String status) { this.status = status; }

    public boolean isVigente() {
        return LocalDateTime.now().isBefore(dataValidade) && "ATIVA".equals(status);
    }
}
