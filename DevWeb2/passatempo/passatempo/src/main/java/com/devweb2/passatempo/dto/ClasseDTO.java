package com.devweb2.passatempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO padrão para Classe (leitura e escrita).
 * Removemos a lista de 'titulos' para evitar referência circular.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseDTO {
    private Long id;
    private String nome;
    private Double valor;
    private Integer prazoDevolucao;
}