package com.devweb2.passatempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DependenteDTO {

    // Campos herdados de Cliente
    private Long id;
    private String numInscricao;
    private String nome;
    private LocalDate dtNascimento;
    private String sexo;
    private Boolean estahAtivo;

    // Relacionamento com Socio
    private Long socioId;
}