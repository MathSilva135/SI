package com.devweb2.passatempo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "titulo")
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    private String ano;

    @Column(columnDefinition = "TEXT")
    private String sinopse;
    private String categoria;

    // Relacionamento: Muitos Títulos para UMA Classe
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    // Relacionamento: Muitos Títulos para UM Diretor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diretor_id", nullable = false)
    private Diretor diretor;

    // Relacionamento: Um Título para Muitos Itens
    @OneToMany(mappedBy = "titulo")
    private Set<Item> itens = new HashSet<>();

    // Relacionamento: Muitos Títulos para Muitos Atores
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "titulo_ator",
            joinColumns = @JoinColumn(name = "titulo_id"),
            inverseJoinColumns = @JoinColumn(name = "ator_id")
    )
    private Set<Ator> atores = new HashSet<>();
}