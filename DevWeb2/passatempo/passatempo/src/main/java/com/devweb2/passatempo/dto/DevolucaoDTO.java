package com.devweb2.passatempo.dto;

import lombok.Data;

@Data
public class DevolucaoDTO {
    private Long numSerieItem;
    private Double multaCobrada; // Novo campo: Valor da multa informado pelo usuário
}