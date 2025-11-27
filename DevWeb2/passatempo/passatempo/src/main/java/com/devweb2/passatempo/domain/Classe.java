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
@Table(name = "classe") // Boa prática adicionar
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double valor;
    private Integer prazoDevolucao; // Em dias (Comentário corrigido)

    @OneToMany(mappedBy = "classe")
    private Set<Titulo> titulos = new HashSet<>();

    // TODO: Remover todos os getters, setters e construtores manuais daqui
}