package com.devweb2.passatempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO para Socio.
 * Inclui os campos da classe pai (Cliente) e os campos próprios.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocioDTO {

    // Campos herdados de Cliente
    private Long id;
    private String numInscricao;
    private String nome;
    private LocalDate dtNascimento;
    private String sexo;
    private Boolean estahAtivo;

    // Campos próprios de Socio
    private String cpf;
    private String endereco;
    private String tel;

    // Não incluímos a lista de dependentes aqui para evitar loops.
}