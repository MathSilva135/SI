package com.devweb2.passatempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO padrão para Item (leitura e escrita).
 * Usamos 'tituloId' para representar o relacionamento com Titulo.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String numSerie;
    private LocalDate dtAquisicao;
    private String tipoItem;
    private Long tituloId; // ID para o relacionamento
}