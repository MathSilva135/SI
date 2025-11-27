package com.devweb2.passatempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * DTO para RETORNAR um Título.
 * Expõe os relacionamentos como DTOs aninhados (ClasseDTO, DiretorDTO, AtorDTO).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TituloResponseDTO {
    private Long id;
    private String nome;
    private String ano;
    private String categoria;
    private String sinopse;
    private ClasseDTO classe; // DTO aninhado
    private DiretorDTO diretor; // DTO aninhado
    private Set<AtorDTO> atores; // DTO aninhado
}