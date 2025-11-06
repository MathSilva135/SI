package com.devweb2.passatempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de Resumo. Usado para representar um Cliente
 * dentro de outro DTO, evitando recursão infinita.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResumoDTO {
    private Long id;
    private String nome;
    private Integer numInscricao;
}
