package com.devweb2.passatempo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Anotações do Lombok
@Data // Cria Getters, Setters, equals, hashCode e toString
@NoArgsConstructor // Cria um construtor vazio
@AllArgsConstructor // Cria um construtor com todos os campos
@Entity
@Table(name = "ator") // Boa prática especificar o nome da tabela
public class Ator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // Não precisamos mais de getters e setters manuais!
    // O Lombok cuida disso.
}