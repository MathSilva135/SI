package com.devweb2.passatempo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
@Inheritance(strategy = InheritanceType.JOINED) // Estratégia de Herança
public abstract class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numInscricao;
    @Column(nullable = false)
    private String nome;
    private LocalDate dtNascimento;
    private String sexo;
    private Boolean estahAtivo;
}