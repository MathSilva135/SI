package com.devweb2.passatempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO padrão para Diretor (leitura e escrita).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiretorDTO {
    private Long id;
    private String nome;
}