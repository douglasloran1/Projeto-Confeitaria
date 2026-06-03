package com.projetoconfeitaria.project.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pedidos")
public class Pedidos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    
    private String cliente;
    @ManyToMany 
    @JoinTable(
        name = "pedidos_Produtos", //nome da tabela que sera a intermediação entre os dois
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn( name = "produtos_id")
     )
    private List<Produto> itemPedido;



}
    