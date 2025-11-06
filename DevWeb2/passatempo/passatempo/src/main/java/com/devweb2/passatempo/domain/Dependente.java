package com.devweb2.passatempo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidade Dependente.
 * Esta classe herda de Cliente (assim como Socio).
 * Usa a estratégia @PrimaryKeyJoinColumn para se ligar à tabela pai.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true) // Importante para o Lombok lidar corretamente com a herança
@Entity
@Table(name = "dependente")
@PrimaryKeyJoinColumn(name = "cliente_id") // Define qual coluna faz a ligação (PK e FK) com a tabela 'cliente'
public class Dependente extends Cliente {

    // Relacionamento: Muitos Dependentes pertencem a UM Socio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socio_id", nullable = false) // Um dependente DEVE estar ligado a um socio
    private Socio socio;
}