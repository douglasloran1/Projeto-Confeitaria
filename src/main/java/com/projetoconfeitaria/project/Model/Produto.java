package com.projetoconfeitaria.project.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToMany(mappedBy = "itemPedido")
    private List<Pedidos> pedidos;
    private String name;
    private LocalDate dataFabricado;
    private LocalDate validade;

    

}
