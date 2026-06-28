package com.projetoconfeitaria.project.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "item_catalogo_id", nullable = false)
    private ItemCatalogo itemCatalogo;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private double precoUnitario;

    public ItemPedido() {}

    public UUID getId() { return id; }
    public Pedido getPedido() { return pedido; }
    public ItemCatalogo getItemCatalogo() { return itemCatalogo; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }

    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public void setItemCatalogo(ItemCatalogo itemCatalogo) { this.itemCatalogo = itemCatalogo; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setPrecoUnitario(double precoUnitario) { this.precoUnitario = precoUnitario; }

    public double calcularSubtotal() {
        return precoUnitario * quantidade;
    }
}
