package com.devweb2.passatempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocacaoDTO {
    private Long id;
    private LocalDate dtLocacao;
    private LocalDate dtDevolucaoPrevista;
    private LocalDate dtDevolucaoEfetiva;
    private Double valorCobrado;
    private Double multaCobrada;

    // Estamos usando os DTOs completos conforme sua interface TypeScript pede.
    // Se 'ItemDTO' ou 'ClienteDTO' forem muito grandes, considere usar DTOs de resumo.
    private ItemDTO item;
    private ClienteDTO cliente;
}