package com.projetoconfeitaria.project.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name = "estoque")

public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private int quantidadeAtual;

    @Column(nullable = false)
    private int estoqueMinimo;

    @Column(nullable = false)
    private LocalDateTime ultimaAtualizacao;

    public Estoque(){

    }

    // getters
    public UUID getId() {
        return id;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }


    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    // setters
    
    public void atualizar(int quantidade){
        this.quantidadeAtual += quantidade;
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
    

    public boolean reservar(int quantidade){
        if(quantidadeAtual >= quantidade){
            quantidadeAtual -= quantidade;
            return true;
        }
        return false;
    }

    public void liberarReserva(int quantidade){
        quantidadeAtual += quantidade;
    }

    public boolean verificarMinimo(){
        return this.quantidadeAtual <= this.estoqueMinimo;
    }

}
