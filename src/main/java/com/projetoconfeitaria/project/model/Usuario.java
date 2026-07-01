package com.projetoconfeitaria.project.model;

import jakarta.persistence.*;
import java.util.UUID;
import com.projetoconfeitaria.project.enuns.Role;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean ativo;

    public Usuario() {}

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getTelefone() { return telefone; }
    public Role getRole() { return role; }
    public boolean isAtivo() { return ativo; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setRole(Role role) { this.role = role; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public boolean isAdmin() { return Role.ADMIN.equals(this.role); }
}
