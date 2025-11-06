package com.devweb2.passatempo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private Integer numInscricao;
    private LocalDate dtNascimento;
    private String sexo;
    private Boolean estahAtivo;
    private String cpf;
    private String endereco;
    private String tel;

    // --- Relacionamentos (usando DTO de Resumo) ---
    private ClienteResumoDTO socio;
    private List<ClienteResumoDTO> dependentes;
}