package com.devweb2.passatempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * DTO para CRIAR um novo Título.
 * Recebe apenas os IDs dos relacionamentos, que o Service irá processar.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TituloCreateDTO {
    private String nome;
    private String ano;
    private String sinopse;
    private String categoria;
    private Long classeId; // Apenas o ID
    private Long diretorId; // Apenas o ID
    private Set<Long> atorIds; // Apenas os IDs
}