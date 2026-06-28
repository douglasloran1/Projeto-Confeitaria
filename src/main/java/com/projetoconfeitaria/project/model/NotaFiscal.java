package com.projetoconfeitaria.project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "pedido_id", nullable = false, unique = true)
    private Pedido pedido;

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false)
    private LocalDateTime dataEmissao;

    @Column(nullable = false)
    private double valorTotal;

    @Column(nullable = false)
    private String status;

    public NotaFiscal() {}

    public UUID getId() { return id; }
    public Pedido getPedido() { return pedido; }
    public String getNumero() { return numero; }
    public LocalDateTime getDataEmissao() { return dataEmissao; }
    public double getValorTotal() { return valorTotal; }
    public String getStatus() { return status; }

    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public void setNumero(String numero) { this.numero = numero; }
    public void setDataEmissao(LocalDateTime dataEmissao) { this.dataEmissao = dataEmissao; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    public void setStatus(String status) { this.status = status; }
}
