package com.devweb2.passatempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
//Classe pra envio de dados simples, apenas id, para criação do Titulo.
public class TituloCreateDTO {
    private String nome;
    private String ano;
    private String sinopse;
    private String categoria;
    private Long classeId; // Apenas o ID
    private Long diretorId; // Apenas o ID
    private Set<Long> atorIds; // Apenas os IDs
}