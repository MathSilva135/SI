package com.devweb2.passatempo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double valor;
    private Integer prazoDevolucao; // Em dias

    // Construtores, Getters e Setters

    public Classe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getPrazoDevolucao() {
        return prazoDevolucao;
    }

    public void setPrazoDevolucao(Integer prazoDevolucao) {
        this.prazoDevolucao = prazoDevolucao;
    }
}