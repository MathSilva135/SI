package com.devweb2.passatempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseDTO {
    private Long id;
    private String nome;
    private Double valor;
    private Integer prazoDevolucao;
}