package com.devweb2.passatempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
//Classe para recebimento detalhado com os objetos completos.
public class TituloResponseDTO {
    private Long id;
    private String nome;
    private String ano;
    private String sinopse;
    private ClasseDTO classe; // DTO aninhado
    private DiretorDTO diretor; // DTO aninhado
    private Set<AtorDTO> atores; // DTO aninhado
}