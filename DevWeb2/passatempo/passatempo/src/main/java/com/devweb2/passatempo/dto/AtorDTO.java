package com.devweb2.passatempo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// DTO (Data Transfer Object) para Ator
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtorDTO {
    private Long id;
    private String nome;

}